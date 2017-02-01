package com.thedistrictheat.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {
	protected float gameWidthRatio, gameHeightRatio;
	protected int screenWidth, screenHeight;
	protected int gameX, gameY;
	
    public InputHandler(float gameWidthRatio, float gameHeightRatio) {
		this.gameWidthRatio = gameWidthRatio;
		this.gameHeightRatio = gameHeightRatio;
		this.screenWidth = Gdx.graphics.getWidth();
		this.screenHeight = Gdx.graphics.getHeight();
	}

    protected int scaleX(int screenX) {
        return (int) (screenX * gameWidthRatio);
    }

    protected int scaleY(int invertedScreenY) {
		int screenY = screenHeight - invertedScreenY;
        return (int) (screenY * gameWidthRatio);
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
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
