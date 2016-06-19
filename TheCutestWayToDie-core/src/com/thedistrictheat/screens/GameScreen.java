package com.thedistrictheat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.thedistrictheat.gameworld.GameRenderer;
import com.thedistrictheat.gameworld.GameWorld;
import com.thedistrictheat.helpers.AssetLoader;
import com.thedistrictheat.helpers.GameInputHandler;
import com.thedistrictheat.helpers.SoundHandler;
import com.thedistrictheat.thecutestwaytodie.TCWTDGame;

public class GameScreen implements Screen {
	private TCWTDGame game;
	private CharacterSelectScreen screen;
    private GameWorld world;
	private GameInputHandler inputHandler;
    private GameRenderer renderer;
    private float runTime;
    private boolean levelNotLoaded = true;

    public GameScreen(TCWTDGame game, CharacterSelectScreen screen, float gameWidth, float gameHeight) {
		Gdx.input.setCatchBackKey(true);
    	this.game = game;
    	this.screen = screen;
    	
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        
		float gameWidthRatio = gameWidth/screenWidth;
		float gameHeightRatio = gameHeight/screenHeight;
        world = new GameWorld((int)gameWidth, (int)gameHeight, gameWidthRatio, gameHeightRatio);
        inputHandler = new GameInputHandler(world);
        Gdx.input.setInputProcessor(inputHandler);
        renderer = new GameRenderer(world, (int)gameWidth, (int)gameHeight);
    }

    @Override
    public void render(float delta) {      
    	if(levelNotLoaded) {
    		AssetLoader.loadLevel(screen.getGuy());
    		renderer.refreshGameAssets();
    		levelNotLoaded = false;
    	}
		if(world.goToCharacterSelect()) {
			world.setGoToCharacterSelect(false);
			game.setScreen(screen);
		}
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        world.ready();
        levelNotLoaded = true;
		Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(inputHandler);
        SoundHandler.toggleMainSong();
    }

    @Override
    public void hide() {
        SoundHandler.toggleMainSong();
    	world.restart();
    }

    @Override
    public void resume() {
    	this.show();
    }

    @Override
    public void pause() {
        SoundHandler.stopSongs();
    	world.restart();
    }

    @Override
    public void dispose() {
    }
}
