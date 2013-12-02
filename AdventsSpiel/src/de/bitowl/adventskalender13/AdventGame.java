package de.bitowl.adventskalender13;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class AdventGame implements ApplicationListener {

	SpriteBatch batch;

	Texture tree;
	TextureRegion christmasTree;
	Texture ball;
	TextureRegion christmasBall;

	Texture snowflake;

	OrthographicCamera camera;

	float ballX; // Position des Balles
	float ballY;

	Array<Snowflake> flakes;

	@Override
	public void create() {
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		tree = new Texture(Gdx.files.internal("graphics/tree.png"));
		christmasTree = new TextureRegion(tree, 0, 0, 450, 730);

		ball = new Texture(Gdx.files.internal("graphics/ball.png"));
		christmasBall = new TextureRegion(ball, 0, 0, 90, 80);

		snowflake = new Texture(Gdx.files.internal("graphics/snowflake.png"));

		Gdx.input.setInputProcessor(new MyInputProcessor());

		flakes = new Array<Snowflake>();

		// generate some snowflakes
		for (int i = 0; i < 20; i++) {
			flakes.add(new Snowflake(MathUtils.random(800), MathUtils
					.random(480), MathUtils.random(-100, -50), MathUtils
					.random(0.3f, 1.5f)));
		}
	}

	@Override
	public void dispose() {
		tree.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0.7f, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined); // mal wo kamara hingugt!
		batch.begin();
		batch.draw(christmasTree, 0, 0);
		/*
		 * if(Gdx.input.isTouched()){
		 * 
		 * Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),
		 * 0); camera.unproject(touchPos);
		 * Gdx.app.log("mouse pressed",touchPos.x + ", "+touchPos.y);
		 * 
		 * //ballY += Gdx.graphics.getDeltaTime()*300; }
		 */
		batch.draw(christmasBall, ballX, ballY);

		for (int i = 0; i < flakes.size; i++) { // go through all the snowflakes
			Snowflake current = flakes.get(i);
			
			current.act(Gdx.graphics.getDeltaTime());
			
			if (current.posY < -snowflake.getHeight() * current.size) { // snowflake gets deleted
				flakes.removeIndex(i);
				continue;
			}
			

			batch.draw(snowflake, current.posX, current.posY,
					snowflake.getWidth() * current.size,
					snowflake.getHeight() * current.size);
		}

		batch.end();
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
			camera.unproject(touchPos);
			ballX = touchPos.x - christmasBall.getRegionWidth() / 2;
			ballY = touchPos.y - christmasBall.getRegionHeight() / 2;

			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}

	}
}
