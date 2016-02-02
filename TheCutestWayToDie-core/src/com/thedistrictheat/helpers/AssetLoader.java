package com.thedistrictheat.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	public static final int ROCKWIDTH = 19;
	public static final int ROCKHEIGHT = 9;
	
	public static Texture loadingTexture, texture;
	public static TextureRegion loading, playButtonUp, playButtonDown, francis, francisSelected, francisStand, francisBlink, bombCat, rock, mountains, grass;
	public static Animation francisAnimation;
	public static BitmapFont font, timeFont;
	public static Preferences prefs;
	
	public static void load() {
        loadingTexture = new Texture(Gdx.files.internal("graphics/loading.jpg"));
        loadingTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        loading = new TextureRegion(loadingTexture, 0, 0, 1920, 1080);
        
		texture = new Texture(Gdx.files.internal("graphics/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		// TODO: Add Play Button texture regions
		playButtonUp = new TextureRegion(texture, 57, 7, 29, 16);
        playButtonDown = new TextureRegion(texture, 70, 18, 29, 16);

		francis = new TextureRegion(texture, 0, 0, 19, 27);
		francisSelected = new TextureRegion(texture, 0, 0, 19, 27);
		francisStand = new TextureRegion(texture, 19, 0, 19, 27);
		francisBlink = new TextureRegion(texture, 38, 0, 19, 27);
		
        TextureRegion[] francisSprites = { francis, francisStand };
        francisAnimation = new Animation(0.2f, francisSprites);
        francisAnimation.setPlayMode(Animation.PlayMode.LOOP);
		
		bombCat = new TextureRegion(texture, 57, 7, 13, 20);
		rock = new TextureRegion(texture, 70, 18, ROCKWIDTH, ROCKHEIGHT);
		mountains = new TextureRegion(texture, 0, 27, 213, 50);
		grass = new TextureRegion(texture, 0, 77, 210, 7);

		font = new BitmapFont(Gdx.files.internal("fonts/Calibri.fnt"));
		font.setColor(Color.WHITE);
		font.getData().setScale(0.33f);
		
		timeFont = new BitmapFont(Gdx.files.internal("fonts/Calibri.fnt"));
		timeFont.setColor(Color.BLACK);
        timeFont.getData().setScale(0.2f);
        
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
		texture.dispose();
		font.dispose();
		timeFont.dispose();
	}
}
