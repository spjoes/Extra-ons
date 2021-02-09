package com.split.extraons.items;

import com.split.extraons.Main;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HDMICableItem extends Item {

    private boolean isConsoleCreeper = false;

    public HDMICableItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        ItemStack stack = playerEntity.getStackInHand(hand);
        if (block == Main.CONSOLE_CREEPER) {
            isConsoleCreeper = true;
        }
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
            return isConsoleCreeper;
    }
}
