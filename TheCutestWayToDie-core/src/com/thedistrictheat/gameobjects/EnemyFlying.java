package com.thedistrictheat.gameobjects;

public class EnemyFlying extends Enemy{
    private static final int ENEMY_FLYING_SPEED = -90;

	public EnemyFlying(float x, float y, int width, int height) {
		super(x, y, width, height, ENEMY_FLYING_SPEED, EnemyType.FLYING);
	}

}
