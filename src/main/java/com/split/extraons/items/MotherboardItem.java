package com.split.extraons.items;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class MotherboardItem extends Item {


    public static boolean hasCPUInstalled = false;
    public static boolean hasRAMInstalled = false;
    public static boolean hasGPUInstalled = false;
    public static boolean hasHDDSSDInstalled = false;
    public static boolean hasCoolerInstalled = false;
    public static boolean hasBatteryInstalled = false;

    public MotherboardItem(Settings settings) {
        super(settings);
    }


    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {

        if(Screen.hasShiftDown()) {

            //CPU
            if (hasCPUInstalled == true) {
                tooltip.add(new TranslatableText("extraons.motherboard.cpu").append(new TranslatableText("extraons.motherboard.state.installed")).formatted(Formatting.GREEN, Formatting.BOLD));
            } else {
                tooltip.add(new TranslatableText("extraons.motherboard.cpu").append(new TranslatableText("extraons.motherboard.state.notinstalled")).formatted(Formatting.RED, Formatting.BOLD));
            }

            //RAM
            if (hasRAMInstalled == true) {
                tooltip.add(new TranslatableText("extraons.motherboard.ram").append(new TranslatableText("extraons.motherboard.state.installed")).formatted(Formatting.GREEN, Formatting.BOLD));
            } else {
                tooltip.add(new TranslatableText("extraons.motherboard.ram").append(new TranslatableText("extraons.motherboard.state.notinstalled")).formatted(Formatting.RED, Formatting.BOLD));
            }

            //GPU
            if (hasGPUInstalled == true) {
                tooltip.add(new TranslatableText("extraons.motherboard.gpu").append(new TranslatableText("extraons.motherboard.state.installed")).formatted(Formatting.GREEN, Formatting.BOLD));
            } else {
                tooltip.add(new TranslatableText("extraons.motherboard.gpu").append(new TranslatableText("extraons.motherboard.state.notinstalled")).formatted(Formatting.RED, Formatting.BOLD));
            }

            //Battery
            if (hasBatteryInstalled == true) {
                tooltip.add(new TranslatableText("extraons.motherboard.battery").append(new TranslatableText("extraons.motherboard.state.installed")).formatted(Formatting.GREEN, Formatting.BOLD));
            } else {
                tooltip.add(new TranslatableText("extraons.motherboard.battery").append(new TranslatableText("extraons.motherboard.state.notinstalled")).formatted(Formatting.RED, Formatting.BOLD));
            }

            //HDD & SSD
            if (hasHDDSSDInstalled == true) {
                tooltip.add(new TranslatableText("extraons.motherboard.hddssd").append(new TranslatableText("extraons.motherboard.state.installed")).formatted(Formatting.GREEN, Formatting.BOLD));
            } else {
                tooltip.add(new TranslatableText("extraons.motherboard.hddssd").append(new TranslatableText("extraons.motherboard.state.notinstalled")).formatted(Formatting.RED, Formatting.BOLD));
            }

            //Cooler
            if (hasCoolerInstalled == true) {
                tooltip.add(new TranslatableText("extraons.motherboard.cooler").append(new TranslatableText("extraons.motherboard.state.installed")).formatted(Formatting.GREEN, Formatting.BOLD));
            } else {
                tooltip.add(new TranslatableText("extraons.motherboard.cooler").append(new TranslatableText("extraons.motherboard.state.notinstalled")).formatted(Formatting.RED, Formatting.BOLD));
            }
        } else {
            tooltip.add(new TranslatableText("extraons.motherboard.info").formatted(Formatting.YELLOW, Formatting.BOLD));
        }
    }

}
