package de.bitowl.adventskalender13;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class IngameScreen implements Screen {

	Stage stage;
	
	Texture tree;
	TextureRegion christmasTree;

	Image treeActor;
	
	public IngameScreen(){
		stage = new Stage();
		
		tree = new Texture(Gdx.files.internal("graphics/tree.png"));
		tree.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		christmasTree = new TextureRegion(tree, 0, 0, 450, 730);
		
		treeActor = new Image(christmasTree);
		treeActor.setX(0);treeActor.setY(0);
		treeActor.setOrigin(treeActor.getWidth()/2, treeActor.getHeight()/2);
		
		stage.addActor(treeActor);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		tree.dispose();
		stage.dispose();

	}

}
