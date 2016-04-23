package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Scrollable {
	private static final int GRAVITY = -300;
	private static final int MAX_SPEED = -80;
	
	private Vector2 acceleration;
	
	private Rectangle hitBox;	
	private EnemyType type;
	boolean isExploding;

	public static enum EnemyType {
		WALKING, JUMPING, FLYING
	}

	public Enemy(float x, float y, int width, int height, float scrollSpeed, EnemyType type) {
		super(x, y, width, height, scrollSpeed);
		acceleration = new Vector2(0, GRAVITY);
		hitBox = new Rectangle(x, y, width-4, height);
		this.type = type;
		isExploding = false;
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
        velocity.add(acceleration.cpy().scl(delta));
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

}
