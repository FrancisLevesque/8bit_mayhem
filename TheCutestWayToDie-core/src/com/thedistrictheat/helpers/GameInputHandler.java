package com.thedistrictheat.helpers;

import com.badlogic.gdx.Input.Keys;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameworld.GameWorld;
import com.thedistrictheat.ui.SimpleButton;

public class GameInputHandler extends InputHandler {
	private GameWorld world;
	private Guy guy;
	private SimpleButton backButton;

	public GameInputHandler(GameWorld world) {
		super(world.getGameWidthRatio(), world.getGameHeightRatio());
		this.world = world;
		this.guy = world.getGuy();
		backButton = new SimpleButton(0, (screenHeight * gameHeightRatio) - 12, 10, 10, AssetLoader.backButton, AssetLoader.backButton);
	}

	@Override
	public boolean touchDown(int screenX, int invertedScreenY, int pointer, int button) {
		if(!world.isPausedByWin()) {
			gameX = scaleX(screenX);
		    gameY = scaleY(invertedScreenY);
		    if (backButton.checkIfPressed(gameX, gameY)) {
		    	world.setGoToCharacterSelect(true);
				world.unpause();
		    	return true;
			}
			if(world.isReady()) {
				world.start();
			}
			else if (world.isRunning()){
				guy.onClick();
			}
			else if (world.isPaused()){
				guy.onClick();
				world.unpause();
			}
			else if(world.isGameOver()){
				world.restart();
			}
			else if(world.isWinner()) {
				if(AssetLoader.prefs.getBoolean("firstTime") == true){
					AssetLoader.prefs.putBoolean("firstTime", false);
					AssetLoader.prefs.flush();
					world.restart();
				} else {
					world.setGoToCharacterSelect(true);	
				}
		    	return true;
			}
		}
		return true;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK){
			world.setGoToCharacterSelect(true);
		}
		return false;
	}

	public SimpleButton getBackButton() {
		return backButton;
	}
}
