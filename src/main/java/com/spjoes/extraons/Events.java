package com.spjoes.extraons;

import com.spjoes.extraons.recipes.RecipeHandler;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class Events {

	@SubscribeEvent
	public void onPlayerJoining(PlayerLoggedInEvent e) {
		e.player.unlockRecipes(RecipeHandler.RECIPES); // Gimme those recipes
	}
	
}
