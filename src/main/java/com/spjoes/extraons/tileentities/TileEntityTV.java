package com.spjoes.extraons.tileentities;

import com.spjoes.extraons.blocks.BlockHandler;
import com.spjoes.extraons.blocks.BlockTV;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityTV extends TileEntity {
	
	private int channelId = -1; //Just because I'm lazy to check whever it's on or not
	
	public void next() {
		System.out.println("next");
		this.channelId ++;
		if(this.channelId == BlockTV.TVChannels.count()) {
			this.channelId = 0;
		}
		IBlockState state = this.world.getBlockState(this.pos);
		if(!state.getValue(BlockTV.ON)) {
			state = state.withProperty(BlockTV.ON, true);
		}
		//System.out.println(this.channelId);
		this.world.setBlockState(this.pos, state.withProperty(BlockTV.CHANNEL, BlockTV.TVChannels.get(this.channelId)));
	}
	
	public void shutdown() {
		this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).withProperty(BlockTV.ON, false));
		this.channelId = -1;
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != BlockHandler.TV || newState.getBlock() != BlockHandler.TV;
	}

}
