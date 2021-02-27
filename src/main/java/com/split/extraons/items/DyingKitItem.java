package com.split.extraons.items;

import com.split.extraons.Main;

import com.split.extraons.WorldDyable;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.split.extraons.blocks.GamingChairBlock.COLORID;

public class DyingKitItem extends Item {

    private final DyeColor color;

    public DyingKitItem(Settings settings, DyeColor color) {
        super(settings);
        this.color = color;
    }

    public DyeColor getDyeColor() {
        return this.color;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity playerEntity = context.getPlayer();
        ItemStack stack = context.getStack();
        BlockPos blockPos = context.getBlockPos();
        World world = context.getWorld();
        BlockState blockState = world.getBlockState(blockPos);
        if (playerEntity.isSneaking()) {
            System.out.println("sneaking");
            System.out.println(stack.getItem());
            System.out.println(stack.getItem() instanceof DyingKitItem);
            if(blockState.getBlock() instanceof WorldDyable) {
                if (stack.getItem() instanceof DyingKitItem) {
                    System.out.println("kit");
                    world.playSound(null, blockPos, Main.PAINT_SPLASH_EVENT, SoundCategory.BLOCKS, 0.3f, 1f);
                    world.setBlockState(blockPos, blockState.with(COLORID, ((DyingKitItem) stack.getItem()).getDyeColor()), 3);
                    stack.decrement(1);
                    return ActionResult.CONSUME;
                }
            }
        }
        return ActionResult.PASS;
    }
}
