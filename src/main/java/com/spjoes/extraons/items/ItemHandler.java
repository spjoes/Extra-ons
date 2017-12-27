package com.spjoes.extraons.items;

import com.spjoes.extraons.Constants;
import com.spjoes.extraons.blocks.BlockHandler;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemHandler {

	public static Item MICROPHONE, TAB_ICON, MONITOR_ITEM;
	
	public static void registerItems() {
		MICROPHONE = new ItemMic();
		TAB_ICON = new Item().setRegistryName(Constants.MODID, "tab_icon");
		MONITOR_ITEM = new ItemBlock(BlockHandler.MONITOR).setRegistryName(BlockHandler.MONITOR.getRegistryName());
		ForgeRegistries.ITEMS.register(MICROPHONE);
		ForgeRegistries.ITEMS.register(TAB_ICON);
		ForgeRegistries.ITEMS.register(MONITOR_ITEM);
	}
	
}
