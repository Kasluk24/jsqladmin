package com.bplaced.lukasgafner.jsqladmin;

import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class JsqlaXML {
	
	// Read the specified XML file an returns the root element
	public Element readXML(String filepath) {
		
		Element xmlroot = null;
		
		try {
			DocumentBuilderFactory docbuilderfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docbuilder = docbuilderfactory.newDocumentBuilder();
			Document doc = docbuilder.parse(new File(filepath));
			
			doc.normalize();
			
			xmlroot = doc.getDocumentElement();
						
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Unable to read the selected XML file\n" + e.getMessage(), "Read XML Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return xmlroot;
	}
	
	public void writeXML() {
		// Not in use yet
	}
}
