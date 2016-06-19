package com.thedistrictheat.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SimpleToggle extends SimpleButton {

	public SimpleToggle(float x, float y, float width, float height, TextureRegion buttonUp, TextureRegion buttonDown) {
		super(x, y, width, height, buttonUp, buttonDown);
	}

    public boolean checkIfPressed(int gameX, int gameY) {
        if (bounds.contains(gameX, gameY)) {
        	if(isPressed) {
        		isPressed = false;
        	} else {
        		isPressed = true;
        	}
            return true;
        }
        return false;
    }

}
