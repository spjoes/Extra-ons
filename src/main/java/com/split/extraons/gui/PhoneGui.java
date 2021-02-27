package com.split.extraons.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class PhoneGui extends LightweightGuiDescription {

    public PhoneGui() {

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(105, 170);

        WSprite icon = new WSprite(new Identifier("minecraft:textures/item/redstone.png"));
        root.add(icon, 0, 3, 1, 1);

        WButton button = new WButton(new TranslatableText("gui.extraons.music_play"));
        button.setOnClick(() -> {

            // This code runs on the client when you click the button.
            System.out.println("music_play");
        });
        root.add(button, 2, 2, 3, 1);

        root.validate(this);
    }
}
