package com.spjoes.extraons.client;

public class GuiConsole /*extends Laptop*/ {
	
//	private static final ResourceLocation LAPTOP_GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/laptop.png");
//	private static final ResourceLocation NO_SIGNAL = new ResourceLocation(Constants.MODID, "textures/gui/laptop.png");
//	private static final ResourceLocation BIOS_SCREEN = new ResourceLocation(Constants.MODID, "textures/gui/bios.png");
//	
//	private static final int BORDER = 10;
//	private static final int DEVICE_WIDTH = 400;
//	private static final int DEVICE_HEIGHT = 230;
//	static final int SCREEN_WIDTH = DEVICE_WIDTH - BORDER * 2;
//	static final int SCREEN_HEIGHT = DEVICE_HEIGHT - BORDER * 2;
//	
//	private TileEntityCentralUnit unit;
//	
//	private static String[] BOOT_LINES = {
//			"Loading Console",
//			"Checking servers",
//			"Preparing for booting",
//			"", // See the JOKE_LINES below
//			"Booting CozBox OS"
//	};
//	
//	private static String[] JOKE_LINES = {
//		"Give me a break",
//		"Wishing to be a real person",
//		"Eating cheese and crackers",
//		"Searching Google on Google",
//		"Snapping dat like button",
//		"Uninstalling all apps from the store",
//		"Executing \"while true { Thread.sleep(1) }\""
//	};
//	
//	private int JOKE_LINE_INDEX = 0;
//	
//	public GuiConsole(TileEntityCentralUnit unit) {
//		super(unit);
//		this.unit = unit;
//		this.JOKE_LINE_INDEX = new Random().nextInt(JOKE_LINES.length);
//	}
//	
//	@Override
//	public void initGui() {
//		Keyboard.enableRepeatEvents(true);
//	}
//	
//	@Override
//	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
//		this.drawDefaultBackground();
//		
//		TextureManager tm = this.mc.getTextureManager();
//		
//		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//		tm.bindTexture(LAPTOP_GUI);
//		
//		/* Physical Screen */
//		int posX = (width - DEVICE_WIDTH) / 2;
//		int posY = (height - DEVICE_HEIGHT) / 2;
//		
//		/* Actual Display */
//		int scrPosX = posX + BORDER;
//		int scrPosY = posY + BORDER;
//		
//		/* Corners */
//		this.drawTexturedModalRect(posX, posY, 0, 0, BORDER, BORDER); // TOP-LEFT
//		this.drawTexturedModalRect(posX + DEVICE_WIDTH - BORDER, posY, 11, 0, BORDER, BORDER); // TOP-RIGHT
//		this.drawTexturedModalRect(posX + DEVICE_WIDTH - BORDER, posY + DEVICE_HEIGHT - BORDER, 11, 11, BORDER, BORDER); // BOTTOM-RIGHT
//		this.drawTexturedModalRect(posX, posY + DEVICE_HEIGHT - BORDER, 0, 11, BORDER, BORDER); // BOTTOM-LEFT
//		
//		/* Edges */
//		RenderUtil.drawRectWithTexture(posX + BORDER, posY, 10, 0, SCREEN_WIDTH, BORDER, 1, BORDER); // TOP
//		RenderUtil.drawRectWithTexture(posX + DEVICE_WIDTH - BORDER, posY + BORDER, 11, 10, BORDER, SCREEN_HEIGHT, BORDER, 1); // RIGHT
//		RenderUtil.drawRectWithTexture(posX + BORDER, posY + DEVICE_HEIGHT - BORDER, 10, 11, SCREEN_WIDTH, BORDER, 1, BORDER); // BOTTOM
//		RenderUtil.drawRectWithTexture(posX, posY + BORDER, 0, 11, BORDER, SCREEN_HEIGHT, BORDER, 1); // LEFT
//		
//		/* Center */
//		RenderUtil.drawRectWithTexture(posX + BORDER, posY + BORDER, 10, 10, SCREEN_WIDTH, SCREEN_HEIGHT, 1, 1);
//		
//		/* Screen */
//		if(!this.unit.isOn() /* isn't turned on */) {
//			tm.bindTexture(NO_SIGNAL);
//			this.drawDispBG();
//		} else {
//			int bootTime = this.unit.getBootTimer();
//			if(bootTime > 0) {
//				tm.bindTexture(BIOS_SCREEN);
//				this.drawDispBG();
//				int numOfLines = ((TileEntityCentralUnit.BOOT_ON_TIME-bootTime)/(TileEntityCentralUnit.BOOT_ON_TIME/BOOT_LINES.length)) + 1;
//
//				if(numOfLines > BOOT_LINES.length) {
//					numOfLines = BOOT_LINES.length;
//				}
//				FontRenderer fr = this.mc.fontRenderer;
//				for(int i = 0; i < numOfLines; i++) {
//					String s = BOOT_LINES[i];
//					if(s.isEmpty()) {
//						s = JOKE_LINES[this.JOKE_LINE_INDEX];
//					}
//					fr.drawString(s + "...", posX + BORDER + 5, posX + BORDER + 75 + i*10, 0xFFFFFF);
//				}
//			} else {
//				
//			}
//		}
//	}
//	
//	@Override
//	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
//		
//	}
//	
//	private void drawDispBG() {
//		int posX = (width - SCREEN_WIDTH) / 2;
//		int posY = (height - SCREEN_HEIGHT) / 2;
//		Gui.drawModalRectWithCustomSizedTexture(posX, posY, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT);
//	}
//	
//	@Override
//	public void openContext(Layout layout, int x, int y) {
//		
//	}
//
//	@Override
//	public boolean hasContext() {
//		return false;
//	}
//
//	@Override
//	public void closeContext() {
//		
//	}
//
//	@Override
//	public Settings getSettings() {
//		return null;
//	}
//	
//	@Override
//	public boolean doesGuiPauseGame() {
//		return false;
//	}

}
