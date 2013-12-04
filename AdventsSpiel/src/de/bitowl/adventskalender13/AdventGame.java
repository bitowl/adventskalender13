package de.bitowl.adventskalender13;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class AdventGame implements ApplicationListener {

	static final int INITIAL_FLAKES = 40;
	
	Stage stage;

	Texture tree;
	TextureRegion christmasTree;

	Texture snowflake;
	Texture cursor;
	
	BitmapFont defaultFont;
	BitmapFont winFont;
	BitmapFont loseFont;

	int points = 50;
	
	boolean win;
	boolean lose;
	
	Image cursorActor;
	Image treeActor;

	Group snowflakes;
	
	@Override
	public void create() {
		stage = new Stage(800,480);

		tree = new Texture(Gdx.files.internal("graphics/tree.png"));
		tree.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		christmasTree = new TextureRegion(tree, 0, 0, 450, 730);

		snowflake = new Texture(Gdx.files.internal("graphics/snowflake.png"));

		cursor = new Texture(Gdx.files.internal("graphics/cursor.png"));
		
		defaultFont = new BitmapFont(Gdx.files.internal("fonts/white.fnt"));
		defaultFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		winFont = new BitmapFont(Gdx.files.internal("fonts/win.fnt"));
		winFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		loseFont = new BitmapFont(Gdx.files.internal("fonts/lose.fnt"));
		loseFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		
		Gdx.input.setInputProcessor(new MyInputProcessor());

		snowflakes = new Group();
		stage.addActor(snowflakes);
		
		treeActor = new Image(christmasTree);
		treeActor.setX(0);treeActor.setY(0);
		treeActor.setOrigin(treeActor.getWidth()/2, treeActor.getHeight()/2);
		
		stage.addActor(treeActor);
		treeActor.toBack();
		// generate some snowflakes
		for (int i = 0; i < INITIAL_FLAKES; i++) {
			generateRandomSnowflake(MathUtils.random(480));
		}
		
		cursorActor = new Image(cursor);
		stage.addActor(cursorActor);
		
		cursorActor.toFront();

	}

	@Override
	public void dispose() {
		tree.dispose();
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
		
		SpriteBatch batch = stage.getSpriteBatch();
		batch.begin();
		if (!win && !lose) {
			defaultFont.draw(batch, "points: " + points, 10, 470);
		}
		
		if (win) {
			 winFont.drawWrapped(batch, "you won with " + points +" points!", 0, 280, 800, HAlignment.CENTER);
		} else if (lose) {
			loseFont.drawWrapped(batch, "you lose", 0, 240, 800, HAlignment.CENTER);
		}
		batch.end();

		if (points < 0) {
			// you lose
			lose = true;
			// Gdx.app.exit();
		}
		
		if (snowflakes.getChildren().size == 0) { // no more snowflakes are falling
			// you win
			win = true;
			//Gdx.app.exit();
		}
	}
	
	
	public void generateRandomSnowflake(float pY) {
		snowflakes.addActor(new Snowflake(snowflake,MathUtils.random(800), pY, MathUtils.random(-100, -50), MathUtils
				.random(0.3f, 1.5f), MathUtils.random(0, 360), MathUtils.random(30,100), this));
	}

	// after win or lost restart the game with a click
	public void restartGame() {
		snowflakes.clear();
		
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
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
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
			
			Actor hit = snowflakes.hit(touchPos.x, touchPos.y, true); // search the snowflake we hit
			if(hit != null){
				hit.remove();
				points += 3;
			}
			
			
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {

			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			stage.getCamera().unproject(touchPos);
			
			cursorActor.setPosition(touchPos.x - 16, touchPos.y -16);
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			
			// move cursor
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			stage.getCamera().unproject(touchPos);
			
			cursorActor.setPosition(touchPos.x - 16, touchPos.y -16);
			
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			return false;
		}

	}

	public void removePointAndCreateSnowflake() {
		points--;
		generateRandomSnowflake(480); // spawn a new snowflake at the top of the screen		
	}
}
