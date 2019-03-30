/*
 * Created on Apr 4, 2005
 *
 */
package com.community.model;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.TreeNode;

/**
 * @author peter
 *
 */
public class Organization implements TreeNode {
	private List list;
	private String name;
	private TreeNode parent;
	
	public Organization(TreeNode parent, String name){
		this.parent = parent;
		this.name = name;
		list = new LinkedList();
	}
	public String toString() {
		return name;
	}
	public Enumeration children() {
		return new Enumeration(){
			Iterator it = list.iterator();
			public Object nextElement(){
				return it.next();
			}
			public boolean hasMoreElements(){
				return it.hasNext();
			}
		};
	}
	public boolean getAllowsChildren() {
		return true;
	}
	public TreeNode getChildAt(int childIndex) {
		return (TreeNode)list.get(childIndex);
	}
	public int getChildCount() {
		return list.size();
	}
	public int getIndex(TreeNode node) {
		return list.indexOf(node);
	}
	public TreeNode getParent() {
		return parent;
	}
	public boolean isLeaf() {
		return false;
	}
	public void add(PersonTreeModelMember node){
		list.add(node);
	}
	public List getList(){
		return list;
	}
}
