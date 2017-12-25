package com.spjoes.extraons.recipes;

import java.util.ArrayList;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class RecipeHandler {

	public static final ArrayList<IRecipe> RECIPES = new ArrayList<IRecipe>();
	
	public static void registerRecipes() {
		for(int i = 0; i < 16; i++) {
			registerRecipe(new RecipeMicrophone(i));
			if(i != 0) {
				//ForgeRegistries.RECIPES.register();
			}
		}
	}
	
	private static void registerRecipe(IRecipe recipe) {
		RECIPES.add(recipe);
		ForgeRegistries.RECIPES.register(recipe);
	}
	
}
