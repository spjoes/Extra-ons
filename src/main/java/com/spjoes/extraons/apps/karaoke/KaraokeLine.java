package com.spjoes.extraons.apps.karaoke;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

public class KaraokeLine {

	private List<String> lines;
	private int duration;
	private int position;
	private int height;
	private int totalWidth;
	
	public KaraokeLine(String text, int duration) {
		FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
		
		this.lines = new ArrayList<String>();
		String[] split = text.split(" ");
		String lineToAdd = "";
		for(String word : split) {
			if(fr.getStringWidth(lineToAdd + word) > 200) {
				this.lines.add(lineToAdd);
				lineToAdd = word + " ";
			} else {
				lineToAdd = lineToAdd + word + " ";
			}
		}
		if(lineToAdd.charAt(lineToAdd.length() - 1) == ' ') {
			lineToAdd = lineToAdd.substring(0, lineToAdd.length() - 1);
		}
		this.lines.add(lineToAdd);
		
		this.height = (this.lines.size()-1)*10+8; // Because I want a padding of 2 pixels between each line
		
		this.duration = duration; // in ticks
		
		this.totalWidth = 0;
		for(String line : this.lines) {
			this.totalWidth += fr.getStringWidth(line);
		}
		
		this.position = 0;
	}
	
	public void render(Minecraft mc, int x, int y, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc);
		int scale = sr.getScaleFactor();
		GL11.glEnable(GL11.GL_SCISSOR_TEST);
		GL11.glScissor(x*scale, mc.displayHeight - (y + 100)*scale, 200*scale, 100*scale);
		float realPosition = this.position + partialTicks;
		
		for(int i = 0; i < this.lines.size(); i++) {
			String line = this.lines.get(i);
			int w = mc.fontRenderer.getStringWidth(line);
			mc.fontRenderer.drawString(line, x + 100 - w/2, y + 50 - this.height/2 + i*10, 0xFFFFFF);
		}

		int currW = 0;
		ArrayList<float[]> scissors = new ArrayList<float[]>();
		for(int i = 0; i < this.lines.size(); i++) {
			String line = this.lines.get(i);
			float[] scissor = new float[2];
			int w = mc.fontRenderer.getStringWidth(line);
			scissor[0] = 100 - mc.fontRenderer.getStringWidth(line)/2;
			float progress = this.totalWidth*(realPosition/this.duration);
			int progrW = (int) (this.totalWidth*progress) - currW*this.totalWidth;
			
			if(currW <= progress) {
				if(currW + w >= progress) {
					scissor[1] = progrW/this.totalWidth;
				} else {
					scissor[1] = w;
				}
			} else {
				scissor[1] = 0;
			}
			scissors.add(scissor);
			currW += w;
		}
		for(int i = 0; i < this.lines.size(); i++) {
			GL11.glScissor((int) ((x + scissors.get(i)[0]))*scale, mc.displayHeight - (y + 50 - this.height/2 + (i+1)*10)*scale, (int) (scissors.get(i)[1]*scale), 10*scale);
			String line = this.lines.get(i);
			int w = mc.fontRenderer.getStringWidth(line);
			mc.fontRenderer.drawString(line, x + 100 - w/2, y + 50 - this.height/2 + i*10, 0x000000);
		}
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
	}
	
	public void onTick() {
		this.position = (this.position + 1)%(this.duration + 20);
	}
	
}
