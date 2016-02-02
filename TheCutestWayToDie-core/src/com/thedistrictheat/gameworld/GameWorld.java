package com.thedistrictheat.gameworld;

import com.badlogic.gdx.Gdx;
import com.thedistrictheat.gameobjects.Character;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameobjects.ScrollHandler;

public class GameWorld {
	public static final int GRAVITY = -300;
	private static final float LOADTIME = 1;

    private int start;
    private int overflow;
	private int x1, x2, x3;
	
	public Character francis, sean, brandon, stew;
	private boolean characterSelected = false;

	private float runTime = 0;
	private int floorHeight;
	private float gameWidthRatio;
	private float gameHeightRatio;
	private int standingHeight;
	private Guy guy;
	private ScrollHandler scrollHandler;
	
	public enum GameState {
		LOADING, SELECT, READY, RUNNING, GAMEOVER
	}
	
	private GameState currentState;
	
	public GameWorld(int floorHeight, int gameWidth, int gameHeight, float gameWidthRatio, float gameHeightRatio) {
		currentState = GameState.LOADING;
		this.floorHeight = floorHeight;
		this.gameWidthRatio = gameWidthRatio;
		this.gameHeightRatio = gameHeightRatio;
		standingHeight = (int)(floorHeight * 0.7);
		guy = new Guy(standingHeight, GRAVITY, (int)(gameWidth * 0.1));
		scrollHandler = new ScrollHandler(floorHeight, gameWidth, gameHeight);

	    start = -gameWidth/4;
	    overflow = gameWidth - start;
		x1 = 0;
		x2 = gameWidth/2;
		x3 = gameWidth;
		
		francis = new Character(gameWidth * 0.14f, gameHeight * 0.5f, guy.getWidth(), guy.getHeight());
		sean = new Character(gameWidth * 0.34f, gameHeight * 0.5f, guy.getWidth(), guy.getHeight());
		brandon = new Character(gameWidth * 0.54f, gameHeight * 0.5f, guy.getWidth(), guy.getHeight());
		stew = new Character(gameWidth * 0.74f, gameHeight * 0.5f, guy.getWidth(), guy.getHeight());
	}
	
	public void updateLoading(float delta) {
		runTime += delta;
		if (runTime > LOADTIME) {
			currentState = GameState.SELECT;
		}
	}
	
	public void updateSelect(float delta) {
        x1 = increment(x1);
        x2 = increment(x2);
        x3 = increment(x3);
	}
	
	public void updateReady(float delta) {

	}
	
	public void updateRunning(float delta) {
		guy.update(delta);
		scrollHandler.update(delta);
		
		if (scrollHandler.collides(guy)) {
			scrollHandler.stop();
			guy.setIsAlive(false);
			currentState = GameState.GAMEOVER;
		}
	}
	
	public void updateGameOver(float delta) {

	}
	
	public void update(float delta) {
		switch(currentState) {
		case LOADING:
			updateLoading(delta);
			break;
		case SELECT:
			updateSelect(delta);
			break;
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
	
	private int increment(int x) {
        x++;
        if(x > overflow) {
        	x = start;
        }
        return x;
	}
	
	public int getX1() {
		return x1;
	}
	
	public int getX2() {
		return x2;
	}
	
	public int getX3() {
		return x3;
	}

	public void checkIfCharacterSelected(int gameX, int gameY) {
		if(francis.gotClicked(gameX, gameY)) {
			characterSelected = true;
			// TODO: make PLAY button clickable (should be disabled when no character selected)
			if(francis.characterSelected()) {
				francis.reset();
			}
			else {
				francis.select();
			}
			sean.reset();
			brandon.reset();
			stew.reset();
		}
	}

	public boolean playButtonPressed(int screenX, int screenY) {
		return false;
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
	
	public GameState currentGameState() {
		return currentState;
	}
	
	public boolean characterSelected() {
		return characterSelected;
	}
	
	public boolean isLoading() {
		return currentState == GameState.LOADING;
	}
	
	public boolean isSelect() {
		return currentState == GameState.SELECT;
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
	
	public ScrollHandler getScrollHandler() {
		return scrollHandler;
	}
}
