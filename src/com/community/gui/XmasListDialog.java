package com.community.gui;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.community.model.PersonSupport;

public class XmasListDialog extends JDialog {

	private JFrame parent;
	
	public XmasListDialog(Community parent) {
		super(parent);
		this.parent = parent;
		getContentPane().add(new CenterPanel(), BorderLayout.CENTER);
		JButton ok = new JButton("Exit");
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				dispose();
			}
		});
		JPanel p = new JPanel();
		p.add(ok);
		getContentPane().add(p, BorderLayout.SOUTH);
		pack();
	}

	class CenterPanel extends JPanel {
		JTextField file;
		JButton eq;

		public CenterPanel() {
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			add(new Label("File name"));
			file = new JTextField(15);
			add(file);
			eq = new JButton("Export Xmas list");
			add(eq);
			eq.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					PersonSupport.outputXmasList(file.getText());
					XmasListDialog.this.dispose();
				}
			});
			file.addMouseListener(new MouseAdapter(){

				@Override
				public void mouseClicked(MouseEvent e) {
					FileDialog dlg = new FileDialog(XmasListDialog.this, "Choose output file", FileDialog.SAVE);
					dlg.setVisible(true);
					File[] files = dlg.getFiles();
					if (files.length < 1) return;
					file.setText(files[0].getPath());;
				}
				
			});
		}

	}

}
