package com.split.extraons.items;

import com.split.extraons.Main;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HDMICableItem extends Item {

    public boolean isGlinted = false;
    public boolean isConnectedToCableBox = false;

    public HDMICableItem(Settings settings) {
        super(settings);
    }

//    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand, BlockPos pos) {
//        BlockState blockState = world.getBlockState(pos);
//        Block block = blockState.getBlock();
//        ItemStack stack = playerEntity.getStackInHand(hand);
//        if (block == Main.CABLE_BOX) {
//            playerEntity.sendMessage(new LiteralText("HDMI Cable linked to cable box! Please crouch and right click the TV you would like to link it to"), false);
//            stack.hasGlint();
//            isConnectedToCableBox = true;
//        }
//        if (block == Main.TV) {
//            if (isConnectedToCableBox == true) {
//                playerEntity.sendMessage(new LiteralText("Cable Box successfully linked to TV! Enjoy!"), false);
//
//
//            } else if (isConnectedToCableBox == false) {
//                playerEntity.sendMessage(new LiteralText("Please crouch and right click a cable box first!"), false);
//            }
//        }
//        return TypedActionResult.success(playerEntity.getStackInHand(hand));
//    }

    @Override
    public boolean hasGlint(ItemStack stack) {
            return false;
    }
}
