package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Scrollable {
	private static final int MAX_SPEED = -80;
	private static final int GRAVITY = -300;
	
	private Vector2 acceleration;
	protected Rectangle hitBox;	
	protected EnemyType type;
	protected boolean isExploding;

	public static enum EnemyType {
		WALKING, JUMPING, FLYING
	}

	public Enemy(int x, int y, int width, int height, float scrollSpeed, EnemyType type) {
		super(x, y, width, height, scrollSpeed);
		setAcceleration(new Vector2(0, GRAVITY));
		hitBox = new Rectangle(x, y, width-4, height);
		this.type = type;
		isExploding = false;
	}
	
	public void update(float delta, float frameEdge) {
		super.update(delta);
		if (getX() <= frameEdge) {
			start();
		} else if (getX() > frameEdge) {
			start(Tile.TILE_SPEED/2);
		}
		
        velocity.add(getAcceleration().cpy().scl(delta));
        if (velocity.y < MAX_SPEED) {
            velocity.y = MAX_SPEED;
        }
        position.add(velocity.cpy().scl(delta));
        
        if(position.y + getHeight() < 0) {
        	velocity.y = 0;
        }

		hitBox.setPosition(getX(), getY());
	}
    
    public void tileCollision(Tile tile) {
    	if(velocity.y < 0) {
    		if (Intersector.overlaps(hitBox, tile.getBoundingRectangle())) {
    			velocity.y = 0;
    			position.y = tile.getY() + tile.getHeight();
    		}
    	}
    }
	
	@Override
	public void restart() {
		super.restart();
		setIsExploding(false);
	}

	public Rectangle getHitBox() {
		return hitBox;
	}
	
	public EnemyType getEnemyType() {
		return type;
	}

	public boolean isExploding() {
		return isExploding;
	}

	public void setIsExploding(boolean value) {
		isExploding = value;
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}

}
