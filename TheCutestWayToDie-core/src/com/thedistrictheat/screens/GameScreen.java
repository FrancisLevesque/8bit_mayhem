package com.thedistrictheat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.thedistrictheat.gameworld.GameRenderer;
import com.thedistrictheat.gameworld.GameWorld;
import com.thedistrictheat.helpers.InputHandler;

public class GameScreen implements Screen {
    private static final float DESIREDWIDTH = 180;
	
    private GameWorld world;
    private GameRenderer renderer;
    private int floorHeight;
    private float runTime;

    public GameScreen() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();        
		float gameWidth = DESIREDWIDTH;
		float gameHeight = DESIREDWIDTH * (screenHeight/screenWidth);

    	Gdx.app.log("Screen Width", screenWidth + "");
    	Gdx.app.log("Screen Height", screenHeight + "");
    	Gdx.app.log("Game Width", gameWidth + "");
    	Gdx.app.log("Game Height", gameHeight + "");
        
		floorHeight = (int)(gameHeight * 0.1);
        world = new GameWorld(floorHeight, (int)gameWidth, (int)gameHeight);
        renderer = new GameRenderer(world, (int)gameWidth, (int)gameHeight);
        Gdx.input.setInputProcessor(new InputHandler(world.getGuy()));
    }

    @Override
    public void render(float delta) {        
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
