package com.bplaced.lukasgafner.jsqladmin;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import org.w3c.dom.NodeList;

public class JsqlaSyntax {
	// Objects
	JsqlaProperties jsqlaproperties = new JsqlaProperties();
	
	
	// Styles
	private final StyleContext cont = StyleContext.getDefaultStyleContext();
	// TODO: Changeable colors
	private final AttributeSet blue = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(0,0,255));
	private final AttributeSet black = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(0,0,0));
	private final AttributeSet green = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(0,140,0));
	private final AttributeSet purple = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(70,0,140));
    
    // SQL Syntax Strings
    private static List<String> keywordlist = new ArrayList<>();
    private static List<String> quotelist = new ArrayList<>();
    private static String slcomment = "";
    private static String mlscomment = "";
    private static String mlecomment = "";
	
	public void setHighlighting(JTextPane txtPane) throws BadLocationException {
		StyledDocument styleddoc = txtPane.getStyledDocument();
		javax.swing.text.Element root = styleddoc.getDefaultRootElement();
				
		int[][] lines = new int[root.getElementCount()][2];
		for (int i = 0; i < root.getElementCount(); i++) {
			lines[i][0] = root.getElement(i).getStartOffset();
			lines[i][1] = root.getElement(i).getEndOffset() - lines[i][0] - 1;
		}
		
		String fulltext = styleddoc.getText(0, styleddoc.getLength());
		styleddoc.setCharacterAttributes(0, styleddoc.getLength(), black, false);
		
		// SQL keywords
		for (int k = 0; k < keywordlist.size(); k++) {
			String sword = keywordlist.get(k);
			int swordlen = keywordlist.get(k).length();
			
			for (int pos = 0; pos < styleddoc.getLength(); pos++) {
				int begin = fulltext.indexOf(sword, pos);
				if (begin > -1) {
					styleddoc.setCharacterAttributes(begin, swordlen, blue, false);
				}
			}
		}
				
		// Single line comment
		for (int j = 0; j < lines.length; j++) {
			String line = styleddoc.getText(lines[j][0], lines[j][1]);
			
			int icomment = line.indexOf(slcomment);			
			if (icomment > -1) {
				styleddoc.setCharacterAttributes(icomment + lines[j][0], lines[j][1] - icomment, green, false);
			}
		}
		
		// SQL string values
		for ( int i = 0; i < quotelist.size(); i++) {
			String[] ssplitted = fulltext.split(quotelist.get(i));
			int sstartpos = 0;
			for (int l = 0; l < ssplitted.length - 1; l++) {
				sstartpos = sstartpos + ssplitted[l].length() + 1;
				if (l % 2 == 0) {
					styleddoc.setCharacterAttributes(sstartpos - 1, ssplitted[l + 1].length() + 2, purple, false);
				}
			}
		}
		
		// Multi line comment
		String[] csplitted = fulltext.split("[" + mlscomment + "][" + mlecomment + "]");
		int cstartpos = 0;
		for (int m = 0; m < csplitted.length - 1; m++) {
			cstartpos = cstartpos + csplitted[m].length() + 2;
			if (m % 2 == 0) {
				styleddoc.setCharacterAttributes(cstartpos - 2, csplitted[m + 1].length() + 4, green, false);
			}
		}
	}
	
	public void getSyntax() {
		JsqlaXML jsqlaxml = new JsqlaXML();
		org.w3c.dom.Element xmlroot = jsqlaxml.readXML(jsqlaproperties.getHighlightxml());
		
		// Keywords
		org.w3c.dom.Element xmlkeywords = (org.w3c.dom.Element) xmlroot.getElementsByTagName("keywords").item(0);
		NodeList keywords = xmlkeywords.getElementsByTagName("keyword");
		for (int i = 0; i < keywords.getLength(); i++) {
			keywordlist.add(keywords.item(i).getTextContent());
		}
		
		// Quotes
		org.w3c.dom.Element xmlquotes = (org.w3c.dom.Element) xmlroot.getElementsByTagName("quotes").item(0);
		NodeList quotes = xmlquotes.getElementsByTagName("quote");
		for (int i = 0; i < quotes.getLength(); i++) {
			quotelist.add(quotes.item(i).getTextContent());
		}
		
		// Comments
		slcomment = xmlroot.getElementsByTagName("sl-comment").item(0).getTextContent();
		mlscomment = xmlroot.getElementsByTagName("ml-s-comment").item(0).getTextContent();
		mlecomment = xmlroot.getElementsByTagName("ml-e-comment").item(0).getTextContent();
	}
}
