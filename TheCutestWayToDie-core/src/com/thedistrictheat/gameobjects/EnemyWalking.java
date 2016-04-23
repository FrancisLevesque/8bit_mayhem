package com.thedistrictheat.gameobjects;

public class EnemyWalking extends Enemy{
    private static final int ENEMY_WALKING_SPEED = -30;

	public EnemyWalking(float x, float y, int width, int height) {
		super(x, y, width, height, ENEMY_WALKING_SPEED, EnemyType.WALKING);
	}

}
