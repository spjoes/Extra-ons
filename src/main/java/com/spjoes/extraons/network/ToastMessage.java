package com.spjoes.extraons.network;

import com.google.common.base.Joiner;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.toasts.GuiToast;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

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
