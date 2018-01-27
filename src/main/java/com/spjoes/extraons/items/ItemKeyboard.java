package com.spjoes.extraons.items;

import com.spjoes.extraons.ExtraOns;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemKeyboard extends Item {

    public ItemKeyboard() {
        this.setMaxStackSize(1);
        this.setRegistryName("keyboard");
        this.setUnlocalizedName("keyboard");
    }
    
    
}