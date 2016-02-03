package com.thedistrictheat.helpers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameworld.GameWorld;
import com.thedistrictheat.ui.SimpleButton;

public class InputHandler implements InputProcessor {
	private final int BUTTON_WIDTH = 36;
	private final int BUTTON_HEIGHT = 10;
	
	private GameWorld gameWorld;
	private float gameWidthRatio, gameHeightRatio;
	private int screenWidth, screenHeight;
	private int gameX, gameY;
	private Guy guy;
    private List<SimpleButton> menuButtons;
    private SimpleButton playButton;
	
    public InputHandler(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
		this.gameWidthRatio = gameWorld.getGameWidthRatio();
		this.gameHeightRatio = gameWorld.getGameHeightRatio();
		this.screenWidth = Gdx.graphics.getWidth();
		this.screenHeight = Gdx.graphics.getHeight();
		this.guy = gameWorld.getGuy();
		menuButtons = new ArrayList<SimpleButton>();
        playButton = new SimpleButton(((screenWidth/2)*gameWidthRatio) - (BUTTON_WIDTH/2), ((screenHeight/4)*gameHeightRatio) - (BUTTON_HEIGHT/2), BUTTON_WIDTH, BUTTON_HEIGHT, AssetLoader.playButtonUp, AssetLoader.playButtonDown);
        menuButtons.add(playButton);
	}

	@Override
	public boolean touchDown(int screenX, int invertedScreenY, int pointer, int button) {
		if(gameWorld.isSelect()) {
			gameX = scaleX(screenX);
		    gameY = scaleY(invertedScreenY);
			if(gameWorld.characterSelected(gameX, gameY)) {
				playButton.isTouchDown(gameX, gameY);
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
	public boolean touchUp(int screenX, int invertedScreenY, int pointer, int button) {
		if(gameWorld.isSelect()) {
			gameX = scaleX(screenX);
		    gameY = scaleY(invertedScreenY);
			if(gameWorld.characterSelected(gameX, gameY)) {
			    if (playButton.isTouchDown(gameX, gameY)) {
			    	gameWorld.ready();
			    	return true;
			    }
			}
		}
		return false;
	}

    private int scaleX(int screenX) {
        return (int) (screenX * gameWidthRatio);
    }

    private int scaleY(int invertedScreenY) {
		int screenY = screenHeight - invertedScreenY;
        return (int) (screenY * gameWidthRatio);
    }
	
	public SimpleButton getPlayButton() {
		return playButton;
	}
	
	public List<SimpleButton> getMenuButtons() {
        return menuButtons;
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
