package com.spjoes.extraons.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemHandler {

	public static Item MICROPHONE;
	
	public static void registerItems() {
		MICROPHONE = new ItemMic();
		ForgeRegistries.ITEMS.register(MICROPHONE);
	}
	
}
