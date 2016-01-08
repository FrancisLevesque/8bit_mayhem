package com.thedistrictheat.thecutestwaytodie.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.thedistrictheat.thecutestwaytodie.TCWTDGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "The Cutest Way To Die";
        config.width = 980;
        config.height = 540;
		new LwjglApplication(new TCWTDGame(), config);
	}
}
