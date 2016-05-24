package com.thedistrictheat.gameworld;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.thedistrictheat.gameobjects.Character;
import com.thedistrictheat.gameobjects.ScrollHandler;
import com.thedistrictheat.gameobjects.Scrollable;

public class CharacterSelectWorld {
    private static final int STARS_IDLE_SPEED = 20;
    private static final int STARS_SELECTED_SPEED = -70;
    
	private float gameWidthRatio;
	private float gameHeightRatio;
	private boolean characterSelected = false;
	private boolean startGame = false;
	
	private ScrollHandler scrollHandler;
	private List<Scrollable> list;
	private Scrollable stars, stars2;

	public Character francis, brandon, stew, sean;
	public enum CharacterType { FRANCIS, BRANDON, STEW, SEAN };
	
	public CharacterSelectWorld(int gameWidth, int gameHeight, float gameWidthRatio, float gameHeightRatio) {
		this.gameWidthRatio = gameWidthRatio;
		this.gameHeightRatio = gameHeightRatio;    	

		stars = new Scrollable(0, 0, gameWidth, gameHeight, STARS_IDLE_SPEED); 
		stars2 = new Scrollable(gameWidth, 0, gameWidth, gameHeight, STARS_IDLE_SPEED); 
		list = new ArrayList<Scrollable>();
    	list.add(stars);
    	list.add(stars2);
    	scrollHandler = new ScrollHandler(list);
		
		francis = new Character(gameWidth * 0.14f, gameHeight * 0.5f, Color.FOREST);
		brandon = new Character(gameWidth * 0.34f, gameHeight * 0.5f, Color.ROYAL);
		stew = new Character(gameWidth * 0.54f, gameHeight * 0.5f, Color.FIREBRICK);
		sean = new Character((int)(gameWidth * 0.74f), gameHeight * 0.5f, Color.DARK_GRAY);
	}
	
	public void update(float delta) {
		scrollHandler.update(delta);
	}
	
	public boolean characterSelected() {
		return characterSelected;
	}

	public boolean characterSelected(int gameX, int gameY) {
		if (francis.gotClicked(gameX, gameY)) {
			setCharacterSelected(true);
			francis.characterSelected();
			brandon.reset();
			stew.reset();
			sean.reset();
		}
		else if (brandon.gotClicked(gameX, gameY)) {
			setCharacterSelected(true);
			francis.reset();
			brandon.characterSelected();
			stew.reset();
			sean.reset();
		}
		else if (stew.gotClicked(gameX, gameY)) {
			setCharacterSelected(true);
			francis.reset();
			brandon.reset();
			stew.characterSelected();
			sean.reset();
		}
		else if (sean.gotClicked(gameX, gameY)) {
			setCharacterSelected(true);
			francis.reset();
			brandon.reset();
			stew.reset();
			sean.characterSelected();
		}
		return characterSelected;
	}
	
	public void setCharacterSelected(boolean selected) {
		if(selected) {
			scrollHandler.start(STARS_SELECTED_SPEED);
			characterSelected = true;
		} else {
			scrollHandler.start(STARS_IDLE_SPEED);
			francis.reset();
			brandon.reset();
			stew.reset();
			sean.reset();
			characterSelected = false;
		}
	}
	
	public void setStartGame(boolean value) {
		startGame = value;
	}
	
	public boolean startGame() {
		return startGame;
	}
	
	public float getGameWidthRatio() {
		return gameWidthRatio;
	}
	
	public float getGameHeightRatio() {
		return gameHeightRatio;
	}
    
    public Scrollable getStars() {
    	return stars;
    }
    
    public Scrollable getStars2() {
    	return stars2;
    }

	public CharacterType getSelectedCharacter() {
		if(francis.characterSelected()) {
			return CharacterType.FRANCIS;
		}
		else if(brandon.characterSelected()) {
			return CharacterType.BRANDON;
		}
		else if(stew.characterSelected()) {
			return CharacterType.STEW;
		}
		else if (sean.characterSelected()) {
			return CharacterType.SEAN;
		}
		else {
			Gdx.app.log("CharacterSelectWorld", "NO CHARACTER SELECTED! Defaulting to Francis...");
			return CharacterType.FRANCIS;
		}
	}
}
