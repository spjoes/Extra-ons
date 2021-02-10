package com.split.extraons.entities;

import com.split.extraons.blocks.GamingChairBlock;
import com.split.extraons.blocks.MonitorBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class MonitorEntity extends Entity {

    private DyeColor monitorColor;

    public MonitorEntity(EntityType<?> type, World world) {
        super(type, world);
    }


    @Override
    protected void initDataTracker() {

    }

    @Override
    public void tick()
    {
        super.tick();

        if(!world.isClient || world.getBlockState(getBlockPos()).isAir())
        {
            Block block = world.getBlockState(getBlockPos()).getBlock();

            if (block instanceof MonitorBlock) {
                MonitorBlock cb = (MonitorBlock) block;
                Direction facing = world.getBlockState(getBlockPos()).get(GamingChairBlock.FACING);
                world.setBlockState(getBlockPos(), cb.getDefaultState().with(MonitorBlock.FACING, facing).with(MonitorBlock.COLORID, monitorColor));
            }

            remove();
        }
    }


    @Override
    protected void readCustomDataFromTag(CompoundTag tag) {
        tag.putInt("color", monitorColor.getId());
    }

    @Override
    protected void writeCustomDataToTag(CompoundTag tag) {
        monitorColor = DyeColor.byId(tag.getInt("color"));
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return null;
    }

    public void setColor(DyeColor color) {
        this.monitorColor = color;
    }

}
