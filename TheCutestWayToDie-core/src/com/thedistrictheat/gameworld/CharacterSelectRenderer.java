package com.thedistrictheat.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.thedistrictheat.gameobjects.Character;
import com.thedistrictheat.gameobjects.Scrollable;
import com.thedistrictheat.helpers.AssetLoader;
import com.thedistrictheat.helpers.CharacterSelectInputHandler;
import com.thedistrictheat.ui.SimpleButton;

public class CharacterSelectRenderer {
	private CharacterSelectWorld world;
	private int gameWidth, gameHeight;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatcher;
		
	// Game Objects
	private Scrollable stars;
	
	// Game Assets
	private Color backgroundColor;
	private TextureRegion starsTexture, francisTexture, brandonTexture, stewTexture, seanTexture;
	private TextureRegion selectYourCharacterText;
	private TextureRegion francisText, brandonText, stewText, seanText;
	private TextureRegion howToPlay;
	private Animation francisRunning, brandonRunning, stewRunning, seanRunning;
	private SimpleButton howToPlayButton, playButton, musicButton, soundButton, downloadButton;
	private Animation star;
	private float textHeight, textWidth;
	
	public CharacterSelectRenderer(CharacterSelectWorld world, Color backgroundColor, int gameWidth, int gameHeight) {
		this.world = world;
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		this.backgroundColor = backgroundColor;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, gameWidth, gameHeight);
		spriteBatcher = new SpriteBatch();
		spriteBatcher.setProjectionMatrix(camera.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
		
		initGameObjects();
		initGameAssets();
	}
	
	private void initGameObjects() {
		stars = world.getStars();
		howToPlayButton = ((CharacterSelectInputHandler)(Gdx.input.getInputProcessor())).getHowToPlayButton();
		playButton = ((CharacterSelectInputHandler)(Gdx.input.getInputProcessor())).getPlayButton();
		musicButton = ((CharacterSelectInputHandler)(Gdx.input.getInputProcessor())).getMusicButton();
		soundButton = ((CharacterSelectInputHandler)(Gdx.input.getInputProcessor())).getSoundButton();
		downloadButton = ((CharacterSelectInputHandler)(Gdx.input.getInputProcessor())).getDownloadButton();
	}
	
	private void initGameAssets() {
	    starsTexture = AssetLoader.stars;
	    selectYourCharacterText = AssetLoader.selectYourCharacterText;
	    francisText = AssetLoader.francisText;
	    brandonText = AssetLoader.brandonText;
	    stewText = AssetLoader.stewText;
	    seanText = AssetLoader.seanText;
		francisTexture = AssetLoader.francis;
		francisRunning = AssetLoader.francisRunning;
		brandonTexture = AssetLoader.brandon;
		brandonRunning = AssetLoader.brandonRunning;
		stewTexture = AssetLoader.stew;
		stewRunning = AssetLoader.stewRunning;
		seanTexture = AssetLoader.sean;
		seanRunning = AssetLoader.seanRunning;
		howToPlay = AssetLoader.howToPlay;
		star = AssetLoader.star;
	}
	
	private void drawCharacter(Character character, TextureRegion waiting, TextureRegion running, TextureRegion name) {        
		if(character.characterSelected()){
			backgroundColor = character.getColor();
        	spriteBatcher.draw(running, character.getX(), character.getY(), character.getWidth(), character.getHeight());
    		int nameWidth = name.getRegionWidth()/4;
    		int nameHeight = name.getRegionHeight()/4;
    		spriteBatcher.draw(name, character.getX()+(character.getWidth()/2)-(nameWidth/2), character.getY()-nameHeight-4, nameWidth, nameHeight);
        } else {
        	spriteBatcher.draw(waiting, character.getX(), character.getY(), character.getWidth(), character.getHeight());
        }
	}
	
	public void render(float runTime) {		
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(backgroundColor);
        shapeRenderer.rect(0, 0, gameWidth, gameHeight);
        shapeRenderer.end();

        spriteBatcher.begin();
        spriteBatcher.draw(starsTexture, stars.getX(), stars.getY(), stars.getWidth(), stars.getHeight());
        spriteBatcher.draw(starsTexture, stars.getX() - stars.getWidth(), stars.getY(), stars.getWidth(), stars.getHeight());
        spriteBatcher.draw(starsTexture, stars.getX() + stars.getWidth(), stars.getY(), stars.getWidth(), stars.getHeight());
        textHeight = selectYourCharacterText.getRegionHeight()/4;
        textWidth = selectYourCharacterText.getRegionWidth()/4;
        spriteBatcher.draw(selectYourCharacterText, (gameWidth/2)-(textWidth/2), gameHeight*0.9f, textWidth, textHeight);
    	soundButton.draw(spriteBatcher);
    	musicButton.draw(spriteBatcher);
    	
        drawCharacter(world.francis, francisTexture, francisRunning.getKeyFrame(runTime), francisText);
        drawCharacter(world.brandon, brandonTexture, brandonRunning.getKeyFrame(runTime), brandonText);
        drawCharacter(world.stew, stewTexture, stewRunning.getKeyFrame(runTime), stewText);
        drawCharacter(world.sean, seanTexture, seanRunning.getKeyFrame(runTime), seanText);
        
        if(AssetLoader.prefs.getBoolean("beatFrancisLevel")) {
        	spriteBatcher.draw(star.getKeyFrame(runTime), world.francis.getX() + world.francis.getWidth(), world.francis.getY()+world.francis.getHeight(), world.francis.getWidth()/4, world.francis.getHeight()/4);
        }
        if(AssetLoader.prefs.getBoolean("beatBrandonLevel")) {
        	spriteBatcher.draw(star.getKeyFrame(runTime+1f), world.brandon.getX() + world.brandon.getWidth(), world.brandon.getY()+world.brandon.getHeight(), world.brandon.getWidth()/4, world.brandon.getHeight()/4);
        }
        if(AssetLoader.prefs.getBoolean("beatStewLevel")) {
        	spriteBatcher.draw(star.getKeyFrame(runTime+1.7f), world.stew.getX() + world.stew.getWidth(), world.stew.getY()+world.stew.getHeight(), world.stew.getWidth()/4, world.stew.getHeight()/4);
        }
        if(AssetLoader.prefs.getBoolean("beatSeanLevel")) {
        	spriteBatcher.draw(star.getKeyFrame(runTime+2.3f), world.sean.getX() + world.sean.getWidth(), world.sean.getY()+world.sean.getHeight(), world.sean.getWidth()/4, world.sean.getHeight()/4);
        }
        //if(AssetLoader.prefs.getBoolean("beatFrancisLevel") && AssetLoader.prefs.getBoolean("beatBrandonLevel") && AssetLoader.prefs.getBoolean("beatStewLevel") && AssetLoader.prefs.getBoolean("beatSeanLevel")) {
            downloadButton.draw(spriteBatcher);
        //}
        
        if(world.characterSelected()) {
        	howToPlayButton.draw(spriteBatcher);
        	playButton.draw(spriteBatcher);
        }
        
        if(howToPlayButton.isEnabled()) {
        	spriteBatcher.draw(howToPlay, 0, 0, gameWidth, gameHeight);
        }
        
        spriteBatcher.end();
	}
	
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
}
