package de.bitowl.advent.game2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.AtlasTmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class Game2 extends Game {
	public AssetManager assets;
	private boolean assetsLoaded;

	@Override
	public void create() {
		assets = new AssetManager();
		// allows us to load .tmx files with the AssetManager
		assets.setLoader(TiledMap.class, new AtlasTmxMapLoader(new InternalFileHandleResolver()));
				
				
		assets.load("ui/ui.atlas", TextureAtlas.class);		
		assets.load("maps/map1.tmx", TiledMap.class);
		assets.load("graphics/graphics.pack", TextureAtlas.class);
	}

	@Override
	public void render() {
		if (assetsLoaded) {
			super.render();
		} else {
			// TODO draw loading bar
			if (assets.update()) {
				assetsLoaded = true;
				setScreen(new IngameScreen(this));
			}
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		assets.dispose();
	}
}
