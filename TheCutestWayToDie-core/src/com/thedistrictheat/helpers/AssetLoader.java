package com.thedistrictheat.helpers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.thedistrictheat.gameobjects.Enemy;
import com.thedistrictheat.gameobjects.EnemyFlying;
import com.thedistrictheat.gameobjects.EnemyJumping;
import com.thedistrictheat.gameobjects.EnemyWalking;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.gameobjects.TalkTile;
import com.thedistrictheat.gameobjects.Tile;
import com.thedistrictheat.gameobjects.TileBottom;
import com.thedistrictheat.gameobjects.TileBottomLeft;
import com.thedistrictheat.gameobjects.TileBottomRight;
import com.thedistrictheat.gameobjects.TileFlag;
import com.thedistrictheat.gameobjects.TileTop;
import com.thedistrictheat.gameobjects.TileTopLeft;
import com.thedistrictheat.gameobjects.TileTopRight;
import com.thedistrictheat.gameworld.CharacterSelectWorld.CharacterType;

public class AssetLoader {
	public static final int CAT_WIDTH = 10;
	public static final int CAT_HEIGHT = 10;
	public static final int CAT_FLYING_WIDTH = 12;
	
	public static Texture starsTexture, charactersTexture, textTexture, textPlatesTexture, buttonTexture, enemiesTexture, levelTexture;
	public static TextureRegion stars;
	public static TextureRegion francis, francisHit, francisRun1, francisRun2, francisRun3, francisJump;
	public static TextureRegion brandon, brandonHit, brandonRun1, brandonRun2, brandonRun3, brandonJump;
	public static TextureRegion stew, stewHit, stewRun1, stewRun2, stewRun3, stewJump;
	public static TextureRegion sean, seanHit, seanRun1, seanRun2, seanRun3, seanJump;
	public static Animation francisRunning, brandonRunning, stewRunning, seanRunning;
	public static TextureRegion playButtonUp, playButtonDown, playButtonDisabled, backButton, downloadButtonUp, downloadButtonDown;
	public static TextureRegion musicButtonOn, musicButtonOff, soundButtonOn, soundButtonOff;
	public static TextureRegion selectYourCharacterText, clickToBeginText, gameOverText, youWinText;
	public static TextureRegion clickToBeginPlate, youWinPlate, gameOverPlate, selectYourCharacterPlate, watchOutForKittensPlate, tapTheScreenToJumpPlate;
	public static TextureRegion francisText, brandonText, stewText, seanText;
	public static TextureRegion catWalking, catWalkingExploding1, catWalkingExploding2, catWalkingExploding3;
	public static TextureRegion catJumping, catJumpingExploding1, catJumpingExploding2, catJumpingExploding3;
	public static TextureRegion catFlying, catFlyingExploding1, catFlyingExploding2, catFlyingExploding3;
	public static TextureRegion topTile, topTileRight, topTileLeft;
	public static TextureRegion bottomTile, bottomTileRight, bottomTileLeft;
	public static TextureRegion star1, star2, star3, star4;
	public static Animation star;
	public static TextureRegion flag1, flag2, flag3;
	public static Animation flag;
	public static TextureRegion firstBackgroundLayer, secondBackgroundLayer, thirdBackgroundLayer;
	public static Sound jump, click, win, explosion1, explosion2, explosion3;
	public static Music intro, thecutestwaytodie;
	
	public static Preferences prefs;
	public static Guy guy = new Guy(20, 20);
	public static ArrayList<Tile> tileList = new ArrayList<Tile>();
	public static ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	public static ArrayList<Sound> explosions = new ArrayList<Sound>();
	
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
        selectYourCharacterText = new TextureRegion(textTexture, 0, 0, 490, 26);
        clickToBeginText = new TextureRegion(textTexture, 0, 40, 286, 26);
        gameOverText = new TextureRegion(textTexture, 0, 80, 206, 26);
        francisText = new TextureRegion(textTexture, 0, 120, 150, 26);
        brandonText = new TextureRegion(textTexture, 0, 160, 166, 26);
        stewText = new TextureRegion(textTexture, 0, 200, 94, 26);
        seanText = new TextureRegion(textTexture, 0, 240, 94, 26);
        youWinText = new TextureRegion(textTexture, 0, 280, 150, 26);
		
		// textPlatesTexture
        textPlatesTexture = new Texture("graphics/text_plates.png");
        textPlatesTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        clickToBeginPlate = new TextureRegion(textPlatesTexture, 0, 0, 120, 80);
        youWinPlate = new TextureRegion(textPlatesTexture, 0, 80, 120, 80);
        gameOverPlate = new TextureRegion(textPlatesTexture, 0, 160, 120, 80);
        selectYourCharacterPlate = new TextureRegion(textPlatesTexture, 0, 240, 160, 80);
        watchOutForKittensPlate = new TextureRegion(textPlatesTexture, 0, 320, 160, 80);
        tapTheScreenToJumpPlate = new TextureRegion(textPlatesTexture, 0, 400, 200, 80);
        
        // buttonTexture
        buttonTexture = new Texture("graphics/buttons.png");
        buttonTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		playButtonDown = new TextureRegion(buttonTexture, 0, 80, 272, 40);
        playButtonUp = new TextureRegion(buttonTexture, 0, 120, 272, 40);
        playButtonDisabled = new TextureRegion(buttonTexture, 0, 160, 272, 40);
        downloadButtonDown = new TextureRegion(buttonTexture, 0, 200, 272, 40);
        downloadButtonUp = new TextureRegion(buttonTexture, 0, 240, 272, 40);
        backButton = new TextureRegion(buttonTexture, 0, 280, 80, 80);
        musicButtonOn = new TextureRegion(buttonTexture, 0, 360, 80, 80);
        musicButtonOff = new TextureRegion(buttonTexture, 0, 440, 80, 80);
        soundButtonOn = new TextureRegion(buttonTexture, 0, 520, 80, 80);
        soundButtonOff = new TextureRegion(buttonTexture, 0, 600, 80, 80);
        star1 = new TextureRegion(buttonTexture, 0, 680, 80, 80);
		star2 = new TextureRegion(buttonTexture, 0, 760, 80, 80);
		star3 = new TextureRegion(buttonTexture, 0, 840, 80, 80);
		star4 = new TextureRegion(buttonTexture, 0, 920, 80, 80);
        TextureRegion[] stars = { star1, star2, star3, star4 };
        star = new Animation(0.15f, stars);
        star.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        
        // enemiesTexture
        enemiesTexture = new Texture("graphics/enemies.png");
        enemiesTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		catWalking = new TextureRegion(enemiesTexture, 0, 0, 14, 20);
		catWalkingExploding1 = new TextureRegion(enemiesTexture, 0, 20, 14, 20);
		catWalkingExploding2 = new TextureRegion(enemiesTexture, 0, 40, 14, 20);
		catWalkingExploding3 = new TextureRegion(enemiesTexture, 0, 60, 14, 20);
		catJumping = new TextureRegion(enemiesTexture, 20, 0, 14, 20);
		catJumpingExploding1 = new TextureRegion(enemiesTexture, 20, 20, 14, 20);
		catJumpingExploding2 = new TextureRegion(enemiesTexture, 20, 40, 14, 20);
		catJumpingExploding3 = new TextureRegion(enemiesTexture, 20, 60, 14, 20);
		catFlying = new TextureRegion(enemiesTexture, 40, 0, 16, 20);
		catFlyingExploding1 = new TextureRegion(enemiesTexture, 40, 20, 16, 20);
		catFlyingExploding2 = new TextureRegion(enemiesTexture, 40, 40, 16, 20);
		catFlyingExploding3 = new TextureRegion(enemiesTexture, 40, 60, 16, 20);
		
		// Audio
		jump = Gdx.audio.newSound(Gdx.files.internal("audio/jump.wav"));
		click = Gdx.audio.newSound(Gdx.files.internal("audio/click.wav"));
		win = Gdx.audio.newSound(Gdx.files.internal("audio/win.wav"));
		explosion1 = Gdx.audio.newSound(Gdx.files.internal("audio/explosion1.wav"));
		explosion2 = Gdx.audio.newSound(Gdx.files.internal("audio/explosion2.wav"));
		explosion3 = Gdx.audio.newSound(Gdx.files.internal("audio/explosion3.wav"));
		explosions.add(explosion1);
		explosions.add(explosion2);
		explosions.add(explosion3);
		intro = Gdx.audio.newMusic(Gdx.files.internal("audio/intro.wav"));
		intro.setLooping(true);
		thecutestwaytodie = Gdx.audio.newMusic(Gdx.files.internal("audio/thecutestwaytodie.wav"));
		thecutestwaytodie.setLooping(true);		

		// Preferences File
        prefs = Gdx.app.getPreferences("TheCutestWayToDie");
        if(!prefs.contains("beatFrancisLevel")) {
        	prefs.putBoolean("beatFrancisLevel", false);
        }
        if(!prefs.contains("beatBrandonLevel")) {
        	prefs.putBoolean("beatBrandonLevel", false);
        }
        if(!prefs.contains("beatStewLevel")) {
        	prefs.putBoolean("beatStewLevel", false);
        }
        if(!prefs.contains("beatSeanLevel")) {
        	prefs.putBoolean("beatSeanLevel", false);
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
		
		if(!prefs.contains("firstTime")) {
			prefs.putBoolean("firstTime", true);
	    	prefs.flush();
		}
		if(prefs.getBoolean("firstTime") == true) {
			textFile = "levels/how_to_play.txt";
		}

		levelTexture = new Texture(graphicsFile);
		levelTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		topTile               = new TextureRegion(levelTexture, 0, 0, 10, 10);
		topTileRight          = new TextureRegion(levelTexture, 10, 0, 10, 10);
		topTileLeft           = new TextureRegion(levelTexture, 20, 0, 10, 10);
		bottomTile            = new TextureRegion(levelTexture, 0, 10, 10, 10);
		bottomTileRight       = new TextureRegion(levelTexture, 10, 10, 10, 10);
		bottomTileLeft        = new TextureRegion(levelTexture, 20, 10, 10, 10);
		firstBackgroundLayer  = new TextureRegion(levelTexture, 0, 20, 180, 100);
		secondBackgroundLayer = new TextureRegion(levelTexture, 0, 120, 180, 100);
		thirdBackgroundLayer  = new TextureRegion(levelTexture, 0, 220, 180, 100);
		flag1                 = new TextureRegion(levelTexture, 150, 10, 10, 10);
		flag2                 = new TextureRegion(levelTexture, 160, 10, 10, 10);
		flag3                 = new TextureRegion(levelTexture, 170, 10, 10, 10);
		
        TextureRegion[] flags = { flag1, flag2, flag3 };
        flag = new Animation(0.3f, flags);
        flag.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
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
//        T: Talk Tile
//        -: Top Tile
//        {: Top Tile Left
//        }: Top Tile Right
//        =: Bottom Tile
//        [: Bottom Tile Left
//        ]: Bottom Tile Right
//        !: End of Level Flag

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
    				enemyList.add(new EnemyFlying(i * 10, j * 10, CAT_FLYING_WIDTH, CAT_HEIGHT));
    				break;
    			case 'T':
    				tileList.add(new TalkTile(i, j));
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
    			case '!':
    				tileList.add(new TileFlag(i, j));
    				break;
    			case ' ':
    			case '\n':
    			case '\r':
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
