package com.bplaced.lukasgafner.jsqladmin;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class JsqlaXMLFiles {
	
	// Get the file
	private Document getDocument(String filepath) {
		DocumentBuilderFactory docbuilderfactory = DocumentBuilderFactory.newInstance();
		Document document = null;
		
		try {
			DocumentBuilder docbuilder = docbuilderfactory.newDocumentBuilder();
			document = docbuilder.parse(new File(filepath));
		} catch (ParserConfigurationException e) {
			JOptionPane.showMessageDialog(null, "Unable to read the selected XML file\n" + e.getMessage(), "Read XML Error", JOptionPane.ERROR_MESSAGE);
		} catch (SAXException e) {
			JOptionPane.showMessageDialog(null, "Unable to read the selected XML file\n" + e.getMessage(), "Read XML Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unable to read the selected XML file\n" + e.getMessage(), "Read XML Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return document;
	}
	
	// Read the root element of xml file
	public Element readXML(String filepath) {
		Document doc = getDocument(filepath);
		doc.normalize();
		
		Element xmlroot = doc.getDocumentElement();
	
		return xmlroot;
	}
	
	// Save xml file (not used yet)
	/*
	private void saveFile(String filepath, Document document) {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		DOMSource domSource = new DOMSource(document);
		
		StreamResult streamResult = new StreamResult(new File(filepath));
		try {
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(domSource, streamResult);

		} catch (TransformerException e) {
			JOptionPane.showMessageDialog(null, "Unable to write the selected XML file\n" + e.getMessage(), "Write XML Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	*/
	
	// Creates a document to write into the xml file (not used yet)
	/*
	public void writeXML(String filepath, String nodestring, String value) {
		Document doc = getDocument(filepath);
		doc.normalize();
		
		Element xmlroot = doc.getDocumentElement();
		
		String[] elements = nodestring.split(";");
		
		Element parents = xmlroot;
		for (int i = 0; i < elements.length - 1; i++) {
			parents = (Element) parents.getElementsByTagName(elements[i]).item(0);
		}
		
		Node editnode = parents.getElementsByTagName(elements[elements.length - 1]).item(0);
		editnode.setTextContent(value);
		
		saveFile(filepath, doc);
	}
	*/
}
