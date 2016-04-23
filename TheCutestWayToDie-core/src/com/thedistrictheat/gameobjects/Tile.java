package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.math.Rectangle;

public class Tile extends Scrollable {
	public static final int TILE_WIDTH = 10;
	public static final int TILE_HEIGHT = 10;
	public static final int TILE_SPEED = -20;
	
	private Rectangle boundingRectangle;
	private TileType type;

	public static enum TileType {
		TILE_TOP, TILE_TOP_RIGHT, TILE_TOP_LEFT, TILE_BOTTOM, TILE_BOTTOM_RIGHT, TILE_BOTTOM_LEFT
	}
	
	public Tile(int x, int y, TileType type) {
		super(x * TILE_WIDTH, y * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, TILE_SPEED);
		boundingRectangle = new Rectangle(x * TILE_WIDTH, y * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
		this.type = type;
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		boundingRectangle.setPosition(getX(), getY());
	}
	
	public Rectangle getBoundingRectangle() {
		return boundingRectangle;
	}
	
	public TileType tileType() {
		return type;
	}
}
