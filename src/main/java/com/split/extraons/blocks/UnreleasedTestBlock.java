package com.split.extraons.blocks;

import com.split.extraons.Main;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class UnreleasedTestBlock extends Block {

    public static final IntProperty HOLDING_LAVA = IntProperty.of("holding_lava", 0, 2);


    public UnreleasedTestBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(HOLDING_LAVA, 0));
    }


    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        if (itemStack.isEmpty()) {
            return ActionResult.PASS;
        } else {
            Item item = itemStack.getItem();
            if (item == Items.LAVA_BUCKET) {
                if(!world.isClient) {
                    if (blockState.get(HOLDING_LAVA) == 1){
                        return ActionResult.PASS;
                    } else {

                        if (!playerEntity.abilities.creativeMode) {
                            playerEntity.setStackInHand(hand, new ItemStack(Items.BUCKET));
                        }
                        world.setBlockState(blockPos, blockState.with(HOLDING_LAVA, 1));
                        world.playSound((PlayerEntity) null, blockPos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    }
                }
                return ActionResult.success(world.isClient);
            }
            else if (item == Items.WATER_BUCKET) {
                if (!world.isClient) {
                    if (blockState.get(HOLDING_LAVA) == 0){
                        return ActionResult.PASS;
                    } else if (blockState.get(HOLDING_LAVA) == 1){

                        if (!playerEntity.abilities.creativeMode) {
                            playerEntity.setStackInHand(hand, new ItemStack(Items.BUCKET));
                        }
                        world.setBlockState(blockPos, blockState.with(HOLDING_LAVA, 2));
                        world.playSound((PlayerEntity) null, blockPos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    }
                }

                return ActionResult.success(world.isClient);
            }
            else if (item == Items.BUCKET) {
                if (!world.isClient) {
                    if (!playerEntity.abilities.creativeMode) {
                        if(blockState.get(HOLDING_LAVA) == 1) {
                            itemStack.decrement(1);
                            if (itemStack.isEmpty()) {
                                playerEntity.setStackInHand(hand, new ItemStack(Items.LAVA_BUCKET));
                            } else if (!playerEntity.inventory.insertStack(new ItemStack(Items.LAVA_BUCKET))) {
                                playerEntity.dropItem(new ItemStack(Items.LAVA_BUCKET), false);
                            }
                        } else {
                            return ActionResult.PASS;
                        }
                    }
                    world.setBlockState(blockPos, blockState.with(HOLDING_LAVA, 0));
                    world.playSound((PlayerEntity)null, blockPos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                return ActionResult.success(world.isClient);
            }
            else if (item == Main.EMPTY_SHARD) {
                if (!world.isClient) {
                    if (!playerEntity.abilities.creativeMode) {
                        if(blockState.get(HOLDING_LAVA) == 2) {
                            itemStack.decrement(1);
                            if (itemStack.isEmpty()) {
                                playerEntity.setStackInHand(hand, new ItemStack(Main.CRUSTEDLAVA_SHARD));
                            } else if (!playerEntity.inventory.insertStack(new ItemStack(Main.CRUSTEDLAVA_SHARD))) {
                                playerEntity.dropItem(new ItemStack(Main.CRUSTEDLAVA_SHARD), false);
                            }
                        } else {
                            return ActionResult.PASS;
                        }
                    }
                    world.setBlockState(blockPos, blockState.with(HOLDING_LAVA, 0));
                    world.playSound((PlayerEntity)null, blockPos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                return ActionResult.success(world.isClient);
            }


        }
        return ActionResult.SUCCESS;
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(HOLDING_LAVA);
    }

}

