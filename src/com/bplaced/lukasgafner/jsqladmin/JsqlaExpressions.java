package com.bplaced.lukasgafner.jsqladmin;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class JsqlaExpressions implements JsqlaConstants {
	// Variables with expressions
	private String viewlist = "";
	
	// Properties object
	JsqlaProperties jsqlaproperties = new JsqlaProperties();
	
	// Read the expressions from the XML file
	public void loadExpressions() {
		// Variable with current dialect
		String currentdialect = getDialectName();
		
		JsqlaXML jsqlaxml = new JsqlaXML();
		Element expressionsroot = jsqlaxml.readXML(jsqlaproperties.getProperty("expressions-file"));
		NodeList dialectlist = expressionsroot.getElementsByTagName("dialect");
		Element dialect = null;
		
//		int count = 0;
//		System.out.println(dialectlist.getLength());
//		while (count < dialectlist.getLength()) {
//			if (((Element)dialectlist.item(count)).getAttribute("dname").equals(currentdialect)) {
//				dialect = (Element)dialectlist.item(count);
//				count = dialectlist.getLength();
//			}
//			count += 1;
//		}
		
		for (int i = 0; i < dialectlist.getLength(); i++) {
			if (dialectlist.item(i).getAttributes().getNamedItem("dname").getNodeValue().equals(currentdialect)) {
				dialect = (Element)dialectlist.item(i);
			}
		}
		
		
		// Write the expressions into the variables
		viewlist = dialect.getElementsByTagName("viewlist").item(0).getTextContent();
		
	}
	
	// Get current dialect name
	private String getDialectName() {
		String syntaxfile = jsqlaproperties.getProperty(p_syntaxfile);
		String [] splitslash = syntaxfile.split("/");
		String dialectname = splitslash[splitslash.length - 1].split("\\.")[0];
		
		return dialectname;
	}
	
	
	// Getters
	public String getViewlist() {
		return viewlist;
	}
	
}
