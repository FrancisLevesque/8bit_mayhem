package com.thedistrictheat.gameworld;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.thedistrictheat.gameobjects.Enemy;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameobjects.ScrollHandler;
import com.thedistrictheat.gameobjects.Scrollable;
import com.thedistrictheat.gameobjects.Tile;
import com.thedistrictheat.gameobjects.Tile.TileType;
import com.thedistrictheat.helpers.AssetLoader;

public class GameWorld {
	public static final int GRAVITY = -300;
    
	private boolean goToCharacterSelect = false;
	private float gameWidth, gameWidthRatio, gameHeightRatio;
	
	private ScrollHandler scrollHandler;
	private List<Scrollable> list;
    private Scrollable frontFirstLayer, backFirstLayer;
    private Scrollable frontSecondLayer, backSecondLayer;
    private Scrollable frontThirdLayer, backThirdLayer;

	private Guy guy;
    private ArrayList<Enemy> enemyList;
    private ArrayList<Tile> tileList;
	
	public enum GameState {
		READY, RUNNING, GAMEOVER, WINNER
	}
	
	private GameState currentState;
	
	public GameWorld(int gameWidth, int gameHeight, float gameWidthRatio, float gameHeightRatio) {
		currentState = GameState.READY;
		this.gameWidth = gameWidth;
		this.gameWidthRatio = gameWidthRatio;
		this.gameHeightRatio = gameHeightRatio;

		frontFirstLayer  = new Scrollable(0, 0, gameWidth, (int)(gameHeight/4), -30);
		backFirstLayer   = new Scrollable(gameWidth, 0, gameWidth, (int)(gameHeight/4), -30);
		frontSecondLayer = new Scrollable(0, 0, gameWidth, (int)(gameHeight/2), -10);
		backSecondLayer  = new Scrollable(gameWidth, 0, gameWidth, (int)(gameHeight/2), -10);
		frontThirdLayer  = new Scrollable(0, 0, gameWidth, gameHeight, -6);
		backThirdLayer   = new Scrollable(gameWidth, 0, gameWidth, gameHeight, -6);

		list = new ArrayList<Scrollable>();
    	list.add(frontFirstLayer);
    	list.add(backFirstLayer);
    	list.add(frontSecondLayer);
    	list.add(backSecondLayer);
    	list.add(frontThirdLayer);
    	list.add(backThirdLayer);
    	scrollHandler = new ScrollHandler(list);

		guy = AssetLoader.guy;
    	enemyList = AssetLoader.enemyList;
    	tileList = AssetLoader.tileList;
	}
	
	public void updateReady(float delta) {
	}
	
	public void updateRunning(float delta) {
		guy.update(delta);
		for(int i = 0;i < enemyList.size();i++) {
			enemyList.get(i).update(delta, gameWidth);
		}
    	for(int i = 0;i < tileList.size();i++) {
    		tileList.get(i).update(delta);
    	}
		scrollHandler.update(delta);
		
		checkIfGameWon();
    	checkIfAlive();
    	handleCollisions();
	}
	
	public void updateGameOver(float delta) {
	}
	
	public void updateWinner(float delta) {
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
		case WINNER:
			updateWinner(delta);
			break;
		default:
			Gdx.app.error("ERROR", "Unhandled GameState:" + currentState);
		}
	}
	
	private void checkIfGameWon() {
		if(getGuy().isGameWon()) {
			scrollHandler.stop();
			currentState = GameState.WINNER;
		}
	}
	
	private void checkIfAlive() {
		if(!getGuy().isAlive()) {
			scrollHandler.stop();
			currentState = GameState.GAMEOVER;
		}
	}
	
	private void handleCollisions() {
    	for(int i = 0;i < enemyList.size();i++) {
    		Enemy enemy = enemyList.get(i);
    		guy.enemyCollision(enemy);
        	for(int j = 0;j < tileList.size();j++) {
        		Tile tile = tileList.get(j);
        		TileType type = tile.tileType();
        		if(type == TileType.TILE_TOP || type == TileType.TILE_TOP_RIGHT || type == TileType.TILE_TOP_LEFT) {
        			guy.tileCollision(tile);
        			enemy.tileCollision(tile);
        		} 
        		else if(type == TileType.TILE_FLAG) {
        			guy.flagCollision(tile);
        		}
        	}
    	}
	}
	
	public void ready() {
//		guy.setSprites(type);
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
    	for(int i = 0;i < enemyList.size();i++) {
    		enemyList.get(i).restart();
    	}
    	for(int i = 0;i < tileList.size();i++) {
    		tileList.get(i).restart();
    	}
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
	
	public boolean isWinner() {
		return currentState == GameState.WINNER;
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

    public Scrollable getFrontFirstLayer() {
		return frontFirstLayer;
	}

	public Scrollable getBackFirstLayer() {
		return backFirstLayer;
	}

    public Scrollable getFrontSecondLayer() {
		return frontSecondLayer;
	}

	public Scrollable getBackSecondLayer() {
		return backSecondLayer;
	}

    public Scrollable getFrontThirdLayer() {
		return frontThirdLayer;
	}

	public Scrollable getBackThirdLayer() {
		return backThirdLayer;
	}
	
	public ScrollHandler getScrollHandler() {
		return scrollHandler;
	}
}
