package com.spjoes.extraons.items;

import com.mrcrayfish.device.block.BlockDevice;
import com.spjoes.extraons.UsefulStuff;
import com.spjoes.extraons.blocks.BlockHandler;
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

public class ItemHeadPhonesBlack extends Item {

	public ItemHeadPhonesBlack() {
		this.setMaxStackSize(1);
		this.setRegistryName("headphone_black");
		this.setUnlocalizedName("headphone_black");
	}
	
}
