package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

public class Tile extends Scrollable {
	public static final int TILE_WIDTH = 10;
	public static final int TILE_HEIGHT = 10;
	public static final int TILE_SPEED = 0;
	
	private Rectangle boundingRectangle;
	private TileType type;
	private Color color;
	private int idX, idY;

	public static enum TileType {
		GRASS, DIRT
	}
	
	public Tile(int x, int y, TileType type, Color color) {
		super(x * TILE_WIDTH, y * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, TILE_SPEED);
		idX = x;
		idY = y;
		Gdx.app.log("Tile", "TILE ID: " + idX+ ", " + idY);
		boundingRectangle = new Rectangle(x, y, width, height);
		this.type = type;
		this.color = color;
	}
	
	public int getIdX() {
		return idX;
	}
	
	public int getIdY() {
		return idY;
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
