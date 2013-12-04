package de.bitowl.adventskalender13;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Snowflake extends Image{
	
	// float posX, posY;
	float speedY = -50;
	
	// float size;
	
	// float rotation;
	float rotationSpeed;
	
	private AdventGame game;
	
	public Snowflake(Texture pImage, float pPosX, float pPosY){
		this(pImage, pPosX, pPosY, -50, 1, 0, 40,3);
	}
	
	public Snowflake(Texture pImage, float pPosX, float pPosY, float pSpeedY, float pSize, float pRotation, float pRotationSpeed, int pZIndex) {
		super(pImage);
		setPosition(pPosX,pPosY);
		setOrigin(getWidth()/2, getHeight()/2);
		//posX = pPosX;
		//posY = pPosY;
		speedY = pSpeedY;
		
		//size = pSize;
		setScale(pSize);
		//rotation = pRotation;
		setRotation(pRotation);
		rotationSpeed = pRotationSpeed;
		//setZIndex(pZIndex);
		toFront();
	}
	public Snowflake(Texture pImage, float pPosX, float pPosY, float pSpeedY, float pSize, float pRotation, float pRotationSpeed, int pZIndex,AdventGame pGame) {
		this(pImage,pPosX,pPosY,pSpeedY,pSize,pRotation,pRotationSpeed,pZIndex);
		game = pGame;
	}
	public void setGame(AdventGame pGame){
		game = pGame;
	}
	public void act(float pDelta) {
		//posY += pDelta * speedY;
		//rotation += rotationSpeed * pDelta;
		setY(getY()+ pDelta * speedY);
		setRotation(getRotation() + pDelta * rotationSpeed);
		
		if (getY() < -getHeight() * getScaleY()) {
			// remove snowflake from screen
			remove();
			
			game.removePointAndCreateSnowflake();
		}
	}
}
