package com.spjoes.extraons.items;

import com.mrcrayfish.device.block.BlockDevice;
import com.spjoes.extraons.ExtraOns;
import com.spjoes.extraons.UsefulStuff;
import com.spjoes.extraons.handlers.BlockHandler;
import com.spjoes.extraons.network.ToastMessage;
import com.spjoes.extraons.tileentities.TileEntityCentralUnit;
import com.spjoes.extraons.tileentities.TileEntityMonitor;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

public class ItemSMB extends Item {

	public ItemSMB(int maxStackSize, String name) {
		this.setMaxStackSize(maxStackSize);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
	}
	
	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		IBlockState blockState = world.getBlockState(pos);
		Block block = blockState.getBlock();
		ItemStack stack = player.getHeldItem(hand);
		if(block == BlockHandler.CONSOLE) {
				try {
					stack.shrink(1);
					player.sendStatusMessage(new TextComponentTranslation("smb.infos.success" , new Object[0]).setStyle(new Style().setColor(TextFormatting.GREEN)), true);
				} catch(Exception e) {
					player.sendStatusMessage(new TextComponentTranslation("smb.infos.fail" , new Object[0]).setStyle(new Style().setColor(TextFormatting.RED)), true);
					stack.setTagCompound(null);
				}
		} else if(block instanceof BlockDevice) {
			player.sendStatusMessage(new TextComponentTranslation("hdmi_cable.infos.not_device", block.getUnlocalizedName() , new Object[0]).setStyle(new Style().setColor(TextFormatting.RED)), true);
			//sendGameInfoMessage(player, "hdmi_cable.infos.not_device", block.getUnlocalizedName());
		}
		return EnumActionResult.SUCCESS;
	}
	
	private void sendGameInfoMessage(EntityPlayer player, String message, Object... args) {
		if(player instanceof EntityPlayerMP) {
			((EntityPlayerMP) player).connection.sendPacket(new SPacketChat(new TextComponentTranslation(message), ChatType.GAME_INFO));
		}
	}
	
	private void sendToastMessage(EntityPlayer player, String message, Object... args) {
		if(player instanceof EntityPlayerMP) {
			String[] strings = new String[args.length + 1];
			strings[0] = message;
			for(int i = 0; i < args.length; i++) {
				strings[i + 1] = args[i].toString();
			}
			ExtraOns.wrapper.sendTo(new ToastMessage(strings), (EntityPlayerMP) player);
		}
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return stack.hasTagCompound();
	}
	
}
