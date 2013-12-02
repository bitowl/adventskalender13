package de.bitowl.adventskalender13;

public class Snowflake {
	
	float posX, posY;
	
	float speedY = -50;
	
	float size;
	
	public Snowflake(float pPosX, float pPosY){
		this(pPosX, pPosY, -50, 1);
	}
	
	public Snowflake(float pPosX, float pPosY, float pSpeedY, float pSize) {
		posX = pPosX;
		posY = pPosY;
		speedY = pSpeedY; 
		size = pSize;
	}
	public void act(float pDelta) {
		posY += pDelta * speedY;
	}
}
