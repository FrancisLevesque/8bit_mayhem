package com.thedistrictheat.gameobjects;

public class TalkTile extends Tile{
	protected boolean active;
	
	public TalkTile(int x, int y) {
		super(x, y, TileType.TALK_TILE);
		active = true;
	}
	
	public void disable() {
		active = false;
	}
	
	public boolean isEnabled() {
		return active;
	}

}