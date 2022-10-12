package com.spjoes.extraons.items;

import com.spjoes.extraons.handlers.ItemHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class CreativeTabExtraons extends CreativeTabs {

	public CreativeTabExtraons() {
		super("extraons");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemHandler.TAB_ICON);
	}
	
	@Override
	public void displayAllRelevantItems(NonNullList<ItemStack> list) {
		for(int i = 0; i < 17; i++) {
			list.add(new ItemStack(ItemHandler.MICROPHONE, 1, i));
		}

		list.add(new ItemStack(ItemHandler.MIC_HANDLE));

		for(int i = 0; i < 17; i++) {
			list.add(new ItemStack(ItemHandler.HEADPHONES, 1, i));
		}

		list.add(new ItemStack(ItemHandler.DYINGKIT_BLACK));
		list.add(new ItemStack(ItemHandler.DYINGKIT_BLUE));
		list.add(new ItemStack(ItemHandler.DYINGKIT_BROWN));
		list.add(new ItemStack(ItemHandler.DYINGKIT_CYAN));
		list.add(new ItemStack(ItemHandler.DYINGKIT_GRAY));
		list.add(new ItemStack(ItemHandler.DYINGKIT_GREEN));
		list.add(new ItemStack(ItemHandler.DYINGKIT_LIGHT_BLUE));
		list.add(new ItemStack(ItemHandler.DYINGKIT_LIME));
		list.add(new ItemStack(ItemHandler.DYINGKIT_MAGENTA));
		list.add(new ItemStack(ItemHandler.DYINGKIT_ORANGE));
		list.add(new ItemStack(ItemHandler.DYINGKIT_PINK));
		list.add(new ItemStack(ItemHandler.DYINGKIT_PURPLE));
		list.add(new ItemStack(ItemHandler.DYINGKIT_RED));
		list.add(new ItemStack(ItemHandler.DYINGKIT_SILVER));
		list.add(new ItemStack(ItemHandler.DYINGKIT_WHITE));
		list.add(new ItemStack(ItemHandler.DYINGKIT_YELLOW));

		list.add(new ItemStack(ItemHandler.MONITOR_ITEM));
		list.add(new ItemStack(ItemHandler.CENTRAL_UNIT_ITEM));
		list.add(new ItemStack(ItemHandler.HDMI_CABLE));
		list.add(new ItemStack(ItemHandler.MIC_CABLE));
		list.add(new ItemStack(ItemHandler.TV_ITEM));
		list.add(new ItemStack(ItemHandler.PHONE));
		list.add(new ItemStack(ItemHandler.MOUSE_ITEM));
		list.add(new ItemStack(ItemHandler.KEYBOARD));
		list.add(new ItemStack(ItemHandler.CHAIR));

		for(int i = 0; i < 3; i++) {
			list.add(new ItemStack(ItemHandler.CONSOLE, 1, i));
		}

		for(int i = 0; i < 3; i++) {
			list.add(new ItemStack(ItemHandler.CONTROLLER, 1, i));
		}

		list.add(new ItemStack(ItemHandler.SMB));

	}

}
