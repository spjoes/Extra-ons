package com.split.extraons.items;

import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;

public class DyingKitItem extends Item {

    private final DyeColor color;

    public DyingKitItem(Settings settings, DyeColor color) {
        super(settings);
        this.color = color;
    }

    public DyeColor getDyeColor() {
        return this.color;
    }


}
