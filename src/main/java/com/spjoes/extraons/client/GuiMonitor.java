package com.spjoes.extraons.client;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.spjoes.extraons.Constants;
import org.lwjgl.opengl.GL11;

import com.mrcrayfish.device.api.app.Application;
import com.spjoes.extraons.centralUnitStuff.CUAppBar;
import com.spjoes.extraons.tileentities.TileEntityCentralUnit;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class GuiMonitor extends GuiScreen {

	private static final ResourceLocation MONITOR_GUI = new ResourceLocation(Constants.MODID, "textures/gui/monitor.png");
	private static final ResourceLocation NO_SIGNAL = new ResourceLocation(Constants.MODID, "textures/gui/no_signal.png");
	private static final ResourceLocation BIOS_SCREEN = new ResourceLocation(Constants.MODID, "textures/gui/bios.png");
	
	private static final ArrayList<ResourceLocation> BACKGROUNDS = new ArrayList<ResourceLocation>();
	static {
		String[] names = {"grass", "creeper", "slime", "coolcat"};
		for(String name : names) {
			BACKGROUNDS.add(new ResourceLocation(Constants.MODID, "textures/backgrounds/" + name + ".png"));
		}
	}
	private static final int BGINDEX = 0;
	
	private TileEntityCentralUnit unit;
	
	private static String[] BOOT_LINES = {
			"Loading BIOS",
			"Checking boot sectors",
			"Initializing boot",
			"", // See the JOKE_LINES below
			"Booting into DbrownOS"
	};
	
	private static String[] JOKE_LINES = {
		"Give me a break",
		"Wishing to be a real person",
		"Eating cheese and crackers",
		"Loading GRUB... Wait what?",
		"Searching Google on Google",
		"Snapping dat like button",
		"Uninstalling all apps from the store",
		"Executing \"while(true) { Thread.sleep(1); }\""
	};
	
	private int JOKE_LINE_INDEX = 0;
	
	private int posX, posY;
	public static final int border = 10;
	public static final int monitorWidth = 400;
	public static final int monitorHeight = 230;
	public static final int dispWidth = monitorWidth - border*2;
	public static final int dispHeight = monitorHeight - border*2; 
	
	private CUAppBar bar;
	
	private int lastMouseX;
	private int lastMouseY;
	
	private List<Application> APPLICATIONS;
	
	private static Field WINDOW_OFFSETX, WINDOW_OFFSETY, WINDOW_WIDTH, WINDOW_HEIGHT;
	
	public GuiMonitor(TileEntityCentralUnit unit) {
		this.unit = unit;
		this.JOKE_LINE_INDEX = new Random().nextInt(JOKE_LINES.length);
	}
	
	@Override
	public void initGui() {
		super.initGui();
		this.posX = (this.width - this.monitorWidth) / 2;
		this.posY = (this.height - this.monitorHeight) / 2;
		this.bar = new CUAppBar();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		
		TextureManager tm = this.mc.getTextureManager();
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		tm.bindTexture(MONITOR_GUI);
		
		Gui.drawModalRectWithCustomSizedTexture(this.posX, this.posY, 0, 0, this.monitorWidth, this.monitorHeight, this.monitorWidth, this.monitorHeight);
		
		/* Screen */
		if(this.unit == null || !this.unit.isOn() /* isn't turned on */) {
			tm.bindTexture(NO_SIGNAL);
			this.drawDispBG();
		} else {
			int bootTime = this.unit.getBootTimer();
			if(bootTime > 0) {
				tm.bindTexture(BIOS_SCREEN);
				this.drawDispBG();
				int numOfLines = ((TileEntityCentralUnit.BOOT_ON_TIME-bootTime)/(TileEntityCentralUnit.BOOT_ON_TIME/BOOT_LINES.length)) + 1;

				if(numOfLines > BOOT_LINES.length) {
					numOfLines = BOOT_LINES.length;
				}
				FontRenderer fr = this.mc.fontRenderer;
				for(int i = 0; i < numOfLines; i++) {
					String s = BOOT_LINES[i];
					if(s.isEmpty()) {
						s = JOKE_LINES[this.JOKE_LINE_INDEX];
					}
					fr.drawString(s + "...", posX + border + 5, posX + border + 75 + i*10, 0xFFFFFF);
				}
			} else {
				/* Wallpaper */
				this.mc.getTextureManager().bindTexture(BACKGROUNDS.get(BGINDEX));
				this.drawDispBG();
				
				this.bar.render(mc, this.posX + border, this.posY + monitorHeight - border - 16, mouseX, mouseY);
			}
		}
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		
	}
	
	private void drawDispBG() {
		Gui.drawModalRectWithCustomSizedTexture(this.posX + border, this.posY + border, 0, 0, this.dispWidth, this.dispHeight, this.dispWidth, this.dispHeight);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
