package com.bplaced.lukasgafner.jsqladmin;


import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class SyntaxSQL {
	final StyleContext cont = StyleContext.getDefaultStyleContext();
    final AttributeSet blue = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLUE);
    final AttributeSet black = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
    final AttributeSet green = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.GREEN);
    final AttributeSet purple = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.pink);
	
	public void setHighlighting(JTextPane txtPane) throws BadLocationException {
		StyledDocument styleddoc = txtPane.getStyledDocument();
		Element root = styleddoc.getDefaultRootElement();
		String[] keywords = {"SELECT", "FROM", "WHERE"};
		
		int[][] lines = new int[root.getElementCount()][2];
		for (int i = 0; i < root.getElementCount(); i++) {
			lines[i][0] = root.getElement(i).getStartOffset();
			lines[i][1] = root.getElement(i).getEndOffset() - lines[i][0] - 1;
		}
		
		String fulltext = styleddoc.getText(0, styleddoc.getLength());
		styleddoc.setCharacterAttributes(0, styleddoc.getLength(), black, false);
		
		// SQL keywords
		for (int k = 0; k < keywords.length; k++) {
			String sword = keywords[k];
			int swordlen = keywords[k].length();
			
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
			
			int icomment = line.indexOf("--");			
			if (icomment > -1) {
				styleddoc.setCharacterAttributes(icomment + lines[j][0], lines[j][1] - icomment, green, false);
			}
		}
		
		// SQL string values
		String[] ssplitted = fulltext.split("'");
		int sstartpos = 0;
		for (int l = 0; l < ssplitted.length - 1; l++) {
			sstartpos = sstartpos + ssplitted[l].length() + 1;
			if (l % 2 == 0) {
				styleddoc.setCharacterAttributes(sstartpos - 1, ssplitted[l + 1].length() + 2, purple, false);
			}
		}
		
		// Multi line comment
		String[] csplitted = fulltext.split("[/*][*/]");
		int cstartpos = 0;
		for (int m = 0; m < csplitted.length - 1; m++) {
			cstartpos = cstartpos + csplitted[m].length() + 2;
			if (m % 2 == 0) {
				styleddoc.setCharacterAttributes(cstartpos - 2, csplitted[m + 1].length() + 4, green, false);
			}
		}
	}
}
