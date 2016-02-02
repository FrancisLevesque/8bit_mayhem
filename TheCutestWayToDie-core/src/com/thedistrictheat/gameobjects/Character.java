package com.thedistrictheat.gameobjects;

public class Character {
	private float x, y, width, height; 
	private boolean characterSelected = false;
	
	public Character(float x, float y, float width, float height) {
		this.x = x;
		this.y = y; 
		this.width = width;
		this.height = height;
	}
	
	public boolean gotClicked(int screenX, int screenY) {
		if (screenX > x && screenX < x + width && screenY > y && screenY < y + height) {
			return true;
		}
		return false;
	}
	
	public void select() {
		characterSelected = true;
	}
	
	public void reset() {
		characterSelected = false;
	}
	
	public boolean characterSelected() {
		return characterSelected;
	}
	
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
