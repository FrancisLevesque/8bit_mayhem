package com.thedistrictheat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.thedistrictheat.gameworld.GameRenderer;
import com.thedistrictheat.gameworld.GameWorld;
import com.thedistrictheat.helpers.InputHandler;

public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;

    public GameScreen() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = gameWidth*(screenHeight/screenWidth);
        Gdx.app.log("screenWidth", screenWidth + "");
        Gdx.app.log("screenHeight", screenHeight + "");
        Gdx.app.log("gameWidth", gameWidth + "");
        Gdx.app.log("gameHeight", gameHeight + "");

        world = new GameWorld();
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
