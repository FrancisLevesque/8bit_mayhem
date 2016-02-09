package com.thedistrictheat.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	public static final int ROCKWIDTH = 19;
	public static final int ROCKHEIGHT = 9;
	
	public static Texture loadingTexture, starsTexture, spritesTexture;
	public static TextureRegion loading, stars, playButtonUp, playButtonDown;
	public static TextureRegion francis, francisHit, francisRun1, francisRun2, francisRun3, francisJump;
	public static TextureRegion brandon, brandonHit, brandonRun1, brandonRun2, brandonRun3, brandonJump;
	public static TextureRegion stew, stewHit, stewRun1, stewRun2, stewRun3, stewJump;
	public static TextureRegion sean, seanHit, seanRun1, seanRun2, seanRun3, seanJump;
	public static TextureRegion bombCat, rock, mountains, grass;
	public static Animation francisRunning;
	public static Preferences prefs;
	
	public static void load() {
		// loadingTexture
        loadingTexture = new Texture(Gdx.files.internal("graphics/loading.jpg"));
        loadingTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        loading = new TextureRegion(loadingTexture, 0, 0, 1920, 1080);
        
        // starsTexture
        starsTexture = new Texture(Gdx.files.internal("graphics/stars.png"));
        starsTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        stars = new TextureRegion(starsTexture, 0, 0, 980, 540);
        
		// spritesTexture
		spritesTexture = new Texture(Gdx.files.internal("graphics/texture.png"));
		spritesTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		playButtonUp = new TextureRegion(spritesTexture, 170, 57, 36, 10);
        playButtonDown = new TextureRegion(spritesTexture, 170, 67, 36, 10);

		francis = new TextureRegion(spritesTexture, 1, 1, 26, 33);
		francisHit = new TextureRegion(spritesTexture, 1, 35, 26, 33);
		francisRun1 = new TextureRegion(spritesTexture, 1, 69, 26, 33);
		francisRun2 = new TextureRegion(spritesTexture, 1, 103, 26, 33);
		francisRun3 = new TextureRegion(spritesTexture, 1, 137, 26, 33);
		francisJump = new TextureRegion(spritesTexture, 1, 171, 26, 33);

		brandon = new TextureRegion(spritesTexture, 28, 1, 26, 33);
		brandonHit = new TextureRegion(spritesTexture, 28, 35, 26, 33);
		brandonRun1 = new TextureRegion(spritesTexture, 28, 69, 26, 33);
		brandonRun2 = new TextureRegion(spritesTexture, 28, 103, 26, 33);
		brandonRun3 = new TextureRegion(spritesTexture, 28, 137, 26, 33);
		brandonJump = new TextureRegion(spritesTexture, 28, 171, 26, 33);

		stew = new TextureRegion(spritesTexture, 55, 1, 26, 33);
		stewHit = new TextureRegion(spritesTexture, 55, 35, 26, 33);
		stewRun1 = new TextureRegion(spritesTexture, 55, 69, 26, 33);
		stewRun2 = new TextureRegion(spritesTexture, 55, 103, 26, 33);
		stewRun3 = new TextureRegion(spritesTexture, 55, 137, 26, 33);
		stewJump = new TextureRegion(spritesTexture, 55, 171, 26, 33);

		sean = new TextureRegion(spritesTexture, 82, 1, 26, 33);
		seanHit = new TextureRegion(spritesTexture, 82, 35, 26, 33);
		seanRun1 = new TextureRegion(spritesTexture, 82, 69, 26, 33);
		seanRun2 = new TextureRegion(spritesTexture, 82, 103, 26, 33);
		seanRun3 = new TextureRegion(spritesTexture, 82, 137, 26, 33);
		seanJump = new TextureRegion(spritesTexture, 82, 171, 26, 33);
		
        TextureRegion[] francisSprites = { francisRun2, francisRun1, francisRun3 };
        francisRunning = new Animation(0.2f, francisSprites);
        francisRunning.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		bombCat = new TextureRegion(spritesTexture, 110, 0, 13, 20);
		rock = new TextureRegion(spritesTexture, 130, 0, ROCKWIDTH, ROCKHEIGHT);
		mountains = new TextureRegion(spritesTexture, 170, 0, 213, 50);
		grass = new TextureRegion(spritesTexture, 170, 50, 210, 7);

        prefs = Gdx.app.getPreferences("TheCutestWayToDie");
        if(!prefs.contains("highScore")) {
        	prefs.putInteger("highScore", 0);
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
		loadingTexture.dispose();
		spritesTexture.dispose();
	}
}
