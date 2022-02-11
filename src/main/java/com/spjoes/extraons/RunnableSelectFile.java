package com.spjoes.extraons;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import net.minecraft.client.Minecraft;

public class RunnableSelectFile implements Runnable {

	private boolean isClosed = false;
	private File selectedFile = null;
	
	@Override
	public void run() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
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
		fc.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// For some reason, loosing focus of the window destroys the window
				isClosed = true;
			}
			
			@Override
			public void focusGained(FocusEvent e) { }
		});
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
