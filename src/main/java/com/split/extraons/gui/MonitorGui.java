package com.split.extraons.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.util.Identifier;

public class MonitorGui extends LightweightGuiDescription {
    public MonitorGui() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(380, 210);

        WSprite icon = new WSprite(new Identifier("extraons:textures/gui/no_signal.png"));
        root.add(icon, 0, 0, 25, 13);

        root.validate(this);
    }
}