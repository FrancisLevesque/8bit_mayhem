package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Billboard extends Scrollable {
	public static final int TILE_WIDTH = 10;
	public static final int TILE_HEIGHT = 10;
	public static final int TILE_SPEED = -30;
	
	private TextureRegion board;
	
	public Billboard(int x, int y, TextureRegion texture) {
		super(x * TILE_WIDTH, y * TILE_HEIGHT, TILE_WIDTH*6, TILE_HEIGHT*3, TILE_SPEED);
		board = texture;
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
	}
	
	public TextureRegion getBoard() {
		return board;
	}
}
