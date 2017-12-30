package com.spjoes.extraons.proxies;

import com.spjoes.extraons.Constants;
import com.spjoes.extraons.client.TESRCentralUnit;
import com.spjoes.extraons.items.ItemHandler;
import com.spjoes.extraons.tileentities.TileEntityCentralUnit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerModels() {
		for(int i = 0; i < 16; i++) {
			this.registerModel(ItemHandler.MICROPHONE, i, new ResourceLocation(Constants.MODID, "mic"));
			this.registerModel(ItemHandler.HEADPHONES, i, new ResourceLocation(Constants.MODID, "headphones"));
		}
		this.registerModel(ItemHandler.HEADPHONES, 16, new ResourceLocation(Constants.MODID, "headphones_rainbow"));
		this.registerModel(ItemHandler.MONITOR_ITEM, 0, new ResourceLocation(Constants.MODID, "monitor"));
		this.registerModel(ItemHandler.CENTRAL_UNIT_ITEM, 0, new ResourceLocation(Constants.MODID, "central_unit"));
		this.registerModel(ItemHandler.HDMI_CABLE, 0, new ResourceLocation(Constants.MODID, "hdmi_cable"));
		this.registerModel(ItemHandler.TAB_ICON, 0, new ResourceLocation(Constants.MODID, "tab_icon"));
		this.registerModel(ItemHandler.PHONE, 0, new ResourceLocation(Constants.MODID, "phone"));
	}
	
	@Override
	public void registerItemColors() {
		IItemColor color = new IItemColor() {
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				return tintIndex == 1 && stack.getItemDamage() < 16 ? EnumDyeColor.byMetadata(stack.getItemDamage()).getColorValue() : 0xFFFFFF;
			}
		};
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(color, ItemHandler.MICROPHONE, ItemHandler.HEADPHONES);
	}
	
	private void registerModel(Item item, int damage, ResourceLocation rl) {
		ModelLoader.setCustomModelResourceLocation(item, damage, new ModelResourceLocation(rl, "inventory"));
	}
	
	@Override
	public void registerTERenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCentralUnit.class, new TESRCentralUnit());
	}
	
}
