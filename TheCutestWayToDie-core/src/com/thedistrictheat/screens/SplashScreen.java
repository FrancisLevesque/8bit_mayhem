package com.thedistrictheat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thedistrictheat.helpers.AssetLoader;
import com.thedistrictheat.thecutestwaytodie.TCWTDGame;

// TODO: fix this class to load the image properly; implement callback to switch to next screen
public class SplashScreen implements Screen {
    private SpriteBatch batcher;
    private Sprite loadingSprite;
    private TCWTDGame game;

    public SplashScreen(TCWTDGame game) {
        this.game = game;
    }
    
	@Override
	public void show() {
        loadingSprite = new Sprite(AssetLoader.loading);
        loadingSprite.setColor(Color.BLACK);
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        loadingSprite.setSize(loadingSprite.getWidth(), loadingSprite.getHeight());
        batcher = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batcher.begin();
        loadingSprite.draw(batcher);
        batcher.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}
}
