package de.bitowl.advent.game2;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Player extends Image {
	float speedX;
	float speedY;
	float speedFactorX = 80;
	float speedFactorY = 80;

	IngameScreen screen;

	public Player(IngameScreen pScreen) {
		super(pScreen.atlas.findRegion("player"));
		screen = pScreen;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		setPosition(getX() + speedX * speedFactorX * delta, getY() + speedY
				* speedFactorY * delta);
		
		// don't leave the map
		if(getX() < 0){
			setX(0);
		}
		if(getY() < 0){
			setY(0);
		}
		if(getX() > screen.layer0.getWidth()*screen.layer0.getTileWidth() + 4*getWidth()){
			setX(screen.layer0.getWidth()*screen.layer0.getTileWidth() + 4*getWidth());
		}
	}

}
