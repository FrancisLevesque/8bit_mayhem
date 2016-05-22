package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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
	private boolean gameWon = false;
	private Level currentLevel;
	
	private TextureRegion standingSprite, jumpingSprite,  hitSprite;
	private Animation runningAnimation;

	private Rectangle hitBox;
	
	public static enum Level {
		FOREST, VOLCANO, TUNDRA, CITY
	}
	
	private void updateBoundingBoxes() {
		hitBox.setPosition(position.x + 3, position.y);
	}
	
	public Guy(int startingX, int startingY) {
		this.startingX = startingX;
		this.startingY = startingY;
		position = new Vector2(startingX, startingY);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, GRAVITY);
		hitBox = new Rectangle(0, 0, WIDTH - 6, HEIGHT - 6);
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
    	if(velocity.y < 0) {
			if (Intersector.overlaps(hitBox, tile.getBoundingRectangle())) {
				velocity.y = 0;
				position.y = tile.getY() + tile.getHeight();
				if(jumping) {
					jumping = false;
				}
			}
    	}
    }
    
    public void flagCollision(Tile tile) {
		if (Intersector.overlaps(hitBox, tile.getBoundingRectangle())) {
			setGameWon(true);
		}
    }
    
    public void enemyCollision(Enemy enemy) {
		if (Intersector.overlaps(hitBox, enemy.getHitBox())) {
			setIsAlive(false);
			enemy.setIsExploding(true);
			AssetLoader.explosion.play();
    	}
    }

	public void onClick() {
    	if (isAlive && jumping == false) {
    		velocity.y = JUMP_SPEED;
    		jumping = true;
    		AssetLoader.jump.play();
    	}
    }

	public void restart() {
		position.x = startingX;
		position.y = startingY;
		velocity.x = 0;
		velocity.y = 0;
		jumping = false;
		isAlive = true;
		setGameWon(false);
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
		setIsAlive(true);
        updateBoundingBoxes();
	}

    public void setSprites(CharacterType type) {
    	if(type == CharacterType.FRANCIS) {
	    	standingSprite = AssetLoader.francis;
	    	runningAnimation = AssetLoader.francisRunning;
	    	jumpingSprite = AssetLoader.francisJump;
	    	hitSprite = AssetLoader.francisHit;
	    	currentLevel = Level.FOREST;
    	}
    	else if (type == CharacterType.BRANDON) {
	    	standingSprite = AssetLoader.brandon;
	    	runningAnimation = AssetLoader.brandonRunning;
	    	jumpingSprite = AssetLoader.brandonJump;
	    	hitSprite = AssetLoader.brandonHit;
	    	currentLevel = Level.TUNDRA;
    	}
    	else if (type == CharacterType.STEW) {
	    	standingSprite = AssetLoader.stew;
	    	runningAnimation = AssetLoader.stewRunning;
	    	jumpingSprite = AssetLoader.stewJump;
	    	hitSprite = AssetLoader.stewHit;
	    	currentLevel = Level.VOLCANO;
    	}
    	else if (type == CharacterType.SEAN) {
	    	standingSprite = AssetLoader.sean;
	    	runningAnimation = AssetLoader.seanRunning;
	    	jumpingSprite = AssetLoader.seanJump;
	    	hitSprite = AssetLoader.seanHit;
	    	currentLevel = Level.CITY;
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
    
    public Rectangle getHitBox() {
		return hitBox;
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

	public boolean isGameWon() {
		return gameWon;
	}

	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}
	
	public Color getBackgroundColour() {
		switch(currentLevel) {
		case FOREST:
			return Color.CYAN;
		case TUNDRA:
			return Color.SKY;
		case VOLCANO:
			return Color.ORANGE;
		case CITY:
			return Color.DARK_GRAY;
		default:
			Gdx.app.log("Guy", "Unsupported Level Type: " + currentLevel);
			return Color.CYAN;
		}
	}
}
