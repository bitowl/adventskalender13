package de.bitowl.adventskalender13;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

public class IngameScreen extends AbstractScreen {

	static final int INITIAL_FLAKES = 40;
	
	int points = 50;
	
	boolean win;
	boolean lose;
	
	TextureAtlas atlas;
	
	AtlasRegion tree;
	AtlasRegion snowflake;
	AtlasRegion cursor;
	
	BitmapFont defaultFont;
	BitmapFont winFont;
	BitmapFont loseFont;
	
	Image cursorActor;
	Image treeActor;
	
	
	Group snowflakes;
	
	Animation megaAnimation;
	float megaAnimationTime;
	Animation stopAnimation;
	
	Player player;
	
	
	public IngameScreen(AdventGame pGame){
		super(pGame);
		
		atlas = new TextureAtlas(Gdx.files.internal("graphics/textures.pack"));
		
		tree = atlas.findRegion("tree");
		cursor = atlas.findRegion("cursor");
		snowflake = atlas.findRegion("snowflake");
		
		megaAnimation = new Animation(0.1f, atlas.findRegions("guy"));
		megaAnimation.setPlayMode(Animation.LOOP_PINGPONG);
		
		stopAnimation = new Animation(1f, new Array<AtlasRegion>(new AtlasRegion[]{atlas.findRegion("guy")}));
		
		player = new Player(stopAnimation, megaAnimation);
		stage.addActor(player);
		
		
		
		treeActor = new Image(tree);
		treeActor.setX(0);treeActor.setY(0);
		treeActor.setOrigin(treeActor.getWidth()/2, treeActor.getHeight()/2);

		
		stage.addActor(treeActor);
				
		defaultFont = new BitmapFont(Gdx.files.internal("fonts/white.fnt"));
		defaultFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		winFont = new BitmapFont(Gdx.files.internal("fonts/win.fnt"));
		winFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		loseFont = new BitmapFont(Gdx.files.internal("fonts/lose.fnt"));
		loseFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

		treeActor.toBack();
		snowflakes = new Group();
		stage.addActor(snowflakes);
		
		// generate some snowflakes
		for (int i = 0; i < INITIAL_FLAKES; i++) {
			generateRandomSnowflake(MathUtils.random(480));
		}
	
		cursorActor = new Image(cursor);
		stage.addActor(cursorActor);
		
		Gdx.input.setInputProcessor(new MyInputProcessor());
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		SpriteBatch batch = stage.getSpriteBatch();
		batch.begin();
		
		
		
		// render our animation
		// megaAnimationTime += delta;
		// batch.draw(megaAnimation.getKeyFrame(megaAnimationTime),0,0);
		
		
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
	
	
	class MyInputProcessor implements InputProcessor {
		Vector3 touchPos = new Vector3();

		@Override
		public boolean keyDown(int keycode) {
	/*		treeActor.removeAction(currentAnimation);
			currentAnimation = new AnimAction(megaAnimation);
			treeActor.addAction(currentAnimation);*/
			if(keycode == Keys.RIGHT){
				player.goRight();
			}else if(keycode == Keys.LEFT){
				player.goLeft();
			}
			
			System.out.println(treeActor.getActions().size);
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			if(keycode == Keys.RIGHT || keycode == Keys.LEFT){
				player.stop();
			}
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
				// TODO win lose as screeeeens
				game.setScreen(new MenuScreen(game));
				// restartGame();
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
	@Override
	public void dispose() {
		super.dispose();
		atlas.dispose();
		/*tree.dispose();
		snowflake.dispose();
		cursor.dispose();*/
		loseFont.dispose();
		winFont.dispose();
		defaultFont.dispose();
	}
	
}
