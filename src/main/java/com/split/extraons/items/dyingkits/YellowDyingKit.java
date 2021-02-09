package com.split.extraons.items.dyingkits;

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

import static com.split.extraons.blocks.GamingChairBlock.COLORID;

public class YellowDyingKit extends Item {
    public YellowDyingKit(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand, BlockPos pos, BlockState blockState) {
        if (blockState.getBlock() == Main.PLAIN_GAMING_CHAIR_BLOCK) {
            world.setBlockState(pos, blockState.with(COLORID, 1));
        }
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
