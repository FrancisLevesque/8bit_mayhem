package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.thedistrictheat.helpers.AssetLoader;

public class Tile extends Scrollable {
	public static final int TILE_WIDTH = 10;
	public static final int TILE_HEIGHT = 10;
	public static final int TILE_SPEED = 0;
	
	private Rectangle boundingRectangle;
	private TileType type;
	private Color color;

	public static enum TileType {
		GRASS, DIRT
	}
	
	public Tile(int x, int y, TileType type, Color color) {
		super(x * TILE_WIDTH, y * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, TILE_SPEED);
		boundingRectangle = new Rectangle(x, y, width, height);
		this.type = type;
		this.color = color;
	}
	
	public Rectangle getBoundingRectangle() {
		return boundingRectangle;
	}
	
	public TileType tileType() {
		return type;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
