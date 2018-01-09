package com.spjoes.extraons.items;

import com.spjoes.extraons.Constants;
import com.spjoes.extraons.blocks.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemHandler {

	public static Item MICROPHONE, TAB_ICON, MONITOR_ITEM, CENTRAL_UNIT_ITEM, HDMI_CABLE, PHONE, HEADPHONES, MOUSE_ITEM, TV_ITEM, CONSOLE;
	
	public static void registerItems() {
		MICROPHONE = new ItemMic();
		TAB_ICON = new Item().setRegistryName(Constants.MODID, "tab_icon");
		MONITOR_ITEM = createIB(BlockHandler.MONITOR);
		CONSOLE = createIB(BlockHandler.CONSOLE, true);
		MOUSE_ITEM = createIB(BlockHandler.MOUSE);
		CENTRAL_UNIT_ITEM = new ItemBlock(BlockHandler.CENTRAL_UNIT).setRegistryName(BlockHandler.CENTRAL_UNIT.getRegistryName());
		HDMI_CABLE = new ItemHDMICable();
		PHONE = new ItemPhone();
		HEADPHONES = new ItemHeadphones();
		TV_ITEM = createIB(BlockHandler.TV);
		
		registerAll(MICROPHONE, TAB_ICON, MONITOR_ITEM, CENTRAL_UNIT_ITEM, HDMI_CABLE, PHONE, HEADPHONES, MOUSE_ITEM, TV_ITEM, CONSOLE);
	}
	
	private static Item createIB(Block block) {
		return createIB(block, false);
	}
	
	private static Item createIB(Block block, boolean hasSubtypes) {
		Item item = new ItemBlock(block).setRegistryName(block.getRegistryName()).setUnlocalizedName(block.getUnlocalizedName());
		if(hasSubtypes) {
			item = item.setMaxDamage(0).setHasSubtypes(true);
		}
		return item;
	}
	
	private static void registerAll(Item... items) {
		for(Item i : items) {
			ForgeRegistries.ITEMS.register(i);
		}
	}
	
}
