package com.thedistrictheat.helpers;

import com.badlogic.gdx.Gdx;
import com.thedistrictheat.gameworld.CharacterSelectWorld;
import com.thedistrictheat.ui.SimpleButton;
import com.thedistrictheat.ui.SimpleToggle;

public class CharacterSelectInputHandler extends InputHandler {
	private final int BUTTON_WIDTH = 30;
	private final int BUTTON_SMALL_WIDTH = 10;
	private final int BUTTON_HEIGHT = 10;
	
	private CharacterSelectWorld world;    
    private SimpleButton howToPlayButton;   
    private SimpleButton playButton;   
    private SimpleButton musicButton;   
    private SimpleButton soundButton;
	
	public CharacterSelectInputHandler(CharacterSelectWorld world) {
		super(world.getGameWidthRatio(), world.getGameHeightRatio());
		this.world = world;
        howToPlayButton = new SimpleButton((screenWidth*gameWidthRatio)/4 - (BUTTON_WIDTH/2), 
        		(screenHeight*gameHeightRatio)/4 - (BUTTON_HEIGHT/2), 
        		BUTTON_WIDTH, BUTTON_HEIGHT, AssetLoader.playButtonUp, AssetLoader.playButtonDown);
        playButton = new SimpleButton(((screenWidth*gameWidthRatio)/4) * 3 - (BUTTON_WIDTH/2), 
        		(screenHeight*gameHeightRatio)/4 - (BUTTON_HEIGHT/2), 
        		BUTTON_WIDTH, BUTTON_HEIGHT, AssetLoader.playButtonUp, AssetLoader.playButtonDown);
        musicButton = new SimpleToggle((screenWidth*gameWidthRatio) - BUTTON_SMALL_WIDTH, 
        		(screenHeight*gameHeightRatio) - BUTTON_HEIGHT, 
        		BUTTON_SMALL_WIDTH, BUTTON_HEIGHT, AssetLoader.musicButtonOn, AssetLoader.musicButtonOff);
        soundButton = new SimpleToggle((screenWidth*gameWidthRatio) - BUTTON_SMALL_WIDTH, 
        		(screenHeight*gameHeightRatio) - (BUTTON_HEIGHT * 2), 
        		BUTTON_SMALL_WIDTH, BUTTON_HEIGHT, AssetLoader.soundButtonOn, AssetLoader.soundButtonOff);
	}

	@Override
	public boolean touchDown(int screenX, int invertedScreenY, int pointer, int button) {

//				gameX = scaleX(screenX);
//			    gameY = scaleY(invertedScreenY);
//				if(world.characterSelected(gameX, gameY)) {
//					playButton.checkIfPressed(gameX, gameY);
//				}
			
		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int invertedScreenY, int pointer, int button) {		
	    if(AssetLoader.prefs.getBoolean("firstTime") == false) {
			SoundHandler.playClickSound();
			if(howToPlayButton.isEnabled()) {
				howToPlayButton.reset();
			} else {
				gameX = scaleX(screenX);
			    gameY = scaleY(invertedScreenY);
			    if (musicButton.checkIfPressed(gameX, gameY)) {
			    	SoundHandler.toggleMusic();
			    }		    
			    if (soundButton.checkIfPressed(gameX, gameY)) {
			    	SoundHandler.toggleSound();
			    }
			    howToPlayButton.checkIfPressed(gameX, gameY);
				if(world.characterSelected(gameX, gameY)) {
				    if (playButton.checkIfPressed(gameX, gameY)) {
				    	world.setStartGame(true);
				    	return true;
				    }
				}
			}
		} else {
			AssetLoader.prefs.putBoolean("firstTime", false);
		}
		return false;
	}
	
	public SimpleButton getHowToPlayButton() {
		return howToPlayButton;
	}
	
	public SimpleButton getPlayButton() {
		return playButton;
	}
	
	public SimpleButton getMusicButton() {
		return musicButton;
	}
	
	public SimpleButton getSoundButton() {
		return soundButton;
	}
}
