package com.thedistrictheat.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.TimeUtils;
import com.thedistrictheat.gameobjects.Grass;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameobjects.Mountains;
import com.thedistrictheat.gameobjects.Rock;
import com.thedistrictheat.gameobjects.ScrollHandler;
import com.thedistrictheat.helpers.AssetLoader;
import com.thedistrictheat.helpers.InputHandler;
import com.thedistrictheat.ui.SimpleButton;

public class GameRenderer {
	private GameWorld world;
	private int gameWidth, gameHeight, floorHeight;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatcher;
    
	private long startTime = 0;
	private long currentTime = 0;
	
	// Game Strings
	private String startString = "Touch Screen";
	private String gameOverString = "Game Over";
	private String tryAgainString = "Try Again?";
	
	// Game Objects
	private Guy guy;
	private ScrollHandler scrollHandler;
	private Mountains frontMountains, backMountains;
	private Grass frontGrass, backGrass;
	private Rock rock1;
	
	// Game Assets
	private TextureRegion francis, francisHit, francisJump;
	private TextureRegion brandon, brandonHit, brandonJump;
	private TextureRegion stew, stewHit, stewJump;
	private TextureRegion sean, seanHit, seanJump;
	private TextureRegion bombCat, mountains, grass, rock;
	private Animation francisRunning;
	private BitmapFont font, timeFont;
	private SimpleButton playButton;
	
	public GameRenderer(GameWorld world, int gameWidth, int gameHeight) {
		this.world = world;
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		floorHeight = world.getFloorHeight();
		
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
		guy = world.getGuy();
		scrollHandler = world.getScrollHandler();
		frontMountains = scrollHandler.getFrontMountains();
		backMountains = scrollHandler.getBackMountains();
		frontGrass = scrollHandler.getFrontGrass();
		backGrass = scrollHandler.getBackGrass();
		rock1 = scrollHandler.getRock1();

		playButton = ((InputHandler)(Gdx.input.getInputProcessor())).getPlayButton();
	}
	
	private void initGameAssets() {
		francis = AssetLoader.francis;
		francisHit = AssetLoader.francisHit;
		francisJump = AssetLoader.francisJump;
		francisRunning = AssetLoader.francisRunning;

		brandon = AssetLoader.brandon;
		brandonHit = AssetLoader.brandonHit;
		brandonJump = AssetLoader.brandonJump;

		stew = AssetLoader.stew;
		stewHit = AssetLoader.stewHit;
		stewJump = AssetLoader.stewJump;

		sean = AssetLoader.sean;
		seanHit = AssetLoader.seanHit;
		seanJump = AssetLoader.seanJump;
		
		bombCat = AssetLoader.bombCat;
		mountains = AssetLoader.mountains;
		grass = AssetLoader.grass;
		rock = AssetLoader.rock;
		font = AssetLoader.font;
		timeFont = AssetLoader.timeFont;
	}

	private void drawMountains() {
		spriteBatcher.draw(mountains, frontMountains.getX(), frontMountains.getY(), frontMountains.getWidth(), frontMountains.getHeight());
		spriteBatcher.draw(mountains, backMountains.getX(), backMountains.getY(), backMountains.getWidth(), backMountains.getHeight());
	}
	
	private void drawGrass() {
		spriteBatcher.draw(grass, frontGrass.getX(), frontGrass.getY(), frontGrass.getWidth(), frontGrass.getHeight());
		spriteBatcher.draw(grass, backGrass.getX(), backGrass.getY(), backGrass.getWidth(), backGrass.getHeight());
	}
	
	private void drawRocks() {
		spriteBatcher.draw(rock, rock1.getX(), rock1.getY(), rock1.getWidth(), rock1.getHeight());
	}
	
	private void setTime() {
		startTime = TimeUtils.millis()/1000;
	}

	private void drawTime() { 
		if(world.isReady()) {
			currentTime = 0;
		}
		else if(world.isRunning()) {
	        currentTime = (TimeUtils.millis()/1000) - startTime;
		}
        int seconds = (int)(currentTime%60);
        int minutes = ((int)currentTime)/60;
        if (seconds < 10) {
        	timeFont.draw(spriteBatcher, minutes + ":0" + seconds, 0, gameHeight * 0.98f); 
        }
        else {
        	timeFont.draw(spriteBatcher, minutes + ":" + seconds, 0, gameHeight * 0.98f); 
        }
	}
	
	public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        switch(world.currentGameState()) {
        case SELECT:
            shapeRenderer.begin(ShapeType.Filled);
            // Draw Background
            shapeRenderer.setColor(Color.PURPLE);
            shapeRenderer.rect(0, 0, gameWidth, gameHeight);
            shapeRenderer.setColor(Color.CYAN);
            shapeRenderer.rect(world.getX1(), 0, gameWidth/4, gameHeight);
            shapeRenderer.setColor(Color.CYAN);
            shapeRenderer.rect(world.getX2(), 0, gameWidth/4, gameHeight);
            shapeRenderer.setColor(Color.CYAN);
            shapeRenderer.rect(world.getX3(), 0, gameWidth/4, gameHeight);
            shapeRenderer.end();
            
            spriteBatcher.begin();
        	font.draw(spriteBatcher, "Select your character!", (gameWidth * 0.24f), gameHeight * 0.95f);
        	if(world.francis.characterSelected()) {
        		spriteBatcher.draw(francis, world.francis.getX()-1, world.francis.getY()-1, world.francis.getWidth()+2, world.francis.getHeight()+2);
        	} 
        	else {
        		spriteBatcher.draw(francis, world.francis.getX(), world.francis.getY(), world.francis.getWidth(), world.francis.getHeight());
        	}
            spriteBatcher.draw(brandon, world.brandon.getX(), world.brandon.getY(), world.brandon.getWidth(), world.brandon.getHeight());
            spriteBatcher.draw(stew, world.stew.getX(), world.stew.getY(), world.stew.getWidth(), world.stew.getHeight());
            spriteBatcher.draw(sean, world.sean.getX(), world.sean.getY(), world.sean.getWidth(), world.sean.getHeight());
            if(world.characterSelected()) {
            	playButton.draw(spriteBatcher);
            }
            spriteBatcher.end();
        	break;
        case READY:
        case RUNNING:
        case GAMEOVER:
            shapeRenderer.begin(ShapeType.Filled);
            // Draw Background color
            shapeRenderer.setColor(Color.CYAN);
            shapeRenderer.rect(0, floorHeight, gameWidth, gameHeight - floorHeight);
            // Draw Dirt
            shapeRenderer.setColor(Color.BROWN);
            shapeRenderer.rect(0, 0, gameWidth, (int)(floorHeight * 0.6));
            // Draw Collision Bounding Shapes
//            shapeRenderer.setColor(Color.RED);
//            shapeRenderer.rect(guy.getBoundingRectangle().getX(), guy.getBoundingRectangle().getY(), guy.getBoundingRectangle().getWidth(), guy.getBoundingRectangle().getHeight());
//            shapeRenderer.circle(rock1.getBoundingCircle().x, rock1.getBoundingCircle().y, rock1.getBoundingCircle().radius);
            shapeRenderer.end();
            
            spriteBatcher.begin();
            drawMountains();
            drawGrass();
            drawRocks();
            
            if(world.isReady()) {
            	spriteBatcher.draw(francis, guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
            	font.draw(spriteBatcher, startString, (gameWidth * 0.5f) - startString.length() * 2, gameHeight * 0.66f);
            	setTime();
            }
            else if (world.isRunning()) {
                if(guy.isJumping()) {
                	spriteBatcher.draw(francisJump, guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
                }
                else {
                	spriteBatcher.draw(francisRunning.getKeyFrame(runTime), guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
                }
            }
            else {
            	if(world.isGameOver()) {
                    if(!guy.isAlive()) {
                    	spriteBatcher.draw(francisHit, guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
                    }
                	font.draw(spriteBatcher, gameOverString, (gameWidth * 0.5f) - gameOverString.length() * 2, gameHeight * 0.8f);
                	font.draw(spriteBatcher, tryAgainString, (gameWidth * 0.5f) - tryAgainString.length() * 2, gameHeight * 0.7f);
            	}
            }
            
            drawTime();
            spriteBatcher.end();
        	break;
        default:
        	Gdx.app.log("WARNING", "Unhandled game state of " + world.currentGameState() + " was passed in.");
        	break;
        }
	}
}
