package com.spjoes.extraons.items;

import java.lang.reflect.Field;
import java.util.List;

import com.mrcrayfish.device.api.app.component.Text;
import com.mrcrayfish.device.init.DeviceBlocks;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class ItemHeadphones extends ItemLinkable {

	public ItemHeadphones() {
		super();
		this.setRegistryName("headphones");
		this.setUnlocalizedName("headphones");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if(!worldIn.isRemote && !playerIn.isSneaking()) {
			if(playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty()) {
				playerIn.setItemStackToSlot(EntityEquipmentSlot.HEAD, stack.copy());
				if(!playerIn.capabilities.isCreativeMode) {
					playerIn.setHeldItem(handIn, ItemStack.EMPTY);
				}
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
			} else {
				System.out.println("Already something on your head");
				return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public EntityEquipmentSlot[] getCorrectSlots() {
		return new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD};
	}
	
}
