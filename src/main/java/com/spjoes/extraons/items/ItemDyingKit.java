package com.spjoes.extraons.items;

import com.mrcrayfish.device.init.DeviceBlocks;
import com.spjoes.extraons.handlers.BlockHandler;
import com.spjoes.extraons.tileentities.TileEntityChair;
import com.spjoes.extraons.tileentities.TileEntityConsole;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.spjoes.extraons.blocks.BlockChair.COLORID;

public class ItemDyingKit extends Item {

    private final EnumDyeColor color;

    public ItemDyingKit(int maxStackSize, String name, EnumDyeColor color) {
        this.color = color;
        this.setMaxStackSize(maxStackSize);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }

    public EnumDyeColor getColor() {
        return this.color;
    }

    //use on block
    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        TileEntity te = world.getTileEntity(pos);
        if(player.isSneaking()) {
            if (world.getBlockState(pos).getBlock() == BlockHandler.CHAIR) {
                if (stack.getItem() instanceof ItemDyingKit) {
                    if(!world.isRemote) {
                        if(te != null && te instanceof TileEntityChair) {
                            world.setBlockState(pos, world.getBlockState(pos).withProperty(COLORID, ((ItemDyingKit) stack.getItem()).getColor()), 3);
                            ((TileEntityChair)te).setColor(world.getBlockState(pos).getValue(COLORID));
                            return EnumActionResult.SUCCESS;
                        }
                    }
                }
            }
        }
        return EnumActionResult.PASS;
    }
}