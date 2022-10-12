package com.spjoes.extraons.blocks;

import javax.annotation.Nullable;

import com.mrcrayfish.device.entity.EntitySeat;
import com.mrcrayfish.device.object.Bounds;
import com.mrcrayfish.device.tileentity.TileEntityOfficeChair;
import com.mrcrayfish.device.util.SeatUtil;

import com.spjoes.extraons.tileentities.TileEntityChair;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockChair extends Block implements ITileEntityProvider  {
	
	private static final AxisAlignedBB NS = new AxisAlignedBB(5.0/16.0, 0, 4.0/16.0, 11.0/16.0, 3.0/16.0, 12.0/16.0);
	private static final AxisAlignedBB EW = new AxisAlignedBB(4.0/16.0, 0, 5.0/16.0, 12.0/16.0, 3.0/16.0, 11.0/16.0);
	
    public static final PropertyEnum<Type> TYPE = PropertyEnum.create("type", Type.class);
    public static final PropertyEnum<EnumDyeColor> COLORID = PropertyEnum.create("color", EnumDyeColor.class);

    private static final AxisAlignedBB EMPTY_BOX = new Bounds(0, 0, 0, 0, 0, 0).toAABB();
    private static final AxisAlignedBB SELECTION_BOX = new Bounds(1, 0, 1, 15, 27, 15).toAABB();
    private static final AxisAlignedBB SEAT_BOUNDING_BOX = new Bounds(1, 0, 1, 15, 10, 15).toAABB();
	
	public BlockChair() {
		super(Material.IRON);
		this.setRegistryName("chair");
		this.setUnlocalizedName("chair");
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockHorizontal.FACING, EnumFacing.NORTH).withProperty(COLORID, EnumDyeColor.WHITE));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BlockHorizontal.FACING, TYPE, COLORID);
	}

	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        return false;
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(BlockHorizontal.FACING, placer.getHorizontalFacing()).withProperty(COLORID, EnumDyeColor.byMetadata(meta));
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        if(Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.getRidingEntity() instanceof EntitySeat)
        {
            return EMPTY_BOX;
        }
        return SELECTION_BOX;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return SEAT_BOUNDING_BOX;
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote)
        {
            SeatUtil.createSeatAndSit(worldIn, pos, playerIn, 0.5);
        }
        return true;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(TYPE).ordinal() << 2) + state.getValue(BlockHorizontal.FACING).getHorizontalIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.getHorizontal(meta & 0b11)).withProperty(TYPE, Type.values()[((meta & 0b100) >> 2)]);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityChair();
    }

    public enum Type implements IStringSerializable
    {
        LEGS, SEAT;

        @Override
        public String getName()
        {
            return name().toLowerCase();
        }
    }
}
