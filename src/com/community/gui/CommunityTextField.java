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

import javax.swing.JTextField;
import javax.swing.text.Document;

import com.community.util.Config;

/**
 * @author peter
 * A JTextField with extra properties:
 * 1. When the focus is received, the contents is selected.
 * 2. When a mouse click is received, the contents is copied to the clipboard.
 */
public class CommunityTextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2797554746442909553L;

	/**
	 * 
	 */
	public CommunityTextField() {
		this(null, "", 0);
	}

	/**
	 * @param text
	 */
	public CommunityTextField(String text) {
		this(null, text, 0);
	}

	/**
	 * @param columns
	 */
	public CommunityTextField(int columns) {
		this(null, "", columns);
	}

	/**
	 * @param text
	 * @param columns
	 */
	public CommunityTextField(String text, int columns) {
		this(null, text, columns);
	}

	/**
	 * @param doc
	 * @param text
	 * @param columns
	 */
	public CommunityTextField(Document doc, String text, int columns) {
		super(doc, text, columns);
		init();
	}
	
	private void init(){
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if (e.getClickCount() == 2){
					selectAll();
					copy();
				}
			}
		});
		addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent e){
				CommunityTextField.this.selectAll();
			}
		});
	}

}
