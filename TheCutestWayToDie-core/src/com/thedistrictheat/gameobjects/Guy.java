package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Guy {	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private int width = 19;
	private int height = 27;
	private int standingHeight;
	private int gravity;
	private int startingX;
	private boolean jumping = false;
	private boolean isAlive = true;
	
	private Rectangle boundingRectangle; 
	
	public Guy(int standingHeight, int gravity, int startingX) {
		this.standingHeight = standingHeight;
		this.gravity = gravity;
		this.startingX = startingX;
		position = new Vector2(startingX, standingHeight);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, gravity);
		boundingRectangle = new Rectangle();
	}

    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y < gravity) {
            velocity.y = gravity;
        }

        position.add(velocity.cpy().scl(delta));
        if(position.y < standingHeight) {
        	position.y = standingHeight;
        	jumping = false;
        }
        
        boundingRectangle.set(position.x, position.y, width, height);
    }

	public void onClick() {
    	if (isAlive && jumping == false) {
    		velocity.y = 160;
    		jumping = true;
    	}
    }

	public void restart() {
		position.x = startingX;
		position.y = standingHeight;
		velocity.x = 0;
		velocity.y = 0;
		acceleration.x = 0;
		acceleration.y = gravity;
		jumping = false;
		isAlive = true;
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
    
    public boolean isJumping() {
    	return jumping;
    }
    
    public boolean isAlive() {
    	return isAlive;
    }
    
    public void setIsAlive(boolean isAlive) {
    	this.isAlive = isAlive;
    }

    public Rectangle getBoundingRectangle() {
		return boundingRectangle;
	}
}
