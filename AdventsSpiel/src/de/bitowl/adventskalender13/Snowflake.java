package de.bitowl.adventskalender13;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Snowflake extends Image{
	private AdventGame game;
	
	public Snowflake(Texture pImage, float pPosX, float pPosY){
		this(pImage, pPosX, pPosY, -50, 1, 0, 40);
	}
	
	public Snowflake(Texture pImage, float pPosX, float pPosY, float pSpeedY, float pSize, float pRotation, float pRotationSpeed) {
		super(pImage);
		
		setPosition(pPosX,pPosY);
		setOrigin(getWidth()/2, getHeight()/2);
		setScale(pSize);
		setRotation(pRotation);
		
		
		addAction(Actions.repeat(-1, Actions.moveBy(0, pSpeedY, 1f)));
		addAction(Actions.repeat(-1, Actions.rotateBy(pRotationSpeed, 1f)));
	}
	public Snowflake(Texture pImage, float pPosX, float pPosY, float pSpeedY, float pSize, float pRotation, float pRotationSpeed,AdventGame pGame) {
		this(pImage,pPosX,pPosY,pSpeedY,pSize,pRotation,pRotationSpeed);
		game = pGame;
	}
	public void setGame(AdventGame pGame){
		game = pGame;
	}
	public void act(float pDelta) {
		super.act(pDelta);

		// remove snowflake from screen when on bottom
		if (getY() < -getHeight() * getScaleY()) {
			remove();
			game.removePointAndCreateSnowflake();
		}
	}
}
