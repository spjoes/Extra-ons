package com.spjoes.extraons;

import com.mrcrayfish.device.api.ApplicationManager;
import com.mrcrayfish.device.api.app.Application;
import com.spjoes.extraons.apps.ApplicationClicker;
import com.spjoes.extraons.apps.karaoke.ApplicationKaraoke;
import com.spjoes.extraons.handlers.BlockHandler;
import com.spjoes.extraons.client.GuiHandler;
import com.spjoes.extraons.items.CreativeTabExtraons;
import com.spjoes.extraons.handlers.ItemHandler;
import com.spjoes.extraons.network.ToastMessage;
import com.spjoes.extraons.network.ToastMessageHandler;
import com.spjoes.extraons.proxies.CommonProxy;
import com.spjoes.extraons.handlers.TileEntityHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid=Constants.MODID, name=Constants.NAME, version=Constants.VERSION, dependencies=Constants.DEPS, acceptedMinecraftVersions=Constants.MCVER)
public class ExtraOns {

	public static Application CLICKER, KARAOKE, CUSETTINGS;
	
	@SidedProxy(clientSide = "com.spjoes.extraons.proxies.ClientProxy", serverSide = "com.spjoes.extraons.proxies.CommonProxy")
	private static CommonProxy proxy;
	
	@Instance
	public static ExtraOns INSTANCE;
	
	public static CreativeTabs TAB;
	
	public static final SimpleNetworkWrapper wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Constants.MODID);
	
	@EventHandler
	public static void onPreInit(FMLPreInitializationEvent e) {
		BlockHandler.registerBlocks();
		ItemHandler.registerItems();
		TileEntityHandler.registerTileEntities();
		TAB = new CreativeTabExtraons();

		
		proxy.registerModels();
		proxy.registerTERenders();
		
		wrapper.registerMessage(ToastMessageHandler.class, ToastMessage.class, 0, Side.CLIENT);
	}
	
	@EventHandler
	public static void onInit(FMLInitializationEvent e) {
		CLICKER = ApplicationManager.registerApplication(new ResourceLocation(Constants.MODID, "clicker"), ApplicationClicker.class);
		KARAOKE = ApplicationManager.registerApplication(new ResourceLocation(Constants.MODID, "karaoke"), ApplicationKaraoke.class);
		
		proxy.registerItemColors();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());
	}
	 
}
