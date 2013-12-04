package de.bitowl.adventskalender13;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

public class AdventGame implements ApplicationListener {

	static final int INITIAL_FLAKES = 40;
	
	Stage stage;
	// SpriteBatch batch;

	Texture tree;
	TextureRegion christmasTree;
	Texture ball;
	TextureRegion christmasBall;

	Texture snowflake;
	Texture cursor;

	//OrthographicCamera camera;
	
	BitmapFont defaultFont;
	BitmapFont winFont;
	BitmapFont loseFont;

	float ballX; // Position des Balles
	float ballY;
	
	float cursorX;
	float cursorY;

	Array<Snowflake> flakes;

	int points = 50;
	
	boolean win;
	boolean lose;
	
	Image cursorActor;
	Image treeActor;
	
	@Override
	public void create() {
		stage = new Stage(800,480);
		//batch = new SpriteBatch();

//		camera = new OrthographicCamera();
	//	camera.setToOrtho(false, 800, 480);

		tree = new Texture(Gdx.files.internal("graphics/tree.png"));
		tree.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		christmasTree = new TextureRegion(tree, 0, 0, 450, 730);

		ball = new Texture(Gdx.files.internal("graphics/ball.png"));
		christmasBall = new TextureRegion(ball, 0, 0, 90, 80);

		snowflake = new Texture(Gdx.files.internal("graphics/snowflake.png"));

		cursor = new Texture(Gdx.files.internal("graphics/cursor.png"));
		
		defaultFont = new BitmapFont(Gdx.files.internal("fonts/white.fnt"));
		defaultFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		winFont = new BitmapFont(Gdx.files.internal("fonts/win.fnt"));
		winFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		loseFont = new BitmapFont(Gdx.files.internal("fonts/lose.fnt"));
		loseFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		
		Gdx.input.setInputProcessor(new MyInputProcessor());

		flakes = new Array<Snowflake>();
		
		
		// TODO check why z-index does not do as i think
		treeActor = new Image(christmasTree);
		treeActor.setX(0);treeActor.setY(0);
		treeActor.setOrigin(treeActor.getWidth()/2, treeActor.getHeight()/2);
		//treeActor.setZIndex(0);
		treeActor.toBack();
		//treeActor.setRotation(10);
//		treeActor.setScale(0.7f);
		
		treeActor.setTouchable(Touchable.disabled);
		//treeActor.addAction(Actions.repeat(-1,Actions.sequence(Actions.rotateBy(180f,2f),Actions.delay(1f))));
		stage.addActor(treeActor);
		
		// generate some snowflakes
		for (int i = 0; i < INITIAL_FLAKES; i++) {
			generateRandomSnowflake(MathUtils.random(480));
		}
		
		cursorActor = new Image(cursor);
		cursorActor.setTouchable(Touchable.disabled);
		stage.addActor(cursorActor);

	}

	@Override
	public void dispose() {
		tree.dispose();
		ball.dispose();
		snowflake.dispose();
		cursor.dispose();
		defaultFont.dispose();
		winFont.dispose();
		loseFont.dispose();
		stage.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0.7f, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
		
		
	//	batch.setProjectionMatrix(camera.combined); // mal wo kamara hingugt!
	//	batch.begin();
	//	batch.draw(christmasTree, 0, 0);
		/*
		 * if(Gdx.input.isTouched()){
		 * 
		 * Vector3 touchPos = new Vector3(Gdx		// generate some snowflakes
		for (int i = 0; i < 200; i++) {
			generateRandomSnowflake(MathUtils.random(480));
		}.input.getX(), Gdx.input.getY(),
		 * 0); camera.unproject(touchPos);
		 * Gdx.app.log("mouse pressed",touchPos.x + ", "+touchPos.y);
		 * 
		 * //ballY += Gdx.graphics.getDeltaTime()*300; }
		 */
	//	batch.draw(christmasBall, ballX, ballY);
//
//		for (int i = 0; i < flakes.size; i++) { // go through all the snowflakes
//			Snowflake current = flakes.get(i);
//
//			current.act(Gdx.graphics.getDeltaTime());
//
//			if (current.posY < -snowflake.getHeight() * current.size) { // snowflake
//																		// gets
//																		// deleted
//				flakes.removeIndex(i);
//				i--;
//				
//				points--;
//				
//				generateRandomSnowflake(480); // spawn a new snowflake at the top of the screen
//				continue;
//			}
//
//			// mysterious something that draws snowflakes
//			batch.draw(snowflake, current.posX, current.posY,
//					// origin
//					snowflake.getWidth() * current.size / 2,
//					snowflake.getHeight() * current.size / 2,
//					
//					snowflake.getWidth(), snowflake.getHeight(),
//					current.size, current.size,
//					current.rotation, 0, 0, snowflake.getWidth(),
//					snowflake.getHeight(), false, false);
//		}
//
//		if (!win && !lose) {
//			defaultFont.draw(batch, "points: " + points, 10, 470);
//		}
//		
//		if (win) {
//			 winFont.drawWrapped(batch, "you won with " + points +" points!", 0, 280, 800, HAlignment.CENTER);
//		} else if (lose) {
//			loseFont.drawWrapped(batch, "you lose", 0, 240, 800, HAlignment.CENTER);
//		}
//		
//		
//		// Cursor malen
//		batch.draw(cursor, cursorX,cursorY);
//		
//		batch.end();
		
		//System.out.println("Punkte: " + points);
		
		
		if (points < 0) {
			// you lose
			//System.err.println("Du hast verloren.");
			lose = true;
			// Gdx.app.exit();
		}
		
		if (stage.getActors().size == 2) { // cursor + tree are still there
			// you win
			//System.out.println("Du hast mit " + points + " Punkten gewonnen.");
			win = true;
			//Gdx.app.exit();
		}
	}
	
	
	public void generateRandomSnowflake(float pY) {
		stage.addActor(new Snowflake(snowflake,MathUtils.random(800), pY, MathUtils.random(-100, -50), MathUtils
				.random(0.3f, 1.5f), MathUtils.random(0, 360), MathUtils.random(30,100),MathUtils.random(0,100), this));
		// flakes.add();	
	}

	// after win or lost restart the game with a click
	public void restartGame() {
	//	flakes.clear();
		stage.clear();
		stage.addActor(treeActor);
		stage.addActor(cursorActor);
		
		// generate some snowflakes
		for (int i = 0; i < INITIAL_FLAKES; i++) {
			generateRandomSnowflake(MathUtils.random(480));
		}
		win = false;
		lose = false;
		points = 50;
	}
	
	
	@Override
	public void resize(int width, int height) {
		// camera.setToOrtho(false,width,height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	class MyInputProcessor implements InputProcessor {
		Vector3 touchPos = new Vector3();

		@Override
		public boolean keyDown(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
			
			if (win || lose) {
				restartGame();
				return false;
			}
			
			// shoot the snowflakes
			
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			stage.getCamera().unproject(touchPos);
			
			Actor hit = stage.hit(touchPos.x, touchPos.y, true);
			if(hit != null){
				hit.remove();
				points += 3;
			}
			
		/*	for (int i = 0;i < flakes.size; i++) {
				Snowflake current=flakes.get(i);
					if(hit){
					
					// we shot this snowflake
					flakes.removeIndex(i);
					points += 3;
					
					break;
				}
			}*/
			
			
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {

			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			stage.getCamera().unproject(touchPos);
		//	ballX = touchPos.x - christmasBall.getRegionWidth() / 2;
		//	ballY = touchPos.y - christmasBall.getRegionHeight() / 2;
			
			cursorActor.setPosition(touchPos.x - 16, touchPos.y -16);
		//	cursorX = touchPos.x - 16;
			//cursorY = touchPos.y - 16;

			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			
			// move cursor
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			stage.getCamera().unproject(touchPos);
			
			cursorActor.setPosition(touchPos.x - 16, touchPos.y -16);
			//cursorX = touchPos.x - 16;
			//cursorY = touchPos.y - 16;
			
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}

	}

	public void removePointAndCreateSnowflake() {
		points--;
		generateRandomSnowflake(480); // spawn a new snowflake at the top of the screen		
	}
}
