package com.spjoes.extraons.blocks;

import com.spjoes.extraons.ExtraOns;
import com.spjoes.extraons.tileentities.TileEntityConsole;
import com.spjoes.extraons.tileentities.TileEntityMonitor;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockConsole extends Block implements ITileEntityProvider {
	
	public static final PropertyEnum<EnumConsoleType> TYPE = PropertyEnum.<EnumConsoleType>create("type", EnumConsoleType.class);
	
	public BlockConsole() {
		super(Material.IRON);
		this.setRegistryName("console");
		this.setUnlocalizedName("console");
		this.setDefaultState(this.getBlockState().getBaseState().withProperty(TYPE, EnumConsoleType.UNKNOWN));
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BlockHorizontal.FACING, TYPE);
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
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int DO_NOT_USE_THIS, EntityLivingBase placer, EnumHand hand) {
		ItemStack stack = placer.getHeldItem(hand);
		int meta = stack.getItemDamage();
		return this.getDefaultState().withProperty(BlockHorizontal.FACING, placer.getHorizontalFacing().getOpposite()).withProperty(TYPE, EnumConsoleType.fromMeta(meta));
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te != null && te instanceof TileEntityConsole) {
			((TileEntityConsole)te).setType(state.getValue(TYPE));
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityConsole();
	}
	
	public static enum EnumConsoleType implements IStringSerializable {
		UNKNOWN,
		CREEPER,
		PIG,
		COW;
		
		public static EnumConsoleType fromMeta(int meta) {
			if(meta >= 0 && meta < values().length - 1) {
				return values()[meta + 1];
			}
			return UNKNOWN;
		}
		
		@Override
		public String getName() {
			return name().toLowerCase();
		}
		
		public TileEntity createNewTileEntity(World worldIn, int meta) {
			return new TileEntityMonitor();
		}
		
		public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
			if(worldIn.isRemote) {
				playerIn.openGui(ExtraOns.INSTANCE, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
			}
			return true;
		
	}
    
} }
