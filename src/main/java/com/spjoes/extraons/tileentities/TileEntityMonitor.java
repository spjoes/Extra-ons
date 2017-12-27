package com.spjoes.extraons.tileentities;

import com.mrcrayfish.device.tileentity.TileEntityDevice;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityMonitor extends TileEntityDevice {
	
	@Override
	public String getDeviceName() {
		return "Monitor";
	}

	@Override
	public void update() {
		
	}

	@Override
	public NBTTagCompound writeSyncTag() {
		return new NBTTagCompound();
	}

}
