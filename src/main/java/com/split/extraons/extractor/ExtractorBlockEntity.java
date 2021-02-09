package com.split.extraons.extractor;

import com.split.extraons.Main;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;

public class ExtractorBlockEntity extends AbstractFurnaceBlockEntity {
    //Since we already now the BlockEntityType and RecipeType we don't need them in the constructor's parameters
    public ExtractorBlockEntity() {
        super(Main.EXTRACTOR_BLOCK_ENTITY, Main.EXTRACTOR_RECIPE_TYPE);
    }

    @Override
    public Text getContainerName() {
        //you should use a translation key instead but this is easier
        return Text.of("Extractor");
    }

    @Override
    public ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new ExtractorScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}
