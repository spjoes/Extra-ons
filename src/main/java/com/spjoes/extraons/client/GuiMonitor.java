package com.spjoes.extraons.client;

import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.mrcrayfish.device.MrCrayfishDeviceMod;
import com.mrcrayfish.device.Reference;
import com.mrcrayfish.device.api.app.Application;
import com.mrcrayfish.device.api.app.Dialog;
import com.mrcrayfish.device.api.app.Icons;
import com.mrcrayfish.device.api.app.Layout;
import com.mrcrayfish.device.api.app.component.Button;
import com.mrcrayfish.device.api.task.TaskManager;
import com.mrcrayfish.device.api.utils.RenderUtil;
import com.mrcrayfish.device.core.Laptop;
import com.mrcrayfish.device.core.Settings;
import com.mrcrayfish.device.core.TaskBar;
import com.mrcrayfish.device.core.Window;
import com.mrcrayfish.device.core.network.TrayItemWifi;
import com.mrcrayfish.device.object.AppInfo;
import com.mrcrayfish.device.object.TrayItem;
import com.mrcrayfish.device.programs.system.SystemApplication;
import com.mrcrayfish.device.programs.system.task.TaskUpdateApplicationData;
import com.mrcrayfish.device.util.GuiHelper;
import com.spjoes.extraons.Constants;
import com.spjoes.extraons.tileentities.TileEntityCentralUnit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class GuiMonitor extends Laptop {

	private static final ResourceLocation LAPTOP_GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/laptop.png");
	private static final ResourceLocation NO_SIGNAL = new ResourceLocation(Constants.MODID, "textures/gui/no_signal.png");
	private static final ResourceLocation BIOS_SCREEN = new ResourceLocation(Constants.MODID, "textures/gui/bios.png");
	
	private static final int BORDER = 10;
	private static final int DEVICE_WIDTH = 400;
	private static final int DEVICE_HEIGHT = 230;
	static final int SCREEN_WIDTH = DEVICE_WIDTH - BORDER * 2;
	static final int SCREEN_HEIGHT = DEVICE_HEIGHT - BORDER * 2;
	
	private static final ArrayList<ResourceLocation> BACKGROUNDS = new ArrayList<ResourceLocation>();
	static {
		BACKGROUNDS.add(new ResourceLocation(Constants.MODID, "textures/backgrounds/grass"));
	}
	private static final int BGINDEX = 0;
	
	private Layout context;
	private Window[] windows = new Window[10];
	private TaskBar bar;
	
	private NBTTagCompound appData;
	private NBTTagCompound systemData;
	
	private boolean dragging = false;
	
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
	
	private int lastMouseX;
	private int lastMouseY;
	
	private static Field WINDOW_OFFSETX, WINDOW_OFFSETY, WINDOW_WIDTH, WINDOW_HEIGHT;
	
	public GuiMonitor(TileEntityCentralUnit unit) {
		super(unit);
		this.unit = unit;
		this.appData = unit.getApplicationData();
		this.systemData = unit.getSystemData();
		this.JOKE_LINE_INDEX = new Random().nextInt(JOKE_LINES.length);
		this.bar = new DesktopTaskbar(this.getApplications());
		
		if(WINDOW_OFFSETX == null) {
			try {
				WINDOW_OFFSETX = Window.class.getDeclaredField("offsetX");
				WINDOW_OFFSETX.setAccessible(true);
				
				WINDOW_OFFSETY = Window.class.getDeclaredField("offsetY");
				WINDOW_OFFSETY.setAccessible(true);
				
				WINDOW_WIDTH = Window.class.getDeclaredField("width");
				WINDOW_WIDTH.setAccessible(true);
				
				WINDOW_HEIGHT = Window.class.getDeclaredField("height");
				WINDOW_HEIGHT.setAccessible(true);
			} catch(Exception e) {
				WINDOW_OFFSETX = null;
				WINDOW_OFFSETY = null;
				WINDOW_WIDTH = null;
				WINDOW_HEIGHT = null;
			}
		}
		
		BACKGROUNDS.clear();
		BACKGROUNDS.add(new ResourceLocation(Constants.MODID, "textures/backgrounds/grass.png"));
	}
	
	@Override
	public void initGui() {
		super.initGui();
		this.posX = (this.width - DEVICE_WIDTH) / 2;
		this.posY = (this.height - DEVICE_HEIGHT) / 2;
		this.bar.init(this.posX + BORDER, this.posY + DEVICE_HEIGHT - 28);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		
		TextureManager tm = this.mc.getTextureManager();
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		tm.bindTexture(LAPTOP_GUI);
		
		/* Actual Display */
		int scrPosX = this.posX + BORDER;
		int scrPosY = this.posY + BORDER;
		
		/* Corners */
		this.drawTexturedModalRect(this.posX, this.posY, 0, 0, BORDER, BORDER); // TOP-LEFT
		this.drawTexturedModalRect(this.posX + DEVICE_WIDTH - BORDER, this.posY, 11, 0, BORDER, BORDER); // TOP-RIGHT
		this.drawTexturedModalRect(this.posX + DEVICE_WIDTH - BORDER, this.posY + DEVICE_HEIGHT - BORDER, 11, 11, BORDER, BORDER); // BOTTOM-RIGHT
		this.drawTexturedModalRect(this.posX, this.posY + DEVICE_HEIGHT - BORDER, 0, 11, BORDER, BORDER); // BOTTOM-LEFT
		
		/* Edges */
		RenderUtil.drawRectWithTexture(this.posX + BORDER, this.posY, 10, 0, SCREEN_WIDTH, BORDER, 1, BORDER); // TOP
		RenderUtil.drawRectWithTexture(this.posX + DEVICE_WIDTH - BORDER, posY + BORDER, 11, 10, BORDER, SCREEN_HEIGHT, BORDER, 1); // RIGHT
		RenderUtil.drawRectWithTexture(this.posX + BORDER, posY + DEVICE_HEIGHT - BORDER, 10, 11, SCREEN_WIDTH, BORDER, 1, BORDER); // BOTTOM
		RenderUtil.drawRectWithTexture(this.posX, this.posY + BORDER, 0, 11, BORDER, SCREEN_HEIGHT, BORDER, 1); // LEFT
		
		/* Center */
		RenderUtil.drawRectWithTexture(this.posX + BORDER, posY + BORDER, 10, 10, SCREEN_WIDTH, SCREEN_HEIGHT, 1, 1);
		
		/* Screen */
		if(!this.unit.isOn() /* isn't turned on */) {
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
					fr.drawString(s + "...", posX + BORDER + 5, posX + BORDER + 75 + i*10, 0xFFFFFF);
				}
			} else {
				/* Wallpaper */
				this.mc.getTextureManager().bindTexture(BACKGROUNDS.get(BGINDEX));
				this.drawDispBG();

				drawString(fontRenderer, "DbrownOS - v. " + Constants.VERSION, posX + BORDER + 5, posY + BORDER + 5, Color.WHITE.getRGB());

				boolean insideContext = false;
				if(context != null) {
					insideContext = GuiHelper.isMouseInside(mouseX, mouseY, context.xPosition, context.yPosition, context.xPosition + context.width, context.yPosition + context.height);
				}

				/* Window */
				for(int i = windows.length - 1; i >= 0; i--) {
					Window window = windows[i];
					if(window != null) {
						window.render(this, mc, posX + BORDER, posY + BORDER, mouseX, mouseY, i == 0 && !insideContext, partialTicks);
					}
				}
				
				/* Application Bar */
				bar.render(this, mc, posX + 10, posY + DEVICE_HEIGHT - 28, mouseX, mouseY, partialTicks);

				if(context != null) {
					context.render(this, mc, context.xPosition, context.yPosition, mouseX, mouseY, true, partialTicks);
				}
				
				// Do we really need to render vanilla gui stuff?
			}
		}
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if(this.unit.isOn() && this.unit.getBootTimer() == 0) {
			this.lastMouseX = mouseX;
			this.lastMouseY = mouseY;
			
			if(this.context != null) {
				int dropdownX = context.xPosition;
				int dropdownY = context.yPosition;
				if(GuiHelper.isMouseInside(mouseX, mouseY, dropdownX, dropdownY, dropdownX + context.width, dropdownY + context.height)) {
					this.context.handleMouseClick(mouseX, mouseY, mouseButton);
					return;
				} else {
					this.context = null;
				}
			}
			
			this.bar.handleClick(this, this.posX, this.posY + BORDER + SCREEN_HEIGHT - TaskBar.BAR_HEIGHT, mouseX, mouseY, mouseButton);
			
			for(int i = 0; i < windows.length; i++) {
				Window<Application> window = windows[i];
				if(window != null) {
					Window<Dialog> dialogWindow = window.getContent().getActiveDialog();
					if(isMouseWithinWindow(mouseX, mouseY, window) || isMouseWithinWindow(mouseX, mouseY, dialogWindow)) {
						windows[i] = null;
						updateWindowStack();
						windows[0] = window;

						//windows[0].handleMouseClick(this, posX, posY, mouseX, mouseY, mouseButton);
						// Refleciton again
						try {
							Method methodClicky = Window.class.getDeclaredMethod("handleMouseClick", Laptop.class, int.class, int.class, int.class, int.class, int.class);
							methodClicky.setAccessible(true);
							methodClicky.invoke(windows[0], this, posX, posY, mouseX, mouseY, mouseButton);
						} catch(Exception e) {
							e.printStackTrace();
						}
						
						if(isMouseWithinWindowBar(mouseX, mouseY, dialogWindow)) {
							this.dragging = true;
							return;
						}
			
						if(isMouseWithinWindowBar(mouseX, mouseY, window) && dialogWindow == null) {
							this.dragging = true;
							return;
						}
						break;
					}
				}
			}
		}
	}
	
	private boolean isMouseOnScreen(int mouseX, int mouseY) {
		return GuiHelper.isMouseInside(mouseX, mouseY, this.posX + BORDER, this.posY + BORDER, this.posX + BORDER + SCREEN_WIDTH, this.posY + BORDER + SCREEN_HEIGHT);
	}

	private boolean isMouseWithinWindowBar(int mouseX, int mouseY, Window window) {
		if(window == null) return false;
		return GuiHelper.isMouseInside(mouseX, mouseY, this.posX + get(window, EnumGetter.OFFSETX) + 1, this.posY + get(window, EnumGetter.OFFSETY) + 1, this.posX + get(window, EnumGetter.OFFSETX) + get(window, EnumGetter.WIDTH) - 13, this.posY + get(window, EnumGetter.OFFSETY) + 11);
	}

	private boolean isMouseWithinWindow(int mouseX, int mouseY, Window window) {
		if(window == null) return false;
		return GuiHelper.isMouseInside(mouseX, mouseY, this.posX + get(window, EnumGetter.OFFSETX), this.posY + get(window, EnumGetter.OFFSETY), this.posX + get(window, EnumGetter.OFFSETX) + get(window, EnumGetter.WIDTH), this.posY + get(window, EnumGetter.OFFSETY) + get(window, EnumGetter.HEIGHT));
	}
	
	public boolean isMouseWithinApp(int mouseX, int mouseY, Window window) 	{
		return GuiHelper.isMouseInside(mouseX, mouseY, this.posX + get(window, EnumGetter.OFFSETX) + 1, this.posY + get(window, EnumGetter.OFFSETY) + 13, this.posX + get(window, EnumGetter.OFFSETX) + get(window, EnumGetter.WIDTH) - 1, this.posY + get(window, EnumGetter.OFFSETY) + get(window, EnumGetter.HEIGHT) - 1);
	}
	
	private static int get(Window window, EnumGetter getter) {
		try {
			return getter.f.getInt(window);
		} catch(Exception e) {
			return 0;
		}
	}
	
	private void drawDispBG() {
		Gui.drawModalRectWithCustomSizedTexture(this.posX + BORDER, this.posY + BORDER, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT);
	}
	
	private static enum EnumGetter {
		OFFSETX(WINDOW_OFFSETX),
		OFFSETY(WINDOW_OFFSETY),
		WIDTH(WINDOW_WIDTH),
		HEIGHT(WINDOW_HEIGHT);
		
		private Field f;
		
		EnumGetter(Field f) {
			this.f = f;
		}
		
	}
	
	@Override
	public void openContext(Layout layout, int x, int y) {
		layout.updateComponents(x, y);
		this.context = layout;
		layout.init();
	}

	@Override
	public boolean hasContext() {
		return this.context != null;
	}

	@Override
	public void closeContext() {
		this.context = null;
		this.dragging = false;
	}
	
	public void open(Application app)
	{
		if(MrCrayfishDeviceMod.proxy.hasAllowedApplications())
		{
			if(!MrCrayfishDeviceMod.proxy.getAllowedApplications().contains(app.getInfo()))
			{
				return;
			}
		}

		for(int i = 0; i < windows.length; i++)
		{
			Window<Application> window = windows[i];
			if(window != null && window.getContent().getInfo().getFormattedId().equals(app.getInfo().getFormattedId()))
			{
				windows[i] = null;
				updateWindowStack();
				windows[0] = window;
				return;
			}
		}

		app.setLaptopPosition(this.getPos());

		Window<Application> window = new Window<>(app, this);
		//window.init((this.width - SCREEN_WIDTH) / 2, (this.height - SCREEN_HEIGHT) / 2);
		// We need to use reflection
		try {
			Method initMethod = Window.class.getDeclaredMethod("init", int.class, int.class);
			initMethod.setAccessible(true);
			initMethod.invoke(window, (this.width - SCREEN_WIDTH) / 2, (this.height - SCREEN_HEIGHT) / 2);
		} catch(Exception e) {
			e.printStackTrace();
		}

		if(this.appData.hasKey(app.getInfo().getFormattedId())) {
			app.load(this.appData.getCompoundTag(app.getInfo().getFormattedId()));
		}

		if(app instanceof SystemApplication) {
			((SystemApplication) app).setLaptop(this);
		}

		if(app.getCurrentLayout() == null) {
			app.restoreDefaultLayout();
		}
		
		addWindow(window);

	    Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
	}
	
	public void close(Application app)
	{
		for(int i = 0; i < windows.length; i++)
		{
			Window<Application> window = windows[i];
			if(window != null)
			{
				if(window.getContent().getInfo().equals(app.getInfo()))
				{
					if(app.isDirty())
					{
						NBTTagCompound container = new NBTTagCompound();
						app.save(container);
						app.clean();
						appData.setTag(app.getInfo().getFormattedId(), container);
						TaskManager.sendTask(new TaskUpdateApplicationData(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), app.getInfo().getFormattedId(), container));
					}

					if(app instanceof SystemApplication)
					{
						((SystemApplication) app).setLaptop(null);
					}

					window.handleClose();
					this.windows[i] = null;
					return;
				}
			}
		}
	}
	
	private void addWindow(Window<Application> window) {
		if(this.hasReachedWindowLimit()) {
			return;
		}

		this.updateWindowStack();
		this.windows[0] = window;
	}

	private void updateWindowStack() {
		for(int i = this.windows.length - 1; i >= 0; i--) {
			if(this.windows[i] != null) {
				if(i + 1 < this.windows.length) {
					if(i == 0 || this.windows[i - 1] != null) {
						if(this.windows[i + 1] == null) {
							this.windows[i + 1] = this.windows[i];
							this.windows[i] = null;
						}
					}
				}
			}
		}
	}

	private boolean hasReachedWindowLimit() {
		for(Window window : windows) {
			if(window == null) return false;
		}
		return true;
	}
	
	@Override
	public Settings getSettings() {
		return null;
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	public static class DesktopTaskbar extends TaskBar {

		public static final ResourceLocation APP_BAR_GUI = new ResourceLocation("cdm:textures/gui/application_bar.png");

		private static final int APPS_DISPLAYED = MrCrayfishDeviceMod.DEVELOPER_MODE ? 18 : 10;
		public static final int BAR_HEIGHT = 18;
		
		private Button btnLeft;
		private Button btnRight;
		
		private int offset = 0;
		private int pingTimer = 0;

		private List<Application> applications;
		private List<TrayItem> trayItems = new ArrayList<>();
		
		public DesktopTaskbar(List<Application> applications) {
			super(applications);
			this.applications = applications;
			this.trayItems.add(new TrayItemWifi());
		}
		
		public void init(int posX, int posY) {
			this.btnLeft = new Button(0, 0, Icons.CHEVRON_LEFT);
			this.btnLeft.setPadding(1);
			this.btnLeft.xPosition = posX + 3;
			this.btnLeft.yPosition = posY + 3;
			this.btnLeft.setClickListener((mouseX, mouseY, mouseButton) -> {
				if(offset > 0) {
					offset--;
				}
			});
			this.btnRight = new Button(0, 0, Icons.CHEVRON_RIGHT);
			this.btnRight.setPadding(1);
			this.btnRight.xPosition = posX + 15 + 14 * APPS_DISPLAYED + 14;
			this.btnRight.yPosition = posY + 3;
			this.btnRight.setClickListener((mouseX, mouseY, mouseButton) -> {
				if(offset + APPS_DISPLAYED < applications.size()) {
					offset++;
				}
			});
			this.init();
		}
		
		@Override
		public void init() {
			this.trayItems.forEach(TrayItem::init);
		}
		
		@Override
		public void render(Laptop gui, Minecraft mc, int x, int y, int mouseX, int mouseY, float partialTicks) {
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
			GlStateManager.enableBlend();
			mc.getTextureManager().bindTexture(APP_BAR_GUI);
			gui.drawTexturedModalRect(x, y, 0, 0, 1, 18);
			int trayItemsWidth = trayItems.size() * 14;
			RenderUtil.drawRectWithTexture(x + 1, y, 1, 0, GuiMonitor.SCREEN_WIDTH - 36 - trayItemsWidth, 18, 1, 18);
			RenderUtil.drawRectWithTexture(x + GuiMonitor.SCREEN_WIDTH - 35 - trayItemsWidth, y, 2, 0, 35 + trayItemsWidth, 18, 1, 18);
			GlStateManager.disableBlend();
			
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			btnLeft.render(gui, mc, btnLeft.xPosition, btnLeft.yPosition, mouseX, mouseY, true, partialTicks);
			//btnRight.render(gui, mc, btnRight.xPosition, btnLeft.yPosition, mouseX, mouseY, true, partialTicks);
			
			for(int i = 0; i < APPS_DISPLAYED && i < applications.size(); i++) {
				AppInfo info = applications.get(i + offset).getInfo();
				RenderUtil.drawApplicationIcon(info, x + 18 + i * 16, y + 2);
				if(gui.isApplicationRunning(info.getFormattedId()))
				{
					mc.getTextureManager().bindTexture(APP_BAR_GUI);
					gui.drawTexturedModalRect(x + 17 + i * 16, y + 1, 35, 0, 16, 16);
				}
			}

			mc.fontRenderer.drawString(timeToString(mc.player.world.getWorldTime()), x + 349, y + 5, Color.WHITE.getRGB(), true);

			/* Settings App */
			int startX = x + 332;
			for(int i = 0; i < trayItems.size(); i++)
			{
				int posX = startX - (trayItems.size() - 1 - i) * 14;
				if(isMouseInside(mouseX, mouseY, posX, y + 2, posX + 13, y + 15))
				{
					Gui.drawRect(posX, y + 2, posX + 14, y + 16, new Color(1.0F, 1.0F, 1.0F, 0.1F).getRGB());
				}
				trayItems.get(i).getIcon().draw(mc, posX + 2, y + 4);
			}

			mc.getTextureManager().bindTexture(APP_BAR_GUI);

			/* Other Apps */
			if(isMouseInside(mouseX, mouseY, x + 18, y + 1, x + 236, y + 16))
			{
				int appIndex = (mouseX - x - 1) / 16 - 1 + offset;
				if(appIndex < offset + APPS_DISPLAYED && appIndex < applications.size())
				{
					gui.drawTexturedModalRect(x + (appIndex - offset) * 16 + 17, y + 1, 35, 0, 16, 16);
					gui.drawHoveringText(Collections.singletonList(applications.get(appIndex).getInfo().getName()), mouseX, mouseY);
				}
			}
			
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			RenderHelper.disableStandardItemLighting();
		}
		
		@Override
		public void handleClick(Laptop laptop, int x, int y, int mouseX, int mouseY, int mouseButton) {
			this.btnLeft.handleMouseClick(mouseX, mouseY, mouseButton);
			this.btnRight.handleMouseClick(mouseX, mouseY, mouseButton);
			
			if(isMouseInside(mouseX, mouseY, x + 18, y + 1, x + 236, y + 16)) {
				int appIndex = (mouseX - x - 1) / 16 - 1 + offset;
				if(appIndex <= offset + APPS_DISPLAYED && appIndex < applications.size()) {
					laptop.open(applications.get(appIndex));
					return;
				}
			}

			int startX = x + 332;
			for(int i = 0; i < trayItems.size(); i++) {
				int posX = startX - (trayItems.size() - 1 - i) * 14;
				if(isMouseInside(mouseX, mouseY, posX, y + 2, posX + 13, y + 15)) {
					trayItems.get(i).handleClick(mouseX, mouseY, mouseButton);
					return;
				}
			}
		}
		
	}
	
}
