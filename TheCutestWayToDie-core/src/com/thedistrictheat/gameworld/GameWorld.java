package com.thedistrictheat.gameworld;

import com.badlogic.gdx.Gdx;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameobjects.ScrollHandler;

public class GameWorld {
	public static final int GRAVITY = -300;

	private int floorHeight;
	private int standingHeight;
	private Guy guy;
	private ScrollHandler scrollHandler;
	
	public enum GameState {
		LOADING, SELECT, READY, RUNNING, GAMEOVER
	}
	
	private GameState currentState;
	
	public GameWorld(int floorHeight, int gameWidth, int gameHeight) {
		currentState = GameState.READY;
		this.floorHeight = floorHeight;
		standingHeight = (int)(floorHeight * 0.7);
		guy = new Guy(standingHeight, GRAVITY, (int)(gameWidth * 0.1));
		scrollHandler = new ScrollHandler(floorHeight, gameWidth, gameHeight);
	}
	
	public void updateLoading(float delta) {

	}
	
	public void updateSelect(float delta) {

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
	
	public void start() {
		currentState = GameState.RUNNING;
		scrollHandler.start();
	}
	
	public void restart() {
		currentState = GameState.READY;
		guy.restart();
		scrollHandler.restart();
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
	
	public Guy getGuy() {
		return guy;
	}
	
	public ScrollHandler getScrollHandler() {
		return scrollHandler;
	}
}
