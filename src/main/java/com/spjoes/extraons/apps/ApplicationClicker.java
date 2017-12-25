package com.spjoes.extraons.apps;

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
		this.clickMeButton = new Button(80, 75, "Click Me!");
		this.clickMeButton.setClickListener((button, mouse,  par3) -> {
			this.score++;
			this.scoreLabel.setText("Your Score : " + score);
		});
		this.scoreLabel = new Label("Your Score : 0", 65, 55);

		this.timeLabel = new Label("00:00:00", 10, 10);
		this.updateTime();

		this.addComponent(clickMeButton);
		this.addComponent(scoreLabel);
		this.addComponent(timeLabel);
		
		this.score = 0;
	}

	@Override
	public void load(NBTTagCompound tagCompound) {}

	@Override
	public void save(NBTTagCompound tagCompound) {}

	private void updateTime() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int hour = c.get(Calendar.HOUR);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		String ampm = c.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
		timeLabel.setText(String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second) + " " + ampm);
	}
	
	@Override
	public void onTick() {
		this.timeCheck--;
		if(this.timeCheck == 0) {
			this.updateTime();
			this.timeCheck = 20;
		}
	}
}