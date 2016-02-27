package com.thedistrictheat.helpers;

import com.thedistrictheat.gameworld.CharacterSelectWorld;
import com.thedistrictheat.ui.SimpleButton;

public class CharacterSelectInputHandler extends InputHandler {
	private final int BUTTON_WIDTH = 30;
	private final int BUTTON_HEIGHT = 10;
	
	private CharacterSelectWorld world;    
    private SimpleButton playButton;
	
	public CharacterSelectInputHandler(CharacterSelectWorld world) {
		super(world.getGameWidthRatio(), world.getGameHeightRatio());
		this.world = world;
        playButton = new SimpleButton((screenWidth*gameWidthRatio)/2 - (BUTTON_WIDTH/2), 
        		(screenHeight*gameHeightRatio)/4 - (BUTTON_HEIGHT/2), BUTTON_WIDTH, 
        		BUTTON_HEIGHT, AssetLoader.playButtonUp, AssetLoader.playButtonDown);
	}

	@Override
	public boolean touchDown(int screenX, int invertedScreenY, int pointer, int button) {
		gameX = scaleX(screenX);
	    gameY = scaleY(invertedScreenY);
		if(world.characterSelected(gameX, gameY)) {
			playButton.checkIfPressed(gameX, gameY);
		}
		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int invertedScreenY, int pointer, int button) {
		gameX = scaleX(screenX);
	    gameY = scaleY(invertedScreenY);
		if(world.characterSelected(gameX, gameY)) {
		    if (playButton.checkIfReleased(gameX, gameY)) {
		    	world.setStartGame(true);
		    	return true;
		    }
		}
		return false;
	}
	
	public SimpleButton getPlayButton() {
		return playButton;
	}
}
