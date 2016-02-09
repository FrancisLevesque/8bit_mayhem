package com.thedistrictheat.gameworld;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.thedistrictheat.gameobjects.Grass;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameobjects.Mountains;
import com.thedistrictheat.gameobjects.Rock;
import com.thedistrictheat.gameobjects.ScrollHandler;
import com.thedistrictheat.gameobjects.Scrollable;
import com.thedistrictheat.helpers.AssetLoader;

public class GameWorld {
	public static final int GRAVITY = -300;
	private static final int GROUND_SPEED = -50;
    
	private boolean goToCharacterSelect = false;
	private int floorHeight;
	private float gameWidthRatio;
	private float gameHeightRatio;
	private int standingHeight, grassStart, grassHeight;
	
	private ScrollHandler scrollHandler;
	private List<Scrollable> list;
	private Guy guy;
    private Mountains frontMountains, backMountains;
    private Grass frontGrass, backGrass;
    private Rock rock1;
	
	public enum GameState {
		READY, RUNNING, GAMEOVER
	}
	
	private GameState currentState;
	
	public GameWorld(int floorHeight, int gameWidth, int gameHeight, float gameWidthRatio, float gameHeightRatio) {
		currentState = GameState.READY;
		this.floorHeight = floorHeight;
		this.gameWidthRatio = gameWidthRatio;
		this.gameHeightRatio = gameHeightRatio;
		standingHeight = (int)(floorHeight * 0.7);
    	grassStart = (int)(floorHeight * 0.6);
    	grassHeight = (int)(floorHeight * 0.4);

		guy = new Guy(standingHeight, GRAVITY, (int)(gameWidth * 0.1));
		
    	frontMountains = new Mountains(0, floorHeight, gameWidth, gameHeight/2);
    	backMountains = new Mountains(gameWidth, floorHeight, gameWidth, gameHeight/2);
    	
    	frontGrass = new Grass(0, grassStart, gameWidth, grassHeight, GROUND_SPEED);
    	backGrass = new Grass(gameWidth, grassStart, gameWidth, grassHeight, GROUND_SPEED);
    
    	rock1 = new Rock(gameWidth, standingHeight, AssetLoader.ROCKWIDTH, AssetLoader.ROCKHEIGHT, GROUND_SPEED); 

		list = new ArrayList<Scrollable>();
    	list.add(frontMountains);
    	list.add(backMountains);
    	list.add(frontGrass);
    	list.add(backGrass);
    	list.add(rock1);
    	scrollHandler = new ScrollHandler(list);		
	}
	
	public void updateReady(float delta) {

	}
	
	public void updateRunning(float delta) {
		guy.update(delta);
		scrollHandler.update(delta);
		
		if (collisionWith(guy)) {
			scrollHandler.stop();
			guy.setIsAlive(false);
			currentState = GameState.GAMEOVER;
		}
	}
	
	public void updateGameOver(float delta) {

	}
	
	public void update(float delta) {
		switch(currentState) {
		case READY:
			updateReady(delta);
			break;
		case RUNNING:
			updateRunning(delta);
			break;
		case GAMEOVER:
			updateGameOver(delta);
			break;
		default:
			Gdx.app.error("ERROR", "Unhandled GameState:" + currentState);
		}
	}
	
	public void ready() {
		currentState = GameState.READY;
	}
	
	public void start() {
		currentState = GameState.RUNNING;
		scrollHandler.start();
	}
	
	public void restart() {
		currentState = GameState.READY;
		guy.restart();
		scrollHandler.restart();
	}
	
	public void setGoToCharacterSelect(boolean value) {
		goToCharacterSelect = value;
	}
	
	public boolean goToCharacterSelect() {
		return goToCharacterSelect;
	}
	
	public GameState currentGameState() {
		return currentState;
	}
	
	public boolean isReady() {
		return currentState == GameState.READY;
	}
	
	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}
	
	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}
	
	public int getFloorHeight() {
		return floorHeight;
	}
	
	public float getGameWidthRatio() {
		return gameWidthRatio;
	}
	
	public float getGameHeightRatio() {
		return gameHeightRatio;
	}
	
	public Guy getGuy() {
		return guy;
	}

	public boolean collisionWith(Guy guy) {
		return rock1.collides(guy);
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
	
	public ScrollHandler getScrollHandler() {
		return scrollHandler;
	}
}
