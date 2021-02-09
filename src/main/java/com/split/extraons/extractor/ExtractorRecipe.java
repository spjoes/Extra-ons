package com.split.extraons.extractor;

import com.split.extraons.Main;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public class ExtractorRecipe extends AbstractCookingRecipe {
    //Same for the BlockEntity, we don't need some of the parameters in the constructor since we already now the type
    public ExtractorRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(Main.EXTRACTOR_RECIPE_TYPE, id, group, input, output, experience, cookTime);
    }

    @Override
    public ItemStack getRecipeKindIcon() {
        return new ItemStack(Items.BLACKSTONE);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        //The Serializer will be created later
        return Main.EXTRACTOR_RECIPE_SERIALIZER;
    }
}