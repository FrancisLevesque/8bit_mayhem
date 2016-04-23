package com.thedistrictheat.helpers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.thedistrictheat.gameobjects.TileBottom;
import com.thedistrictheat.gameobjects.TileBottomLeft;
import com.thedistrictheat.gameobjects.TileBottomRight;
import com.thedistrictheat.gameobjects.Enemy;
import com.thedistrictheat.gameobjects.EnemyFlying;
import com.thedistrictheat.gameobjects.EnemyJumping;
import com.thedistrictheat.gameobjects.EnemyWalking;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameobjects.Tile;
import com.thedistrictheat.gameobjects.TileTop;
import com.thedistrictheat.gameobjects.TileTopLeft;
import com.thedistrictheat.gameobjects.TileTopRight;
import com.thedistrictheat.gameworld.CharacterSelectWorld.CharacterType;

public class AssetLoader {
	public static final int ROCKWIDTH = 19;
	public static final int ROCKHEIGHT = 9;
	public static final int CAT_WIDTH = 13;
	public static final int CAT_HEIGHT = 20;
	
	public static Texture starsTexture, charactersTexture, textTexture, enemiesTexture, levelTexture;
	public static TextureRegion stars;
	public static TextureRegion francis, francisHit, francisRun1, francisRun2, francisRun3, francisJump;
	public static TextureRegion brandon, brandonHit, brandonRun1, brandonRun2, brandonRun3, brandonJump;
	public static TextureRegion stew, stewHit, stewRun1, stewRun2, stewRun3, stewJump;
	public static TextureRegion sean, seanHit, seanRun1, seanRun2, seanRun3, seanJump;
	public static Animation francisRunning, brandonRunning, stewRunning, seanRunning;
	public static TextureRegion playButtonUp, playButtonDown;
	public static TextureRegion selectYourCharacterText, clickToBeginText, gameOverText;
	public static TextureRegion francisText, brandonText, stewText, seanText;
	public static TextureRegion catExploding1, catExploding2, catExploding3;
	public static TextureRegion catWalking, catJumping, catFlying, rock;
	public static Animation catExploding;
	public static TextureRegion topTile, topTileRight, topTileLeft;
	public static TextureRegion bottomTile, bottomTileRight, bottomTileLeft;
	public static TextureRegion firstBackgroundLayer, secondBackgroundLayer, thirdBackgroundLayer;
	public static Preferences prefs;
	public static Guy guy = new Guy(20, 20);
	public static ArrayList<Tile> tileList = new ArrayList<Tile>();
	public static ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	
	public static void load() {
        // starsTexture
        starsTexture = new Texture("graphics/stars.png");
        starsTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        stars = new TextureRegion(starsTexture, 0, 0, 980, 540);
        
		// charactersTexture
        charactersTexture = new Texture("graphics/characters.png");
        charactersTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		francis = new TextureRegion(charactersTexture, 1, 1, 26, 33);
		francisHit = new TextureRegion(charactersTexture, 1, 35, 26, 33);
		francisRun1 = new TextureRegion(charactersTexture, 1, 69, 26, 33);
		francisRun2 = new TextureRegion(charactersTexture, 1, 103, 26, 33);
		francisRun3 = new TextureRegion(charactersTexture, 1, 137, 26, 33);
		francisJump = new TextureRegion(charactersTexture, 1, 171, 26, 33);
		
        TextureRegion[] francisSprites = { francisRun2, francisRun1, francisRun3 };
        francisRunning = new Animation(0.2f, francisSprites);
        francisRunning.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		brandon = new TextureRegion(charactersTexture, 28, 1, 26, 33);
		brandonHit = new TextureRegion(charactersTexture, 28, 35, 26, 33);
		brandonRun1 = new TextureRegion(charactersTexture, 28, 69, 26, 33);
		brandonRun2 = new TextureRegion(charactersTexture, 28, 103, 26, 33);
		brandonRun3 = new TextureRegion(charactersTexture, 28, 137, 26, 33);
		brandonJump = new TextureRegion(charactersTexture, 28, 171, 26, 33);
		
        TextureRegion[] brandonSprites = { brandonRun2, brandonRun1, brandonRun3 };
        brandonRunning = new Animation(0.2f, brandonSprites);
        brandonRunning.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		stew = new TextureRegion(charactersTexture, 55, 1, 26, 33);
		stewHit = new TextureRegion(charactersTexture, 55, 35, 26, 33);
		stewRun1 = new TextureRegion(charactersTexture, 55, 69, 26, 33);
		stewRun2 = new TextureRegion(charactersTexture, 55, 103, 26, 33);
		stewRun3 = new TextureRegion(charactersTexture, 55, 137, 26, 33);
		stewJump = new TextureRegion(charactersTexture, 55, 171, 26, 33);
		
        TextureRegion[] stewSprites = { stewRun2, stewRun1, stewRun3 };
        stewRunning = new Animation(0.2f, stewSprites);
        stewRunning.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		sean = new TextureRegion(charactersTexture, 82, 1, 26, 33);
		seanHit = new TextureRegion(charactersTexture, 82, 35, 26, 33);
		seanRun1 = new TextureRegion(charactersTexture, 82, 69, 26, 33);
		seanRun2 = new TextureRegion(charactersTexture, 82, 103, 26, 33);
		seanRun3 = new TextureRegion(charactersTexture, 82, 137, 26, 33);
		seanJump = new TextureRegion(charactersTexture, 82, 171, 26, 33);
		
        TextureRegion[] seanSprites = { seanRun2, seanRun1, seanRun3 };
        seanRunning = new Animation(0.2f, seanSprites);
        seanRunning.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		// textTexture
        textTexture = new Texture("graphics/text.png");
        textTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		playButtonUp = new TextureRegion(textTexture, 0, 0, 30, 10);
        playButtonDown = new TextureRegion(textTexture, 0, 10, 30, 10);
        selectYourCharacterText = new TextureRegion(textTexture, 0, 20, 122, 6);
        clickToBeginText = new TextureRegion(textTexture, 0, 30, 72, 6);
        gameOverText = new TextureRegion(textTexture, 0, 40, 51, 6);
        francisText = new TextureRegion(textTexture, 0, 50, 37, 6);
        brandonText = new TextureRegion(textTexture, 0, 60, 41, 6);
        stewText = new TextureRegion(textTexture, 0, 70, 23, 6);
        seanText = new TextureRegion(textTexture, 0, 80, 23, 6);
        
        // enemiesTexture
        enemiesTexture = new Texture("graphics/enemies.png");
        enemiesTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		catWalking = new TextureRegion(enemiesTexture, 0, 0, 13, 20);
		catJumping = new TextureRegion(enemiesTexture, 0, 0, 13, 20);
		catFlying = new TextureRegion(enemiesTexture, 0, 0, 13, 20);
		catExploding1 = new TextureRegion(enemiesTexture, 0, 0, 13, 20);
		catExploding1.flip(true, true);
		catExploding2 = new TextureRegion(enemiesTexture, 0, 0, 13, 20);
		catExploding3 = new TextureRegion(enemiesTexture, 0, 0, 13, 20);
		catExploding3.flip(true, true);
		rock = new TextureRegion(enemiesTexture, 20, 0, ROCKWIDTH, ROCKHEIGHT);

        TextureRegion[] explodingSprites = { catExploding1, catExploding2, catExploding3 };
        catExploding = new Animation(2.0f, explodingSprites);
        catExploding.setPlayMode(Animation.PlayMode.NORMAL);

		// Preferences File
        prefs = Gdx.app.getPreferences("TheCutestWayToDie");
        if(!prefs.contains("highScore")) {
        	prefs.putInteger("highScore", 0);
        }
	}
	
	public static void loadLevel(CharacterType type) {
		String graphicsFile;
		String textFile;
		guy.setSprites(type);
		switch(type) {
		case FRANCIS:
			graphicsFile = "graphics/level_forest.png";
			textFile     = "levels/level_forest.txt";
			break;
		case BRANDON:
			graphicsFile = "graphics/level_tundra.png";
			textFile     = "levels/level_tundra.txt";
			break;
		case STEW:
			graphicsFile = "graphics/level_volcano.png";
			textFile     = "levels/level_volcano.txt";
			break;
		case SEAN:
			graphicsFile = "graphics/level_city.png";
			textFile     = "levels/level_city.txt";
			break;
		default:
			Gdx.app.log("AssetLoader", "ERROR: Unsupported type: " + type + " passed in; defaulting to Forest level...");
			graphicsFile = "graphics/level_forest.png";
			textFile     = "levels/level_forest.txt";
			break;
		}

		levelTexture = new Texture(graphicsFile);
		levelTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		topTile               = new TextureRegion(levelTexture, 0, 0, 10, 10);
		topTileRight          = new TextureRegion(levelTexture, 10, 0, 10, 10);
		topTileLeft           = new TextureRegion(levelTexture, 20, 0, 10, 10);
		bottomTile            = new TextureRegion(levelTexture, 0, 10, 10, 10);
		bottomTileRight       = new TextureRegion(levelTexture, 10, 10, 10, 10);
		bottomTileLeft        = new TextureRegion(levelTexture, 20, 10, 10, 10);
		firstBackgroundLayer  = new TextureRegion(levelTexture, 0, 20, 130, 10);
		secondBackgroundLayer = new TextureRegion(levelTexture, 0, 30, 130, 20);
		thirdBackgroundLayer  = new TextureRegion(levelTexture, 0, 50, 210, 60);
		
        // Creating level
        ArrayList<String> levelLines = new ArrayList<String>();
        FileHandle levelFile = Gdx.files.internal(textFile);
        String levelText = levelFile.readString();        
        String[] allLines = levelText.split("\n");
        
        for(int i = 0;i < allLines.length;i++) {
        	if (!allLines[i].startsWith("#")) {
        		levelLines.add(allLines[i]);
        	}
        }
        
        int levelHeight = levelLines.size();
        
//        LEGEND
//        G: Selected Character
//        W: Walking Cats
//        J: Jumping Cats
//        F: Flying Cats
//        -: Top Tile
//        {: TopTileLeft
//        }: TopTileRight
//        =: BottomTile
//        [: BottomTileLeft
//        ]: BottomTileRight

		Gdx.app.log("AssetLoader", "Creating lists.");
		boolean guyInLevel = false;
        tileList.clear();
        enemyList.clear();
        for(int j = 0;j < levelHeight;j++){
    		String line = (String) levelLines.get(levelHeight - 1 - j);
        	for(int i = 0;i < line.length();i++){
        		switch(line.charAt(i)){
    			case 'G':
    				Gdx.app.log("AssetLoader", "Initiallizing Character at (" + i*10 + ", " + j*10 + ").");
    				guy.restart(i * 10, j * 10);
    				guyInLevel = true;
    				break;
    			case 'W':
    				enemyList.add(new EnemyWalking(i * 10, j * 10, CAT_WIDTH, CAT_HEIGHT));
    				break;
    			case 'J':
    				enemyList.add(new EnemyJumping(i * 10, j * 10, CAT_WIDTH, CAT_HEIGHT));
    				break;
    			case 'F':
    				enemyList.add(new EnemyFlying(i * 10, j * 10, CAT_WIDTH, CAT_HEIGHT));
    				break;
    			case '-':
    				tileList.add(new TileTop(i, j));
    				break;
    			case '}':
    				tileList.add(new TileTopRight(i, j));
    				break;
    			case '{':
    				tileList.add(new TileTopLeft(i, j));
    				break;
    			case '=':
    				tileList.add(new TileBottom(i, j));
    				break;
    			case ']':
    				tileList.add(new TileBottomRight(i, j));
    				break;
    			case '[':
    				tileList.add(new TileBottomLeft(i, j));
    				break;
    			default:
    				Gdx.app.log("AssetLoader", "Character " + line.charAt(i) + " is not currently supported.");
    				break;
        		}
        	}
        }
    	if (guyInLevel == false) { 
    		Gdx.app.log("AssetLoader", "No character was specified in the level file; Defaulting to (10, 20).");
			guy.restart(10, 20);
    	}
	}
	
	public static void setHighScore(int score) {
    	prefs.putInteger("highScore", score);
    	prefs.flush();
	}
	
	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}
	
	public static void dispose() {
		starsTexture.dispose();
		charactersTexture.dispose();
		textTexture.dispose();
		enemiesTexture.dispose();
		levelTexture.dispose();
	}
}
