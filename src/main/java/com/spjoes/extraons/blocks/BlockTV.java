package com.spjoes.extraons.blocks;

import com.spjoes.extraons.tileentities.TileEntityTV;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTV extends Block implements ITileEntityProvider {
	
	public static final PropertyBool ON = PropertyBool.create("on");
	public static final PropertyEnum CHANNEL = PropertyEnum.<TVChannels>create("channel", TVChannels.class);
	
	public BlockTV() {
		super(Material.IRON);
		this.setRegistryName("tv");
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(BlockHorizontal.FACING, EnumFacing.NORTH)
				.withProperty(ON, false)
				.withProperty(CHANNEL, TVChannels.FREE_REAL_ESTATE));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BlockHorizontal.FACING, ON, CHANNEL);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.getHorizontal(meta));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(BlockHorizontal.FACING).getHorizontalIndex();
	}
	
	@Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }
    
    @Override
    public boolean isFullCube(IBlockState state) {
    	return false;
    }
    
    @Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(BlockHorizontal.FACING, placer.getHorizontalFacing());
	}
	
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    	if(!worldIn.isRemote) {
	    	TileEntity te = worldIn.getTileEntity(pos);
	    	if(te instanceof TileEntityTV) {
	    		TileEntityTV tv = (TileEntityTV) te;
	    		if(playerIn.isSneaking()) {
	    			tv.shutdown();
	    		} else {
	    			tv.next();
	    		}
	    		return true;
	    	}
    	}
    	return true;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
    	return new TileEntityTV();
    }
    
	public static enum TVChannels implements IStringSerializable {
		FREE_REAL_ESTATE,
		WOW_WINK,
		PIG_RIDING,
		DIAMOND,
		HA;
		
		TVChannels() {
			// In case of a sound
		}
		
		@Override
		public String getName() {
			return this.name().toLowerCase();
		}		
	
		public static int count() {
			return values().length;
		}

		public static TVChannels get(int channelId) {
			if(channelId >= 0 && channelId < count()) {
				return values()[channelId];
			}
			return null;
		}
		
	}
}
