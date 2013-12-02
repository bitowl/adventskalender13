package de.bitowl.adventskalender13;

public class Snowflake {
	
	float posX, posY;
	float speedY = -50;
	
	float size;
	
	float rotation;
	float rotationSpeed;
	
	public Snowflake(float pPosX, float pPosY){
		this(pPosX, pPosY, -50, 1, 0, 40);
	}
	
	public Snowflake(float pPosX, float pPosY, float pSpeedY, float pSize, float pRotation, float pRotationSpeed) {
		posX = pPosX;
		posY = pPosY;
		speedY = pSpeedY; 
		size = pSize;
		rotation = pRotation;
		rotationSpeed = pRotationSpeed;
	}
	public void act(float pDelta) {
		posY += pDelta * speedY;
		rotation += rotationSpeed * pDelta;
	}
}
