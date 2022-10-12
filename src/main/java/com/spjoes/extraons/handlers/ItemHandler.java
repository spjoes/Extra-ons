package com.spjoes.extraons.handlers;

import com.spjoes.extraons.Constants;
import com.spjoes.extraons.handlers.BlockHandler;

import com.spjoes.extraons.items.*;
import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemHandler {

	public static Item MICROPHONE, TAB_ICON, MONITOR_ITEM, CENTRAL_UNIT_ITEM, HDMI_CABLE, PHONE, HEADPHONES, MOUSE_ITEM, TV_ITEM, CONSOLE, CONTROLLER, SMB, KEYBOARD, CHAIR, MIC_HANDLE, MIC_CABLE,

	DYINGKIT_BLACK, DYINGKIT_BLUE, DYINGKIT_BROWN, DYINGKIT_CYAN, DYINGKIT_GRAY, DYINGKIT_GREEN, DYINGKIT_LIGHT_BLUE, DYINGKIT_LIME, DYINGKIT_MAGENTA, DYINGKIT_ORANGE, DYINGKIT_PINK, DYINGKIT_PURPLE, DYINGKIT_RED, DYINGKIT_SILVER, DYINGKIT_WHITE, DYINGKIT_YELLOW;
	
	public static void registerItems() {
		MICROPHONE = new ItemMic();
		TAB_ICON = new Item().setRegistryName(Constants.MODID, "tab_icon");
		MONITOR_ITEM = createIB(BlockHandler.MONITOR);
		CONSOLE = createIB(BlockHandler.CONSOLE, true);
		MOUSE_ITEM = createIB(BlockHandler.MOUSE);
		KEYBOARD = createIB(BlockHandler.KEYBOARD);
		CHAIR = createIB(BlockHandler.CHAIR);
		TV_ITEM = createIB(BlockHandler.TV);
		CENTRAL_UNIT_ITEM = new ItemBlock(BlockHandler.CENTRAL_UNIT).setRegistryName(BlockHandler.CENTRAL_UNIT.getRegistryName());
		HDMI_CABLE = new ItemHDMICable();
		PHONE = new ItemPhone();
		HEADPHONES = new ItemHeadphones();
		CONTROLLER = new ItemBase(1, "controller");
		SMB = new ItemSMB(1, "smb");
		MIC_HANDLE = new ItemBase(64, "mic_handle");
		MIC_CABLE = new ItemBase(64, "mic_cable");


//		DYINGKIT = new ItemDyingKit(64, "dyingkit", EnumDyeColor.WHITE);
		DYINGKIT_BLACK = new ItemDyingKit(64, "dyingkit_black", EnumDyeColor.BLACK);
		DYINGKIT_BLUE = new ItemDyingKit(64, "dyingkit_blue", EnumDyeColor.BLUE);
		DYINGKIT_BROWN = new ItemDyingKit(64, "dyingkit_brown", EnumDyeColor.BROWN);
		DYINGKIT_CYAN = new ItemDyingKit(64, "dyingkit_cyan", EnumDyeColor.CYAN);
		DYINGKIT_GRAY = new ItemDyingKit(64, "dyingkit_gray", EnumDyeColor.GRAY);
		DYINGKIT_GREEN = new ItemDyingKit(64, "dyingkit_green", EnumDyeColor.GREEN);
		DYINGKIT_LIGHT_BLUE = new ItemDyingKit(64, "dyingkit_light_blue", EnumDyeColor.LIGHT_BLUE);
		DYINGKIT_LIME = new ItemDyingKit(64, "dyingkit_lime", EnumDyeColor.LIME);
		DYINGKIT_MAGENTA = new ItemDyingKit(64, "dyingkit_magenta", EnumDyeColor.MAGENTA);
		DYINGKIT_ORANGE = new ItemDyingKit(64, "dyingkit_orange", EnumDyeColor.ORANGE);
		DYINGKIT_PINK = new ItemDyingKit(64, "dyingkit_pink", EnumDyeColor.PINK);
		DYINGKIT_PURPLE = new ItemDyingKit(64, "dyingkit_purple", EnumDyeColor.PURPLE);
		DYINGKIT_RED = new ItemDyingKit(64, "dyingkit_red", EnumDyeColor.RED);
		DYINGKIT_SILVER = new ItemDyingKit(64, "dyingkit_silver", EnumDyeColor.SILVER);
		DYINGKIT_WHITE = new ItemDyingKit(64, "dyingkit_white", EnumDyeColor.WHITE);
		DYINGKIT_YELLOW = new ItemDyingKit(64, "dyingkit_yellow", EnumDyeColor.YELLOW);

		registerAll(MICROPHONE, TAB_ICON, MONITOR_ITEM, CENTRAL_UNIT_ITEM, HDMI_CABLE, PHONE, HEADPHONES, MOUSE_ITEM, TV_ITEM, CONSOLE, CONTROLLER, SMB, KEYBOARD, CHAIR, MIC_HANDLE, MIC_CABLE, 	DYINGKIT_BLACK, DYINGKIT_BLUE, DYINGKIT_BROWN, DYINGKIT_CYAN, DYINGKIT_GRAY, DYINGKIT_GREEN, DYINGKIT_LIGHT_BLUE, DYINGKIT_LIME, DYINGKIT_MAGENTA, DYINGKIT_ORANGE, DYINGKIT_PINK, DYINGKIT_PURPLE, DYINGKIT_RED, DYINGKIT_SILVER, DYINGKIT_WHITE, DYINGKIT_YELLOW);
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
