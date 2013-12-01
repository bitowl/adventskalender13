package de.bitowl.adventskalender13;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AdventGame implements ApplicationListener {

	SpriteBatch batch;
	
	Texture tree;
	TextureRegion christmasTree;
	Texture ball;
	TextureRegion christmasBall;
	
	OrthographicCamera camera;

	@Override
	public void create() {
		batch = new SpriteBatch();
		
		camera=new OrthographicCamera();
		camera.setToOrtho(false, 800,480);
		
		tree = new Texture(Gdx.files.internal("graphics/tree.png"));
		christmasTree=new TextureRegion(tree, 0, 0, 450,730);
		
		ball = new Texture(Gdx.files.internal("graphics/ball.png"));
		christmasBall = new TextureRegion(ball, 0,0,90,80);
		
	}

	@Override
	public void dispose() {
		tree.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined); // mal wo kamara hingugt!
		batch.begin();
		batch.draw(christmasTree,0,0);
		batch.draw(christmasBall,0,0);
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
}
