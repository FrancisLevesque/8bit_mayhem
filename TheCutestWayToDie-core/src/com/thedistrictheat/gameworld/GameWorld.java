package com.thedistrictheat.gameworld;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameobjects.Mountains;
import com.thedistrictheat.gameobjects.Rock;
import com.thedistrictheat.gameobjects.ScrollHandler;
import com.thedistrictheat.gameobjects.Scrollable;
import com.thedistrictheat.gameobjects.Tile;
import com.thedistrictheat.gameworld.CharacterSelectWorld.CharacterType;
import com.thedistrictheat.helpers.AssetLoader;

public class GameWorld {
	public static final int GRAVITY = -300;
    
	private boolean goToCharacterSelect = false;
	private float gameWidthRatio;
	private float gameHeightRatio;
	
	private ScrollHandler scrollHandler;
	private List<Scrollable> list;
	private Guy guy;
    private Mountains frontMountains, backMountains;
    private Rock rock1;
    
    private ArrayList<Tile> tileList;
	
	public enum GameState {
		READY, RUNNING, GAMEOVER
	}
	
	private GameState currentState;
	
	public GameWorld(int gameWidth, int gameHeight, float gameWidthRatio, float gameHeightRatio) {
		currentState = GameState.READY;
		this.gameWidthRatio = gameWidthRatio;
		this.gameHeightRatio = gameHeightRatio;

		guy = new Guy();
    	frontMountains = new Mountains(0, gameHeight, gameWidth, gameHeight/2);
    	backMountains = new Mountains(gameWidth, gameHeight, gameWidth, gameHeight/2);
//    	rock1 = new Rock(gameWidth, standingHeight, AssetLoader.ROCKWIDTH, AssetLoader.ROCKHEIGHT, GROUND_SPEED); 

		list = new ArrayList<Scrollable>();
    	list.add(frontMountains);
    	list.add(backMountains);
//    	list.add(rock1);
    	scrollHandler = new ScrollHandler(list);
    	
    	tileList = AssetLoader.tileList;
	}
	
	public void updateReady(float delta) {

	}
	
	public void updateRunning(float delta) {
		guy.update(delta);
		scrollHandler.update(delta);
    	for(int i = 0;i < tileList.size();i++) {
    		tileList.get(i).update(delta);
    	}
		
    	handleCollisions();
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
	
	private boolean rockCollisionWith(Guy guy) {
    	return false;
//		return rock1.collides(guy);
	}
	
	private void handleCollisions() {
//		if (rockCollisionWith(guy)) {
//			scrollHandler.stop();
//			guy.setIsAlive(false);
//			currentState = GameState.GAMEOVER;
//		}
    	for(int i = 0;i < tileList.size();i++) {
    		guy.tileCollision(tileList.get(i));
    	}
	}
	
	public void ready(CharacterType type) {
		guy.setSprites(type);
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
	
	public float getGameWidthRatio() {
		return gameWidthRatio;
	}
	
	public float getGameHeightRatio() {
		return gameHeightRatio;
	}
	
	public Guy getGuy() {
		return guy;
	}

    public Mountains getFrontMountains() {
		return frontMountains;
	}

	public Mountains getBackMountains() {
		return backMountains;
	}
    
    public Rock getRock1() {
    	return rock1;
    }
	
	public ScrollHandler getScrollHandler() {
		return scrollHandler;
	}
}
