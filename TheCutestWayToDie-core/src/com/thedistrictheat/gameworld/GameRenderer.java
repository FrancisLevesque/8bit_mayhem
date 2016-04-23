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
import com.thedistrictheat.gameobjects.Enemy;
import com.thedistrictheat.gameobjects.FirstBackgroundLayer;
import com.thedistrictheat.gameobjects.Guy;
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
	private float explodingTime;
	
	// Game Objects
	private Guy guy;
    private FirstBackgroundLayer frontFirstLayer, backFirstLayer;
    private SecondBackgroundLayer frontSecondLayer, backSecondLayer;
    private ThirdBackgroundLayer frontThirdLayer, backThirdLayer;
	
	// Game Assets
	private TextureRegion catWalking, catJumping, catFlying;
	private TextureRegion clickToBeginText, gameOverText;
	private TextureRegion topTile, topTileRight, topTileLeft;
	private TextureRegion bottomTile, bottomTileRight, bottomTileLeft;
	private TextureRegion firstBackgroundLayer, secondBackgroundLayer, thirdBackgroundLayer;
	private ArrayList<Tile> tileList;
	private ArrayList<Enemy> enemyList;
	
	public GameRenderer(GameWorld world, int gameWidth, int gameHeight) {
		this.world = world;
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		explodingTime = 0.0f;
		
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
		catWalking = AssetLoader.catWalking;
		catJumping = AssetLoader.catJumping;
		catFlying = AssetLoader.catFlying;
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
	
	private void resetExplodingTime() {
		if (explodingTime != 0.0f) {
			explodingTime = 0.0f;
		}
	}
	
	private void initGameObjects() {
		guy = world.getGuy();
		frontFirstLayer = world.getFrontFirstLayer();
		backFirstLayer = world.getBackFirstLayer();
		frontSecondLayer = world.getFrontSecondLayer();
		backSecondLayer = world.getBackSecondLayer();
		frontThirdLayer = world.getFrontThirdLayer();
		backThirdLayer = world.getBackThirdLayer();
	}
	
	private void initGameAssets() {
		clickToBeginText = AssetLoader.clickToBeginText;
		gameOverText = AssetLoader.gameOverText;
		enemyList = AssetLoader.enemyList;
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
	
	private void drawEnemies(float runTime) {
		for(int i = 0;i < enemyList.size();i++) {
			Enemy enemy = enemyList.get(i);
			if(enemy.isExploding()) {
				if (explodingTime < 20.0f) {
					spriteBatcher.draw(AssetLoader.catExploding1, enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
					explodingTime += runTime;
				} else if (explodingTime < 40.0f) {
					spriteBatcher.draw(AssetLoader.catExploding2, enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
					explodingTime += runTime;
				} else {
					spriteBatcher.draw(AssetLoader.catExploding3, enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
				}
			} else {
		        TextureRegion texture;
				switch (enemy.getEnemyType()) {
				case WALKING:
					texture = catWalking;
					break;
				case JUMPING:
					texture = catJumping;
					break;
				case FLYING:
					texture = catFlying;
					break;
				default:
					Gdx.app.log("GameRenderer", "Unssuported EnemyType passed in: " + enemy.getEnemyType() + "\nDefaulting to 'WALKING' type.");
					texture = catWalking;
					break;
				}
		        spriteBatcher.draw(texture, enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
	        }
		}
	}
	
	private void drawTiles() {
		for(int i = 0;i < tileList.size();i++) {
			Tile tile = tileList.get(i);
	        TextureRegion texture;
			switch (tile.tileType()) {
			case TILE_TOP:
				texture = topTile;
				break;
			case TILE_TOP_RIGHT:
				texture = topTileRight;
				break;
			case TILE_TOP_LEFT:
				texture = topTileLeft;
				break;
			case TILE_BOTTOM:
				texture = bottomTile;
				break;
			case TILE_BOTTOM_RIGHT:
				texture = bottomTileRight;
				break;
			case TILE_BOTTOM_LEFT:
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
	
	public void render(float runTime) {
        Gdx.gl.glClearColor(0.2f, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        spriteBatcher.begin();
        drawBackground();
        drawEnemies(runTime);
        drawTiles();
        
        if(world.isReady()) {
        	resetExplodingTime();
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
        
//      Draw Collision Bounding Shapes
//        shapeRenderer.begin(ShapeType.Line);
//        shapeRenderer.setColor(Color.RED);
//
//    	shapeRenderer.rect(guy.getHitBox().getX(), guy.getHitBox().getY(), guy.getHitBox().getWidth(), guy.getHitBox().getHeight());
//		for(int i = 0;i < enemyList.size();i++) {
//			Enemy enemy = enemyList.get(i);
//	        shapeRenderer.rect(enemy.getHitBox().x, enemy.getHitBox().y, enemy.getHitBox().width, enemy.getHitBox().height);
//		}
//		for(int i = 0;i < tileList.size();i++) {
//			Tile tile = tileList.get(i);
//	        shapeRenderer.rect(tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
//		}
//        shapeRenderer.end();
	}
}
