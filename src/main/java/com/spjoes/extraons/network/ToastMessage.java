package com.spjoes.extraons.network;

import com.google.common.base.Joiner;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;

public class ToastMessage implements IMessage {

	private static final Joiner joiner = Joiner.on(";");
	private String[] args;
	
	public ToastMessage(String... args) {
		this.args = args;
	}
	
	public ToastMessage() { }

	@Override
	public void fromBytes(ByteBuf buf) {
		String s = ByteBufUtils.readUTF8String(buf);
		this.args = s.split(";");
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, joiner.join(this.args));
	}

	public String[] getArgs() {
		return this.args;
	}
	
}
