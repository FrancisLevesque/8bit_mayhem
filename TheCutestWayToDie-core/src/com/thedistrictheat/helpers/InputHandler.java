package com.thedistrictheat.helpers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameworld.GameWorld;
import com.thedistrictheat.ui.SimpleButton;

public class InputHandler implements InputProcessor {
	private GameWorld gameWorld;
	private float gameWidthRatio;
	private float gameHeightRatio;
	private int screenHeight;
	private Guy guy;
    private List<SimpleButton> menuButtons;
    private SimpleButton playButton;
	
    public InputHandler(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
		this.gameWidthRatio = gameWorld.getGameWidthRatio();
		this.gameHeightRatio = gameWorld.getGameHeightRatio();
		this.screenHeight = Gdx.graphics.getHeight();
		this.guy = gameWorld.getGuy();
		menuButtons = new ArrayList<SimpleButton>();
		// TODO: This is drawing both sprites at once
        playButton = new SimpleButton(60, 10, 36, 10, AssetLoader.playButtonUp, AssetLoader.playButtonDown);
        menuButtons.add(playButton);
	}

	@Override
	public boolean touchDown(int screenX, int invertedScreenY, int pointer, int button) {
		if(gameWorld.isSelect()) {
			int screenY = screenHeight - invertedScreenY;
		    int gameX = (int)(screenX * gameWidthRatio);
		    int gameY = (int)(screenY * gameHeightRatio);
			gameWorld.checkIfCharacterSelected(gameX, gameY);
			if(gameWorld.characterSelected()) {
				if (playButton.isTouchDown(gameX, gameY)) {
					gameWorld.restart();
				}
			}
		}
		else if(gameWorld.isReady()) {
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
	
	public SimpleButton getPlayButton() {
		return playButton;
	}
	
	public List<SimpleButton> getMenuButtons() {
        return menuButtons;
    }
}
