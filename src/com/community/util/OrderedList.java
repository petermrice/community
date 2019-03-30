/*
 * Created on Apr 9, 2005
 *
 */
package com.community.util;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * @author peter
 * An implementation of the List interface which stores its elements
 * in sorted order. The default order is alphabetical: "String.compareIngoreCase".
 */
public class OrderedList<T> extends ArrayList<T> {

	private Comparator<T> comparator = new Comparator<T>(){
		public int compare(T arg0, T arg1) {
			if (arg1 == null) return 1;
			if (arg0 == null) return -1;
			return arg0.toString().compareToIgnoreCase(arg1.toString());
		}
	};
	
	public OrderedList(){}
	
	public OrderedList(Comparator<T> c){comparator = c;}
	

	/* (non-Javadoc)
	 * @see java.util.Collection#toArray()
	 */
	public T[] toArray() {
		return (T[])super.toArray();
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	public boolean add(T arg0) {
		boolean ok = super.add(arg0);
		if (ok) Collections.sort(this, comparator);
		return ok;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Collection arg0) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection arg0) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	public boolean addAll(int arg0, Collection arg1) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Collection arg0) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	public boolean retainAll(Collection arg0) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see java.util.List#get(int)
	 */
	public T get(int index) {
		return super.get(index);
	}

	/* (non-Javadoc)
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	public T set(int i, T o) {
		return super.set(i, o);
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	public void add(int i, T o) {
		super.add(i, o);
		Collections.sort(this, comparator);
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(int)
	 */
	public T remove(int index) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	public int indexOf(Object o) {
		return super.indexOf(o);
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator()
	 */
	public ListIterator listIterator() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator(int)
	 */
	public ListIterator listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see java.util.List#subList(int, int)
	 */
	public List subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

}
