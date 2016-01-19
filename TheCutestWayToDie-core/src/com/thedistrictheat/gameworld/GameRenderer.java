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

public class GameRenderer {
	private GameWorld world;
	private int gameWidth, gameHeight, floorHeight;
	private long startTime = 0;
	private long currentTime = 0;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatcher;
	
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
	private TextureRegion francis, francisBlink, mountains, grass, rock;
	private Animation francisAnimation;
	private BitmapFont font, timeFont;
	
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
	}
	
	private void initGameAssets() {
		francis = AssetLoader.francis;
		francisBlink = AssetLoader.francisBlink;
		francisAnimation = AssetLoader.francisAnimation;
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
        
        shapeRenderer.begin(ShapeType.Filled);
        // Draw Background color
        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.rect(0, floorHeight, gameWidth, gameHeight - floorHeight);
        // Draw Dirt
        shapeRenderer.setColor(Color.BROWN);
        shapeRenderer.rect(0, 0, gameWidth, (int)(floorHeight * 0.6));
        // Draw Collision Bounding Shapes
//        shapeRenderer.setColor(Color.RED);
//        shapeRenderer.rect(guy.getBoundingRectangle().getX(), guy.getBoundingRectangle().getY(), guy.getBoundingRectangle().getWidth(), guy.getBoundingRectangle().getHeight());
//        shapeRenderer.circle(rock1.getBoundingCircle().x, rock1.getBoundingCircle().y, rock1.getBoundingCircle().radius);
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
            	spriteBatcher.draw(francisBlink, guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
            }
            else {
            	spriteBatcher.draw(francisAnimation.getKeyFrame(runTime), guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
            }
        }
        else {
        	if(world.isGameOver()) {
                if(!guy.isAlive()) {
                	spriteBatcher.draw(francisBlink, guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
                }
            	font.draw(spriteBatcher, gameOverString, (gameWidth * 0.5f) - gameOverString.length() * 2, gameHeight * 0.8f);
            	font.draw(spriteBatcher, tryAgainString, (gameWidth * 0.5f) - tryAgainString.length() * 2, gameHeight * 0.7f);
        	}
        }

        drawTime();
        spriteBatcher.end();
	}
}
