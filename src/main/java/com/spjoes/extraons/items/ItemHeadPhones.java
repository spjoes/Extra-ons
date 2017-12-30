package com.spjoes.extraons.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.EnumHelper;

public class ItemHeadPhones extends ItemArmor {

	private static final ArmorMaterial MAT = EnumHelper.addArmorMaterial("headset", "", 0, new int[] {0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
	
	public ItemHeadPhones() {
		super(MAT, 0, EntityEquipmentSlot.HEAD);
		this.setMaxStackSize(1);
		this.setRegistryName("headphones");
		this.setUnlocalizedName("headphones");
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		return new ModelHeadset();
	}
	
	public static class ModelHeadset extends ModelBiped {
		
		public ModelHeadset() {
			super(1.0f);
		}
		
	}

}
