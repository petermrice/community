/*
 * Created on Apr 6, 2005
 *
 */
package com.community.gui;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;
import javax.swing.text.Document;

import com.community.util.Config;

/**
 * @author peter
 * A JTextArea with special features:
 * 1. When the focus appears, the contents is selected.
 * 2. When it is clicked, the contents is placed on the clipboard.
 */
public class CommunityTextArea extends JTextArea {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3948049385538533159L;

	/**
	 * 
	 */
	public CommunityTextArea() {
		super();
		init();
	}

	/**
	 * @param text
	 */
	public CommunityTextArea(String text) {
		super(text);
		init();
	}

	/**
	 * @param rows
	 * @param columns
	 */
	public CommunityTextArea(int rows, int columns) {
		super(rows, columns);
		init();
	}

	/**
	 * @param text
	 * @param rows
	 * @param columns
	 */
	public CommunityTextArea(String text, int rows, int columns) {
		super(text, rows, columns);
		init();
	}

	/**
	 * @param doc
	 */
	public CommunityTextArea(Document doc) {
		super(doc);
		init();
	}

	/**
	 * @param doc
	 * @param text
	 * @param rows
	 * @param columns
	 */
	public CommunityTextArea(Document doc, String text, int rows, int columns) {
		super(doc, text, rows, columns);
	}
	
	private void init(){
		addMouseListener(new CommunityMouseListener());
		addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent e){
				selectAll();
			}
		});
	}
	
	public class CommunityMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			if (e.getClickCount() == 2) {
				selectAll();
				copy();
			}
		}
	}
	
}
