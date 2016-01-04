package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Guy {
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private int width;
	private int height;
	
	public Guy(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, -100);
	}

    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y < -100) {
            velocity.y = -100;
        }

        position.add(velocity.cpy().scl(delta));
        if(position.y < 20) {
        	position.y = 20;
        }
    }

    public void onClick() {
        velocity.y = 100;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
