package com.spjoes.extraons.items;

import com.spjoes.extraons.Constants;
import com.spjoes.extraons.blocks.BlockHandler;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemHandler {

	public static Item MICROPHONE, TAB_ICON, MONITOR_ITEM, CENTRAL_UNIT_ITEM, HDMI_CABLE, PHONE, HEADPHONES;
	
	public static void registerItems() {
		MICROPHONE = new ItemMic();
		TAB_ICON = new Item().setRegistryName(Constants.MODID, "tab_icon");
		MONITOR_ITEM = new ItemBlock(BlockHandler.MONITOR).setRegistryName(BlockHandler.MONITOR.getRegistryName());
		CENTRAL_UNIT_ITEM = new ItemBlock(BlockHandler.CENTRAL_UNIT).setRegistryName(BlockHandler.CENTRAL_UNIT.getRegistryName());
		HDMI_CABLE = new ItemHDMICable();
		PHONE = new ItemPhone();
		HEADPHONES = new ItemHeadphones();
		
		ForgeRegistries.ITEMS.register(MICROPHONE);
		ForgeRegistries.ITEMS.register(TAB_ICON);
		ForgeRegistries.ITEMS.register(MONITOR_ITEM);
		ForgeRegistries.ITEMS.register(CENTRAL_UNIT_ITEM);
		ForgeRegistries.ITEMS.register(HDMI_CABLE);
		ForgeRegistries.ITEMS.register(PHONE);
		ForgeRegistries.ITEMS.register(HEADPHONES);
	}
	
}
