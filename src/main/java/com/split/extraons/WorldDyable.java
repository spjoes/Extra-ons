package com.split.extraons;

import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.DyeColor;

public interface WorldDyable {

    EnumProperty<DyeColor> COLORID = EnumProperty.of("color", DyeColor.class);

}
