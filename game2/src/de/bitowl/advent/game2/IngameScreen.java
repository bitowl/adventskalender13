package de.bitowl.advent.game2;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class IngameScreen extends AbstractScreen {

	TiledMap map;
	OrthogonalTiledMapRenderer renderer;
	public IngameScreen(Game2 pGame) {
		super(pGame);
		map = game.assets.get("tilemap/map1.tmx", TiledMap.class);
		renderer = new OrthogonalTiledMapRenderer(map,stage.getSpriteBatch());
		renderer.setView((OrthographicCamera) stage.getCamera());
	}
	@Override
	public void render(float delta) {
		super.render(delta);
		renderer.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		renderer.dispose();
	}
}
