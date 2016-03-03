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
import com.thedistrictheat.gameobjects.Stars;
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
	private Stars stars, stars2;
	
	// Game Assets
	private Color backgroundColor;
	private TextureRegion starsTexture, francisTexture, brandonTexture, stewTexture, seanTexture;
	private TextureRegion selectYourCharacterText;
	private TextureRegion francisText, brandonText, stewText, seanText;
	private Animation francisRunning, brandonRunning, stewRunning, seanRunning;
	private SimpleButton playButton;
	
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
		stars2 = world.getStars2();
		playButton = ((CharacterSelectInputHandler)(Gdx.input.getInputProcessor())).getPlayButton();
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
	}
	
	private void drawCharacter(Character character, TextureRegion waiting, TextureRegion running, TextureRegion name) {        
		if(character.characterSelected()){
			backgroundColor = character.getColor();
        	spriteBatcher.draw(running, character.getX(), character.getY(), character.getWidth(), character.getHeight());
    		int nameWidth = name.getRegionWidth();
    		int nameHeight = name.getRegionHeight();
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
        if(world.characterSelected()) {
            spriteBatcher.draw(starsTexture, stars.getX(), stars.getY(), stars.getWidth(), stars.getHeight());
            spriteBatcher.draw(starsTexture, stars2.getX(), stars2.getY(), stars2.getWidth(), stars2.getHeight());
        }
        spriteBatcher.draw(selectYourCharacterText, (gameWidth/2)-(selectYourCharacterText.getRegionWidth()/2), gameHeight*0.9f, selectYourCharacterText.getRegionWidth(), selectYourCharacterText.getRegionHeight());
        drawCharacter(world.francis, francisTexture, francisRunning.getKeyFrame(runTime), francisText);
        drawCharacter(world.brandon, brandonTexture, brandonRunning.getKeyFrame(runTime), brandonText);
        drawCharacter(world.stew, stewTexture, stewRunning.getKeyFrame(runTime), stewText);
        drawCharacter(world.sean, seanTexture, seanRunning.getKeyFrame(runTime), seanText);
        
        if(world.characterSelected()) {
        	playButton.draw(spriteBatcher);
        }
        spriteBatcher.end();
	}
	
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
}
