package com.thedistrictheat.gameworld;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.thedistrictheat.gameobjects.Billboard;
import com.thedistrictheat.gameobjects.Enemy;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameobjects.Scrollable;
import com.thedistrictheat.gameobjects.Tile;
import com.thedistrictheat.helpers.AssetLoader;
import com.thedistrictheat.helpers.GameInputHandler;
import com.thedistrictheat.ui.SimpleButton;

public class GameRenderer {
	private GameWorld world;
	private int gameWidth, gameHeight;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatcher;
	private float explodingTime;
	
	// Game Objects
	private Guy guy;
    private Scrollable firstLayer;
    private Scrollable secondLayer;
    private Scrollable thirdLayer;
	
	// Game Assets
	private TextureRegion catWalking, catJumping, catFlying;
	private TextureRegion topTile, topTileRight, topTileLeft;
	private TextureRegion bottomTile, bottomTileRight, bottomTileLeft;
	private TextureRegion firstBackgroundLayer, secondBackgroundLayer, thirdBackgroundLayer;
	private TextureRegion flag_fixed;
	private Animation flag;
//	private TextureRegion clickToBeginText, gameOverText, youWinText;
	private TextureRegion clickToBeginPlate, youWinPlate, gameOverPlate;//, watchOutForKittensPlate, tapTheScreenToJumpPlate;
    private SimpleButton backButton;
	private ArrayList<Tile> tileList;
	private ArrayList<Billboard> boardList;
	private ArrayList<Enemy> enemyList;
//	private float beginTextHeight, beginTextWidth, gameOverTextHeight, gameOverTextWidth, winTextHeight, winTextWidth;
	private float beginPlateHeight, beginPlateWidth, gameOverPlateHeight, gameOverPlateWidth, winPlateHeight, winPlateWidth;//, watchPlateHeight, watchPlateWidth, tapPlateHeight, tapPlateWidth;
	
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
		flag_fixed = AssetLoader.flag1;
		flag = AssetLoader.flag;
	}
	
	private void resetExplodingTime() {
		if (explodingTime != 0.0f) {
			explodingTime = 0.0f;
		}
	}
	
	private void initGameObjects() {
		guy = world.getGuy();
		firstLayer = world.getFirstLayer();
		secondLayer = world.getSecondLayer();
		thirdLayer = world.getThirdLayer();
		backButton = ((GameInputHandler)(Gdx.input.getInputProcessor())).getBackButton();
	}
	
	private void initGameAssets() {
		enemyList = AssetLoader.enemyList;
		boardList = AssetLoader.boardList;
		tileList = AssetLoader.tileList;
//		clickToBeginText = AssetLoader.clickToBeginText;
//		gameOverText = AssetLoader.gameOverText;
//		youWinText = AssetLoader.youWinText;
		clickToBeginPlate = AssetLoader.clickToBeginPlate;
		gameOverPlate = AssetLoader.gameOverPlate;
		youWinPlate = AssetLoader.youWinPlate;
//		watchOutForKittensPlate = AssetLoader.watchOutForKittensPlate;
//		tapTheScreenToJumpPlate = AssetLoader.tapTheScreenToJumpPlate;
	}

	private void drawBackground() {
		spriteBatcher.draw(thirdBackgroundLayer, thirdLayer.getX(), thirdLayer.getY(), thirdLayer.getWidth(), thirdLayer.getHeight());
		spriteBatcher.draw(thirdBackgroundLayer, thirdLayer.getX() + thirdLayer.getWidth(), thirdLayer.getY(), thirdLayer.getWidth(), thirdLayer.getHeight());
		spriteBatcher.draw(secondBackgroundLayer, secondLayer.getX(), secondLayer.getY(), secondLayer.getWidth(), secondLayer.getHeight());
		spriteBatcher.draw(secondBackgroundLayer, secondLayer.getX() + secondLayer.getWidth(), secondLayer.getY(), secondLayer.getWidth(), secondLayer.getHeight());
		spriteBatcher.draw(firstBackgroundLayer, firstLayer.getX(), firstLayer.getY(), firstLayer.getWidth(), firstLayer.getHeight());
		spriteBatcher.draw(firstBackgroundLayer, firstLayer.getX() + firstLayer.getWidth(), firstLayer.getY(), firstLayer.getWidth(), firstLayer.getHeight());
	}
	
	private void drawEnemies(float runTime) {
		for(int i = 0;i < enemyList.size();i++) {
			Enemy enemy = enemyList.get(i);
	        TextureRegion texture;
			switch (enemy.getEnemyType()) {
			case WALKING:			
				if(enemy.isExploding()) {
					if (explodingTime < 20.0f) {
						texture = AssetLoader.catWalkingExploding1;
						explodingTime += runTime;
					} else if (explodingTime < 40.0f) {
						texture = AssetLoader.catWalkingExploding2;
						explodingTime += runTime;
					} else {
						texture = AssetLoader.catWalkingExploding3;
					}
				} else {
					texture = catWalking;
				}
				break;
			case JUMPING:
				if(enemy.isExploding()) {
					if (explodingTime < 20.0f) {
						texture = AssetLoader.catJumpingExploding1;
						explodingTime += runTime;
					} else if (explodingTime < 40.0f) {
						texture = AssetLoader.catJumpingExploding2;
						explodingTime += runTime;
					} else {
						texture = AssetLoader.catJumpingExploding3;
					}
				} else {
					texture = catJumping;
				}
				break;
			case FLYING:
				if(enemy.isExploding()) {
					if (explodingTime < 20.0f) {
						texture = AssetLoader.catFlyingExploding1;
						explodingTime += runTime;
					} else if (explodingTime < 40.0f) {
						texture = AssetLoader.catFlyingExploding2;
						explodingTime += runTime;
					} else {
						texture = AssetLoader.catFlyingExploding3;
					}
				} else {
					texture = catFlying;
				}
				break;
			default:
				Gdx.app.log("GameRenderer", "Unssuported EnemyType passed in: " + enemy.getEnemyType() + "\nDefaulting to 'WALKING' type.");
				texture = catWalking;
				break;
			}
	        spriteBatcher.draw(texture, enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
		}
	}
	
	private void drawTiles(float runTime) {
		for(int i = 0;i < tileList.size();i++) {
			Tile tile = tileList.get(i);
	        TextureRegion texture;
			switch (tile.tileType()) {
			case TALK_TILE:
				texture = topTile;
				break;
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
			case TILE_FLAG:
				if (world.isRunning()) {
					texture = flag.getKeyFrame(runTime);
					break;	
				} else {
					texture = flag_fixed;
					break;
				}
			default:
				Gdx.app.log("GameRenderer", "ERROR: Unsupported type: " + tile.tileType() + " passed in; defaulting to topTile...");
				texture = topTile;
				break;
			}
	        spriteBatcher.draw(texture, tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
		}
	}
	
	private void drawBoards(float runTime) {
		for(int i = 0;i < boardList.size();i++) {
			Billboard board = boardList.get(i);
        	spriteBatcher.draw(board.getBoard(), board.getX(), board.getY(), board.getWidth(), board.getHeight());
		}
	}
			
	
	public void render(float runTime) {
        Gdx.gl.glClearColor(0.2f, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(guy.getBackgroundColour());
        shapeRenderer.rect(0, 0, gameWidth, gameHeight);
        shapeRenderer.end();
        
        spriteBatcher.begin();
        drawBackground();
        drawEnemies(runTime);
        drawTiles(runTime);
        
//    	beginTextHeight = clickToBeginText.getRegionHeight()/4;
//    	beginTextWidth = clickToBeginText.getRegionWidth()/4;
//    	gameOverTextHeight = gameOverText.getRegionHeight()/4;
//    	gameOverTextWidth = gameOverText.getRegionWidth()/4;
//    	winTextHeight = youWinText.getRegionHeight()/4;
//    	winTextWidth = youWinText.getRegionWidth()/4;
    	beginPlateHeight = clickToBeginPlate.getRegionHeight()/2;
    	beginPlateWidth = clickToBeginPlate.getRegionWidth()/2;
    	gameOverPlateHeight = gameOverPlate.getRegionHeight()/2;
    	gameOverPlateWidth = gameOverPlate.getRegionWidth()/2;
    	winPlateHeight = youWinPlate.getRegionHeight()/2;
    	winPlateWidth = youWinPlate.getRegionWidth()/2;
//    	watchPlateHeight = watchOutForKittensPlate.getRegionHeight()/2;
//    	watchPlateWidth = watchOutForKittensPlate.getRegionWidth()/2;
//    	tapPlateHeight = tapTheScreenToJumpPlate.getRegionHeight()/2;
//    	tapPlateWidth = tapTheScreenToJumpPlate.getRegionWidth()/2;
        
        if(world.isReady()) {
        	resetExplodingTime();
//        	spriteBatcher.draw(clickToBeginText, (gameWidth/2)-(beginTextWidth/2), gameHeight*0.9f, beginTextWidth, beginTextHeight);
        	spriteBatcher.draw(clickToBeginPlate, (gameWidth/2)-(beginPlateWidth/2), gameHeight*0.58f, beginPlateWidth, beginPlateHeight);
        	spriteBatcher.draw(guy.standingSprite(), guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
        }
        else if (world.isRunning()) {
            drawBoards(runTime);
            if(guy.isJumping()) {
            	spriteBatcher.draw(guy.jumpingSprite(), guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
            }
            else {
            	spriteBatcher.draw(guy.runningAnimation().getKeyFrame(runTime), guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
            }
        }
        else if(world.isPaused()) {
            drawBoards(runTime);
        	spriteBatcher.draw(guy.standingSprite(), guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
        	if(world.showFirstTip()) {
//                spriteBatcher.draw(tapTheScreenToJumpPlate, (gameWidth/2)-(tapPlateWidth/2), gameHeight*0.58f, tapPlateWidth, tapPlateHeight);
        	}
        	else {
//                spriteBatcher.draw(watchOutForKittensPlate, (gameWidth/2)-(watchPlateWidth/2), gameHeight*0.58f, watchPlateWidth, watchPlateHeight);
        	}
        }
        else if(world.isGameOver()) {
           	spriteBatcher.draw(guy.hitSprite(), guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
//            spriteBatcher.draw(gameOverText, (gameWidth/2)-(gameOverTextWidth/2), gameHeight*0.9f, gameOverTextWidth, gameOverTextHeight);
//        	spriteBatcher.draw(clickToBeginText, (gameWidth/2)-(beginTextWidth/2), gameHeight*0.8f, beginTextWidth, beginTextHeight);
            spriteBatcher.draw(gameOverPlate, (gameWidth/2)-(gameOverPlateWidth/2), gameHeight*0.58f, gameOverPlateWidth, gameOverPlateHeight);
        	spriteBatcher.draw(clickToBeginPlate, (gameWidth/2)-(beginPlateWidth/2), gameHeight*0.16f, beginPlateWidth, beginPlateHeight);
        } 
        else if (world.isWinner()) {
           	spriteBatcher.draw(guy.jumpingSprite(), guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
//        	spriteBatcher.draw(youWinText, (gameWidth/2)-(winTextWidth/2), gameHeight*0.9f, winTextWidth, winTextHeight);
//        	spriteBatcher.draw(clickToBeginText, (gameWidth/2)-(beginTextWidth/2), gameHeight*0.8f, beginTextWidth, beginTextHeight);
        	spriteBatcher.draw(youWinPlate, (gameWidth/2)-(winPlateWidth/2), gameHeight*0.58f, winPlateWidth, winPlateHeight);
        	spriteBatcher.draw(clickToBeginPlate, (gameWidth/2)-(beginPlateWidth/2), gameHeight*0.16f, beginPlateWidth, beginPlateHeight);
        }
        
        backButton.draw(spriteBatcher);
        
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
