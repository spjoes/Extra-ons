package com.split.extraons.blocks;

import com.split.extraons.entities.MonitorEntity;
import com.split.extraons.gamingchair.GamingChairSitEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import static com.split.extraons.Main.NON_SIT_ENTITY_TYPE;

public class MonitorBlock extends HorizontalFacingBlock {

    public static final EnumProperty<DyeColor> COLORID = EnumProperty.of("color", DyeColor.class);
    private DyeColor chairColor;

    public MonitorBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(COLORID, DyeColor.WHITE));
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult) {
        if (!world.isClient) {

            if (!playerEntity.isSneaking()) {
                MonitorEntity sit = NON_SIT_ENTITY_TYPE.create(world);
                sit.setColor(blockState.get(COLORID));
                world.spawnEntity(sit);

                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
        stateManager.add(COLORID);
    }


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

