/*
 * Created on Jan 10, 2005
 *
 */
package com.community.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.community.model.Person;
import com.community.model.PersonSupport;

/**
 * @author peter
 *  
 */
public class AddDialog extends JDialog {

	private PersonEditPanel pep = new PersonEditPanel();
	private JButton bsave = new JButton("Save");
	private JButton bexit = new JButton("Exit");
	private Community parent;
	
	/**
	 * @param arg0
	 * @throws java.awt.HeadlessException
	 */
	public AddDialog(Community parent) {
		super(parent);
		this.parent = parent;
		Container c = getContentPane();
		c.add(pep, BorderLayout.CENTER);
		JPanel buttons = new JPanel();
		buttons.add(bsave);
		buttons.add(bexit);
		c.add(buttons, BorderLayout.SOUTH);
		Dimension d = c.getPreferredSize();
		setSize(d.width + 20, d.height + 20);

		bsave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveAction();
			}
		});
		bexit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				exitAction();
			}
		});
	}
		
	private void saveAction(){
		Person s = pep.getPerson();
		PersonSupport.addPerson(s);
		parent.refreshTree();
		exitAction();
	}
	
	private void exitAction(){
		dispose();
	}
}
