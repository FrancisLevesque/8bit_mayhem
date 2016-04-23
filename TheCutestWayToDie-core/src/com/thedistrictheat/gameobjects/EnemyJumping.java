package com.thedistrictheat.gameobjects;

import com.thedistrictheat.gameobjects.Enemy.EnemyType;

public class EnemyJumping extends Enemy{
    private static final int ENEMY_JUMPING_SPEED = -30;

	public EnemyJumping(float x, float y, int width, int height) {
		super(x, y, width, height, ENEMY_JUMPING_SPEED, EnemyType.JUMPING);
	}

}
