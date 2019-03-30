/*
 * Created on Dec 11, 2004
 *
 */
package com.community.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class EditDialog extends JDialog {

	private PersonEditPanel pep;
	private JButton bsave = new JButton("Save");
	private JButton bdelete = new JButton("Delete");
	private JButton bexit = new JButton("Exit");
	private Community parent;
	
	/**
	 * @param Community parent
	 * @throws java.awt.HeadlessException
	 */
	public EditDialog(Community parent, Person p) {
		super(parent);
		this.parent = parent;
		pep = new PersonEditPanel();
		pep.setPerson(p);
		Container c = getContentPane();
		c.add(pep, BorderLayout.CENTER);
		JPanel buttons = new JPanel();
		buttons.add(bsave);
		buttons.add(bdelete);
		buttons.add(bexit);
		c.add(buttons, BorderLayout.SOUTH);
		Dimension d = c.getPreferredSize();
		setSize(d.width + 20, d.height + 40);
		//pack();
		
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
		bdelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				deleteAction();
			}
		});
	}
	
	public void setPerson(Person s){
		pep.setPerson(s);
		this.validate();
	}
	
	private void saveAction(){
		Person s = pep.getPerson();
		PersonSupport.replacePerson(s);
		parent.refreshTree();
		exitAction();
	}
	
	private void exitAction(){
		dispose();
	}
	
	private void deleteAction(){
		int ret = JOptionPane.showConfirmDialog(null, "Confirm deletion of this entry.",
				"Warning", JOptionPane.YES_NO_OPTION);
		if (ret == JOptionPane.NO_OPTION) return;
		Person s = pep.getPerson();
		PersonSupport.deletePerson(s);
		parent.refreshTree();
		exitAction();
	}
	
}
