package com.thedistrictheat.helpers;

import com.badlogic.gdx.InputProcessor;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameworld.GameWorld;

public class InputHandler implements InputProcessor {
	private GameWorld gameWorld;
	private Guy guy;
	
	public InputHandler(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
		this.guy = gameWorld.getGuy();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(gameWorld.isReady()) {
			gameWorld.start();
		}
		else if (gameWorld.isRunning()){
			guy.onClick();
		}
		else if(gameWorld.isGameOver()) {
			gameWorld.restart();
		}
		return true;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
