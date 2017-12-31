package com.spjoes.extraons.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockHandler {

	public static Block MONITOR, CENTRAL_UNIT, MOUSE;
	
	public static void registerBlocks() {
		MONITOR = new BlockMonitor();
		CENTRAL_UNIT = new BlockCentralUnit();
		MOUSE = new BlockMouse();
		
		ForgeRegistries.BLOCKS.register(MONITOR);
		ForgeRegistries.BLOCKS.register(CENTRAL_UNIT);
		ForgeRegistries.BLOCKS.register(MOUSE);
	}
	
}
