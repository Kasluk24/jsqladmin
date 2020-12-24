package com.bplaced.lukasgafner.jsqladmin;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class JsqlaExpressions implements JsqlaConstants {
	// Objects
	JsqlaProperties prop = new JsqlaProperties();
	JsqlaDialects dial = new JsqlaDialects();
	JsqlaXMLFiles xmlf = new JsqlaXMLFiles();
	
	// Variables with expressions
	private static String viewlist = "";
	private static String[] nonerrors;
	
	// Read the expressions from the XML file (once at start)
	public void loadExpressions() {
		Element expressionsroot = xmlf.readXML(prop.getProperty(p_expressionsfile));
		NodeList dialectlist = expressionsroot.getElementsByTagName("dialect");
		Element dialect = null;
				
		for (int i = 0; i < dialectlist.getLength(); i++) {
			if (dialectlist.item(i).getAttributes().getNamedItem("dname").getNodeValue().equals(dial.getCurrentdialect())) {
				dialect = (Element)dialectlist.item(i);
			}
		}
		
		// Write the expressions into the variables
		viewlist = dialect.getElementsByTagName("viewlist").item(0).getTextContent();
		nonerrors = dialect.getElementsByTagName("nonerrcodes").item(0).getTextContent().split(",");
		
	}	
	
	// Getters
	public final String getViewlist() {
		return viewlist;
	}
	public final String[] getNonerrors() {
		return nonerrors;
	}
}
