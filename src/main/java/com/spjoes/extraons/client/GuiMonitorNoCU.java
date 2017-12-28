package com.spjoes.extraons.client;

import org.lwjgl.opengl.GL11;

import com.mrcrayfish.device.Reference;
import com.mrcrayfish.device.api.utils.RenderUtil;
import com.spjoes.extraons.Constants;
import com.spjoes.extraons.tileentities.TileEntityCentralUnit;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class GuiMonitorNoCU extends GuiScreen {

	private static final ResourceLocation LAPTOP_GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/laptop.png");
	private static final ResourceLocation NO_SIGNAL = new ResourceLocation(Constants.MODID, "textures/gui/no_signal.png");
	private static final int BORDER = 10;
	private static final int DEVICE_WIDTH = 400;
	private static final int DEVICE_HEIGHT = 230;
	static final int SCREEN_WIDTH = DEVICE_WIDTH - BORDER * 2;
	static final int SCREEN_HEIGHT = DEVICE_HEIGHT - BORDER * 2;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		
		TextureManager tm = this.mc.getTextureManager();
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		tm.bindTexture(LAPTOP_GUI);
		
		/* Physical Screen */
		int posX = (width - DEVICE_WIDTH) / 2;
		int posY = (height - DEVICE_HEIGHT) / 2;
		
		/* Actual Display */
		int scrPosX = posX + BORDER;
		int scrPosY = posY + BORDER;
		
		/* Corners */
		this.drawTexturedModalRect(posX, posY, 0, 0, BORDER, BORDER); // TOP-LEFT
		this.drawTexturedModalRect(posX + DEVICE_WIDTH - BORDER, posY, 11, 0, BORDER, BORDER); // TOP-RIGHT
		this.drawTexturedModalRect(posX + DEVICE_WIDTH - BORDER, posY + DEVICE_HEIGHT - BORDER, 11, 11, BORDER, BORDER); // BOTTOM-RIGHT
		this.drawTexturedModalRect(posX, posY + DEVICE_HEIGHT - BORDER, 0, 11, BORDER, BORDER); // BOTTOM-LEFT
		
		/* Edges */
		RenderUtil.drawRectWithTexture(posX + BORDER, posY, 10, 0, SCREEN_WIDTH, BORDER, 1, BORDER); // TOP
		RenderUtil.drawRectWithTexture(posX + DEVICE_WIDTH - BORDER, posY + BORDER, 11, 10, BORDER, SCREEN_HEIGHT, BORDER, 1); // RIGHT
		RenderUtil.drawRectWithTexture(posX + BORDER, posY + DEVICE_HEIGHT - BORDER, 10, 11, SCREEN_WIDTH, BORDER, 1, BORDER); // BOTTOM
		RenderUtil.drawRectWithTexture(posX, posY + BORDER, 0, 11, BORDER, SCREEN_HEIGHT, BORDER, 1); // LEFT
		
		/* Center */
		RenderUtil.drawRectWithTexture(posX + BORDER, posY + BORDER, 10, 10, SCREEN_WIDTH, SCREEN_HEIGHT, 1, 1);
		
		/* Screen */
		tm.bindTexture(NO_SIGNAL);
		Gui.drawModalRectWithCustomSizedTexture(posX + BORDER, posY + BORDER, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
}
