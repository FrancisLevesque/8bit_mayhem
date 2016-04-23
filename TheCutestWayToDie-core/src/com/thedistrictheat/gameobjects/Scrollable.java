package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {
    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected float scrollSpeed;
    protected boolean isScrolledLeft;
    protected int startingX, startingY;

    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        this.width = width;
        this.height = height;
        this.scrollSpeed = scrollSpeed;
        position = new Vector2(x, y);
        velocity = new Vector2(scrollSpeed, 0);
        isScrolledLeft = false;
		startingX = (int)x;
		startingY = (int)y;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
        if (position.x + width < 0) {
            isScrolledLeft = true;
        }
    }

    public void reset() {
        position.x = width;
        isScrolledLeft = false;
    }

    public boolean isScrolledLeft() {
        return isScrolledLeft;
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
	}
	
    public void restart() {
        position.x = startingX;
        position.y = startingY;
        isScrolledLeft = false;
    }
}
