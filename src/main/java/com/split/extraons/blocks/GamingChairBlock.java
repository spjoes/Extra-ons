package com.split.extraons.blocks;

import com.split.extraons.gamingchair.GamingChairSitEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
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

import static com.split.extraons.Main.SIT_ENTITY_TYPE;

public class GamingChairBlock extends HorizontalFacingBlock {

    public static final BooleanProperty OCCUPIED;
    public static final EnumProperty<DyeColor> COLORID = EnumProperty.of("color", DyeColor.class);
    //ColorID 1 = Yellow

    public GamingChairBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(COLORID, DyeColor.WHITE));
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(OCCUPIED, false).with(COLORID, DyeColor.WHITE));
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult) {
        if (!world.isClient) {
            if (!blockState.get(OCCUPIED) && !playerEntity.isSneaking()) {
                GamingChairSitEntity sit = SIT_ENTITY_TYPE.create(world);
                Vec3d pos = new Vec3d(blockHitResult.getBlockPos().getX() + 0.5D, blockHitResult.getBlockPos().getY() + 0.25D, blockHitResult.getBlockPos().getZ() + 0.5D);

                world.setBlockState(blockPos, blockState.with(OCCUPIED, true));
                sit.updatePosition(pos.getX(), pos.getY(), pos.getZ());
                sit.setColor(blockState.get(COLORID));

                world.spawnEntity(sit);
                playerEntity.startRiding(sit);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
        stateManager.add(COLORID);
        stateManager.add(Properties.OCCUPIED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ctx) {
        Direction dir = state.get(FACING);
        switch (dir) {
            case NORTH:
                return VoxelShapes.union(VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f));
            case SOUTH:
                return VoxelShapes.union(VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f));
            case EAST:
                return VoxelShapes.union(VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f));
            case WEST:
                return VoxelShapes.union(VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f));
            default:
                return VoxelShapes.fullCube();
        }
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }

    static {
        OCCUPIED = Properties.OCCUPIED;
    }
}
