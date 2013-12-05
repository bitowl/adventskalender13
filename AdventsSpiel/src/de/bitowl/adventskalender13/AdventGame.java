package de.bitowl.adventskalender13;

import com.badlogic.gdx.Game;

public class AdventGame extends Game {
	@Override
	public void create() {
		setScreen(new MenuScreen(this));
	}
}