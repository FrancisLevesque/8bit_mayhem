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
	private Scrollable bombs;
	
	// Game Assets
	private Color backgroundColor;
	private TextureRegion bombsTexture, francisTexture, brandonTexture, stewTexture, seanTexture;
//	private TextureRegion selectYourCharacterText;
	private TextureRegion selectYourCharacterPlate;
	private TextureRegion francisText, brandonText, stewText, seanText;
	private Animation francisRunning, brandonRunning, stewRunning, seanRunning;
	private SimpleButton playButton, musicButton, soundButton, downloadButton;
	private Animation star;
//	private float textHeight, textWidth;
	private float plateHeight, plateWidth;
	
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
		bombs = world.getBombs();
		playButton = ((CharacterSelectInputHandler)(Gdx.input.getInputProcessor())).getPlayButton();
		musicButton = ((CharacterSelectInputHandler)(Gdx.input.getInputProcessor())).getMusicButton();
		soundButton = ((CharacterSelectInputHandler)(Gdx.input.getInputProcessor())).getSoundButton();
		downloadButton = ((CharacterSelectInputHandler)(Gdx.input.getInputProcessor())).getDownloadButton();
	}
	
	private void initGameAssets() {
	    bombsTexture = AssetLoader.bombs;
//	    selectYourCharacterText = AssetLoader.selectYourCharacterText;
	    selectYourCharacterPlate = AssetLoader.selectYourCharacterPlate;
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
        spriteBatcher.draw(bombsTexture, bombs.getX(), bombs.getY(), bombs.getWidth(), bombs.getHeight());
        spriteBatcher.draw(bombsTexture, bombs.getX() - bombs.getWidth(), bombs.getY(), bombs.getWidth(), bombs.getHeight());
        spriteBatcher.draw(bombsTexture, bombs.getX() + bombs.getWidth(), bombs.getY(), bombs.getWidth(), bombs.getHeight());
//        textHeight = selectYourCharacterText.getRegionHeight()/4;
//        textWidth = selectYourCharacterText.getRegionWidth()/4;
//        spriteBatcher.draw(selectYourCharacterText, (gameWidth/2)-(textWidth/2), gameHeight*0.9f, textWidth, textHeight);
        plateHeight = selectYourCharacterPlate.getRegionHeight()/2;
        plateWidth  = selectYourCharacterPlate.getRegionWidth()/2;
        spriteBatcher.draw(selectYourCharacterPlate, (gameWidth/2)-(plateWidth/2), gameHeight*0.6f, plateWidth, plateHeight);
    	soundButton.draw(spriteBatcher);
    	musicButton.draw(spriteBatcher);
    	
        drawCharacter(world.francis, francisTexture, francisRunning.getKeyFrame(runTime), francisText);
        drawCharacter(world.brandon, brandonTexture, brandonRunning.getKeyFrame(runTime), brandonText);
        drawCharacter(world.stew, stewTexture, stewRunning.getKeyFrame(runTime), stewText);
        drawCharacter(world.sean, seanTexture, seanRunning.getKeyFrame(runTime), seanText);
        
        if(AssetLoader.prefs.getBoolean("beatFrancisLevel")) {
        	spriteBatcher.draw(star.getKeyFrame(runTime), world.francis.getX()+((world.francis.getWidth()*5)/6), world.francis.getY()+((world.francis.getHeight()*5)/6), world.francis.getWidth()/4, world.francis.getHeight()/4);
        }
        if(AssetLoader.prefs.getBoolean("beatBrandonLevel")) {
        	spriteBatcher.draw(star.getKeyFrame(runTime+1f), world.brandon.getX()+((world.brandon.getWidth()*5)/6), world.brandon.getY()+((world.brandon.getHeight()*5)/6), world.brandon.getWidth()/4, world.brandon.getHeight()/4);
        }
        if(AssetLoader.prefs.getBoolean("beatStewLevel")) {
        	spriteBatcher.draw(star.getKeyFrame(runTime+1.7f), world.stew.getX()+((world.stew.getWidth()*5)/6), world.stew.getY()+((world.stew.getHeight()*5)/6), world.stew.getWidth()/4, world.stew.getHeight()/4);
        }
        if(AssetLoader.prefs.getBoolean("beatSeanLevel")) {
        	spriteBatcher.draw(star.getKeyFrame(runTime+2.3f), world.sean.getX()+((world.sean.getWidth()*5)/6), world.sean.getY()+((world.sean.getHeight()*5)/6), world.sean.getWidth()/4, world.sean.getHeight()/4);
        }
        if(AssetLoader.prefs.getBoolean("beatFrancisLevel") && AssetLoader.prefs.getBoolean("beatBrandonLevel") && AssetLoader.prefs.getBoolean("beatStewLevel") && AssetLoader.prefs.getBoolean("beatSeanLevel")) {
            downloadButton.draw(spriteBatcher);
        }
        
        if(world.characterSelected()) {
        	playButton.draw(spriteBatcher);
        }
        
        spriteBatcher.end();
	}
	
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
}
