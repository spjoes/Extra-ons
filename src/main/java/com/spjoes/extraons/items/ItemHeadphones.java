package com.spjoes.extraons.items;

import java.util.List;

import com.google.common.base.Predicates;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemHeadphones extends ItemLinkable {

	public ItemHeadphones() {
		super();
		this.setRegistryName("headphones");
		this.setUnlocalizedName("headphones");
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, new IBehaviorDispenseItem() {	
			@Override
			public ItemStack dispense(IBlockSource source, ItemStack stack) {
				BlockPos blockpos = source.getBlockPos().offset((EnumFacing)source.getBlockState().getValue(BlockDispenser.FACING));
		        List<EntityLivingBase> list = source.getWorld().<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(blockpos), Predicates.and(EntitySelectors.NOT_SPECTATING, new EntitySelectors.ArmoredMob(stack)));

		        if (list.isEmpty()) {
		        	return ItemStack.EMPTY;
		        } else {
		        	EntityLivingBase entitylivingbase = list.get(0);
		        	EntityEquipmentSlot entityequipmentslot = EntityEquipmentSlot.HEAD;
		        	ItemStack itemstack = stack.splitStack(1);
		        	entitylivingbase.setItemStackToSlot(entityequipmentslot, itemstack);

		        	if(entitylivingbase instanceof EntityLiving) {
		        		((EntityLiving)entitylivingbase).setDropChance(entityequipmentslot, 2.0F);
		        	}

		        	return stack;
		        }
			}
		});
	}
	
	//ItemArmor
	
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
	
	@Override
	public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity) {
		return armorType == EntityEquipmentSlot.HEAD;
	}
	
}
