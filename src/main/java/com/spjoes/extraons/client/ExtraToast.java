package com.spjoes.extraons.client;

import com.mrcrayfish.device.api.utils.RenderUtil;
import com.spjoes.extraons.handlers.ItemHandler;

import net.minecraft.client.gui.toasts.GuiToast;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

public class ExtraToast implements IToast {

	private String s;
	
	public ExtraToast(String key, String... args) {
		this.s = I18n.format(key, (Object[])args);
	}
	
	@Override
	public Visibility draw(GuiToast toastGui, long delta) {
		toastGui.getMinecraft().getTextureManager().bindTexture(TEXTURE_TOASTS);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        toastGui.drawTexturedModalRect(0, 0, 0, 0, 160, 32);

        int i = 16776960;

        int w = toastGui.getMinecraft().fontRenderer.getStringWidth(this.s);
        toastGui.getMinecraft().fontRenderer.drawString(this.s, 150 - w, 12, i | -16777216);

        RenderUtil.renderItem(4, 4, new ItemStack(ItemHandler.MONITOR_ITEM), false);
        RenderUtil.renderItem(20, 8, new ItemStack(ItemHandler.HDMI_CABLE), false);
        RenderUtil.renderItem(36, 12, new ItemStack(ItemHandler.CENTRAL_UNIT_ITEM), false);
        
        RenderHelper.enableGUIStandardItemLighting();
        return delta >= 5000L ? IToast.Visibility.HIDE : IToast.Visibility.SHOW;
	}
	
}