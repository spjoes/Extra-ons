package com.spjoes.extraons.tileentities;

import com.mrcrayfish.device.tileentity.TileEntityDevice;
import com.spjoes.extraons.UsefulStuff;
import com.spjoes.extraons.blocks.BlockHandler;
import com.spjoes.extraons.items.ItemHandler;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityCentralUnit extends TileEntityDevice {

	private BlockPos monitorPos;
	
	private String name = "Computer";
	private boolean isOn = false;
	
	@Override
	public void update() {
		if(this.world != null && !this.world.isRemote && this.monitorPos != null) {
			if(!this.isCorrectPos(this.monitorPos)) {
				this.world.spawnEntity(new EntityItem(this.world, this.monitorPos.getX(), this.monitorPos.getY(), this.monitorPos.getZ(), new ItemStack(ItemHandler.HDMI_CABLE)));
				this.monitorPos = null;
			}
		}
	}

	@Override
	public String getDeviceName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public boolean isOn() {
		return isOn;
	}
	
	public void toggleOn() {
		this.isOn = !this.isOn;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		if(this.monitorPos != null) {
			compound.setIntArray("monitorPos", UsefulStuff.toIntArray(this.monitorPos));
		}
		return compound;
	}
	
	@Override
	public NBTTagCompound writeSyncTag() {
		NBTTagCompound nbt = new NBTTagCompound();
		if(this.monitorPos != null) {
			nbt.setIntArray("monitorPos", UsefulStuff.toIntArray(this.monitorPos));
		}
		return nbt;
	}
	
	public void link(BlockPos pos) throws IPreferMarioException {
		if(this.isCorrectPos(pos)) {
			this.monitorPos = pos;
		} else {
			throw new IPreferMarioException();
		}
	}
	
	private boolean isCorrectPos(BlockPos pos) {
		return this.world.getBlockState(pos).getBlock() == BlockHandler.MONITOR;
	}
	
	/**
	 * Won't be used but made for the sake of the pun
	 * @author Dbrown55
	 */
	private static class IPreferMarioException extends Exception {
		
		@Override
		public String getMessage() {
			return "Link failed. Reason: I prefer Super Mario over The Legend of Zelda";
		}		
		
	}

}
