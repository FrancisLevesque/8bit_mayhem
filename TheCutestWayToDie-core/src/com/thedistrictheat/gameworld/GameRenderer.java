package com.thedistrictheat.gameworld;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameobjects.FirstBackgroundLayer;
import com.thedistrictheat.gameobjects.Rock;
import com.thedistrictheat.gameobjects.SecondBackgroundLayer;
import com.thedistrictheat.gameobjects.ThirdBackgroundLayer;
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
    private FirstBackgroundLayer frontFirstLayer, backFirstLayer;
    private SecondBackgroundLayer frontSecondLayer, backSecondLayer;
    private ThirdBackgroundLayer frontThirdLayer, backThirdLayer;
	private Rock rock1;
	
	// Game Assets
	private TextureRegion bombCat, rock;
	private TextureRegion clickToBeginText, gameOverText;
	private TextureRegion topTile, topTileRight, topTileLeft;
	private TextureRegion bottomTile, bottomTileRight, bottomTileLeft;
	private TextureRegion firstBackgroundLayer, secondBackgroundLayer, thirdBackgroundLayer;
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
	
	public void refreshGameAssets() {
		topTile = AssetLoader.topTile;
		topTileRight = AssetLoader.topTileRight;
		topTileLeft = AssetLoader.topTileLeft;
		bottomTile = AssetLoader.bottomTile; 
		bottomTileRight = AssetLoader.bottomTileRight; 
		bottomTileLeft = AssetLoader.bottomTileLeft;
		firstBackgroundLayer = AssetLoader.firstBackgroundLayer; 
		secondBackgroundLayer = AssetLoader.secondBackgroundLayer;
		thirdBackgroundLayer = AssetLoader.thirdBackgroundLayer;
	}
	
	private void initGameObjects() {
		guy = world.getGuy();
		frontFirstLayer = world.getFrontFirstLayer();
		backFirstLayer = world.getBackFirstLayer();
		frontSecondLayer = world.getFrontSecondLayer();
		backSecondLayer = world.getBackSecondLayer();
		frontThirdLayer = world.getFrontThirdLayer();
		backThirdLayer = world.getBackThirdLayer();
		rock1 = world.getRock1();
	}
	
	private void initGameAssets() {
		clickToBeginText = AssetLoader.clickToBeginText;
		gameOverText = AssetLoader.gameOverText;
		bombCat = AssetLoader.bombCat;
		rock = AssetLoader.rock;
		tileList = AssetLoader.tileList;
	}

	private void drawBackground() {
		spriteBatcher.draw(thirdBackgroundLayer, frontThirdLayer.getX(), frontThirdLayer.getY(), frontThirdLayer.getWidth(), frontThirdLayer.getHeight());
		spriteBatcher.draw(thirdBackgroundLayer, backThirdLayer.getX(), backThirdLayer.getY(), backThirdLayer.getWidth(), backThirdLayer.getHeight());
		spriteBatcher.draw(secondBackgroundLayer, frontSecondLayer.getX(), frontSecondLayer.getY(), frontSecondLayer.getWidth(), frontSecondLayer.getHeight());
		spriteBatcher.draw(secondBackgroundLayer, backSecondLayer.getX(), backSecondLayer.getY(), backSecondLayer.getWidth(), backSecondLayer.getHeight());
		spriteBatcher.draw(firstBackgroundLayer, frontFirstLayer.getX(), frontFirstLayer.getY(), frontFirstLayer.getWidth(), frontFirstLayer.getHeight());
		spriteBatcher.draw(firstBackgroundLayer, backFirstLayer.getX(), backFirstLayer.getY(), backFirstLayer.getWidth(), backFirstLayer.getHeight());
	}
	
	private void drawTiles() {
		for(int i = 0;i < tileList.size();i++) {
			Tile tile = tileList.get(i);
	        TextureRegion texture;
			switch (tile.tileType()) {
			case TOP_TILE:
				texture = topTile;
				break;
			case TOP_TILE_RIGHT:
				texture = topTileRight;
				break;
			case TOP_TILE_LEFT:
				texture = topTileLeft;
				break;
			case BOTTOM_TILE:
				texture = bottomTile;
				break;
			case BOTTOM_TILE_RIGHT:
				texture = bottomTileRight;
				break;
			case BOTTOM_TILE_LEFT:
				texture = bottomTileLeft;
				break;
			default:
				Gdx.app.log("GameRenderer", "ERROR: Unsupported type: " + tile.tileType() + " passed in; defaulting to topTile...");
				texture = topTile;
				break;
			}
	        spriteBatcher.draw(texture, tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
		}
	}
	
	private void drawRocks() {
		spriteBatcher.draw(rock, rock1.getX(), rock1.getY(), rock1.getWidth(), rock1.getHeight());
	}
	
	public void render(float runTime) {
        Gdx.gl.glClearColor(0.2f, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        spriteBatcher.begin();
        drawBackground();
        drawTiles();
        
        if(world.isReady()) {
        	spriteBatcher.draw(clickToBeginText, (gameWidth/2)-(clickToBeginText.getRegionWidth()/2), gameHeight*0.9f, clickToBeginText.getRegionWidth(), clickToBeginText.getRegionHeight());
        	spriteBatcher.draw(guy.standingSprite(), guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
        }
        else if (world.isRunning()) {
            if(guy.isJumping()) {
            	spriteBatcher.draw(guy.jumpingSprite(), guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
            }
            else {
            	spriteBatcher.draw(guy.runningAnimation().getKeyFrame(runTime), guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
            }
        }
        else {
        	if(world.isGameOver()) {
                if(!guy.isAlive()) {
                	spriteBatcher.draw(guy.hitSprite(), guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
                }
                spriteBatcher.draw(gameOverText, (gameWidth/2)-(gameOverText.getRegionWidth()/2), gameHeight*0.9f, gameOverText.getRegionWidth(), gameOverText.getRegionHeight());
        	}
        }
        spriteBatcher.end();
        
        // Draw Collision Bounding Shapes
//        shapeRenderer.begin(ShapeType.Line);
//        shapeRenderer.setColor(Color.RED);
//        if(world.getGuy().isJumping()) {
//        	shapeRenderer.rect(guy.getJumpingFootBox().getX(), guy.getJumpingFootBox().getY(), guy.getJumpingFootBox().getWidth(), guy.getJumpingFootBox().getHeight());
////        	shapeRenderer.rect(guy.getJumpingFrontBox().getX(), guy.getJumpingFrontBox().getY(), guy.getJumpingFrontBox().getWidth(), guy.getJumpingFrontBox().getHeight());
//        } else {
//            shapeRenderer.rect(guy.getRunningFootBox().getX(), guy.getRunningFootBox().getY(), guy.getRunningFootBox().getWidth(), guy.getRunningFootBox().getHeight());
////            shapeRenderer.rect(guy.getRunningFrontBox().getX(), guy.getRunningFrontBox().getY(), guy.getRunningFrontBox().getWidth(), guy.getRunningFrontBox().getHeight());
//        }
//		for(int i = 0;i < tileList.size();i++) {
//			Tile tile = tileList.get(i);
//	        shapeRenderer.rect(tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
//		}
////        shapeRenderer.circle(rock1.getBoundingCircle().x, rock1.getBoundingCircle().y, rock1.getBoundingCircle().radius);
//        shapeRenderer.end();
	}
}
