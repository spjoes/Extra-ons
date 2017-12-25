package com.spjoes.extraons.items;

import com.spjoes.extraons.Constants;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemHandler {

	public static Item MICROPHONE, TAB_ICON;
	
	public static void registerItems() {
		MICROPHONE = new ItemMic();
		TAB_ICON = new Item().setRegistryName(Constants.MODID, "tab_icon");
		ForgeRegistries.ITEMS.register(MICROPHONE);
		ForgeRegistries.ITEMS.register(TAB_ICON);
	}
	
}
