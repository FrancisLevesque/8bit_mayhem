package com.thedistrictheat.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class SimpleButton {
    private float x, y, width, height;
    private TextureRegion buttonUp;
    private TextureRegion buttonDown;
    protected Rectangle bounds;
    protected boolean isPressed = false;
    protected boolean isEnabled = false;

    public SimpleButton(float x, float y, float width, float height, TextureRegion buttonUp, TextureRegion buttonDown) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;
        bounds = new Rectangle(x, y, width, height);
    }

    public void draw(SpriteBatch batcher) {
        if (isPressed) {
            batcher.draw(buttonDown, x, y, width, height);
        } else {
            batcher.draw(buttonUp, x, y, width, height);
        }
    }

    public boolean checkIfPressed(int gameX, int gameY) {
        if (bounds.contains(gameX, gameY)) {
            isPressed = true;
            if (isEnabled) {
				Gdx.app.log("SimpleButton", "isEnabled = false");
            	isEnabled = false;
            } else {
				Gdx.app.log("SimpleButton", "isEnabled = true");
            	isEnabled = true;
            }
            return true;
        }
        return false;
    }

    public boolean checkIfReleased(int gameX, int gameY) {
        if (bounds.contains(gameX, gameY) && isPressed) {
            isPressed = false;
            return true;
        }
        isPressed = false;
        return false;
    }
    
    public boolean isEnabled() { 
    	return isEnabled;
    }
    
    public void reset() {
		Gdx.app.log("SimpleButton", "isEnabled = false");
    	isEnabled = false;
    }
}