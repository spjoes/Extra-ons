package com.spjoes.extraons.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockHandler {

	public static Block MONITOR, CENTRAL_UNIT, MOUSE, TV, CONSOLE_CREEPER;
	
	public static void registerBlocks() {
		MONITOR = new BlockMonitor();
		CENTRAL_UNIT = new BlockCentralUnit();
		MOUSE = new BlockMouse();
		TV = new BlockTV();
		CONSOLE_CREEPER = new BlockConsoleCreeper();
		
		ForgeRegistries.BLOCKS.register(MONITOR);
		ForgeRegistries.BLOCKS.register(CENTRAL_UNIT);
		ForgeRegistries.BLOCKS.register(MOUSE);
		ForgeRegistries.BLOCKS.register(TV);
		ForgeRegistries.BLOCKS.register(CONSOLE_CREEPER);
	}
	
}
