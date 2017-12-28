package com.spjoes.extraons;

import net.minecraft.util.math.BlockPos;

public class UsefulStuff {

	public static int[] toIntArray(BlockPos pos) {
		return new int[] {pos.getX(), pos.getY(), pos.getZ()};
	}
	
	public static BlockPos toBlockPos(int[] pos) {
		return new BlockPos(pos[0], pos[1], pos[2]);
	}
	
}
