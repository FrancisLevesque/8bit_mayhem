package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class EnemyFlying extends Enemy{
    private static final int ENEMY_FLYING_SPEED = -90;

	public EnemyFlying(int x, int y, int width, int height) {
		super(x, y, width, height, ENEMY_FLYING_SPEED, EnemyType.FLYING);
		setAcceleration(new Vector2(0, 0));
	}
	
	@Override
	public void tileCollision(Tile tile) {
		//nop
	}

}
