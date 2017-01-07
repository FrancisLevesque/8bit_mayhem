package com.thedistrictheat.helpers;

import com.badlogic.gdx.Gdx;
import com.thedistrictheat.gameworld.CharacterSelectWorld;
import com.thedistrictheat.ui.SimpleButton;
import com.thedistrictheat.ui.SimpleToggle;

public class CharacterSelectInputHandler extends InputHandler {
	private final int BUTTON_WIDTH = 60;
	private final int BUTTON_SMALL_WIDTH = 10;
	private final int BUTTON_HEIGHT = 10;
	
	private CharacterSelectWorld world;    
    private SimpleButton howToPlayButton;   
    private SimpleButton playButton;   
    private SimpleButton musicButton;
    private SimpleButton soundButton;
    private SimpleButton downloadButton;
	
	public CharacterSelectInputHandler(CharacterSelectWorld world) {
		super(world.getGameWidthRatio(), world.getGameHeightRatio());
		this.world = world;
		float widthRatio = screenWidth*gameWidthRatio;
		float heightRatio = screenHeight*gameHeightRatio;
        howToPlayButton = new SimpleButton(widthRatio/4 - (BUTTON_WIDTH/2), heightRatio/4 - (BUTTON_HEIGHT/2), 
        		BUTTON_WIDTH, BUTTON_HEIGHT, AssetLoader.howToPlayButtonUp, AssetLoader.howToPlayButtonDown);
        playButton = new SimpleButton((widthRatio/4) * 3 - (BUTTON_WIDTH/2), heightRatio/4 - (BUTTON_HEIGHT/2), 
        		BUTTON_WIDTH, BUTTON_HEIGHT, AssetLoader.playButtonUp, AssetLoader.playButtonDown);
        downloadButton = new SimpleButton(widthRatio/2 - BUTTON_WIDTH/2, heightRatio/4 - (BUTTON_HEIGHT * 2), 
        		BUTTON_WIDTH, BUTTON_HEIGHT, AssetLoader.downloadButtonUp, AssetLoader.downloadButtonDown);
        musicButton = new SimpleToggle(widthRatio - BUTTON_SMALL_WIDTH, heightRatio - BUTTON_HEIGHT, 
        		BUTTON_SMALL_WIDTH, BUTTON_HEIGHT, AssetLoader.musicButtonOn, AssetLoader.musicButtonOff);
        soundButton = new SimpleToggle(widthRatio - BUTTON_SMALL_WIDTH, heightRatio - (BUTTON_HEIGHT * 2), 
        		BUTTON_SMALL_WIDTH, BUTTON_HEIGHT, AssetLoader.soundButtonOn, AssetLoader.soundButtonOff);
	}

	@Override
	public boolean touchDown(int screenX, int invertedScreenY, int pointer, int button) {
		if(!howToPlayButton.isEnabled()) {
			gameX = scaleX(screenX);
		    gameY = scaleY(invertedScreenY);
			if(world.characterSelected(gameX, gameY)) {
			    howToPlayButton.checkIfPressed(gameX, gameY);
				playButton.checkIfPressed(gameX, gameY);
			}
			downloadButton.checkIfPressed(gameX, gameY);
		}
		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int invertedScreenY, int pointer, int button) {		
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
		    howToPlayButton.checkIfReleased(gameX, gameY);
			if(world.characterSelected(gameX, gameY)) {
			    if (playButton.checkIfReleased(gameX, gameY)) {
			    	world.setStartGame(true);
			    	return true;
			    }
			}
			if (downloadButton.checkIfReleased(gameX, gameY)) {
				Gdx.net.openURI("http://thedistrictheat.ca/free_music.html");
			}
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
	
	public SimpleButton getDownloadButton() {
		return downloadButton;
	}
}
