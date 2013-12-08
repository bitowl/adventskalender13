package de.bitowl.adventskalender13;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
* if you perform this action on an actor, his drawable will be changed
* sproingie (irc)
*
* @author bitowl
*
*/
public class AnimAction extends Action{
        Animation animation;
        private float stateTime;
        
        public AnimAction(Animation pAnim){
                animation=pAnim;
        }
        @Override
        public boolean act(float delta) {
                stateTime += Gdx.graphics.getDeltaTime();
                if(actor instanceof Image){
                        Image img=(Image)actor;
                        TextureRegionDrawable draw=(TextureRegionDrawable)img.getDrawable();
                        draw.setRegion(animation.getKeyFrame(stateTime));
                        // img.pack();
                }
                return false;
        }

}