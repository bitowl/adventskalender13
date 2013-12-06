package de.bitowl.adventskalender13;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen extends AbstractScreen{
	Skin skin;
	AdventGame game;
	public MenuScreen(AdventGame pGame){
		super(pGame);
		game = pGame;		
		
		// Ui building
		skin = new Skin(Gdx.files.internal("ui/myui.json"));
		
		Table table = new Table(skin);
		//table.debug();
		table.setSize(800, 480);
		
		
	//	LabelStyle titlestyle = new LabelStyle(skin.get(LabelStyle.class));
		// titlestyle.font = loseFont;
		
		Label title = new Label("Schneeflockenmassaker", skin, "title");
		
		//title.setStyle()
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
		
		stage.addActor(table);
					
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		skin.dispose();
		
	}

}
