package com.spjoes.extraons.items;

import com.mrcrayfish.device.block.BlockDevice;
import com.spjoes.extraons.ExtraOns;
import com.spjoes.extraons.UsefulStuff;
import com.spjoes.extraons.blocks.BlockHandler;
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
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemHDMICable extends Item {

	public ItemHDMICable() {
		this.setMaxStackSize(1);
		this.setRegistryName("hdmi_cable");
		this.setUnlocalizedName("hdmi_cable");
	}
	
	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		IBlockState blockState = world.getBlockState(pos);
		Block block = blockState.getBlock();
		ItemStack stack = player.getHeldItem(hand);
		if(block == BlockHandler.CENTRAL_UNIT) {
			if(stack.hasTagCompound()) {
				sendGameInfoMessage(player, "hdmi_cable.infos.not_monitor");
			} else {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setIntArray("pos", UsefulStuff.toIntArray(pos));
				stack.setTagCompound(nbt);
				sendGameInfoMessage(player, "hdmi_cable.infos.go_click_monitor");
			}
		} else if(block == BlockHandler.MONITOR) {
			if(!stack.hasTagCompound()) {
				sendGameInfoMessage(player, "hdmi_cable.infos.not_central_unit");
			} else {
				BlockPos posFromNBT = UsefulStuff.toBlockPos(stack.getTagCompound().getIntArray("pos"));
				try {
					TileEntityMonitor monitor = (TileEntityMonitor) world.getTileEntity(pos);
					TileEntityCentralUnit centralUnit = (TileEntityCentralUnit) world.getTileEntity(posFromNBT);
					
					monitor.link(posFromNBT);
					centralUnit.link(pos);
					
					stack.shrink(1);
					sendToastMessage(player, "hdmi_cable.infos.success");
				} catch(Exception e) {
					sendGameInfoMessage(player, "hdmi_cable.infos.fail");
					stack.setTagCompound(null);
				}
			}
		} else if(block instanceof BlockDevice) {
			sendGameInfoMessage(player, "hdmi_cable.infos.not_device", block.getUnlocalizedName());
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
