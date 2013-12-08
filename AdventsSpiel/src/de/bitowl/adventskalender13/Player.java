package de.bitowl.adventskalender13;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Player extends Image{
	AnimAction currentAnimation;
	AnimAction runningAnim;
	AnimAction stopAnim;
	float speedX;
	static final float SPEED_CONST = 500;
	
	public Player(Animation pStopAnim, Animation pRunningAnim){
		super(pStopAnim.getKeyFrame(0));
		stopAnim = new AnimAction(pStopAnim);
		runningAnim = new AnimAction(pRunningAnim);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		setX(speedX*delta+getX());
	}
	
	public void goRight(){
		speedX = SPEED_CONST;
		removeAction();
		setAction(runningAnim);
	}
	public void goLeft(){
		speedX = -SPEED_CONST;
		removeAction();
		setAction(runningAnim);
	}
	public void stop(){
		speedX = 0;
		removeAction();
		setAction(stopAnim);
	}
	
	/**
	 * removes the current animation-action
	 */
	public void removeAction(){
		removeAction(currentAnimation);
		System.out.println(getActions().size);
	}
	
	/**
	 * sets the action as the current one and adds it to the actionsarray
	 * @param pAction the action to use
	 */
	public void setAction(AnimAction pAction){
		addAction(pAction);
		currentAnimation = pAction;
	}
	
}
