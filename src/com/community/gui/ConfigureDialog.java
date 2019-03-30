/*
 * Created on Apr 8, 2005
 *
 */
package com.community.gui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.community.util.Config;

/**
 * @author Peter Rice
 * Used to configure the application. Values from here will be 
 * written to config.properties.
 */
public class ConfigureDialog extends JDialog {

	JTextField fontSize = new JTextField(3);
	JButton save = new JButton("Save");
	JButton cancel = new JButton("Cancel");
	
	public ConfigureDialog(Frame owner) {
		super(owner, "Configure Community");
		init();
	}
	
	private void init(){
		fontSize.setText("12");
		JPanel fontSizePanel = new JPanel();
		fontSizePanel.add(new JLabel("Font Size:"));
		fontSizePanel.add(fontSize);
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				bSaveAction();
			}
		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				bCancelAction();
			}
		});
		JPanel buttons = new JPanel();
		buttons.add(save);
		buttons.add(cancel);
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));
		c.add(fontSizePanel);
		c.add(buttons);
		pack();
	}
	
	public void bSaveAction(){
		Config.setFontSize(Integer.parseInt(fontSize.getText()));
		dispose();
	}
	
	public void bCancelAction(){
		dispose();
	}
}
