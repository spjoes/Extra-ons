package com.spjoes.extraons.network;

import com.spjoes.extraons.client.ExtraToast;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ToastMessageHandler implements IMessageHandler<ToastMessage, IMessage> {

	@Override
	public IMessage onMessage(ToastMessage message, MessageContext ctx) {
		String[] msgArgs = message.getArgs();
		String key = msgArgs[0];
		String[] args = new String[msgArgs.length - 1];
		for(int i = 1; i < msgArgs.length; i++) {
			args[i - 1] = msgArgs[i];
		}
		Minecraft.getMinecraft().getToastGui().add(new ExtraToast(key, args));
		return null;
	}
	
}
