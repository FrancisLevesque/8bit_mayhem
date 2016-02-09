package com.thedistrictheat.gameobjects;

public class Mountains extends Scrollable {
    private static final int MOUNTAIN_SPEED = -8;
    
	public Mountains(float x, float y, int width, int height) {
		super(x, y, width, height, MOUNTAIN_SPEED);
	}

}
