/*
 * Created on Apr 4, 2005
 *
 */
package com.community.model;

import java.util.Enumeration;

import javax.swing.tree.TreeNode;

/**
 * @author peter
 *  
 */
public class PersonTreeModelMember implements TreeNode {

	public Enumeration children() {
		return null;
	}
	public boolean getAllowsChildren() {
		return false;
	}
	public TreeNode getChildAt(int childIndex) {
		return null;
	}
	public int getChildCount() {
		return 0;
	}
	public int getIndex(TreeNode node) {
		return 0;
	}
	public TreeNode getParent() {
		return parent;
	}
	public boolean isLeaf() {
		return false;
	}
	
	private TreeNode parent;
	
	private String firstName;

	private String lastName;

	private int id;

	public PersonTreeModelMember(TreeNode parent, String firstName, String lastName, int id) {
		this.parent = parent;
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}