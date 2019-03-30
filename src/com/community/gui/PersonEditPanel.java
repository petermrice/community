/*
 * Created on Dec 11, 2004
 *
 */
package com.community.gui;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.community.model.Person;
import com.community.util.FieldLayout;

/**
 * @author peter
 * Edit a single person's data. Used for both update and
 * new Person creation.
 */
public class PersonEditPanel extends JPanel implements ClipboardOwner {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1499001422911334844L;

	private Person person;
		
	private JTextField firstName = new CommunityTextField(30);
	private JTextField lastName = new CommunityTextField(30);
	private JTextField organization = new CommunityTextField(8);
	private CommunityTextArea address = new CommunityTextArea(4,30);
	private JTextField city = new CommunityTextField(20);
	private JTextField state = new CommunityTextField(20);
	private JTextField zip = new CommunityTextField(8);
	private JTextField country = new CommunityTextField(20);
	private JTextField email1 = new CommunityTextField(30);
	private JTextField email2 = new CommunityTextField(30);
	private JTextField label = new CommunityTextField(8);
	private JTextField phone1 = new CommunityTextField(10);
	private JTextField phone2 = new CommunityTextField(10);
	private JTextField phone3 = new CommunityTextField(10);
	private JTextField phone4 = new CommunityTextField(10);
	private JTextField phone5 = new CommunityTextField(10);
	private CommunityTextArea info = new CommunityTextArea(4, 30);
	private JCheckBox xmas = new JCheckBox("Christmas List");
	private JCheckBox foreign = new JCheckBox("Foreigh Address");
	private JLabel key = new JLabel("-");
	
	public PersonEditPanel(){
		FieldLayout fl = new FieldLayout();
		setLayout(fl);
		add(new JLabel("Key")); add(key);
		add(new JLabel("First Name")); add(firstName);
		add(new JLabel("Last Name")); add(lastName);
		add(new JLabel("Organization")); add(organization);
		add(new JLabel("Address")); add(new JScrollPane(address));
		add(new JLabel("City")); add(city);
		add(new JLabel("State")); add(state);
		add(new JLabel("ZIP")); add(zip);
		add(new JLabel("Country")); add(country);
		add(new JLabel("Email 1")); add(email1);
		add(new JLabel("Email 2")); add(email2);
		add(new JLabel("Home Phone")); add(phone1);
		add(new JLabel("Work Phone")); add(phone2);
		add(new JLabel("Fax")); add(phone3);
		add(new JLabel("Cell Phone")); add(phone4);
		add(label); add(phone5);
		add(new JLabel("Notes")); add(new JScrollPane(info));
		add(xmas); add(foreign);
		setAddressMouseListener();
		foreign.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setUI();
			}
		});
	}
	
	private void setAddressMouseListener(){
		MouseListener[] ml = address.getMouseListeners();
		for (int i = 0; i < ml.length; i++){
			if (ml[i] instanceof CommunityTextArea.CommunityMouseListener)
				address.removeMouseListener(ml[i]);
		}
		address.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				String out = firstName.getText().trim() + " " + lastName.getText().trim() + "\n";
				out += address.getText().trim() + "\n";
				if (foreign.isSelected()){
					out += country.getText().trim();
				}else{
					out += city.getText().trim() + ", " + state.getText().trim() + " " + 
						zip.getText().trim();
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(new StringSelection(out), PersonEditPanel.this);
				}
			}
		});
	}
	
	public void setPerson(Person person){
		this.person = person;
		updateFields();
	}
	
	public Person getPerson(){
		if (person == null) person = new Person();
		updatePerson();
		return person;
	}
	
	private void updateFields(){
		key.setText(person.getKey() + "");
		firstName.setText(person.getFirstName());
		lastName.setText(person.getLastName());
		organization.setText(person.getOrganization());
		address.setText(person.getAddress());
		city.setText(person.getCity());
		state.setText(person.getState());
		zip.setText(person.getZip());
		country.setText(person.getCountry());
		email1.setText(person.getEmail1());
		email2.setText(person.getEmail2());
		label.setText(person.getLabel());
		phone1.setText(person.getPhone1());
		phone2.setText(person.getPhone2());
		phone3.setText(person.getPhone3());
		phone4.setText(person.getPhone4());
		phone5.setText(person.getPhone5());
		info.setText(person.getNotes());
		xmas.setSelected(person.isXmas());
		foreign.setSelected(person.isForeign());
		setUI();
	}
	
	private void updatePerson(){
		person.setFirstName(firstName.getText());
		person.setLastName(lastName.getText());
		person.setOrganization(organization.getText());
		person.setAddress(address.getText());
		person.setCity(city.getText());
		person.setState(state.getText());
		person.setZip(zip.getText());
		person.setCountry(country.getText());
		person.setEmail1(email1.getText());
		person.setEmail2(email2.getText());
		person.setLabel(label.getText());
		person.setPhone1(phone1.getText());
		person.setPhone2(phone2.getText());
		person.setPhone3(phone3.getText());
		person.setPhone4(phone4.getText());
		person.setPhone5(phone5.getText());
		person.setNotes(info.getText());
		person.setXmas(xmas.isSelected());
		person.setForeign(foreign.isSelected());
	}
	
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
	}
	
	private void setUI(){
		city.setEnabled(!foreign.isSelected());
		state.setEnabled(!foreign.isSelected());
		zip.setEnabled(!foreign.isSelected());
		country.setEnabled(foreign.isSelected());
	}
}
