package com.thedistrictheat.helpers;

import com.thedistrictheat.gameworld.CharacterSelectWorld;
import com.thedistrictheat.ui.SimpleButton;

public class CharacterSelectInputHandler extends InputHandler {
	private final int BUTTON_WIDTH = 30;
	private final int BUTTON_SMALL_WIDTH = 10;
	private final int BUTTON_HEIGHT = 10;
	
	private CharacterSelectWorld world;    
    private SimpleButton playButton;   
    private SimpleButton exitButton;   
    private SimpleButton musicButton;   
    private SimpleButton soundButton;
	
	public CharacterSelectInputHandler(CharacterSelectWorld world) {
		super(world.getGameWidthRatio(), world.getGameHeightRatio());
		this.world = world;
        playButton = new SimpleButton((screenWidth*gameWidthRatio)/2 - (BUTTON_WIDTH/2), 
        		(screenHeight*gameHeightRatio)/4 - (BUTTON_HEIGHT/2), 
        		BUTTON_WIDTH, BUTTON_HEIGHT, AssetLoader.playButtonUp, AssetLoader.playButtonDown);
        exitButton = new SimpleButton(0, (screenHeight*gameHeightRatio) - BUTTON_HEIGHT, 
        		BUTTON_SMALL_WIDTH, BUTTON_HEIGHT, AssetLoader.playButtonUp, AssetLoader.playButtonDown);
        musicButton = new SimpleButton((screenWidth*gameWidthRatio) - BUTTON_SMALL_WIDTH, 
        		(screenHeight*gameHeightRatio) - BUTTON_HEIGHT, 
        		BUTTON_SMALL_WIDTH, BUTTON_HEIGHT, AssetLoader.playButtonUp, AssetLoader.playButtonDown);
        soundButton = new SimpleButton((screenWidth*gameWidthRatio) - BUTTON_SMALL_WIDTH, 
        		(screenHeight*gameHeightRatio) - (BUTTON_HEIGHT * 2), 
        		BUTTON_SMALL_WIDTH, BUTTON_HEIGHT, AssetLoader.playButtonUp, AssetLoader.playButtonDown);
	}

	@Override
	public boolean touchDown(int screenX, int invertedScreenY, int pointer, int button) {
		gameX = scaleX(screenX);
	    gameY = scaleY(invertedScreenY);
		if(world.characterSelected(gameX, gameY)) {
			playButton.checkIfPressed(gameX, gameY);
		}
		exitButton.checkIfPressed(gameX, gameY);
		musicButton.checkIfPressed(gameX, gameY);
		soundButton.checkIfPressed(gameX, gameY);
		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int invertedScreenY, int pointer, int button) {
		gameX = scaleX(screenX);
	    gameY = scaleY(invertedScreenY);		    
	    if (exitButton.checkIfReleased(gameX, gameY)) {
	    	
	    }		    
	    if (musicButton.checkIfReleased(gameX, gameY)) {
	    	SoundHandler.toggleMusic();
	    }		    
	    if (soundButton.checkIfReleased(gameX, gameY)) {
	    	SoundHandler.toggleSound();
	    }
		if(world.characterSelected(gameX, gameY)) {
			SoundHandler.playClickSound();
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
	
	public SimpleButton getExitButton() {
		return exitButton;
	}
	
	public SimpleButton getMusicButton() {
		return musicButton;
	}
	
	public SimpleButton getSoundButton() {
		return soundButton;
	}
}
