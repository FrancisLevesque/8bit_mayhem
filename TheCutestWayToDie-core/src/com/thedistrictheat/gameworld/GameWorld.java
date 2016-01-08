package com.thedistrictheat.gameworld;

import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameobjects.ScrollHandler;

public class GameWorld {
	public static final int GRAVITY = -300;

	private int floorHeight;
	private int standingHeight;
	private Guy guy;
	private ScrollHandler scrollHandler;
	
	public GameWorld(int floorHeight, int gameWidth, int gameHeight) {
		this.floorHeight = floorHeight;
		standingHeight = (int)(floorHeight * 0.7);
		guy = new Guy(standingHeight, GRAVITY, (int)(gameWidth * 0.1));
		scrollHandler = new ScrollHandler(floorHeight, gameWidth, gameHeight);
	}
	
	public void update(float delta) {
		guy.update(delta);
		scrollHandler.update(delta);
		
		if (scrollHandler.collides(guy)) {
			scrollHandler.stop();
			guy.setIsAlive(false);
		}
	}
	
	public int getFloorHeight() {
		return floorHeight;
	}
	
	public Guy getGuy() {
		return guy;
	}
	
	public ScrollHandler getScrollHandler() {
		return scrollHandler;
	}
}
