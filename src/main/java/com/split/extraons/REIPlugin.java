package com.split.extraons;

import me.shedaniel.rei.api.*;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class REIPlugin implements REIPluginV0 {
    @Override
    public Identifier getPluginIdentifier() {
        return new Identifier("extraons:rei_plugin");
    }

    @Override
    public void registerOthers(RecipeHelper recipeHelper) {
        // recipeHelper.registerWorkingStations(BuiltinPlugin.SMELTING, EntryStack.create(Main.EXTRACTOR_BLOCK));
    }
}
