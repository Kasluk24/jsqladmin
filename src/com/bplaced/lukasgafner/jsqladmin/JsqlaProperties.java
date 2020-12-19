package com.bplaced.lukasgafner.jsqladmin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class JsqlaProperties implements JsqlaConstants {
	static Properties prop = new Properties();
	
	// Get a property
	public String getProperty(String key) {
		// loadProperties();
		String value = prop.get(key).toString();
		return value;
	}
	
	// Set a property
	public void setProperty(String key, String value) {
		prop.put(key, value);
	}
	
	// Store properties (once on exit)
	public void storeProperties() {
		try {
			FileOutputStream outputstream = new FileOutputStream(p_path);
			prop.store(outputstream, "Main property file");
			outputstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Load properties (once at start)
	public void loadProperties() {
		try {
			FileInputStream inputstream = new FileInputStream(p_path);
			prop.load(inputstream);
			inputstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
