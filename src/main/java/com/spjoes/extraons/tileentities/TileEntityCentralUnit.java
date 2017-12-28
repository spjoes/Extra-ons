package com.spjoes.extraons.tileentities;

import com.mrcrayfish.device.tileentity.TileEntityLaptop;
import com.spjoes.extraons.UsefulStuff;
import com.spjoes.extraons.blocks.BlockCentralUnit;
import com.spjoes.extraons.blocks.BlockHandler;
import com.spjoes.extraons.items.ItemHandler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class TileEntityCentralUnit extends TileEntityLaptop {

	private BlockPos monitorPos;
	
	private String name = "Computer";
	private boolean isOn = false;
	private int bootTimer = 0; // In ticks, holds whenever you can turn it on/off
	
	public static final int BOOT_ON_TIME = 7*20;
	public static final int BOOT_OFF_TIME = 3*20;
	
	@Override
	public void update() {
		if(this.world != null && !this.world.isRemote && this.monitorPos != null) {
			if(!this.isCorrectPos(this.monitorPos)) {
				this.world.spawnEntity(new EntityItem(this.world, this.monitorPos.getX(), this.monitorPos.getY(), this.monitorPos.getZ(), new ItemStack(ItemHandler.HDMI_CABLE)));
				this.monitorPos = null;
			}
		}
		
		if(this.bootTimer > 0) {
			this.bootTimer --;
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
		if(this.bootTimer == 0) {
			this.isOn = !this.isOn;
			this.bootTimer = this.isOn ? BOOT_ON_TIME : BOOT_OFF_TIME;
			this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).withProperty(BlockCentralUnit.ON, this.isOn));
		}
	}
	
	public int getBootTimer() {
		return this.bootTimer;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		return super.writeToNBT(this.writeSyncTag());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if(compound.hasKey("monitorPos", Constants.NBT.TAG_INT_ARRAY)) {
			this.monitorPos = UsefulStuff.toBlockPos(compound.getIntArray("monitorPos"));
		}
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
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != BlockHandler.CENTRAL_UNIT || newState.getBlock() != BlockHandler.CENTRAL_UNIT;
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
