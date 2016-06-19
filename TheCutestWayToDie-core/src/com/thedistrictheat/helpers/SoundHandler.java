package com.thedistrictheat.helpers;

import java.util.Random;

import com.badlogic.gdx.audio.Music;

public class SoundHandler {
	private static boolean musicEnabled = true;
	private static boolean soundEnabled = true;
	private static Music currentSong;
	
	public static void toggleMusic() {
		if(musicEnabled) {
			musicEnabled = false;
			stopSongs();
		} else {
			musicEnabled = true;
			startSong();
		}
	}
	
	public static void toggleSound() {
		if(soundEnabled) {
			soundEnabled = false;
		} else {
			soundEnabled = true;
		}
	}
	
	public static void startSong() {
        currentSong.play();
	}
	
	public static void stopSongs() {
		if(AssetLoader.thecutestwaytodie.isPlaying()) {
			currentSong = AssetLoader.thecutestwaytodie;
	        AssetLoader.thecutestwaytodie.stop();
		} else {
			currentSong = AssetLoader.intro;
	        AssetLoader.intro.stop();
		}
	}
	
	public static void toggleIntroSong() {
		if(musicEnabled) {
	        AssetLoader.thecutestwaytodie.stop();
	        AssetLoader.intro.play();
		}
	}
	
	public static void toggleMainSong() {
		if(musicEnabled) {
	        AssetLoader.intro.stop();
	        AssetLoader.thecutestwaytodie.play();
		}
	}
	
	public static void playClickSound() {
		if(soundEnabled) {
	    	AssetLoader.click.play(0.6f);
		}
	}
	
	public static void playJumpSound() {
		if(soundEnabled) {
			AssetLoader.jump.play(0.5f);
		}		
	}
	
	public static void playExplosionSound() {
		if(soundEnabled) {
	    	Random random = new Random();
	    	int index = random.nextInt(AssetLoader.explosions.size());
		    AssetLoader.explosions.get(index).play(0.5f);
		}	
	}

}
