package com.spjoes.extraons.apps.karaoke;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import net.minecraft.client.Minecraft;

public class Karaoke {

	private ApplicationKaraoke parent;
	private BasicPlayer player;
	private ArrayList<KaraokeLine> lines;
	private String nameAndArtist;
	private Object data;
	private static final Gson GSON = new Gson();
	private int pos = 0;
	private int lineIndex = 0;
	
	public Karaoke(ApplicationKaraoke parent, File music, String json) throws Throwable {
		this.parent = parent;
		HashMap<String, Object> list = GSON.fromJson(json, HashMap.class);
		this.nameAndArtist = "\"" + list.get("name") + "\" - " + list.get("artist");
		ArrayList<LinkedTreeMap<String, Object>> linesRaw = (ArrayList<LinkedTreeMap<String, Object>>) list.get("lyrics");
		this.lines = new ArrayList<KaraokeLine>();
		linesRaw.forEach(theLine -> {
			// I need to do this because just doing (int) theLine.get("start") throws an exception
			this.lines.add(new KaraokeLine((String) theLine.get("text"), (int) (double) theLine.get("start") * 20, (int) (double) theLine.get("duration") * 20));
		});
		this.player = new BasicPlayer();
		this.player.open(music);
		this.player.play();
	}
	
	public String getNameAndArtist() {
		return this.nameAndArtist;
	}
	
	public void onTick() {
		if(this.player.getStatus() == BasicPlayer.PLAYING) {
			this.pos++;
			if(this.pos >= 0) {
				if(this.lineIndex < this.lines.size() - 1 && this.lines.get(this.lineIndex + 1).getStart() == this.pos) {
					this.lineIndex++;
				} else if(this.lineIndex == this.lines.size() - 1) {
					KaraokeLine line = this.lines.get(this.lineIndex);
					if(this.pos > line.getStart() + line.getDuration()) {
						this.stop();
						this.parent.menu();
					}
				}
				this.lines.get(this.lineIndex).onTick();
			}
		}
	}
	
	public void render(Minecraft mc, int x, int y, float partialTicks) {
		this.lines.get(this.lineIndex).render(mc, x, y, partialTicks);
	}
	
	public void stop() {
		try {
			this.player.stop();
		} catch (BasicPlayerException e) {}
	}
	
}
