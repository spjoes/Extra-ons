package com.spjoes.extraons.apps.karaoke;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.mrcrayfish.device.api.app.Application;
import com.mrcrayfish.device.api.app.Layout;
import com.mrcrayfish.device.api.app.component.Button;
import com.mrcrayfish.device.api.app.component.Label;
import com.mrcrayfish.device.core.Laptop;
import com.spjoes.extraons.RunnableSelectFile;
import com.spjoes.extraons.items.ItemLinkable;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Karaoke app. Uses <a href="http://www.javazoom.net/jlgui/api.html">jlGui's BasicPlayer</a>.
 * @author spjoes
 * @author Dbrown55
 */
public class ApplicationKaraoke extends Application {

	private Layout menuLayout;
	private Layout noMicLayout;
	private Layout noMicHeldLayout;
	private Button openFileButton;
	private Label noMicLabel;
	
	private RunnableSelectFile select;
	private Karaoke karaoke;
	
	@Override
	public void init() {		
		this.menuLayout = new Layout(100, 100);
		
		this.openFileButton = new Button(5, 75, "Open file");
		this.openFileButton.setSize(90, 20);
		this.openFileButton.setClickListener((x, y, button) -> {
			if(this.select == null) {
				this.select = new RunnableSelectFile();
				new Thread(this.select).start();
			}
		});
		this.openFileButton.setToolTip("Opens file from your computer", "I mean your real computer, the one you're using to play Minecraft right now");
		this.menuLayout.addComponent(this.openFileButton);
		
		this.noMicLayout = new Layout(100, 100);
		this.noMicLayout.setBackground((gui, mc, x, y, w, h, mx, my, active) -> {
			mc.fontRenderer.drawString("You don't have a", x, y+36, 0xFF0000);
			mc.fontRenderer.drawString("microphone linked", x, y+46, 0xFF0000);
			mc.fontRenderer.drawString("to this laptop.", x, y+56, 0xFF0000);
		});
		
		this.noMicHeldLayout = new Layout(100, 100);
		this.noMicHeldLayout.setBackground((gui, mc, x, y, w, h, mx, my, active) -> {
			mc.fontRenderer.drawString("You don't have a", x, y+36, 0xFF0000);
			mc.fontRenderer.drawString("microphone in your", x, y+46, 0xFF0000);
			mc.fontRenderer.drawString("hand.", x, y+56, 0xFF0000);
		});
		
		// Since it's client side only, I can do this without worries
		EntityPlayer pl = Minecraft.getMinecraft().player;
		InventoryPlayer inv = pl.inventory;
		boolean hasMic = false;
		for(int i = 0; i < inv.getSizeInventory(); i++) {
			if(ItemLinkable.isLinked(inv.getStackInSlot(i), this.getLaptopPositon())) {
				hasMic = true;
			}
		}
		
		AtomicBoolean holdingLinkable = new AtomicBoolean(false);
		ItemLinkable.getLinkables().forEach(linkable -> {
			EntityEquipmentSlot[] slots = linkable.getCorrectSlots();
			for(EntityEquipmentSlot slot : slots) {
				if(pl.getItemStackFromSlot(slot).getItem() == linkable) {
					holdingLinkable.set(true);
				}
			}
		});
		
		if(!hasMic) {
			this.setCurrentLayout(this.noMicLayout);
		} else if(!holdingLinkable.get()) {
			this.setCurrentLayout(this.noMicHeldLayout);
		} else {
			this.setCurrentLayout(this.menuLayout);
		}
	}
	
	@Override
	public void onTick() {
		super.onTick();
		if(this.select != null && this.select.isClosed()) {
			if(this.select.isFileSelected()) {
				ZipFile zip = null;
				File music = null;
				String text = null;
				try {
					String zipPath = this.select.getSelectedFile().getAbsolutePath();
					zip = new ZipFile(zipPath);
					Enumeration<? extends ZipEntry> entries = zip.entries();
					while(entries.hasMoreElements()){
						ZipEntry entry = entries.nextElement();
						String name = entry.getName();
						
						BufferedReader in = new BufferedReader(new InputStreamReader(zip.getInputStream(entry)));
						
						if((name.equalsIgnoreCase("music.ogg") || name.equalsIgnoreCase("music.mp3")) && music == null) {
							String tmpFile = Minecraft.getMinecraft().mcDataDir + "/data.tmp";
							FileSystem fs = FileSystems.newFileSystem(Paths.get(zipPath), null);
							Path toExt = fs.getPath(name);
							File f = new File(tmpFile);
							if(f.exists()) {
								f.delete();
							}
							Files.copy(toExt, Paths.get(tmpFile));
							music = new File(tmpFile);
						} else if(name.equalsIgnoreCase("lyrics.json")) {
							StringBuilder fileContent = new StringBuilder();
							String line;
							while((line = in.readLine()) != null) {
								fileContent.append(line);
								fileContent.append('\n');
							}
							text = fileContent.toString();
						}
					}
					
					this.karaoke = new Karaoke(music, text);
					
					this.setCurrentLayout(new Layout(200, 100));
				} catch(Throwable e) {
					e.printStackTrace();
				}
				if(zip != null) {
					try {
						zip.close();
					} catch (IOException e) {}
				}
			}
			this.select = null;
		}
	}
	
	@Override
	public String getWindowTitle() {
		if(this.karaoke != null) {
			return this.karaoke.getNameAndAuthor();
		}
		return super.getWindowTitle();
	}
	
	@Override
	public void render(Laptop laptop, Minecraft mc, int x, int y, int mouseX, int mouseY, boolean active, float partialTicks) {
		super.render(laptop, mc, x, y, mouseX, mouseY, active, partialTicks);
	}
	
	@Override
	public void load(NBTTagCompound arg0) {
		
	}

	@Override
	public void save(NBTTagCompound arg0) {
		
	}
	
	@Override
	public void onClose() {
		super.onClose();
		if(this.karaoke != null) {
			this.karaoke.stop();
		}
	}
	
}