package com.spjoes.extraons.tileentities;

import com.mrcrayfish.device.tileentity.TileEntityLaptop;
import com.spjoes.extraons.UsefulStuff;
import com.spjoes.extraons.blocks.BlockCentralUnit;
import com.spjoes.extraons.blocks.BlockHandler;
import com.spjoes.extraons.client.SoundHandler;
import com.spjoes.extraons.items.ItemHandler;

import io.netty.buffer.Unpooled;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketCustomPayload;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class TileEntityCentralUnit extends TileEntityLaptop {

	private BlockPos monitorPos;
	
	private String name = "Computer";
	private boolean isOn = false;
	private int bootTimer = 0; // In ticks, holds whenever you can turn it on/off
	private int soundLoopTimer = 0; 
	
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
			if(this.bootTimer == 0 && !this.isOn) {
				this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).withProperty(BlockCentralUnit.ON, false));
			}
		}
		
		if(this.isOn && this.bootTimer == 0) {
			this.soundLoopTimer --;
			if(this.soundLoopTimer <= 0) {
				this.world.playerEntities.forEach((pl) -> {
					this.world.playSound(pl, this.pos, SoundHandler.CENTRAL_UNIT_LOOP, SoundCategory.BLOCKS, 1.0f, 1.0f);
				});
				this.soundLoopTimer = 86;
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
		if(this.bootTimer == 0) {
			this.isOn = !this.isOn;
			this.bootTimer = this.isOn ? BOOT_ON_TIME : BOOT_OFF_TIME;
			if(this.world.isRemote) {
				this.world.playerEntities.forEach((pl) -> {
					Minecraft.getMinecraft().getSoundHandler().stop("extraons:central_unit_loop", SoundCategory.BLOCKS);
					this.world.playSound(pl, this.pos, this.isOn ? SoundHandler.CENTRAL_UNIT_BOOT_ON : SoundHandler.CENTRAL_UNIT_BOOT_OFF, SoundCategory.BLOCKS, 1.0f, 1.0f);
				});
			} else {
				if(this.isOn) {
					this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).withProperty(BlockCentralUnit.ON, true));
				}
			}
			this.soundLoopTimer = 0;
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
