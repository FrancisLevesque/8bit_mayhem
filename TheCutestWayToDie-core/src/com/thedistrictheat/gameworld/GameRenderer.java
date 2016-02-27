package com.thedistrictheat.gameworld;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameobjects.Mountains;
import com.thedistrictheat.gameobjects.Rock;
import com.thedistrictheat.gameobjects.Tile;
import com.thedistrictheat.helpers.AssetLoader;

public class GameRenderer {
	private GameWorld world;
	private int gameWidth, gameHeight;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatcher;
	
	// Game Objects
	private Guy guy;
	private Mountains frontMountains, backMountains;
	private Rock rock1;
	
	// Game Assets
	private TextureRegion francis, francisHit, francisJump;
	private TextureRegion brandon, brandonHit, brandonJump;
	private TextureRegion stew, stewHit, stewJump;
	private TextureRegion sean, seanHit, seanJump;
	private TextureRegion bombCat, mountains, grass, rock;
	private Animation francisRunning;
	private ArrayList<Tile> tileList;
	
	public GameRenderer(GameWorld world, int gameWidth, int gameHeight) {
		this.world = world;
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		
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
		frontMountains = world.getFrontMountains();
		backMountains = world.getBackMountains();
		rock1 = world.getRock1();
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
		rock = AssetLoader.rock;
		
		tileList = AssetLoader.tileList;
	}

	private void drawMountains() {
		spriteBatcher.draw(mountains, frontMountains.getX(), frontMountains.getY(), frontMountains.getWidth(), frontMountains.getHeight());
		spriteBatcher.draw(mountains, backMountains.getX(), backMountains.getY(), backMountains.getWidth(), backMountains.getHeight());
	}
	
	private void drawTiles() {
		for(int i = 0;i < tileList.size();i++) {
			Tile tile = tileList.get(i);
	        shapeRenderer.setColor(tile.getColor());
	        shapeRenderer.rect(tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
		}
	}
	
	private void drawRocks() {
		spriteBatcher.draw(rock, rock1.getX(), rock1.getY(), rock1.getWidth(), rock1.getHeight());
	}
	
	public void render(float runTime) {
        Gdx.gl.glClearColor(0.2f, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        spriteBatcher.begin();
        drawMountains();
        spriteBatcher.end();
        shapeRenderer.begin(ShapeType.Filled);
        drawTiles();
        shapeRenderer.end();
        spriteBatcher.begin();;
//        drawRocks();
        
        if(world.isReady()) {
        	spriteBatcher.draw(francis, guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
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
                // TODO: Draw Game Over Screen
        	}
        }
        spriteBatcher.end();
        
        // Draw Collision Bounding Shapes
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(guy.getBoundingRectangle().getX(), guy.getBoundingRectangle().getY(), guy.getBoundingRectangle().getWidth(), guy.getBoundingRectangle().getHeight());
		for(int i = 0;i < tileList.size();i++) {
			Tile tile = tileList.get(i);
	        shapeRenderer.rect(tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
		}
//        shapeRenderer.circle(rock1.getBoundingCircle().x, rock1.getBoundingCircle().y, rock1.getBoundingCircle().radius);
        shapeRenderer.end();
	}
}
