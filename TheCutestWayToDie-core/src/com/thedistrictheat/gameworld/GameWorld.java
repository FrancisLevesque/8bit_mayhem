package com.thedistrictheat.gameworld;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.thedistrictheat.gameobjects.Enemy;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameobjects.ScrollHandler;
import com.thedistrictheat.gameobjects.Scrollable;
import com.thedistrictheat.gameobjects.TalkTile;
import com.thedistrictheat.gameobjects.Tile;
import com.thedistrictheat.gameobjects.Tile.TileType;
import com.thedistrictheat.helpers.AssetLoader;

public class GameWorld {
	public static final int GRAVITY = -300;
    
	private boolean goToCharacterSelect = false;
	private float gameWidth, gameWidthRatio, gameHeightRatio;
	
	private ScrollHandler scrollHandler;
	private List<Scrollable> list;
    private Scrollable firstLayer;
    private Scrollable secondLayer;
    private Scrollable thirdLayer;

	private Guy guy;
    private ArrayList<Enemy> enemyList;
    private ArrayList<Tile> tileList;

    private float pauseTimeOnWin;
    private boolean isPausedByWin;
	
	public enum GameState {
		READY, RUNNING, PAUSED, GAMEOVER, WINNER
	}
	
	private GameState currentState;
	
	public GameWorld(int gameWidth, int gameHeight, float gameWidthRatio, float gameHeightRatio) {
		currentState = GameState.READY;
		this.gameWidth = gameWidth;
		this.gameWidthRatio = gameWidthRatio;
		this.gameHeightRatio = gameHeightRatio;

		firstLayer  = new Scrollable(0, 0, gameWidth, (int)(gameHeight), -18);
		secondLayer  = new Scrollable(0, 0, gameWidth, gameHeight, -8);
		thirdLayer = new Scrollable(0, 0, gameWidth, (int)(gameHeight), -4);

		list = new ArrayList<Scrollable>();
    	list.add(firstLayer);
    	list.add(secondLayer);
    	list.add(thirdLayer);
    	scrollHandler = new ScrollHandler(list);

    	pauseTimeOnWin = 0f;
    	isPausedByWin = false;

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
		
		checkIfHelpTipOn();
		if(checkIfGameWon() == false && checkIfAlive() == true) {
			handleCollisions();
		}
	}
	
	public void updatePaused(float delta) {
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
		case PAUSED:
			updatePaused(delta);
			break;
		case GAMEOVER:
			updateGameOver(delta);
			break;
		case WINNER:
			updateWinner(delta);
			updatePauseOnWin(delta);
			break;
		default:
			Gdx.app.error("ERROR", "Unhandled GameState:" + currentState);
		}
	}
	
	private void checkIfHelpTipOn() {
		if(getGuy().isHelpTipOn()) {
			pause();
		}
	}
	
	private boolean checkIfGameWon() {
		if(getGuy().isGameWon()) {
			pauseOnWin();
			scrollHandler.stop();
			currentState = GameState.WINNER;
			return true;
		}
		return false;
	}
	
	private boolean checkIfAlive() {
		if(!getGuy().isAlive()) {
			scrollHandler.stop();
			currentState = GameState.GAMEOVER;
			return false;
		}
		return true;
	}
	
	private void handleCollisions() {
    	for(int i = 0;i < enemyList.size();i++) {
    		Enemy enemy = enemyList.get(i);
    		guy.enemyCollision(enemy);
        	for(int j = 0;j < tileList.size();j++) {
        		Tile tile = tileList.get(j);
        		TileType type = tile.tileType();
        		if(type == TileType.TALK_TILE) {
        			guy.talkTileCollision((TalkTile) tile);
        			enemy.tileCollision(tile);
        		}
        		else if(type == TileType.TILE_TOP || type == TileType.TILE_TOP_RIGHT || type == TileType.TILE_TOP_LEFT) {
        			guy.tileCollision(tile);
        			enemy.tileCollision(tile);
        		} 
        		else if(type == TileType.TILE_FLAG) {
        			guy.flagCollision(tile);
        		}
        	}
    	}
	}
	
	private void updatePauseOnWin(float delta) {
		pauseTimeOnWin += delta;
		if(pauseTimeOnWin > 0.6f) {
			isPausedByWin = false;
		}
	}
	
	private void pauseOnWin() {
		pauseTimeOnWin = 0f;
		isPausedByWin = true;
	}
	
	public boolean isPausedByWin() {
		return isPausedByWin;
	}
	
	public void ready() {
		currentState = GameState.READY;
	}
	
	public void start() {
		currentState = GameState.RUNNING;
		scrollHandler.start();
	}
	
	public void restart() {
		AssetLoader.loadLevel(guy.getCharacterType());
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
	
	public void pause() {
		currentState = GameState.PAUSED;
		scrollHandler.stop();
	}
	
	public void unpause() {
		guy.setHelpTip(false);
		start();
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
	
	public boolean isPaused() {
		return currentState == GameState.PAUSED;
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

    public Scrollable getFirstLayer() {
		return firstLayer;
	}

    public Scrollable getSecondLayer() {
		return secondLayer;
	}

    public Scrollable getThirdLayer() {
		return thirdLayer;
	}
	
	public ScrollHandler getScrollHandler() {
		return scrollHandler;
	}
}
