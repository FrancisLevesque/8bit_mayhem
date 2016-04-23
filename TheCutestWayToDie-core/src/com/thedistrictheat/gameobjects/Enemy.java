package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.thedistrictheat.gameobjects.Tile.TileType;

public class Enemy extends Scrollable {
	private static final int GRAVITY = -300;
	private static final int MAX_SPEED = -80;
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private Rectangle hitBox;	
	private EnemyType type;
	boolean isExploding;

	public static enum EnemyType {
		WALKING, JUMPING, FLYING
	}

	public Enemy(float x, float y, int width, int height, float scrollSpeed, EnemyType type) {
		super(x, y, width, height, scrollSpeed);
		hitBox = new Rectangle(x, y, width-4, height);
		this.type = type;
		isExploding = false;
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		hitBox.setPosition(getX(), getY());
	}
//	
//	public void update(float delta) {
//        velocity.add(acceleration.cpy().scl(delta));
//        if (velocity.y < MAX_SPEED) {
//            velocity.y = MAX_SPEED;
//        }
//        position.add(velocity.cpy().scl(delta));
//        
//        if(position.y + getHeight() < 0) {
//        	setIsAlive(false);
//        }
//        
//        updateBoundingBoxes();
//    }
//    
//    public void tileCollision(Tile tile) {
//		TileType type = tile.tileType();
//    	if(jumping && velocity.y < 0) {
//        	if(type == TileType.TILE_TOP || type == TileType.TILE_TOP_RIGHT || type == TileType.TILE_TOP_LEFT) {
//        		if (Intersector.overlaps(jumpingBottomHitBox, tile.getBoundingRectangle())) {
//        			velocity.y = 0;
//        			position.y = tile.getY() + tile.getHeight();
//        			jumping = false;
//        		}
//        	}
//    	}
//    	else if (!jumping) {
//        	if(type == TileType.TILE_TOP || type == TileType.TILE_TOP_RIGHT || type == TileType.TILE_TOP_LEFT) {
//        		if (Intersector.overlaps(runningBottomHitBox, tile.getBoundingRectangle())) {
//        			velocity.y = 0;
//        			position.y = tile.getY() + tile.getHeight();
//        		}
//        	}
//    	}
//    }

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
