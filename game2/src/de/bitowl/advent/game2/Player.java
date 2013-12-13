package de.bitowl.advent.game2;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Player extends Image {
	float speedX;
	float speedY;
	float speedFactorX = 200;
	float speedFactorY = 200;

	IngameScreen screen;

	public Player(IngameScreen pScreen) {
		super(pScreen.atlas.findRegion("player"));
		screen = pScreen;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		
		
		
		// which tiles the hero will be standing on after this frame
		int xtile=(int) ( (getX()+ speedX * speedFactorX * delta) /screen.collisionLayer.getTileWidth());
		int ytile=(int) ( (getY()+ speedY * speedFactorY * delta) /screen.collisionLayer.getTileHeight());		

		getMyCorners(getX(),getY()+speedY * speedFactorY * delta);

		if(speedY>0){
			if(upleft&&upright){
				setY(getY()+speedY * speedFactorY * delta);
			}else{
				setY( (ytile+1)*screen.collisionLayer.getTileHeight()-getHeight());
			}
		}else if(speedY<0){
			if(downleft&&downright){
				setY(getY()+speedY * speedFactorY * delta);
			}else{
				setY( (ytile+1)*screen.collisionLayer.getTileHeight());
			}
		}
		
		
		getMyCorners(getX() + speedX*speedFactorX*delta, getY());

		if(speedX<0){
			if(downleft && upleft){
				setX(getX() + speedX*speedFactorX*delta);
			}else{
				setX( (xtile+1)*screen.collisionLayer.getTileWidth());
			}
		}else if(speedX>0){
			if(downright && upright){
				setX(getX() + speedX*speedFactorX*delta);
			}else{
				setX( (xtile+1)*screen.collisionLayer.getTileWidth()-getWidth() );
			}
		}
		
		//setPosition(getX() + speedX * speedFactorX * delta, getY() + speedY
		//		* speedFactorY * delta);
		
		
		
		
		
		// don't leave the map
		if(getX() < 0){
			setX(0);
		}
		if(getY() < 0){
			setY(0);
		}
		if(getX() > screen.collisionLayer.getWidth()*screen.collisionLayer.getTileWidth() - getWidth()){
			setX(screen.collisionLayer.getWidth()*screen.collisionLayer.getTileWidth() - getWidth());
		}
		if(getY() > screen.collisionLayer.getHeight()*screen.collisionLayer.getTileHeight() - getHeight()){
			setY(screen.collisionLayer.getHeight()*screen.collisionLayer.getTileHeight() - getHeight());
		}
	}
	
	boolean upleft,downleft,upright,downright;

	/**
	 * calculate the corners
	 * @param pX
	 * @param pY
	 */
	public void getMyCorners(float pX,float pY){

		// calculate corner coordinates
		int downY=(int) Math.floor(/*screen.collisionLayer.getHeight() - */(pY)/screen.collisionLayer.getTileHeight());
		int upY=(int) Math.floor(/*screen.collisionLayer.getHeight()-*/(pY+getHeight()-1)/screen.collisionLayer.getTileHeight());
		int leftX=(int) Math.floor((pX)/screen.collisionLayer.getTileWidth());
		int rightX=(int) Math.floor((pX+getWidth()-1)/screen.collisionLayer.getTileWidth());

		System.out.println(upY +" "+ downY);
		System.out.println(leftX +" "+ rightX);
		
		// check if the in the corner is a wall
		upleft=isFree(leftX,upY);
		downleft=isFree(leftX,downY);
		upright=isFree(rightX,upY);
		downright=isFree(rightX,downY);
		System.out.println(upleft+" - "+upright);
		System.out.println(downleft+" - "+downright);
	}
	/**
	 * 
	 * @param pX
	 * @param pY
	 * @return true, if the tile is empty
	 */
	public boolean isFree(int pX, int pY){
		return (screen.collisionLayer.getCell(pX, pY) == null);
	}
}
