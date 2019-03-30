package com.community.gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessageDialog extends JDialog {

	public MessageDialog(Dialog arg0, String arg1, String msg){
		super(arg0, arg1);
		init(msg);
	}

	public MessageDialog(Frame arg0, String arg1, String msg){
		super(arg0, arg1);
		init(msg);
	}
	
	private void init(String msg){
		setSize(220, 100);
		JPanel p = new JPanel();
		p.add(new JLabel(msg));
		getContentPane().add(p, BorderLayout.CENTER);
	}

}
