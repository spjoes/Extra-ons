package com.spjoes.extraons.apps.karaoke;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class KaraokeLine {

	private List<String> lines;
	private List<Integer> starts;
	private List<Integer> durations;
	private int position;
	
	public KaraokeLine(List<String> list) {
		this.lines = list;
	}
	
	/**
	 * <b>TODO</b>: give proper name to variables (the s# ones)
	 * @param mc
	 * @param x
	 * @param y
	 * @param partialTicks
	 */
	public void render(Minecraft mc, int x, int y, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc);
		int scale = sr.getScaleFactor();
		GL11.glEnable(GL11.GL_SCISSOR_TEST);
		GL11.glScissor(x, mc.displayHeight - (y + 200), x + 200*scale, 100*scale);
		float realPosition = this.position + partialTicks;
		String[] s = this.toString().split(" ");
		ArrayList<String> s2 = new ArrayList<String>();
		String s3 = "";
		for(String s4 : s) {
			if(mc.fontRenderer.getStringWidth(s3 + s4) > 200) {
				if(s3.charAt(s3.length() - 1) == ' ') {
					s3 = s3.substring(0, s3.length() - 1);
				}
				s2.add(s3);
				s3 = s4 + " ";
			} else {
				s3 = s3 + s4 + " ";
			}
		}
		if(s3.charAt(s3.length() - 1) == ' ') {
			s3 = s3.substring(0, s3.length() - 1);
		}
		s2.add(s3);
		for(int i = 0; i < s2.size(); i++) {
			String s5 = s2.get(i);
			int w = mc.fontRenderer.getStringWidth(s5);
			mc.fontRenderer.drawString(s5, x + 100 - w/2, y + i*10, 0xFFFFFF);
		}
		int totalW = 0;
		for(String s7 : s2) {
			totalW += mc.fontRenderer.getStringWidth(s7);
		}
		int currW = 0;
		ArrayList<float[]> scissors = new ArrayList<float[]>();
		for(int i = 0; i < s2.size(); i++) {
			String s6 = s2.get(i);
			float[] scissor = new float[2];
			int w = mc.fontRenderer.getStringWidth(s6);
			scissor[0] = 100 - mc.fontRenderer.getStringWidth(s6)/2;
			float progress = totalW*(realPosition/100);
			int progrW = (int) (totalW*progress) - currW*totalW;
			
			if(currW <= progress) {
				if(currW + w >= progress) {
					scissor[1] = progrW/totalW;
				} else {
					scissor[1] = w;
				}
			} else {
				scissor[1] = 0;
			}
			scissors.add(scissor);
			currW += w;
		}
		for(int i = 0; i < s2.size(); i++) {
			GL11.glScissor((int) (x + scissors.get(i)[0])*scale, mc.displayHeight - (y + (i+1)*10)*scale, (int) (scissors.get(i)[1]*scale), 10*scale);
			String s5 = s2.get(i);
			int w = mc.fontRenderer.getStringWidth(s5);
			mc.fontRenderer.drawString(s5, x + 100 - w/2, y + i*10, 0x000000);
		}
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
	}
	
	public void onTick() {
		this.position = (this.position + 1)%100;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		this.lines.forEach(s -> sb.append(s));
		return sb.toString();
	}
	
}
