package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {
    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected float scrollSpeed;
    protected float currentSpeed;
    protected boolean isScrolledOffScreen;
    protected int startingX, startingY;

    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        this.width = width;
        this.height = height;
        this.scrollSpeed = scrollSpeed;
        this.currentSpeed = scrollSpeed;
        position = new Vector2(x, y);
        velocity = new Vector2(scrollSpeed, 0);
        isScrolledOffScreen = false;
		startingX = (int)x;
		startingY = (int)y;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
        if (currentSpeed < 0){
            if (position.x + width < 0) {
                isScrolledOffScreen = true;
            }
        } else {
            if (position.x > width ) {
                isScrolledOffScreen = true;
            }
        }
    }

    public void reset() {
//        if (currentSpeed < 0){
	        position.x = startingX;
	        isScrolledOffScreen = false;
//        } else {
//	        position.x = -width;
//	        isScrolledOffScreen = false;
//        }
    }

    public boolean isScrolledOffScreen() {
        return isScrolledOffScreen;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

	public void stop() {
		velocity.x = 0;
	}

	public void start() {
		velocity.x = scrollSpeed;
		currentSpeed = scrollSpeed;
	}

	public void start(float speed) {
		velocity.x = speed;
		currentSpeed = speed;
	}
	
    public void restart() {
        position.x = startingX;
        position.y = startingY;
        isScrolledOffScreen = false;
    }
}
