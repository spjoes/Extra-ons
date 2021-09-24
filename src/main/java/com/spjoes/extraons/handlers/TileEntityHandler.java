package com.spjoes.extraons.handlers;

import com.spjoes.extraons.tileentities.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class TileEntityHandler {

	public static void registerTileEntities() {
		TileEntity.register("monitor", TileEntityMonitor.class);
		TileEntity.register("central_unit", TileEntityCentralUnit.class);
		TileEntity.register("tv", TileEntityTV.class);
		TileEntity.register("console", TileEntityConsole.class);
		TileEntity.register("chair", TileEntityChair.class);
	}

}
