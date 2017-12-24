package com.spjoes.extraons;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import net.minecraft.client.Minecraft;

public class RunnableSelectFile implements Runnable {

	private boolean isClosed = false;
	private File selectedFile = null;
	
	@Override
	public void run() {
		JFileChooser fc = new JFileChooser(System.getProperty("user.home"));
		fc.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().endsWith(".zip");
			}

			@Override
			public String getDescription() {
				return "Karaoke files (*.zip)";
			}
		});
		fc.setMultiSelectionEnabled(false); // Makes sure you can only select one file
		int ret = fc.showOpenDialog(null);
		
		this.isClosed = true;
		
		if(ret == JFileChooser.APPROVE_OPTION) {
			this.selectedFile = fc.getSelectedFile();
		}
	}
	
	public boolean isClosed() {
		return this.isClosed;
	}

	public boolean isFileSelected() {
		return this.selectedFile != null;
	}
	
	public File getSelectedFile() {
		return this.selectedFile;
	}

}
