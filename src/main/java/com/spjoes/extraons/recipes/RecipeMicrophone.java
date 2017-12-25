package com.spjoes.extraons.recipes;

import com.mrcrayfish.device.init.DeviceItems;
import com.spjoes.extraons.Constants;
import com.spjoes.extraons.items.ItemHandler;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RecipeMicrophone extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

	private int damage;
	private NonNullList<Ingredient> ingredients;
	private static final ItemStack 	BLACK_TERRACOTTA = new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.BLACK.getMetadata()),
									NOTEBLOCK = new ItemStack(Blocks.NOTEBLOCK),
									CABLE = new ItemStack(DeviceItems.ETHERNET_CABLE);
	
	public RecipeMicrophone(int damage) {
		this.damage = damage;
		this.setRegistryName(new ResourceLocation(Constants.MODID, "microphone_" + damage));
		this.ingredients = NonNullList.<Ingredient>from(
			Ingredient.EMPTY,
			Ingredient.EMPTY,
			Ingredient.fromStacks(BLACK_TERRACOTTA),
			Ingredient.fromStacks(NOTEBLOCK),
			Ingredient.fromStacks(BLACK_TERRACOTTA),
			Ingredient.fromStacks(new ItemStack(Blocks.WOOL, 1, this.damage)),
			Ingredient.fromStacks(BLACK_TERRACOTTA),
			Ingredient.fromStacks(CABLE),
			Ingredient.fromStacks(BLACK_TERRACOTTA),
			Ingredient.EMPTY
		);
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		if(!canFit(inv.getWidth(), inv.getHeight())) {
			return false;
		}
		boolean found = false;
		if(inv.getWidth() == 3 && inv.getHeight() == 3) { // Vanilla case
			boolean flag = this.matchesWithOffset(inv, 0, 0);
			return flag;
		}
		for(int offX = 0; offX < inv.getWidth() - 3; offX++) { // In case of modded bigger table
			for(int offY = 0; offY < inv.getHeight() - 3; offY++) {
				if(this.matchesWithOffset(inv, offX, offY)) {
					found = true;
				}
			}
		}
		return found;
	}

	private boolean matchesWithOffset(InventoryCrafting inv, int offX, int offY) {
		if(offX < 0 || offX + 3 > inv.getWidth() || offY < 0 || offY + 3 > inv.getHeight()) {
			return false;
		}
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				Ingredient ingredient = this.ingredients.get(3*y+x);
				ItemStack stack = inv.getStackInRowAndColumn(offX + x, offY + y);
				if(!ingredient.test(stack)) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return this.getRecipeOutput();
	}

	@Override
	public boolean canFit(int width, int height) {
		return width >= 3 && height >= 3;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(ItemHandler.MICROPHONE, 1, this.damage);
	}

	@Override
	public String getGroup() {
		return "microphone";
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.ingredients;
	}
	
}
