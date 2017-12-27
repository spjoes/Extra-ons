package com.spjoes.extraons.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockHandler {

	public static Block MONITOR;
	
	public static void registerBlocks() {
		MONITOR = new BlockMonitor();
		ForgeRegistries.BLOCKS.register(MONITOR);
	}
	
}
