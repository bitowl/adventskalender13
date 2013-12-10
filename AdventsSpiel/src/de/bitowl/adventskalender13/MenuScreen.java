package de.bitowl.adventskalender13;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen extends AbstractScreen{
	
	Skin skin;
	AdventGame game;
	
	
	public MenuScreen(AdventGame pGame){
		super(pGame);
		game = pGame;		
		
		// Ui building
		skin = AdventGame.assets.get("ui/myui.json", Skin.class);
		
		Table table = new Table(skin);
		//table.debug();
		table.setSize(800, 480);
		
				
		Label title = new Label("Schneeflockenmassaker", skin, "title");
		
		table.add(title).padBottom(30).row();

		TextButton newGame = new TextButton("new game", skin);
		newGame.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				game.setScreen(new IngameScreen(game));
			}
		});
		table.add(newGame).pad(10).row();
		
		TextButton quitGame = new TextButton("quit game", skin);
		quitGame.addListener(new ClickListener(){public void clicked(InputEvent event, float x, float y) {
			Gdx.app.exit();
		}
		});
		table.add(quitGame).pad(10).row();
		
		
		TextField test =new TextField("test",skin);
		// test.setDisabled(true);
		table.add(test);
		
		CheckBox test1 = new CheckBox("keeeekse", skin);
		// test1.setDisabled(true);
		table.add(test1);
		
		SelectBox test2 = new SelectBox(new String[]{"test","kekse","blubb"},skin);
		table.add(test2);
		
		
		List test3 = new List(new String[]{"test","kekse","bla","blubb","adsfa","blubb","adsfa","blubb","adsfa","blubb","adsfa","blubb","adsfa","blubb","adsfa","blubb","adsfa","blubb","adsfa","blubb","adsfa"},skin);
		//table.add(test3);
		ScrollPane test4 =new ScrollPane(test3,skin);
		test4.setFadeScrollBars(false);
		test4.setOverscroll(false, false);
		test4.setScrollbarsOnTop(true);
		//test4.setForceScroll(false, true);
		//test4.setSize(20, 20);
		test4.setScrollingDisabled(true, false);
		test4.setCullingArea(new Rectangle(0,0,100,100));
		//table.add(test4);
		
		stage.addActor(table);
					
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		skin.dispose();
		
	}

}
