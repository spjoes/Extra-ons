package com.spjoes.extraons.client;

import com.spjoes.extraons.Constants;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiPhone extends GuiScreen {

	private static final ResourceLocation PHONE = new ResourceLocation(Constants.MODID, "textures/gui/phone.png");
	
	private int guiLeft, guiTop;
	private int xSize, ySize;
	
	@Override
	public void initGui() {
		super.initGui();
		this.xSize = 164;
		this.ySize = 246;
		this.guiLeft = (this.width - this.xSize)/2;
		this.guiTop = (this.height - this.ySize)/2;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.mc.getTextureManager().bindTexture(PHONE);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
}
