package com.thedistrictheat.helpers;

import com.badlogic.gdx.Input.Keys;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameworld.GameWorld;

public class GameInputHandler extends InputHandler {
	private GameWorld world;
	private Guy guy;

	public GameInputHandler(GameWorld world) {
		super(world.getGameWidthRatio(), world.getGameHeightRatio());
		this.world = world;
		this.guy = world.getGuy();
	}

	@Override
	public boolean touchDown(int screenX, int invertedScreenY, int pointer, int button) {
		if(world.isReady()) {
			world.start();
		}
		else if (world.isRunning()){
			guy.onClick();
		}
		else if(world.isGameOver()) {
			world.restart();
		}
		return true;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK){
			world.setGoToCharacterSelect(true);
		}
		return false;
	}

}
