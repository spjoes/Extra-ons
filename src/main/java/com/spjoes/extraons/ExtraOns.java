package com.spjoes.extraons;

import com.mrcrayfish.device.api.ApplicationManager;
import com.mrcrayfish.device.api.app.Application;
import com.spjoes.extraons.apps.ApplicationClicker;
import com.spjoes.extraons.apps.karaoke.ApplicationKaraoke;
import com.spjoes.extraons.items.CreativeTabExtraons;
import com.spjoes.extraons.items.ItemHandler;
import com.spjoes.extraons.proxies.CommonProxy;
import com.spjoes.extraons.recipes.RecipeHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=Constants.MODID, name=Constants.NAME, version=Constants.VERSION, dependencies=Constants.DEPS)
public class ExtraOns {

	public static Application CLICKER, KARAOKE;
	
	@SidedProxy(clientSide = "com.spjoes.extraons.proxies.ClientProxy", serverSide = "com.spjoes.extraons.proxies.CommonProxy")
	private static CommonProxy proxy;
	
	public static CreativeTabs TAB;
	
	@EventHandler
	public static void onPreInit(FMLPreInitializationEvent e) {
		ItemHandler.registerItems();
		RecipeHandler.registerRecipes();
		TAB = new CreativeTabExtraons();
		
		MinecraftForge.EVENT_BUS.register(new Events());
		
		proxy.registerModels();
	}
	
	@EventHandler
	public static void onInit(FMLInitializationEvent e) {
		CLICKER = ApplicationManager.registerApplication(new ResourceLocation(Constants.MODID, "clicker"), ApplicationClicker.class);
		KARAOKE = ApplicationManager.registerApplication(new ResourceLocation(Constants.MODID, "karaoke"), ApplicationKaraoke.class);
	}
	 
}
