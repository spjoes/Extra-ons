package com.split.extraons.extractor;

import com.split.extraons.Main;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;

public class ExtractorScreenHandler extends AbstractFurnaceScreenHandler {
    public ExtractorScreenHandler(int i, PlayerInventory playerInventory) {
        super(Main.EXTRACTOR_SCREEN_HANDLER, Main.EXTRACTOR_RECIPE_TYPE, RecipeBookCategory.FURNACE, i, playerInventory);
    }

    public ExtractorScreenHandler(int i, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(Main.EXTRACTOR_SCREEN_HANDLER, Main.EXTRACTOR_RECIPE_TYPE, RecipeBookCategory.FURNACE, i, playerInventory, inventory, propertyDelegate);
    }
}
