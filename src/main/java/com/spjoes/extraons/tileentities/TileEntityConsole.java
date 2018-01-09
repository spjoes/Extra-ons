package com.spjoes.extraons.tileentities;

import com.spjoes.extraons.blocks.BlockConsole;
import com.spjoes.extraons.blocks.BlockHandler;
import com.spjoes.extraons.blocks.BlockConsole.EnumConsoleType;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityConsole extends TileEntity implements ITickable {

	private EnumConsoleType type;
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setInteger("type", this.type.ordinal());
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if(compound.hasKey("type")) {
			this.type = EnumConsoleType.fromMeta(compound.getInteger("type"));
		}
	}

	public void setType(EnumConsoleType type) {
		this.type = type;
	}
	
	public EnumConsoleType getType() {
		return this.type;
	}
	
	@Override
	public void update() {
		if(this.world != null && this.type != null) {
			IBlockState state = this.world.getBlockState(this.pos);
			if(state.getValue(BlockConsole.TYPE) == EnumConsoleType.UNKNOWN) {
				this.world.setBlockState(this.pos, state.withProperty(BlockConsole.TYPE, this.type));
			}
		}
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != BlockHandler.CONSOLE || newState.getBlock() != BlockHandler.CONSOLE;
	}
	
}
