package com.split.extraons.gamingchair;


import com.split.extraons.blocks.GamingChairBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class GamingChairSitEntity extends Entity
{

    private DyeColor chairColor;

    public GamingChairSitEntity(EntityType<? extends GamingChairSitEntity> type, World world) {
        super(type, world);
        noClip = true;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {

    }

    @Override
    public Packet<?> createSpawnPacket() {
        return S2CEntitySpawnPacket.createPacket(this);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    public void tick()
    {
        super.tick();

        if(!world.isClient && !hasPassengers() || world.getBlockState(getBlockPos()).isAir())
        {
            Block block = world.getBlockState(getBlockPos()).getBlock();

            if (block instanceof GamingChairBlock) {
                GamingChairBlock cb = (GamingChairBlock) block;
                Direction facing = world.getBlockState(getBlockPos()).get(GamingChairBlock.FACING);
                world.setBlockState(getBlockPos(), cb.getDefaultState().with(GamingChairBlock.OCCUPIED, false).with(GamingChairBlock.FACING, facing).with(GamingChairBlock.COLORID, chairColor));
            }

            remove();
        }
    }

    @Override
    protected void readCustomDataFromTag(CompoundTag tag) {
        tag.putInt("color", chairColor.getId());
    }

    @Override
    protected void writeCustomDataToTag(CompoundTag tag) {
        chairColor = DyeColor.byId(tag.getInt("color"));
    }

    @Override
    protected void removePassenger(Entity passenger) {
        BlockPos pos = this.getBlockPos();
        BlockState state = this.world.getBlockState(pos);
        if(state.getBlock() instanceof GamingChairBlock) {
            Direction d = state.get(GamingChairBlock.FACING);
            passenger.updatePosition(pos.getX() + d.getOffsetX() + 0.5D, pos.getY(), pos.getZ() + d.getOffsetZ() + 0.5D);
        }
        super.removePassenger(passenger);
    }

    @Override
    protected void addPassenger(Entity passenger) {
        BlockPos pos = this.getBlockPos();
        BlockState state = this.world.getBlockState(pos);
        if(state.getBlock() instanceof GamingChairBlock) {
            Direction d = state.get(GamingChairBlock.FACING);
            passenger.setYaw(d.getHorizontal() * 90F);
        }
        super.addPassenger(passenger);
    }

    public void setColor(DyeColor color) {
        this.chairColor = color;
    }
}