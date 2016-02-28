package com.thedistrictheat.helpers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.thedistrictheat.gameobjects.DirtTile;
import com.thedistrictheat.gameobjects.GrassTile;
import com.thedistrictheat.gameobjects.Tile;

public class AssetLoader {
	public static final int ROCKWIDTH = 19;
	public static final int ROCKHEIGHT = 9;
	
	public static Texture starsTexture, charactersTexture, textTexture, enemiesTexture, levelForestTexture;
	public static TextureRegion stars;
	public static TextureRegion francis, francisHit, francisRun1, francisRun2, francisRun3, francisJump;
	public static TextureRegion brandon, brandonHit, brandonRun1, brandonRun2, brandonRun3, brandonJump;
	public static TextureRegion stew, stewHit, stewRun1, stewRun2, stewRun3, stewJump;
	public static TextureRegion sean, seanHit, seanRun1, seanRun2, seanRun3, seanJump;
	public static TextureRegion playButtonUp, playButtonDown;
	public static TextureRegion selectYourCharacterText, clickToBeginText, gameOverText;
	public static TextureRegion bombCat, rock;
	public static TextureRegion mountains;
	public static Animation francisRunning, brandonRunning, stewRunning, seanRunning;
	public static Preferences prefs;
	public static ArrayList<Tile> tileList = new ArrayList<Tile>();
	
	public static void load() {
        // starsTexture
        starsTexture = new Texture("graphics/stars.png");
        starsTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        stars = new TextureRegion(starsTexture, 0, 0, 980, 540);
        
		// charactersTexture
        charactersTexture = new Texture("graphics/characters.png");
        charactersTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		francis = new TextureRegion(charactersTexture, 1, 1, 26, 33);
		francisHit = new TextureRegion(charactersTexture, 1, 35, 26, 33);
		francisRun1 = new TextureRegion(charactersTexture, 1, 69, 26, 33);
		francisRun2 = new TextureRegion(charactersTexture, 1, 103, 26, 33);
		francisRun3 = new TextureRegion(charactersTexture, 1, 137, 26, 33);
		francisJump = new TextureRegion(charactersTexture, 1, 171, 26, 33);

		brandon = new TextureRegion(charactersTexture, 28, 1, 26, 33);
		brandonHit = new TextureRegion(charactersTexture, 28, 35, 26, 33);
		brandonRun1 = new TextureRegion(charactersTexture, 28, 69, 26, 33);
		brandonRun2 = new TextureRegion(charactersTexture, 28, 103, 26, 33);
		brandonRun3 = new TextureRegion(charactersTexture, 28, 137, 26, 33);
		brandonJump = new TextureRegion(charactersTexture, 28, 171, 26, 33);

		stew = new TextureRegion(charactersTexture, 55, 1, 26, 33);
		stewHit = new TextureRegion(charactersTexture, 55, 35, 26, 33);
		stewRun1 = new TextureRegion(charactersTexture, 55, 69, 26, 33);
		stewRun2 = new TextureRegion(charactersTexture, 55, 103, 26, 33);
		stewRun3 = new TextureRegion(charactersTexture, 55, 137, 26, 33);
		stewJump = new TextureRegion(charactersTexture, 55, 171, 26, 33);

		sean = new TextureRegion(charactersTexture, 82, 1, 26, 33);
		seanHit = new TextureRegion(charactersTexture, 82, 35, 26, 33);
		seanRun1 = new TextureRegion(charactersTexture, 82, 69, 26, 33);
		seanRun2 = new TextureRegion(charactersTexture, 82, 103, 26, 33);
		seanRun3 = new TextureRegion(charactersTexture, 82, 137, 26, 33);
		seanJump = new TextureRegion(charactersTexture, 82, 171, 26, 33);
		
        TextureRegion[] francisSprites = { francisRun2, francisRun1, francisRun3 };
        francisRunning = new Animation(0.2f, francisSprites);
        francisRunning.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
        TextureRegion[] brandonSprites = { brandonRun2, brandonRun1, brandonRun3 };
        brandonRunning = new Animation(0.2f, brandonSprites);
        brandonRunning.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
        TextureRegion[] stewSprites = { stewRun2, stewRun1, stewRun3 };
        stewRunning = new Animation(0.2f, stewSprites);
        stewRunning.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
        TextureRegion[] seanSprites = { seanRun2, seanRun1, seanRun3 };
        seanRunning = new Animation(0.2f, seanSprites);
        seanRunning.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		// textTexture
        textTexture = new Texture("graphics/text.png");
        textTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		playButtonUp = new TextureRegion(textTexture, 0, 0, 30, 10);
        playButtonDown = new TextureRegion(textTexture, 0, 10, 30, 10);
        selectYourCharacterText = new TextureRegion(textTexture, 0, 20, 122, 6);
        clickToBeginText = new TextureRegion(textTexture, 0, 30, 72, 6);
        gameOverText = new TextureRegion(textTexture, 0, 40, 51, 6);
        
        // enemiesTexture
        enemiesTexture = new Texture("graphics/enemies.png");
        enemiesTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		bombCat = new TextureRegion(enemiesTexture, 0, 0, 13, 20);
		rock = new TextureRegion(enemiesTexture, 20, 0, ROCKWIDTH, ROCKHEIGHT);
		
		// levelForestTexture
		levelForestTexture = new Texture("graphics/level_forest.png");
		levelForestTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		mountains = new TextureRegion(levelForestTexture, 0, 0, 213, 50);

		// Preferences File
        prefs = Gdx.app.getPreferences("TheCutestWayToDie");
        if(!prefs.contains("highScore")) {
        	prefs.putInteger("highScore", 0);
        }
        
        // Creating level
        ArrayList<String> levelLines = new ArrayList<String>();
        FileHandle levelFile = Gdx.files.internal("levels/level1.txt");
        String levelText = levelFile.readString();        
        String[] allLines = levelText.split("\n");
        
        for(int i = 0;i < allLines.length;i++) {
        	if (!allLines[i].startsWith("#")) {
        		levelLines.add(allLines[i]);
        	}
        }
        
        int levelHeight = levelLines.size();
        
        for(int j = 0;j < levelHeight;j++){
    		String line = (String) levelLines.get(levelHeight - 1 - j);
        	for(int i = 0;i < line.length();i++){
        		switch(line.charAt(i)){
    			case '-':
    				Gdx.app.log("AssetLoader", "GrassTile Initialized at (" + i + ", " + j + ")");
    				tileList.add(new GrassTile(i, j));
    				break;
    			case '=':
    				Gdx.app.log("AssetLoader", "DirtTile Initialized at (" + i + ", " + j + ")");
    				tileList.add(new DirtTile(i, j));
    				break;
    			default:
    				Gdx.app.log("AssetLoader", "Character " + line.charAt(i) + " is not currently supported.");
    				break;
        		}
        	}
        }
	}
	
	public static void setHighScore(int score) {
    	prefs.putInteger("highScore", score);
    	prefs.flush();
	}
	
	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}
	
	public static void dispose() {
		starsTexture.dispose();
		charactersTexture.dispose();
		textTexture.dispose();
		enemiesTexture.dispose();
		levelForestTexture.dispose();
	}
}
