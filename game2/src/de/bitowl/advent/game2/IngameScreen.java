package de.bitowl.advent.game2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

public class IngameScreen extends AbstractScreen {

	TiledMap map;
	OrthogonalTiledMapRenderer renderer;
	TiledMapTileLayer layer0;
	
	TextureAtlas atlas;
	
	Player player;
	
	public IngameScreen(Game2 pGame) {
		super(pGame);
		
		map = game.assets.get("maps/map1.tmx", TiledMap.class);
		atlas = game.assets.get("graphics/graphics.pack", TextureAtlas.class);
		
		renderer = new OrthogonalTiledMapRenderer(map,stage.getSpriteBatch());
		renderer.setView((OrthographicCamera) stage.getCamera());
		
		// get layer from map
		layer0 = ((TiledMapTileLayer)map.getLayers().get(0));
		System.out.println("height: "+layer0.getHeight());
		System.out.println("width: "+layer0.getWidth());
		System.out.println("tilewidth: "+layer0.getTileWidth());
		
		player = new Player(this);
		stage.addActor(player);
		
		
		// add input processor
		Gdx.input.setInputProcessor(new MyInputProcessor());
	}
	@Override
	public void render(float delta) {
		// act all the actor
		stage.act(delta);
		
		
		
		// scroll the map so that the player is in center
		Vector3 position = stage.getCamera().position;
		position.set(player.getX()-player.getWidth()/2,player.getY()-player.getHeight()/2, 0);
		// handle borders of the map
		if(position.x < stage.getWidth()/2){
			position.x = stage.getWidth()/2;
		}
		if(position.y < stage.getHeight()/2){
			position.y = stage.getHeight()/2;
		}
		
		if(position.x > layer0.getWidth()*layer0.getTileWidth() - 840-player.getWidth()/2){
			position.x = layer0.getWidth()*layer0.getTileWidth() - 840-player.getWidth()/2;
		}
		if(position.y > layer0.getHeight()*layer0.getTileHeight() - 422-player.getHeight()/2){
			position.y = layer0.getHeight()*layer0.getTileHeight() - 422-player.getHeight()/2;
		}
		
		stage.getCamera().update();
		
		renderer.render();
		stage.draw();
	}

	@Override
	public void dispose() {
		super.dispose();
		renderer.dispose();
	}
	
	
	class MyInputProcessor implements InputProcessor{

		@Override
		public boolean keyDown(int keycode) {
			
			switch(keycode){
				case Keys.LEFT:
					player.speedX = -1;
					break;
				case Keys.RIGHT:
					player.speedX = 1;
					break;
				case Keys.UP:
					player.speedY = 1;
					break;
				case Keys.DOWN:
					player.speedY = -1;
					break;
			}
			
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			switch(keycode){
			case Keys.LEFT:
			case Keys.RIGHT:
				player.speedX = 0;
				break;
			case Keys.UP:
			case Keys.DOWN:
				player.speedY = 0;
				break;
			}
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
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			// TODO Auto-generated method stub
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
