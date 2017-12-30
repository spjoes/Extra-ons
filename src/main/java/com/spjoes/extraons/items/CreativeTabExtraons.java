package com.spjoes.extraons.items;

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
		for(int i = 0; i < 16; i++) {
			list.add(new ItemStack(ItemHandler.MICROPHONE, 1, i));
		}
		list.add(new ItemStack(ItemHandler.MONITOR_ITEM));
		list.add(new ItemStack(ItemHandler.CENTRAL_UNIT_ITEM));
		list.add(new ItemStack(ItemHandler.HDMI_CABLE));
		list.add(new ItemStack(ItemHandler.PHONE));
		for(int i = 0; i < 17; i++) {
			list.add(new ItemStack(ItemHandler.HEADPHONES, 1, i));
		}
	}

}
