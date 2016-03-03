package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

public class Rock extends Scrollable {
	private float radius;
	private Circle boundingCircle;

	public Rock(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		radius = 9;
		boundingCircle = new Circle();
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		boundingCircle.set(position.x + (width/2), position.y + (height/4), radius);
	}

    public void reset() {
        position.x = startingX;
        isScrolledLeft = false;
    }
	
	public Circle getBoundingCircle() {
		return boundingCircle;
	}

	public boolean collides(Guy guy) {
		if (guy.getX() + guy.getWidth() > position.x) {
			return (Intersector.overlaps(boundingCircle, guy.getRunningFrontBox()));
		}
		return false;
	}
}
