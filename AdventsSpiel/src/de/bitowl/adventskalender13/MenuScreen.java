package de.bitowl.adventskalender13;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen implements Screen{

	Stage stage;
	Skin skin;
	AdventGame game;
	public MenuScreen(AdventGame pGame){
		stage = new Stage(800,480);
		game = pGame;
		//snowflakes = new Group();
		//stage.addActor(snowflakes);
		
//		treeActor = new Image(christmasTree);
//		treeActor.setX(0);treeActor.setY(0);
//		treeActor.setOrigin(treeActor.getWidth()/2, treeActor.getHeight()/2);
//		
//		stage.addActor(treeActor);
//		treeActor.toBack();
//		// generate some snowflakes
//		for (int i = 0; i < INITIAL_FLAKES; i++) {
//			generateRandomSnowflake(MathUtils.random(480));
//		}
//		
//		cursorActor = new Image(cursor);
//		stage.addActor(cursorActor);
		
		// Ui building
		skin = new Skin(Gdx.files.internal("ui/defaultskin.json"));
		
		Table table = new Table(skin);
		//table.debug();
		table.setSize(800, 480);
		
		
		LabelStyle titlestyle = new LabelStyle(skin.get(LabelStyle.class));
		// titlestyle.font = loseFont;
		
		Label title = new Label("you lose", titlestyle);
		
		//title.setStyle()
		table.add(title).padBottom(30).row();
		
		TextButton newGame = new TextButton("new game", skin);
		newGame.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				game.setScreen(new IngameScreen());
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
		
		// cursorActor.toFront();
		
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		// multiplexer.addProcessor(new MyInputProcessor());
		
		Gdx.input.setInputProcessor(multiplexer);
	}
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0.7f, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		
	}

}
