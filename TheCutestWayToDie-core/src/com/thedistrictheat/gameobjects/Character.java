package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.graphics.Color;

public class Character {
	private float x, y, width, height; 
	private float originalX, originalY, originalWidth, originalHeight; 
	private float selectedX, selectedY, selectedWidth, selectedHeight;
	private boolean characterSelected = false;
	private Color color;
	
	public Character(float x, float y, Color color) {
		this.x = originalX = x;
		this.y = originalY = y; 
		this.width = originalWidth = 19;
		this.height = originalHeight = 27;
		this.color = color;
		selectedX = originalX - 2;
		selectedY = originalY - 2;
		selectedWidth = originalWidth + 4;
		selectedHeight = originalHeight + 4;
	}
	
	public boolean gotClicked(int screenX, int screenY) {
		if (screenX > x && screenX < x + width && screenY > y && screenY < y + height) {
			select();
			return true;
		}
		return false;
	}
	
	public void select() {
		characterSelected = true;
		x = selectedX;
		y = selectedY;
		width = selectedWidth;
		height = selectedHeight;
	}
	
	public void reset() {
		characterSelected = false;
		x = originalX;
		y = originalY;
		width = originalWidth;
		height = originalHeight;
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
    
    public Color getColor() {
    	return color;
    }
}
