package com.spjoes.extraons.tileentities;

import java.lang.reflect.Field;

import com.spjoes.extraons.Constants;
import com.spjoes.extraons.handlers.BlockHandler;
import com.spjoes.extraons.blocks.BlockTV;
import com.spjoes.extraons.blocks.BlockTV.TVChannels;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class TileEntityTV extends TileEntity {
	
	private int channelId = 0; //Just because I'm lazy to check whever it's on or not
	
	public void next() {
		this.channelId ++;
		if(this.channelId == BlockTV.TVChannels.count()) {
			this.channelId = 1;
		}
		this.updateState();
	}
	
	public void shutdown() {
		this.channelId = 0;
		this.updateState();
	}
	
	private void updateState() {
		TVChannels channel = BlockTV.TVChannels.get(this.channelId);
		this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).withProperty(BlockTV.CHANNEL, channel));
		if(this.world.isRemote) {
			try {
				TextureAtlasSprite atlas = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(Constants.MODID + ":blocks/tv/" + channel.getTextureName());
				if(atlas != null) {
					String fieldName = ObfuscationReflectionHelper.remapFieldNames(TextureAtlasSprite.class.getName(), "field_110973_g")[0];
					Field f = TextureAtlasSprite.class.getDeclaredField(fieldName);
					f.setAccessible(true);
					f.setInt(atlas, 0);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != BlockHandler.TV || newState.getBlock() != BlockHandler.TV;
	}

}
