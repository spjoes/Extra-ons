package com.split.extraons.blocks;

import com.split.extraons.Main;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

public class TVBlock extends HorizontalFacingBlock {

    public static final IntProperty CHANNELNUMBER = IntProperty.of("channel", 0, 4);
    public int ChannelID = 0;

    public TVBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(CHANNELNUMBER, 0));
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(CHANNELNUMBER);
        stateManager.add(Properties.HORIZONTAL_FACING);
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            ChannelID++;
            world.playSound(null, pos, Main.TV_CLICK_EVENT, SoundCategory.BLOCKS, 0.5f, 1f);

            if (player.isSneaking()) {
                this.ChannelID = 0;
                world.setBlockState(pos, state.with(CHANNELNUMBER, 0));
            } else {
                if (ChannelID == 0) {
                    world.setBlockState(pos, state.with(CHANNELNUMBER, 0));
                }

                if (ChannelID == 1) {
                    world.setBlockState(pos, state.with(CHANNELNUMBER, 1));
                }

                if (ChannelID == 2) {
                    world.setBlockState(pos, state.with(CHANNELNUMBER, 2));
                }

                if (ChannelID == 3) {
                    world.setBlockState(pos, state.with(CHANNELNUMBER, 3));
                }

                if (ChannelID == 4) {
                    this.ChannelID = 1;
                    world.setBlockState(pos, state.with(CHANNELNUMBER, 1));
                }
            }
        }
        return ActionResult.SUCCESS;
    }


//    @Override
//    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
//        return VoxelShapes.cuboid(0f, 0f, 0.6f, 1f, 1.0f, 0.5f);
//    }

    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos) {
        Direction dir = state.get(FACING);
        switch(dir) {
            case NORTH:
                return VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.5f);
            case SOUTH:
                return VoxelShapes.cuboid(0.0f, 0.0f, 0.5f, 1.0f, 1.0f, 1.0f);
            case EAST:
                return VoxelShapes.cuboid(0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            case WEST:
                return VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 0.5f, 1.0f, 1.0f);
            default:
                return VoxelShapes.fullCube();
        }
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }

}

