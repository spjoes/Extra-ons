package com.spjoes.extraons.items;

import net.minecraft.inventory.EntityEquipmentSlot;

public class ItemMic extends ItemLinkable {

	public ItemMic() {
		super();
		this.setRegistryName("microphone");
		this.setUnlocalizedName("microphone");

	}

	@Override
	public EntityEquipmentSlot[] getCorrectSlots() {
		return new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND};
	}
	
}
