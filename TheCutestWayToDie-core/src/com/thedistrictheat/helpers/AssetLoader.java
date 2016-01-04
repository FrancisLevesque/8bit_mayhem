package com.thedistrictheat.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	public static Texture texture;
	public static TextureRegion francis, francisBlink;
	
	public static void load() {
		texture = new Texture(Gdx.files.internal("data/SpriteSet_Francis.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		francis = new TextureRegion(texture, 0, 0, 38, 54);
		francisBlink = new TextureRegion(texture, 39, 0, 38, 54);
	}
	
	public static void dispose() {
		texture.dispose();
	}
}
