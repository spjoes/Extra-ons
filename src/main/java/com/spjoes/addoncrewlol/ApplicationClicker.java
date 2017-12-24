package com.spjoes.addoncrewlol;

import com.mrcrayfish.device.api.app.Application;
import com.mrcrayfish.device.api.app.Layout;
import com.mrcrayfish.device.api.app.component.Button;
import com.mrcrayfish.device.api.app.component.Label;

import java.util.Calendar;
import java.util.Date;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;

public class ApplicationClicker extends Application {

    private Button clickMeButton;
    private Label scoreLabel;
    private int score;
    private Label timeLabel;
    private int timeCheck = 20;

    @Override
    public void init() {

        clickMeButton = new Button(80, 75, "Click Me!");
        clickMeButton.setClickListener((button, mouse,  par3) -> {
            score++;
            scoreLabel.setText("Your Score : " + score);
        });
        scoreLabel = new Label("Your Score : 0", 65, 55);

        timeLabel = new Label("00:00:00", 10, 10);

        this.addComponent(clickMeButton);
        this.addComponent(scoreLabel);
        this.addComponent(timeLabel);
    }

    @Override
    public void load(NBTTagCompound tagCompound) {}

    @Override
    public void save(NBTTagCompound tagCompound) {}

    @Override
    public void onTick() {
        timeCheck--;
        if(timeCheck == 0) {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            int hour = c.get(Calendar.HOUR);
            int minute = c.get(Calendar.MINUTE);
            int second = c.get(Calendar.SECOND);
            timeLabel.setText(String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second));
            timeCheck = 20;
        }
    }
}