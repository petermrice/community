/*
 * Created on Apr 4, 2005
 *
 */
package com.community.model;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.tree.TreeNode;

/**
 * @author peter
 *
 */
public class PersonRootNode implements TreeNode{
		private List list;
		public PersonRootNode(List list){
			this.list = list;
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
			return null;
		}
		public boolean isLeaf() {
			return false;
		}
		public String toString(){
			return "Root";
		}
		public List getList(){
			return list;
		}
}


