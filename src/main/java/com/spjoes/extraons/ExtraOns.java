package com.spjoes.extraons;

import com.mrcrayfish.device.api.ApplicationManager;
import com.mrcrayfish.device.api.app.Application;
import com.spjoes.extraons.apps.karaoke.ApplicationKaraoke;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid=Constants.MODID, name=Constants.NAME, version=Constants.VERSION, dependencies=Constants.DEPS)
public class ExtraOns {

	public static Application CLICKER, KARAOKE;
	
	@EventHandler
	public static void onInit(FMLInitializationEvent e) {
		CLICKER = ApplicationManager.registerApplication(new ResourceLocation(Constants.MODID, "clicker"), ApplicationClicker.class);
		KARAOKE = ApplicationManager.registerApplication(new ResourceLocation(Constants.MODID, "karaoke"), ApplicationKaraoke.class);
	}
	 
}