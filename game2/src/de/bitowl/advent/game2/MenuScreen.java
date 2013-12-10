package de.bitowl.advent.game2;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MenuScreen extends AbstractScreen {

	Image todoImg;
	
	public MenuScreen(Game2 pGame) {
		super(pGame);
		TextureAtlas atlas = game.assets.get("ui/ui.atlas", TextureAtlas.class);
		todoImg = new Image(atlas.findRegion("menu"));
		stage.addActor(todoImg);
	}

}
