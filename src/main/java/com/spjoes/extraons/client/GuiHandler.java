package com.spjoes.extraons.client;

import com.spjoes.extraons.tileentities.TileEntityCentralUnit;
import com.spjoes.extraons.tileentities.TileEntityMonitor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case 0:
			TileEntity te1 = world.getTileEntity(new BlockPos(x, y, z));
			if(te1 instanceof TileEntityMonitor) {
				TileEntityMonitor monitor = (TileEntityMonitor) te1;
				BlockPos pos = monitor.getCentralUnitPos();
				if(pos == null) {
					return new GuiMonitorNoCU();
				} else {
					TileEntity te2 = world.getTileEntity(monitor.getCentralUnitPos());
					if(te2 instanceof TileEntityCentralUnit) {
						return new GuiMonitor((TileEntityCentralUnit) te2);
					}
				}
			}
			return null;
		case 1:
			return new GuiPhone();
		default:
			return null;
		}
	}

}
