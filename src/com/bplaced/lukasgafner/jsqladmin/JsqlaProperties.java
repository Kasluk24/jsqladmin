package com.bplaced.lukasgafner.jsqladmin;

import java.util.HashMap;

public class JsqlaProperties implements JsqlaConstants {
	JsqlaXML jsqlaxml = new JsqlaXML();
	
	
	private static HashMap<Object, String> properties = new HashMap<>();
	private static String configxml = "jsqlaconfig.xml";
	private static String highlightxml = "SyntaxMYSQL.xml";
	private static String activetype;
	
	
	// Read properties
	private void readProp() {
		activetype = "0"; // Temporary
		properties.put(activetype, "active-type"); // Temporary
	}
	
	// Write properties
	private void writeProp(Object variable, String value) {
		
		jsqlaxml.writeXML(configxml, properties.get(variable), value);
	}
	
	
	// Getters and setters
	public String getHighlightxml() {
		return highlightxml;
	}
	public void setHighlightxml(String xml) {
		highlightxml = xml;
	}
	public String getConfigxml() {
		return configxml;
	}
	public void setConfigxml(String config) {
		configxml = config;
	}
	public String getActivetype() {
		return activetype;
	}
	public void setActivetype(String type) {
		activetype = type;
		writeProp((Object) activetype, activetype);
	}
}
