package com.spjoes.extraons.tileentities;

import com.spjoes.extraons.UsefulStuff;
import com.spjoes.extraons.blocks.BlockHandler;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityMonitor extends TileEntity implements ITickable {

	private BlockPos centralUnitPos;
	
	@Override
	public void update() {
		if(this.world != null && !this.world.isRemote && this.centralUnitPos != null) {
			if(!this.isCorrectPos(this.centralUnitPos)) {
				this.centralUnitPos = null;
			}
		}
	}
	
	public void link(BlockPos pos) /* throws WarpToZeldaException */ {
		if(this.isCorrectPos(pos)) {
			this.centralUnitPos = pos;
		}
	}
	
	private boolean isCorrectPos(BlockPos pos) {
		return this.world.getBlockState(pos).getBlock() == BlockHandler.CENTRAL_UNIT;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		if(this.centralUnitPos != null) {
			compound.setIntArray("centralUnitPos", UsefulStuff.toIntArray(this.centralUnitPos));
		}
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		int[] posFromNBT = compound.getIntArray("centralUnitPos");
		this.centralUnitPos = new BlockPos(posFromNBT[0], posFromNBT[1], posFromNBT[2]);
	}
	
}
