package com.bplaced.lukasgafner.jsqladmin;

public class JsqlaDialects implements JsqlaConstants {
	// Objects
	JsqlaProperties prop = new JsqlaProperties();
	
	// Variables
	private static String currentdialect = "";
	
	// Load the dialect (once at start)
	public void loadDialects() {
		getDialectName();
	}
	
	// Change current dialect
	public void changeDialect() {
		
	}
	
	// Get current dialect name
	private void getDialectName() {
		String syntaxfile = prop.getProperty(p_syntaxfile);
		String [] splitslash = syntaxfile.split("/");
		currentdialect = splitslash[splitslash.length - 1].split("\\.")[0];
	}	
	
	// Getters
	public final String getCurrentdialect() {
		return currentdialect;
	}	
}
