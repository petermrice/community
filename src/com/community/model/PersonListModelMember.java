/*
 * Created on Mar 31, 2005
 *
 */
package com.community.model;

/**
 * @author peter
 *
 */
public class PersonListModelMember {
	private String firstName;
	private String lastName;
	private int key;
	
	public PersonListModelMember(){}
	
	public PersonListModelMember(String firstName, String lastName, int key) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.key = key;
	}
	
	public String toString() {
		return getLastName() + ", " + getFirstName();
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
