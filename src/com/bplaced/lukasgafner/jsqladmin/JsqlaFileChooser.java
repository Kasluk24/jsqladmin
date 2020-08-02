package com.bplaced.lukasgafner.jsqladmin;

import java.io.File;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JsqlaFileChooser {
	
	public String getFilePath(String approveButtonText, String directory, String[] filter) {
		String filepath = null;
		JFileChooser fchooser = new JFileChooser();
		
		fchooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fchooser.setCurrentDirectory(new File(directory));
				
		for (int i = 0; i < filter.length; i++) {
			fchooser.setFileFilter(new FileNameExtensionFilter(filter[i], filter[i]));
		}
		
		int fch = fchooser.showDialog(null, approveButtonText);
		if (fch == JFileChooser.APPROVE_OPTION) {
			filepath = fchooser.getSelectedFile().getPath();
		}
		
		Path workingdir = Path.of("").toAbsolutePath();
		filepath = String.valueOf(workingdir.relativize(Path.of(filepath)));
		
		return filepath;
	}
	
	public String getDirectoryPath(String approveButtonText, String directory) {
		String dirpath = null;
		JFileChooser fchoser = new JFileChooser();
		
		fchoser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fchoser.setCurrentDirectory(new File(directory));
		
		int fch = fchoser.showDialog(null, approveButtonText);
		if (fch == JFileChooser.APPROVE_OPTION) {
			dirpath = fchoser.getSelectedFile().getPath();
		}
		
		Path workingdir = Path.of("").toAbsolutePath();
		dirpath = String.valueOf(workingdir.relativize(Path.of(dirpath)));
		
		return dirpath;
	}
	
}
