package com.spjoes.extraons.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockHandler {

	public static Block MONITOR, CENTRAL_UNIT, MOUSE, TV, CONSOLE, KEYBOARD, CHAIR, MACHINE;
	
	public static void registerBlocks() {
		MONITOR = new BlockMonitor();
		CENTRAL_UNIT = new BlockCentralUnit();
		MOUSE = new BlockMouse();
		TV = new BlockTV();
		CONSOLE = new BlockConsole();
		KEYBOARD = new BlockKeyboard();
		CHAIR = new BlockChair();
		MACHINE = new BlockMachine();
		
		ForgeRegistries.BLOCKS.register(MONITOR);
		ForgeRegistries.BLOCKS.register(CENTRAL_UNIT);
		ForgeRegistries.BLOCKS.register(MOUSE);
		ForgeRegistries.BLOCKS.register(TV);
		ForgeRegistries.BLOCKS.register(CONSOLE);
		ForgeRegistries.BLOCKS.register(KEYBOARD);
		ForgeRegistries.BLOCKS.register(CHAIR);
		ForgeRegistries.BLOCKS.register(MACHINE);
	}
	
}
