package com.thedistrictheat.gameobjects;

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
	private static final int GRAVITY = -300;
	private static final int MAX_SPEED = -80;
	private static final int JUMP_SPEED = 160;
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private int startingX, startingY;
	private boolean jumping = false;
	private boolean isAlive = true;
	
	private TextureRegion standingSprite, jumpingSprite,  hitSprite;
	private Animation runningAnimation;

	private Rectangle runningFootBox; 
	private Rectangle runningFrontBox; 
	private Rectangle jumpingFootBox; 
	private Rectangle jumpingFrontBox; 
	
	public Guy() {
		this.startingX = 10;
		this.startingY = 20;
		position = new Vector2(startingX, startingY);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, GRAVITY);
		runningFootBox = new Rectangle(position.x + 3, position.y, WIDTH - 7, 2);
		runningFrontBox = new Rectangle(position.x + WIDTH - 6, position.y, 2, HEIGHT - 7);
		jumpingFootBox = new Rectangle(position.x + 6, position.y, 4, 2);
		jumpingFrontBox = new Rectangle(position.x + WIDTH - 3, position.y + 4, 2, HEIGHT - 8);
	}

	public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));
        if (velocity.y < MAX_SPEED) {
            velocity.y = MAX_SPEED;
        }
        position.add(velocity.cpy().scl(delta));
        
        if(position.y + getHeight() < 0) {
        	setIsAlive(false);
        }
        
        runningFootBox.setPosition(position.x + 3, position.y);
        runningFrontBox.setPosition(position.x + WIDTH - 6, position.y);
        jumpingFootBox.setPosition(position.x + 6, position.y);
        jumpingFrontBox.setPosition(position.x + WIDTH - 3, position.y + 4);
    }
    
    public void tileCollision(Tile tile) {
		TileType type = tile.tileType();
    	if(jumping && velocity.y < 0) {
        	if(type == TileType.TOP_TILE || type == TileType.TOP_TILE_RIGHT || type == TileType.TOP_TILE_LEFT) {
        		if (Intersector.overlaps(jumpingFootBox, tile.getBoundingRectangle())) {
        			velocity.y = 0;
        			position.y = tile.getY() + tile.getHeight();
        			jumping = false;
        		}
        	}
    	}
    	else if (!jumping) {
        	if(type == TileType.TOP_TILE || type == TileType.TOP_TILE_RIGHT || type == TileType.TOP_TILE_LEFT) {
        		if (Intersector.overlaps(runningFootBox, tile.getBoundingRectangle())) {
        			velocity.y = 0;
        			position.y = tile.getY() + tile.getHeight();
        		}
        	}
    	}
    }

	public void onClick() {
    	if (isAlive && jumping == false) {
    		velocity.y = JUMP_SPEED;
    		jumping = true;
    	}
    }

	public void restart() {
		position.x = startingX;
		position.y = startingY;
		velocity.x = 0;
		velocity.y = 0;
		jumping = false;
		isAlive = true;
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
    
    public Rectangle getRunningFootBox() {
		return runningFootBox;
	}

    public Rectangle getRunningFrontBox() {
		return runningFrontBox;
	}

    public Rectangle getJumpingFootBox() {
		return jumpingFootBox;
	}

    public Rectangle getJumpingFrontBox() {
		return jumpingFrontBox;
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
