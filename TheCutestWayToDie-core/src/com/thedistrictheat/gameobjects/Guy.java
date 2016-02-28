package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.thedistrictheat.gameobjects.Tile.TileType;
import com.thedistrictheat.gameworld.CharacterSelectWorld.CharacterType;
import com.thedistrictheat.helpers.AssetLoader;

public class Guy {	
	private static final int WIDTH = 19;
	private static final int HEIGHT = 27;
	private static final int GRAVITY = -10;
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private int startingX, startingY;
	private boolean jumping = false;
	private boolean isAlive = true;
	
	private TextureRegion standingSprite, jumpingSprite,  hitSprite;
	private Animation runningAnimation;
	
	private Rectangle boundingRectangle; 
	
	public Guy() {
		this.startingX = 10;
		this.startingY = 40;
		position = new Vector2(startingX, startingY);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, GRAVITY);
		boundingRectangle = new Rectangle();
	}

    public void setSprites(CharacterType type) {
    	if(type == CharacterType.FRANCIS) {
	    	standingSprite = AssetLoader.francis;
	    	runningAnimation = AssetLoader.francisRunning;
	    	jumpingSprite = AssetLoader.francisJump;
	    	hitSprite = AssetLoader.francisHit;
    	}
    	else if (type == CharacterType.BRANDON) {
	    	standingSprite = AssetLoader.brandon;
	    	runningAnimation = AssetLoader.brandonRunning;
	    	jumpingSprite = AssetLoader.brandonJump;
	    	hitSprite = AssetLoader.brandonHit;
    	}
    	else if (type == CharacterType.STEW) {
	    	standingSprite = AssetLoader.stew;
	    	runningAnimation = AssetLoader.stewRunning;
	    	jumpingSprite = AssetLoader.stewJump;
	    	hitSprite = AssetLoader.stewHit;
    	}
    	else if (type == CharacterType.SEAN) {
	    	standingSprite = AssetLoader.sean;
	    	runningAnimation = AssetLoader.seanRunning;
	    	jumpingSprite = AssetLoader.seanJump;
	    	hitSprite = AssetLoader.seanHit;
    	}
		
	}

	public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));
        if (velocity.y > GRAVITY) {
            velocity.y = GRAVITY;
        }
        position.add(velocity.cpy().scl(delta));
        
        boundingRectangle.set(position.x, position.y, WIDTH, HEIGHT);
    }
    
    public void tileCollision(Tile tile) {
    	if(tile.tileType() == TileType.GRASS) {
    		if (Intersector.overlaps(boundingRectangle, tile.getBoundingRectangle())) {
        		Gdx.app.log("Guy", "X: "+getX() + ", Y: "+getY()+ ", Width: "+getWidth()+ ", Height: "+getHeight());
    			Gdx.app.log("Guy", "TILE ID: " + tile.getIdX() + ", " + tile.getIdY());
        		Gdx.app.log("Guy", "X: "+tile.getX() + ", Y: "+tile.getY()+ ", Width: "+tile.getWidth()+ ", Height: "+tile.getHeight());
    			Gdx.app.log("Guy", "TILE TYPE: " + tile.tileType());
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

	public TextureRegion standingSprite() {
		return standingSprite;
	}

	public Animation runningAnimation() {
		return runningAnimation;
	}

	public TextureRegion jumpingSprite() {
		return jumpingSprite;
	}

	public TextureRegion hitSprite() {
		return hitSprite;
	}
}
