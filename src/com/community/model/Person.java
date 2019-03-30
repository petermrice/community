/*
 * Created on Mar 31, 2005
 *
 */
package com.community.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author peter
 * The object representing a row in the community database.
 */
public class Person {
	
	private String firstName;
	private String lastName;
	private String organization;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String email1;
	private String email2;
	private String label;
	private String phone1;
	private String phone2;
	private String phone3;
	private String phone4;
	private String phone5;
	private String notes;
	private boolean xmas;
	private boolean foreign_address;
	private int key;
	
	public Person(){
		this("","","","","","","","","","","","","","","","","",false,false,0);
	}
	
	// next() has already been called...
	public Person(ResultSet rs) throws SQLException{
		this(rs.getString("FIRSTNAME"), rs.getString("LASTNAME"),
				rs.getString("ORGANIZATION"), rs.getString("ADDRESS"),
				rs.getString("CITY"), rs.getString("STATE"),
				rs.getString("ZIP"), rs.getString("COUNTRY"),
				rs.getString("EMAIL1"), rs.getString("EMAIL2"),
				rs.getString("LABEL"), rs.getString("PHONE1"),
				rs.getString("PHONE2"), rs.getString("PHONE3"),
				rs.getString("PHONE4"), rs.getString("PHONE5"),
				rs.getString("NOTES"), 
				rs.getBoolean("XMAS"), rs.getBoolean("FOREIGN_ADDRESS"),
				rs.getInt("ID"));
	}
	
	/**
	 * Be sure to set the id (key) value before a new save
	 * 
	 * @param p
	 * @return
	 */
	public String savePerson() {
		String sql = "INSERT INTO PERSON VALUES ('" + 
				getFirstName() + "','" +
				getLastName() + "','" +
				getOrganization() + "','" + 
				getAddress() + "','" + 
				getCity() + "','" + 
				getState() + "','" + 
				getZip() + "','" + 
				getCountry() + "','" + 
				getEmail1() + "','" + 
				getEmail2() + "','" + 
				getLabel() + "','" + 
				getPhone1() + "','" + 
				getPhone2() + "','" + 
				getPhone3() + "','" + 
				getPhone4() + "','" + 
				getPhone5() + "','" + 
				getNotes() + "'," + 
				isXmas() + "," + 
				isForeign() + "," +
				getKey() + ");";
		return sql;
	}
	
	public String update() {
		String sql = "UPDATE PERSON SET " + 
				" FIRSTNAME = '" + getFirstName() + "'," +
				" LASTNAME = '" + getLastName() + "'," +
				" ORGANIZATION = '" + getOrganization() + "'," + 
				" ADDRESS = '" + getAddress() + "'," + 
				" CITY = '" + getCity() + "'," + 
				" STATE = '" + getState() + "'," + 
				" ZIP = '" + getZip() + "'," + 
				" COUNTRY = '" + getCountry() + "'," + 
				" EMAIL1 = '" + getEmail1() + "'," + 
				" EMAIL2 = '" + getEmail2() + "'," + 
				" LABEL = '" + getLabel() + "'," + 
				" PHONE1 = '" + getPhone1() + "'," + 
				" PHONE2 = '" + getPhone2() + "'," + 
				" PHONE3 = '" + getPhone3() + "'," + 
				" PHONE4 = '" + getPhone4() + "'," + 
				" PHONE5 = '" + getPhone5() + "'," + 
				" NOTES = '" + getNotes() + "'," + 
				" XMAS =  " + isXmas() + "," + 
				" FOREIGN_ADDRESS = " + isForeign() + " WHERE ID = " + getKey() + ";";
		return sql;
	}
	
	public Person(String firstName, String lastName, String organization, String address,
			String city, String state, String zip, String country,
			String email1, String email2, String label,
			String phone1, String phone2, 
			String phone3, String phone4, String phone5, String notes,
			boolean xmas, boolean foreign_address, int key) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.organization = organization;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
		this.email1 = email1;
		this.email2 = email2;
		this.label = label;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.phone4 = phone4;
		this.phone5 = phone5;
		this.notes = notes;
		this.xmas = xmas;
		this.foreign_address = foreign_address;
		this.key = key;
	}
	
	public String toCSVString(){
		return "\"" + getFirstName()+"\","+
			"\"" + getLastName() + "\"," + 
			"\"" + getOrganization() + "\"," + 
			"\"" + getAddress() + "\"," + 
			"\"" + getCity() + "\"," + 
			"\"" + getState() + "\"," + 
			"\"" + getZip() + "\"," + 
			"\"" + getCountry() + "\"," + 
			"\"" + getEmail1() + "\"," + 
			"\"" + getEmail2() + "\"," + 
			"\"" + getLabel() + "\"," + 
			"\"" + getPhone1() + "\"," + 
			"\"" + getPhone2() + "\"," + 
			"\"" + getPhone3() + "\"," + 
			"\"" + getPhone4() + "\"," + 
			"\"" + getPhone5() + "\"," + 
			"\"" + getNotes() + "\"," + 
			(isXmas()?1:0) + "," + 
			(isForeign()?1:0) + "," +
			getKey() + "\n"; 
	}
	
	private String nn(String in){
		return in == null ? "" : in;
	}
	public String toString(){
		return getFirstName() + " " + getLastName() + " " + getKey();
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = nn(address);
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = nn(city);
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = nn(firstName);
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = nn(label);
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = nn(lastName);
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = nn(notes);
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = nn(phone1);
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = nn(phone2);
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = nn(phone3);
	}
	public String getPhone4() {
		return phone4;
	}
	public void setPhone4(String phone4) {
		this.phone4 = nn(phone4);
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = nn(state);
	}
	public boolean isXmas() {
		return xmas;
	}
	public void setXmas(boolean xmas) {
		this.xmas = xmas;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = nn(zip);
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = nn(country);
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = nn(email1);
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = nn(email2);
	}
	public boolean isForeign() {
		return foreign_address;
	}
	public void setForeign(boolean foreign) {
		this.foreign_address = foreign;
	}
	public String getPhone5() {
		return phone5;
	}
	public void setPhone5(String phone5) {
		this.phone5 = nn(phone5);
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = nn(organization);
	}
}
