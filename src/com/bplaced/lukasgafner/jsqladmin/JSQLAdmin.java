package com.bplaced.lukasgafner.jsqladmin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class JSQLAdmin implements KeyListener {
	
	// Swing Elements
	private JFrame frameJSQLA;
	private JMenuBar mbarJSQLA;
	private JMenu mnuFile;
	private JMenuItem mitemExit;

	// Launch application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JSQLAdmin app = new JSQLAdmin();
				app.start();
			}
		});
	}
	
	// Start JSQLAdmin
	public void start() {
		initialize();
		frameJSQLA.setVisible(true);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// not in use yet	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Not in use yet	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Not in use yet	
	}

	/**
	 * Create the frame.
	 */
	private void initialize() {
		//Frame
		frameJSQLA = new JFrame();
		frameJSQLA.setBounds(100, 100, 786, 443);
		frameJSQLA.setTitle("JSQLAdmin");
		frameJSQLA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameJSQLA.setFocusable(true);
		frameJSQLA.addKeyListener(this);
		
		// Menu Bar
		mbarJSQLA = new JMenuBar();
		frameJSQLA.setJMenuBar(mbarJSQLA);
		// Menu File
		mnuFile = new JMenu("File");
		mbarJSQLA.add(mnuFile);
		// Menu Item Exit
		mitemExit = new JMenuItem("Exit");
		mitemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitApplication();
			}
		});
		mnuFile.add(mitemExit);
	}
	
	// Exit the Application
	private void exitApplication() {
		frameJSQLA.dispose();
		System.exit(0);
	}
}
