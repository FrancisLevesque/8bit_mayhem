package com.thedistrictheat.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.thedistrictheat.gameobjects.Guy;
import com.thedistrictheat.helpers.AssetLoader;

public class GameRenderer {
	private GameWorld world;
//	private int gameWidth, gameHeight;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatcher;
	
	public GameRenderer(GameWorld world, int gameWidth, int gameHeight) {
		this.world = world;
//		this.gameWidth = gameWidth;
//		this.gameHeight = gameHeight;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, gameWidth, gameHeight);
		
		spriteBatcher = new SpriteBatch();
		spriteBatcher.setProjectionMatrix(camera.combined);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
	}
	
	public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        Guy guy = world.getGuy();
        spriteBatcher.begin();
        if (((int)runTime)%7 == 0) {
            spriteBatcher.draw(AssetLoader.francisBlink, guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
        }
        else {
            spriteBatcher.draw(AssetLoader.francis, guy.getX(), guy.getY(), guy.getWidth(), guy.getHeight());
        }
        spriteBatcher.end();
	}
}
