package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.thedistrictheat.gameobjects.Tile.TileType;

public class Guy {	
	private static final int WIDTH = 19;
	private static final int HEIGHT = 27;
	private static final int GRAVITY = -300;
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private int startingX, startingY;
	private boolean jumping = false;
	private boolean isAlive = true;
	
	private Rectangle boundingRectangle; 
	
	public Guy() {
		this.startingX = 10;
		this.startingY = 40;
		position = new Vector2(startingX, startingY);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, GRAVITY);
		boundingRectangle = new Rectangle();
	}

    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));
        if (velocity.y > GRAVITY) {
            velocity.y = GRAVITY;
        }
        position.add(velocity.cpy().scl(delta));
        
        boundingRectangle.set(position.x+5, position.y+4, WIDTH-9, HEIGHT-2);
    }
    
    public void tileCollision(Tile tile) {
    	if(tile.tileType() == TileType.GRASS) {
    		if (Intersector.overlaps(boundingRectangle, tile.getBoundingRectangle())) {
    			Gdx.app.log("Guy", "Tile Type: "+tile.tileType());
        		Gdx.app.log("Guy", "X: "+tile.getX() + ", Y: "+tile.getY()+ ", Width: "+tile.getWidth()+ ", Height: "+tile.getHeight());
    			Gdx.app.log("Guy", "COLLISION!");
    			velocity.y = 0;
    			position.y = tile.getY() + tile.getHeight();
    			jumping = false;
    		}
    	}
    }

	public void onClick() {
    	if (isAlive && jumping == false) {
    		velocity.y = 160;
    		jumping = true;
    	}
    }

	public void restart() {
		position.x = startingX;
		position.y = startingY;
		velocity.x = 0;
		velocity.y = 0;
		acceleration.x = 0;
		acceleration.y = GRAVITY;
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
        return WIDTH;
    }

    public float getHeight() {
        return HEIGHT;
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
