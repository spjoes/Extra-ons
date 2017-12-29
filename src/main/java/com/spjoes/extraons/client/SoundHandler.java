package com.spjoes.extraons.client;

import java.util.ArrayList;

import com.spjoes.extraons.Constants;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class SoundHandler {
	
	public static final SoundEvent CENTRAL_UNIT_BOOT_ON = getSound(Constants.MODID, "central_unit_boot_on");
	public static final SoundEvent CENTRAL_UNIT_BOOT_OFF = getSound(Constants.MODID, "central_unit_boot_off");
	public static final SoundEvent CENTRAL_UNIT_LOOP = getSound(Constants.MODID, "central_unit_loop");
	
	public static SoundEvent getSound(String domain, String name) {
		ResourceLocation rl = new ResourceLocation(domain, name);
		SoundEvent se = new SoundEvent(rl);
		se.setRegistryName(rl);
		return se;
	}
	
	@Mod.EventBusSubscriber(modid = Constants.MODID)
    public static class RegistrationHandler {
		
		private static ArrayList<SoundEvent> TO_REGISTER = new ArrayList<SoundEvent>();
		
		public static void addSound(SoundEvent se) {
			TO_REGISTER.add(se);
		}
		
		@SubscribeEvent
		public static void registerSounds(final RegistryEvent.Register<SoundEvent> event) {
			IForgeRegistry<SoundEvent> registry = event.getRegistry();
			TO_REGISTER.forEach(se -> {
				registry.register(se);
			});
		}
		
	}
	
}
