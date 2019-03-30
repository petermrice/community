/*
 * Created on Apr 2, 2005
 *
 */
package com.community.model;

import java.util.List;
import java.util.Vector;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * @author peter
 * The central storage is a List. Most members of the list are PersonTreeModelMember
 * objects. If the member of a list is a list, then the
 * element represents an organization with PeronTreeModelMember members.  
 *
 */
public class PersonTreeModel implements TreeModel {
	
	private TreeNode root;
	private List list;
	private List listeners = new Vector();
	
	public PersonTreeModel(List list, PersonRootNode root){
		this.list = list;
		this.root = root;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getRoot()
	 */
	public Object getRoot() {
		return root;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChild(java.lang.Object, int)
	 */
	public Object getChild(Object parent, int index) {
		if (parent == root) return list.get(index);
		else if (parent instanceof Organization)
			return ((Organization)parent).getChildAt(index);
		else 
			return null;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)
	 */
	public int getChildCount(Object parent) {
		if (parent == root) return list.size();
		else if (parent instanceof Organization)
			return ((Organization)parent).getChildCount();
		else 
			return 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#isLeaf(java.lang.Object)
	 */
	public boolean isLeaf(Object node) {
		return node instanceof PersonTreeModelMember;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#valueForPathChanged(javax.swing.tree.TreePath, java.lang.Object)
	 */
	public void valueForPathChanged(TreePath path, Object newValue) {
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getIndexOfChild(java.lang.Object, java.lang.Object)
	 */
	public int getIndexOfChild(Object parent, Object child) {
		if (parent == root)
			return list.indexOf(child);
		else if (parent instanceof Organization)
			return ((Organization)parent).getIndex((TreeNode)child);
		else
			return 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#addTreeModelListener(javax.swing.event.TreeModelListener)
	 */
	public void addTreeModelListener(TreeModelListener l) {
		listeners.add(l);
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#removeTreeModelListener(javax.swing.event.TreeModelListener)
	 */
	public void removeTreeModelListener(TreeModelListener l) {
		listeners.remove(l);
	}
	
	/**
	 * Getter for the list storage of children
	 * @return
	 */
	public List getList(){
		return list;
	}

}
