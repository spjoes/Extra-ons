package com.spjoes.extraons.centralUnitStuff;

import com.spjoes.extraons.Constants;
import com.spjoes.extraons.client.GuiMonitor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class CUAppBar extends GuiScreen {

    private static final ResourceLocation BAR = new ResourceLocation(Constants.MODID, "textures/gui/bar.png");

    private NonNullList<CUApp> appList;

    // References the settings here so we don't need to update
    // the color manually in here
    private CUSettings settings;

    // x and y represent the position of the bar
    public void render(Minecraft mc, int x, int y, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(BAR);
        int color = 0xF0FF7F00;
        if(settings != null) {
            color = settings.getBarColor();
        }
        float a = (float) ((color >> 24) & 0xFF)/255.0f;
        float r = (float) ((color >> 16) & 0xFF)/255.0f;
        float g = (float) ((color >> 8) & 0xFF)/255.0f;
        float b = (float) (color & 0xFF)/255.0f;
        GlStateManager.color(r, g, b, a);
        GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(x, y, 0, 0, GuiMonitor.dispWidth, 16, GuiMonitor.dispWidth, 16);
    }

}
