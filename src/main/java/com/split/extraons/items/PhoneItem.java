package com.split.extraons.items;

import com.split.extraons.gui.PhoneGui;
import com.split.extraons.gui.PhoneScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class PhoneItem extends Item {

    public PhoneItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient) {
            MinecraftClient.getInstance().openScreen(new PhoneScreen(new PhoneGui()));
        }
        return super.use(world, user, hand);
    }
}
