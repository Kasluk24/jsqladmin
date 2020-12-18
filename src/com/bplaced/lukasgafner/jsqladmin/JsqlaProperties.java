package com.bplaced.lukasgafner.jsqladmin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class JsqlaProperties {
	String prop_path = "config.properties";
	Properties prop = new Properties();
	
	// Get a property
	public String getProperty(String key) {
		String value = prop.get(key).toString();
		return value;
	}
	
	// Set a property
	public void setProperty(String key, String value) {
		prop.put(key, value);
		storeProperties();
	}
	
	// Store properties
	private void storeProperties() {
		try {
			FileOutputStream outputstream = new FileOutputStream(prop_path);
			prop.storeToXML(outputstream, "Main property file");
			outputstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Load properties
	public void loadProperties() {
		try {
			FileInputStream inputstream = new FileInputStream(prop_path);
			prop.loadFromXML(inputstream);
			inputstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
