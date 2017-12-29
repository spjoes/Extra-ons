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
		list.add(new ItemStack(ItemHandler.HEADPHONEBLACK));
		list.add(new ItemStack(ItemHandler.HEADPHONEBLUE));
		list.add(new ItemStack(ItemHandler.HEADPHONELIME));
		list.add(new ItemStack(ItemHandler.HEADPHONEORANGE));
		list.add(new ItemStack(ItemHandler.HEADPHONEPINK));
		list.add(new ItemStack(ItemHandler.HEADPHONEPURPLE));
		list.add(new ItemStack(ItemHandler.HEADPHONEYELLOW));
		list.add(new ItemStack(ItemHandler.HEADPHONERED));
		list.add(new ItemStack(ItemHandler.HEADPHONEWHITE));
		list.add(new ItemStack(ItemHandler.HEADPHONERAINBOW));
	}

}
