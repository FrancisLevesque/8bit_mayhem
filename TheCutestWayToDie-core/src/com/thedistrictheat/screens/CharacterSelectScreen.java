package com.thedistrictheat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.thedistrictheat.gameworld.CharacterSelectRenderer;
import com.thedistrictheat.gameworld.CharacterSelectWorld;
import com.thedistrictheat.gameworld.CharacterSelectWorld.CharacterType;
import com.thedistrictheat.helpers.CharacterSelectInputHandler;
import com.thedistrictheat.helpers.SoundHandler;
import com.thedistrictheat.thecutestwaytodie.TCWTDGame;

public class CharacterSelectScreen implements Screen {
    private static final float DESIREDWIDTH = 180;
    private static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;

	private TCWTDGame game;
	private float gameWidth;
	private float gameHeight;
	private CharacterSelectWorld world;
	private CharacterSelectInputHandler inputHandler;
    private CharacterSelectRenderer renderer;
    private float runTime;
	private GameScreen gameScreen;

    public CharacterSelectScreen(TCWTDGame game) {
    	this.game = game;
    	
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();        
		gameWidth = DESIREDWIDTH;
		gameHeight = DESIREDWIDTH * (screenHeight/screenWidth);

    	Gdx.app.log("Screen Width", screenWidth + "");
    	Gdx.app.log("Screen Height", screenHeight + "");
    	Gdx.app.log("Game Width", gameWidth + "");
    	Gdx.app.log("Game Height", gameHeight + "");
        
		float gameWidthRatio = gameWidth/screenWidth;
		float gameHeightRatio = gameHeight/screenHeight;
    	world = new CharacterSelectWorld((int)gameWidth, (int)gameHeight, gameWidthRatio, gameHeightRatio);
    	inputHandler = new CharacterSelectInputHandler(world);
        Gdx.input.setInputProcessor(inputHandler);
        renderer = new CharacterSelectRenderer(world, BACKGROUND_COLOR, (int)gameWidth, (int)gameHeight);
        runTime = 0;
        
		gameScreen = new GameScreen(game, this, gameWidth, gameHeight);
		
		SoundHandler.toggleIntroSong();
    }

    @Override
    public void render(float delta) {    
    	runTime += delta;
        world.update(delta);
    	renderer.render(runTime);
    	if (world.startGame()) {
    		world.setStartGame(false);
    		game.setScreen(gameScreen);
    	}
    }
    
    public CharacterType getGuy(){
    	return world.getSelectedCharacter();
    }

    @Override
    public void show() {
		world.setCharacterSelected(false);
		renderer.setBackgroundColor(BACKGROUND_COLOR);
		Gdx.input.setCatchBackKey(false);
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void hide() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void resume() {
    	this.show();
    }

    @Override
    public void pause() {
    	this.hide();
    }

    @Override
    public void dispose() {
    }
}
