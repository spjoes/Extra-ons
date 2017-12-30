package com.spjoes.extraons.apps.karaoke;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class Karaoke {

	private BasicPlayer player;
	private ArrayList<KaraokeLine> lines;
	private String nameAndAuthor;
	private Object data;
	private static final Gson GSON = new Gson();
	
	public Karaoke(File music, String json) throws Throwable {
		HashMap<String, Object> list = GSON.fromJson(json, HashMap.class);
		this.nameAndAuthor = "\"" + list.get("name") + "\" - " + list.get("author");
		this.player = new BasicPlayer();
		this.player.open(music);
		this.player.play();
	}
	
	public String getNameAndAuthor() {
		return this.nameAndAuthor;
	}
	
	public void stop() {
		try {
			this.player.stop();
		} catch (BasicPlayerException e) {}
	}
	
}
