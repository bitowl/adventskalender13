package de.bitowl.adventskalender13;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class AbstractScreen implements Screen {
	
	Stage stage;
	AdventGame game;
	public AbstractScreen(AdventGame pGame){
		game = pGame;
		stage = new Stage(800, 480);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0.7f, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
