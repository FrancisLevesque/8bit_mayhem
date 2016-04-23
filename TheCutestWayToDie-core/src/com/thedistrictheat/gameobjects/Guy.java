package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.thedistrictheat.gameobjects.Enemy.EnemyType;
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

	private Rectangle runningBottomHitBox; 
	private Rectangle runningFrontHitBox; 
	private Rectangle jumpingBottomHitBox; 
	private Rectangle jumpingFrontHitBox; 
	
	private void updateBoundingBoxes() {
        runningBottomHitBox.setPosition(position.x + 3, position.y);
        runningFrontHitBox.setPosition(position.x + WIDTH - 6, position.y);
        jumpingBottomHitBox.setPosition(position.x + 6, position.y);
        jumpingFrontHitBox.setPosition(position.x + WIDTH - 3, position.y + 4);
	}
	
	public Guy(int startingX, int startingY) {
		this.startingX = startingX;
		this.startingY = startingY;
		position = new Vector2(startingX, startingY);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, GRAVITY);
		runningBottomHitBox = new Rectangle(position.x + 3, position.y, WIDTH - 7, 2);
		runningFrontHitBox = new Rectangle(position.x + WIDTH - 6, position.y, 2, HEIGHT - 7);
		jumpingBottomHitBox = new Rectangle(position.x + 6, position.y, 4, 2);
		jumpingFrontHitBox = new Rectangle(position.x + WIDTH - 3, position.y + 4, 2, HEIGHT - 8);
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
        
        updateBoundingBoxes();
    }
    
    public void tileCollision(Tile tile) {
		TileType type = tile.tileType();
    	if(jumping && velocity.y < 0) {
        	if(type == TileType.TILE_TOP || type == TileType.TILE_TOP_RIGHT || type == TileType.TILE_TOP_LEFT) {
        		if (Intersector.overlaps(jumpingBottomHitBox, tile.getBoundingRectangle())) {
        			velocity.y = 0;
        			position.y = tile.getY() + tile.getHeight();
        			jumping = false;
        		}
        	}
    	}
    	else if (!jumping) {
        	if(type == TileType.TILE_TOP || type == TileType.TILE_TOP_RIGHT || type == TileType.TILE_TOP_LEFT) {
        		if (Intersector.overlaps(runningBottomHitBox, tile.getBoundingRectangle())) {
        			velocity.y = 0;
        			position.y = tile.getY() + tile.getHeight();
        		}
        	}
    	}
    }
    
    public void enemyCollision(Enemy enemy) {
		EnemyType type = enemy.getEnemyType();
    	if(type == EnemyType.WALKING ) {
    		if (!jumping) {
    			if (Intersector.overlaps(runningFrontHitBox, enemy.getHitBox())) {
    				setIsAlive(false);
    				enemy.setIsExploding(true);
    			}
    		}
    		if (jumping) {
    			if (Intersector.overlaps(jumpingFrontHitBox, enemy.getHitBox())) {
    				setIsAlive(false);
    				enemy.setIsExploding(true);
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
        updateBoundingBoxes();
	}

	public void restart(int x, int y) {
		startingX = x;
		position.x = x;
		startingY = y;
		position.y = y;
		velocity.x = 0;
		velocity.y = 0;
		jumping = false;
		isAlive = true;
        updateBoundingBoxes();
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
		return runningBottomHitBox;
	}

    public Rectangle getRunningFrontBox() {
		return runningFrontHitBox;
	}

    public Rectangle getJumpingFootBox() {
		return jumpingBottomHitBox;
	}

    public Rectangle getJumpingFrontBox() {
		return jumpingFrontHitBox;
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
