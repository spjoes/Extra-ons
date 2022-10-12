package com.spjoes.extraons.tileentities;

import java.util.List;

import com.mrcrayfish.device.entity.EntitySeat;
import com.mrcrayfish.device.tileentity.TileEntitySync;

import com.spjoes.extraons.blocks.BlockChair;
import com.spjoes.extraons.blocks.BlockConsole;
import com.spjoes.extraons.handlers.BlockHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityChair extends TileEntitySync implements ITickable {
    private EnumDyeColor color = EnumDyeColor.WHITE;

    public EnumDyeColor getColor() {
        return color;
    }
    public void setColor(EnumDyeColor color) {
        this.color = color;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        if(compound.hasKey("color", Constants.NBT.TAG_BYTE))
        {
            color = EnumDyeColor.byMetadata(compound.getByte("color"));
        }
    }

    @Override
    public void update() {
        if(this.world != null && this.color != null) {
            IBlockState state = this.world.getBlockState(this.pos);
            if(state.getValue(BlockChair.COLORID) == EnumDyeColor.WHITE) {
                this.world.setBlockState(this.pos, state.withProperty(BlockChair.COLORID, this.color));
            }
        }
    }

    @Override
    public void onLoad() {
        if(this.world != null && this.color != null) {
            IBlockState state = this.world.getBlockState(this.pos);
            if(state.getValue(BlockChair.COLORID) == EnumDyeColor.WHITE) {
                this.world.setBlockState(this.pos, state.withProperty(BlockChair.COLORID, this.color));
            }
        }
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != BlockHandler.CHAIR || newState.getBlock() != BlockHandler.CHAIR;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setByte("color", (byte) color.getMetadata());
        return compound;
    }

    @Override
    public NBTTagCompound writeSyncTag()
    {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setByte("color", (byte) color.getMetadata());
        return tag;
    }

    @SideOnly(Side.CLIENT)
    public float getRotation()
    {
        List<EntitySeat> seats = world.getEntitiesWithinAABB(EntitySeat.class, new AxisAlignedBB(pos));
        if(!seats.isEmpty())
        {
            EntitySeat seat = seats.get(0);
            if(seat.getControllingPassenger() != null)
            {
                if(seat.getControllingPassenger() instanceof EntityLivingBase)
                {
                    EntityLivingBase living = (EntityLivingBase) seat.getControllingPassenger();
                    living.renderYawOffset = living.rotationYaw;
                    living.prevRenderYawOffset = living.rotationYaw;
                    return living.rotationYaw;
                }
                return seat.getControllingPassenger().rotationYaw;
            }
        }
        return getBlockMetadata() * 90F + 180F;
    }

}