package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.math.Intersector;

public class EnemyJumping extends Enemy{
    private static final int ENEMY_JUMPING_SPEED = -22;

	public EnemyJumping(int x, int y, int width, int height) {
		super(x, y, width, height, ENEMY_JUMPING_SPEED, EnemyType.JUMPING);
	}

	@Override
    public void tileCollision(Tile tile) {
    	if(velocity.y < 0) {
    		if (Intersector.overlaps(hitBox, tile.getBoundingRectangle())) {
    			velocity.y = 60;
    			position.y = tile.getY() + tile.getHeight();
    		}
    	}
    }
	
}
