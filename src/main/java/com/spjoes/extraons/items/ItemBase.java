package com.spjoes.extraons.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item {

    public ItemBase(int maxStackSize, String name) {
        this.setMaxStackSize(maxStackSize);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }
}