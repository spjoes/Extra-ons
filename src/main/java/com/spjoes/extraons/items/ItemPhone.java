package com.spjoes.extraons.items;

import com.spjoes.extraons.ExtraOns;
import com.spjoes.extraons.client.GuiPhone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemPhone extends Item {

    public ItemPhone() {
        this.setMaxStackSize(1);
        this.setRegistryName("phone");
        this.setUnlocalizedName("phone");
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
            BlockPos pos = playerIn.getPosition();
            Minecraft.getMinecraft().displayGuiScreen(new GuiPhone(pos, worldIn));
            //playerIn.openGui(ExtraOns.INSTANCE, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }
    
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
            Minecraft.getMinecraft().displayGuiScreen(new GuiPhone(pos, worldIn));
        return EnumActionResult.SUCCESS;
    }
    
}