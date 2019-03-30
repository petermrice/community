/*
 * Created on Dec 9, 2004
 *
 */
package com.community.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.community.model.Person;
import com.community.model.PersonSupport;
import com.community.model.PersonTreeModelMember;
import com.community.util.Config;
import com.community.util.DatabaseUtils;
import com.community.util.Debuggable;

/**
 * @author Peter Rice
 * Date: 2005-4-8
 * Update: 2019-3-19
 * 
 * A multi-user personal contact list.
 */
public class Community extends JFrame implements Debuggable {
	
	private JTree jtree = new JTree(new Object[]{});
	private EditDialog personDialog;

	public static void main(String[] args) {
		try {
			if (DEBUG) System.out.println("In main.");
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        Config.init();
	    } catch (Exception e) {
	    	System.out.println(e);
	    }
	    try{
		    SwingUtilities.invokeAndWait(new Runnable(){
		    	public void run(){
		    		Community f = new Community();
		    		f.setupGUI(); 
		    	}
		    });
	    }catch(Exception e){
	    	System.out.println(e);
	    }
	}
	
	private void setupGUI(){
		if (DEBUG) System.out.println("Setting up GUI.");
		setSize(330,370);
		setLocation(Config.getX(),Config.getY());
		setVisible(true);		
		setTitle("Community");
	}
	
	public Community(){
		if (DEBUG) System.out.println("In Community constructor.");

		Container cp = getContentPane();
		try {
			this.setIconImage(new ImageIcon("com/community.image/Community.png").getImage());
		} catch (Exception e) {
			System.out.println(e);
		} 
		cp.add(new JScrollPane(jtree), BorderLayout.CENTER);
		JPanel buttons = new JPanel(new FlowLayout());
		JButton exit = new JButton("EXIT");
		JButton addNew = new JButton("Add New");
		JButton query = new JButton("Xmas List");
		buttons.add(addNew);
		buttons.add(query);
		buttons.add(exit);
		cp.add(buttons, BorderLayout.SOUTH);
		
		exit.addActionListener(new ExitActionListener());
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				exitAction();
			}
		});
		
		addNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addNew();
			}
		});
		
		query.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				XmasListDialog dlg = new XmasListDialog(Community.this);
				dlg.setLocation(getLocation());
				dlg.setVisible(true);
			}
		});

		jtree.getSelectionModel().setSelectionMode
			(TreeSelectionModel.SINGLE_TREE_SELECTION);
		jtree.setRootVisible(true);
		jtree.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				TreePath path = jtree.getClosestPathForLocation(e.getX(), e.getY());
				Object o = path.getLastPathComponent();
				if (o instanceof PersonTreeModelMember)
					showPerson((PersonTreeModelMember)o);
			}
		});
		jtree.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent arg0) {
				
			}
			public void keyReleased(KeyEvent arg0) {
				
			}
			public void keyTyped(KeyEvent arg0) {
				if (0 == arg0.getKeyCode()){
					Object o = jtree.getSelectionPath().getLastPathComponent();
					if (o instanceof PersonTreeModelMember)
						showPerson((PersonTreeModelMember)o);
				}
			}});
		if (DEBUG) System.out.println("About to fill the tree.");
		fillTree();
		if (DEBUG) System.out.println("Tree is full.");

		//Create the popup menu.
	    JPopupMenu popup = new JPopupMenu();
	    
	    //Create item
	    JMenuItem menuItem = new JMenuItem("Configure");
	    menuItem.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		JDialog dlg = new ConfigureDialog(Community.this);
	    		dlg.setLocation(Community.this.getLocation().x + 20,
	    				Community.this.getLocation().y + 20);
	    		dlg.setVisible(true);
	    	}});
	    popup.add(menuItem);
	    
	    //Add listener to the tree and frame so the popup menu can come up.
	    menuItem = new JMenuItem("Help");
	    menuItem.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    	}});
	    popup.add(menuItem);
	    MouseListener popupListener = new PopupListener(popup);
	    addMouseListener(popupListener);
	    jtree.addMouseListener(popupListener);
		if (DEBUG) System.out.println("All set up.");
	}

	/**
	 * Fill the tree with data from the database
	 */
	private void fillTree() {
		DatabaseUtils.init();
		jtree.setModel(PersonSupport.getNewTreeModel());
	}
	
	private void showPerson(PersonTreeModelMember m){
		Person p = PersonSupport.getPerson(m.getId());
		if (personDialog != null){ 
			if (!personDialog.isVisible()) personDialog.setVisible(true);
			personDialog.setPerson(p);
		} else {
			personDialog = new EditDialog(this, p);
			personDialog.setLocation(10,10);
			personDialog.setVisible(true);
		}
	}

	private void addNew(){
		JDialog dlg = new AddDialog(this);
		dlg.setLocation(10,10);
		dlg.setVisible(true);
	}
	
	public void refreshTree(){
		jtree.setModel(PersonSupport.getNewTreeModel());
		getContentPane().validate();
		repaint();
	}
	
	public void exitAction(){
		try {
			Config.saveConfig(this.getX(), this.getY());
		} catch (IOException e) {
			e.printStackTrace();
		}
		dispose();
	}
	
	class ExitActionListener implements ActionListener{	
		public void actionPerformed(ActionEvent e) {
			exitAction();
		}
	}
	
    class PopupListener extends MouseAdapter {
        JPopupMenu popup;
        PopupListener(JPopupMenu popupMenu) {
            popup = popupMenu;
        }
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }
        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }
        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }
}

