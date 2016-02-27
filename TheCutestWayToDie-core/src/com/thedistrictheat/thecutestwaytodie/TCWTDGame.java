package com.thedistrictheat.thecutestwaytodie;

import com.badlogic.gdx.Game;
import com.thedistrictheat.helpers.AssetLoader;
import com.thedistrictheat.screens.CharacterSelectScreen;

public class TCWTDGame extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new CharacterSelectScreen(this));
	}
	
	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
