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
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.BorderLayout;

public class JSQLAdmin implements KeyListener, JsqlaConstants {
	
	// Swing Elements
	private JFrame frameJSQLA;
	private JMenuBar mbarJSQLA;
	private JMenu mnuFile;
	private JMenuItem mitemExit;
	private JPanel panTop, panBottom, panRight;
	private GridBagLayout gblJSQLA;
	private GridBagConstraints gbcTop, gbcBottom, gbcRight;
	private JScrollPane scrpTop, scrpBottom;
	
	private JTextPane txtpanTop, txtpanBottom;

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
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			updateHighlighting();
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			updateHighlighting();
		}
		if (e.getKeyCode() == KeyEvent.VK_DELETE) {
			updateHighlighting();
		}
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			updateHighlighting();
		}
		if (e.getKeyChar() == ';') {
			updateHighlighting();
		}
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
		
		// Menu
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
		
		// Layout
		// GridBagLaout
		gblJSQLA = new GridBagLayout();
		gblJSQLA.columnWidths = new int[]{200, 180, 0};
		gblJSQLA.rowHeights = new int[]{40, 40, 0};
		gblJSQLA.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gblJSQLA.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		frameJSQLA.getContentPane().setLayout(gblJSQLA);
		// Top
		panTop = new JPanel();
		panTop.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		gbcTop = new GridBagConstraints();
		gbcTop.insets = new Insets(0, 0, 5, 5);
		gbcTop.fill = GridBagConstraints.BOTH;
		gbcTop.gridx = 0;
		gbcTop.gridy = 0;
		frameJSQLA.getContentPane().add(panTop, gbcTop);
		panTop.setLayout(new BorderLayout(0, 0));
		// Right
		panRight = new JPanel();
		panRight.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		gbcRight = new GridBagConstraints();
		gbcRight.gridheight = 2;
		gbcRight.fill = GridBagConstraints.BOTH;
		gbcRight.gridx = 1;
		gbcRight.gridy = 0;
		frameJSQLA.getContentPane().add(panRight, gbcRight);
		// Bottom
		panBottom = new JPanel();
		panBottom.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		gbcBottom = new GridBagConstraints();
		gbcBottom.insets = new Insets(0, 0, 0, 5);
		gbcBottom.fill = GridBagConstraints.BOTH;
		gbcBottom.gridx = 0;
		gbcBottom.gridy = 1;
		frameJSQLA.getContentPane().add(panBottom, gbcBottom);
		panBottom.setLayout(new BorderLayout(0, 0));
		
		// Components
		// Text Pane Bottom with Scroll Pane
		txtpanBottom = new JTextPane();
		scrpBottom = new JScrollPane();
		panBottom.add(scrpBottom);
		scrpBottom.setViewportView(txtpanBottom);;
		// Text Pane Top with Scroll Pane
		txtpanTop = new JTextPane();
		txtpanTop.addKeyListener(this);
		scrpTop = new JScrollPane();
		panTop.add(scrpTop);
		scrpTop.setViewportView(txtpanTop);
	}
	
	// Exit the Application
	private void exitApplication() {
		frameJSQLA.dispose();
		System.exit(0);
	}
	
	// Update Syntax Highlighting
	private void updateHighlighting() {
		JsqlaSyntax syntaxsql = new JsqlaSyntax();
		try {
			syntaxsql.setHighlighting(txtpanTop);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}
