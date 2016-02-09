package com.thedistrictheat.gameobjects;

public class Stars extends Scrollable {
    private static final int STARS_SPEED = -70;
    
	public Stars(float x, float y, int width, int height) {
		super(x, y, width, height, STARS_SPEED);
	}

    public void reset() {
        position.x = width;
        isScrolledLeft = false;
    }

}
