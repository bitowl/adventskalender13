package de.bitowl.adventskalender13;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AdventGame extends Game {
	public static AssetManager assets;
	private boolean finishedLoading;
	private BitmapFont font;
	private SpriteBatch batch;
	private Texture emptyT;
	private Texture fullT;
	private NinePatch empty;
	private NinePatch full;
	
	@Override
	public void create() {
		
		assets = new AssetManager();
		assets.load("audio/background.mp3", Music.class);

		assets.load("audio/win.ogg", Sound.class);
		assets.load("audio/game_over.ogg", Sound.class);
		assets.load("audio/snowflake_hit.ogg", Sound.class);
		assets.load("audio/snowflake_explode.ogg", Sound.class);
		
		assets.load("ui/myui.json", Skin.class);
		
		assets.load("fonts/white.fnt", BitmapFont.class);
		assets.load("fonts/win.fnt", BitmapFont.class);
		assets.load("fonts/lose.fnt", BitmapFont.class);

		assets.load("graphics/textures.pack", TextureAtlas.class);
			
		// load the assets for the loading screen :D
		font   = new BitmapFont();
		batch  = new SpriteBatch();
		emptyT = new Texture(Gdx.files.internal("graphics/empty.png"));
		fullT  = new Texture(Gdx.files.internal("graphics/full.png"));
		empty  = new NinePatch(new TextureRegion(emptyT,24,24),8,8,8,8);
		full   = new NinePatch(new TextureRegion(fullT,24,24),8,8,8,8);

		// [...] render():
		 
	}
	
	@Override
	public void render() {
		if(finishedLoading){
			super.render();
		}else{
			if(assets.update()){
				setScreen(new MenuScreen(this));
				finishedLoading = true;
			}
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			System.out.println("progress: "+assets.getProgress());
			batch.begin();
			empty.draw(batch, 40, 225, 720, 30);
			full.draw(batch, 40, 225, assets.getProgress()*720, 30);
			font.drawMultiLine(batch,(int)(assets.getProgress()*100)+"% loaded",400,247,0, BitmapFont.HAlignment.CENTER);
			batch.end();
		}
	}
}