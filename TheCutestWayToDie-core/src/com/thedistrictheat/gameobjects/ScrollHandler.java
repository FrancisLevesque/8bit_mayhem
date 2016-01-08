package com.thedistrictheat.gameobjects;

import com.badlogic.gdx.utils.Array;
import com.thedistrictheat.helpers.AssetLoader;

public class ScrollHandler {
    private static final int MOUNTAIN_SPEED = -3;
    private static final int GROUND_SPEED = -50;
    
    private Mountains frontMountains, backMountains;
    private Grass frontGrass, backGrass;
    private Rock rock1;
    private int rockStart;

    public ScrollHandler(int floorHeight, int gameWidth, int gameHeight) {  
    	frontMountains = new Mountains(0, floorHeight, gameWidth, gameHeight/2, MOUNTAIN_SPEED);
    	backMountains = new Mountains(frontMountains.getTailX(), floorHeight, gameWidth, gameHeight/2, MOUNTAIN_SPEED);
    	
    	int grassStart = (int)(floorHeight * 0.6);
    	int grassHeight = (int)(floorHeight * 0.4);
    	frontGrass = new Grass(0, grassStart, gameWidth, grassHeight, GROUND_SPEED);
    	backGrass = new Grass(frontGrass.getTailX(), grassStart, gameWidth, grassHeight, GROUND_SPEED);
    	
    	rockStart = gameWidth;
		int standingHeight = (int)(floorHeight * 0.7);
    	rock1 = new Rock(rockStart, standingHeight, AssetLoader.ROCKWIDTH, AssetLoader.ROCKHEIGHT, GROUND_SPEED); 	
    }

    public void update(float delta) {
    	frontMountains.update(delta);
    	backMountains.update(delta);
        if (frontMountains.isScrolledLeft()) {
        	frontMountains.reset(backMountains.getTailX());
        } 
        else if (backMountains.isScrolledLeft()) {
        	backMountains.reset(frontMountains.getTailX());
        }
        
        frontGrass.update(delta);
        backGrass.update(delta);
        if (frontGrass.isScrolledLeft()) {
            frontGrass.reset(backGrass.getTailX());
        } 
        else if (backGrass.isScrolledLeft()) {
            backGrass.reset(frontGrass.getTailX());
        }
        
        rock1.update(delta);
        if (rock1.isScrolledLeft()) {
        	rock1.reset(rockStart);
        } 
    }

	public boolean collides(Guy guy) {
		return rock1.collides(guy);
	}

	public void stop() {
		frontMountains.stop();
		backMountains.stop();
	    frontGrass.stop();
	    backGrass.stop();
	    rock1.stop();
	}

    public Mountains getFrontMountains() {
		return frontMountains;
	}

	public Mountains getBackMountains() {
		return backMountains;
	}

	public Grass getFrontGrass() {
        return frontGrass;
    }

    public Grass getBackGrass() {
        return backGrass;
    }
    
    public Rock getRock1() {
    	return rock1;
    }
}
