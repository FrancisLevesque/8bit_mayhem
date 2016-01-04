package com.thedistrictheat.gameworld;

import com.thedistrictheat.gameobjects.Guy;

public class GameWorld {
	private Guy guy;
	
	public GameWorld() {
		guy = new Guy (10, 20, 19, 27);
	}
	
	public void update(float delta) {
		guy.update(delta);
	}
	
	public Guy getGuy() {
		return guy;
	}
}
