package com.split.extraons.items;

import com.split.extraons.Main;
import com.split.extraons.toast.ToastExecutor;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HDMICableItem extends Item {

    public static boolean hasGlint = false;

    public HDMICableItem(Settings settings) {
        super(settings);
    }

//      idfk what I did, or how I got here


//    @Override
//    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
//        if(!world.isClient) {
//            LightningEntity lightning = (LightningEntity)EntityType.LIGHTNING_BOLT.create(world);
//            VillagerEntity villager = (VillagerEntity)EntityType.VILLAGER.create(world);
//            user.addCritParticles(user);
//            lightning.refreshPositionAndAngles(user.getX(), user.getY(), user.getZ(), user.yaw, user.pitch);
//            villager.refreshPositionAndAngles(user.getX(), user.getY(), user.getZ(), user.yaw, user.pitch);
//            world.spawnEntity(villager);
//            world.spawnEntity(lightning);
//        }
//        return super.use(world, user, hand);
//    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity playerEntity = context.getPlayer();
        ItemStack stack = context.getStack();
        BlockPos blockPos = context.getBlockPos();
        World world = context.getWorld();
        BlockState blockState = world.getBlockState(blockPos);
        if(world.isClient) {
            if (playerEntity.isSneaking()) {
                if (blockState.getBlock() == Main.MONITOR) {
                    if (stack.getItem() == this) {
                        if (hasGlint == true) {
                            hasGlint = false;
                            ToastExecutor.executeLinkToast("HDMI Cable","unlinked", "monitor", ".");
                        } else if (hasGlint == false) {
                            hasGlint = true;
                            ToastExecutor.executeLinkToast("HDMI Cable","linked", "monitor", ".");
                        }
                    }
                }
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return hasGlint;
    }
}
