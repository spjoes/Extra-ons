package com.spjoes.extraons.apps.karaoke;

import java.io.File;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class Karaoke {

	private BasicPlayer player;
	private Object data;
	
	public Karaoke(File music) throws Throwable {
		this.player = new BasicPlayer();
		this.player.open(music);
		this.player.play();
	}
	
	public void stop() {
		try {
			this.player.stop();
		} catch (BasicPlayerException e) {}
	}
	
}
