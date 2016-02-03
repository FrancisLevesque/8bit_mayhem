package com.thedistrictheat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.thedistrictheat.helpers.AssetLoader;
import com.thedistrictheat.thecutestwaytodie.TCWTDGame;

public class SplashScreen implements Screen {
	private TCWTDGame game;
    private float width;
    private float height;
    private OrthographicCamera camera;
    private SpriteBatch spriteBatcher;
    private ShapeRenderer shapeRenderer;
    private Sprite loadingSprite;
    private float loadTime;
    private boolean swap;
    private float transparency;

    public SplashScreen(TCWTDGame game) {
    	this.game = game; 
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();
        loadTime = 0;
        swap = false;
        transparency = 1;
        
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		spriteBatcher = new SpriteBatch();
		spriteBatcher.setProjectionMatrix(camera.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);

        loadingSprite = new Sprite(AssetLoader.loading);
        loadingSprite.setSize(width, height);
    }
    
	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		loadTime += delta;		
		if (loadTime > 2.0) {
			game.setScreen(new GameScreen());
		}
		
		if(swap) {
			transparency += delta;
		}
		else {
			transparency -= delta;
			if (transparency <= 0) {
				swap = true;
			}
		}

        spriteBatcher.begin();
        loadingSprite.draw(spriteBatcher);
        spriteBatcher.end();
        
		Gdx.gl20.glEnable(GL20.GL_BLEND);
	    Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        
	    shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(new Color(0, 0, 0, transparency));
        shapeRenderer.rect(0, 0, width, height);
        shapeRenderer.end();
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
