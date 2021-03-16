package com.split.extraons.toast;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.LiteralText;

public class ToastExecutor {
    public static void executeLinkToast(String linkableItem, String isLinked, String linkedItem, String toFrom){

        if(isLinked == "unlinked"){
            toFrom = " from ";
        } else if (isLinked == "linked") {
            toFrom = " to ";
        }

        SystemToast toast = SystemToast.create(MinecraftClient.getInstance(), SystemToast.Type.TUTORIAL_HINT,
                new LiteralText("Link Succeeded"), new LiteralText(linkableItem + " successfully " + isLinked + toFrom + linkedItem + "!"));
        MinecraftClient.getInstance().getToastManager().add(toast);
    }
}