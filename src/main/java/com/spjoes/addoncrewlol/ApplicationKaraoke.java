package com.spjoes.addoncrewlol;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.mrcrayfish.device.api.app.Application;
import com.mrcrayfish.device.api.app.Layout;
import com.mrcrayfish.device.api.app.component.Button;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Karaoke app. Uses <a href="http://www.javazoom.net/jlgui/api.html">jlGui's BasicPlayer</a>.
 * @author spjoes
 * @author Dbrown55
 */
public class ApplicationKaraoke extends Application {

	private Layout menuLayout;
	private Button openFileButton;
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
		
		this.setCurrentLayout(this.menuLayout);
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
							Files.copy(toExt, Paths.get(tmpFile));
							music = new File(tmpFile);
						} else if(name.equalsIgnoreCase("text.json")) {
							StringBuilder fileContent = new StringBuilder();
							String line;
							while((line = in.readLine()) != null) {
								fileContent.append(line);
							}
							text = fileContent.toString();
						}
					}
					try {
						this.karaoke = new Karaoke(music);
					} catch (Throwable e) {}
				} catch(Exception e) {
					
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
	
	public static class Karaoke {
		
		private BasicPlayer player;
		private Object data;
		
		public Karaoke(File music) throws Throwable {
			this.player = new BasicPlayer();
			this.player.open(music);
			music.delete();
			this.player.play();
		}
		
		public void stop() {
			try {
				this.player.stop();
			} catch (BasicPlayerException e) {}
		}
		
	}
	
}