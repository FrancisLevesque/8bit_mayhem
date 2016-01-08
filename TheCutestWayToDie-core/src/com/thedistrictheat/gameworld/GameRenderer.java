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
import com.thedistrictheat.gameobjects.Grass;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameobjects.Mountains;
import com.thedistrictheat.gameobjects.Rock;
import com.thedistrictheat.gameobjects.ScrollHandler;
import com.thedistrictheat.helpers.AssetLoader;

public class GameRenderer {
	private GameWorld world;
	private int gameWidth, gameHeight, floorHeight;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatcher;
	
	// Game Objects
	private Guy guy;
	private ScrollHandler scrollHandler;
	private Mountains frontMountains, backMountains;
	private Grass frontGrass, backGrass;
	private Rock rock1;
	
	// Game Assets
	private TextureRegion francisBlink, mountains, grass, rock;
	private Animation francisAnimation;
	private BitmapFont font;
	
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
		francisBlink = AssetLoader.francisBlink;
		francisAnimation = AssetLoader.francisAnimation;
		mountains = AssetLoader.mountains;
		grass = AssetLoader.grass;
		rock = AssetLoader.rock;
		font = AssetLoader.font;
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
        if(guy.isJumping() || !guy.isAlive()) {
        	spriteBatcher.draw(francisBlink, guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
        }
        else {
        	spriteBatcher.draw(francisAnimation.getKeyFrame(runTime), guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
        }
        
        // Draw Text
//        font.getData().setScale(0.25f);
//        font.draw(spriteBatcher, "WOO! TEXT!", 20, (gameHeight * 0.8f)); 
        spriteBatcher.end();
	}
}
