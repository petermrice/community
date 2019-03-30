package com.community.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
/**
 * Layout for labels and fields. In the default arrangement,
 * there are two columns, with the left column containing right-
 * justified components and the right column containing left-
 * justified components. This arrangement is designed to display
 * labels and fields (one each to a row). The total arrangement is
 * centered in the available space. Vertical spacing is controled by
 * the vPad and hPad values (default = 5).<br>
 * By setting the column arrangement value to ONE_COLUMN, the
 * arrangement will be one column, left justified, with the whole
 * column centered in the available space.<br>
 * Creation date: (3/25/2002 6:28:16 PM)<br>
 * @author: Peter Rice
 */
public class FieldLayout implements LayoutManager {
	private int vpad = 5;
	private int hpad = 5;
	private int maxLabelWidth = 0;
	private int maxFieldWidth = 0;
	private int[] rowHeight;
	private int containerWidth = 0;
	private int containerHeight = 0;
	public final static int TWO_COLUMNS = 0;
	public final static int ONE_COLUMN = 0;
	private int cols = ONE_COLUMN;
	/**
	 * FieldLayout constructor.
	 */
	public FieldLayout() {
		super();
	}
	/**
	 * FieldLayout constructor. The only acceptable values for the
	 * parameter are ONE_COLUMN, snd TWO_COLUMNS.
	 */
	public FieldLayout(int columns) {
		super();
		setColumns(columns);
	}
	/**
	 * Adds the specified component with the specified name to
	 * the layout.
	 * @param name the component name
	 * @param comp the component to be added
	 */
	public void addLayoutComponent(String name, Component comp) {
		
	}
	/** 
	 * Lays out the container in the specified panel.
	 * @param parent the component which needs to be laid out 
	 */
	public void layoutContainer(Container parent) {
		setSizes(parent);
		Dimension dim = preferredLayoutSize(parent);
//		Insets insets = parent.getInsets();
		int fieldStart = (containerWidth - dim.width) / 2 + maxLabelWidth + hpad;
		int currentY = (containerHeight - dim.height) / 2;
		int maxWidth = maxFieldWidth > maxLabelWidth ? maxFieldWidth : maxLabelWidth;
		int lmargin = (containerWidth - maxWidth)/2;
		for (int i = 0; i < parent.getComponentCount(); i += 2) {
			Component c1 = parent.getComponent(i);
			Component c2 = null;
			if (i + 1 < parent.getComponentCount())
				c2 = parent.getComponent(i+1);
			else
				c2 = c1; // got to do something!
			Dimension d1 = c1.getPreferredSize();
			Dimension d2 = c2.getPreferredSize();
			if(cols == TWO_COLUMNS){
				c1.setBounds(fieldStart - hpad - d1.width, currentY, d1.width, d1.height);
				c2.setBounds(fieldStart, currentY, d2.width, d2.height);
				currentY += rowHeight[i / 2] + vpad;
			}else{
				c1.setBounds(lmargin, currentY, d1.width, d1.height);
				currentY += d1.height + vpad;
				c2.setBounds(lmargin, currentY, d2.width, d2.height);
				currentY += d2.height + vpad;
			}
		}
	}
	/** 
	 * Calculates the minimum size dimensions for the specified 
	 * panel given the components in the specified parent container.
	 * @param parent the component to be laid out
	 * @see #preferredLayoutSize
	 */
	public Dimension minimumLayoutSize(Container parent) {
		return preferredLayoutSize(parent);
	}
	/**
	 * Calculates the preferred size dimensions for the specified 
	 * panel given the components in the specified parent container.
	 * @param parent the component to be laid out
	 *  
	 * @see #minimumLayoutSize
	 */
	public Dimension preferredLayoutSize(Container parent) {
		Dimension dim = new Dimension(0, 0);
		setSizes(parent);
		Insets insets = parent.getInsets();
		dim.width = insets.left + maxFieldWidth + hpad + maxLabelWidth + insets.right;
		int total = 0;
		if (cols == TWO_COLUMNS){
			for (int i = 0; i < parent.getComponentCount() / 2; i++) {
				total += (rowHeight[i] + vpad);
			}
		}else{
			for (int i = 0; i < parent.getComponentCount(); i++){
				total += parent.getComponent(i).getPreferredSize().height + vpad;
			}
		}
		dim.height = total - vpad;
		return dim;
	}
	/**
	 * Removes the specified component from the layout.
	 * @param comp the component to be removed
	 */
	public void removeLayoutComponent(Component comp) {
	}
	/**
	 * Fix sizes for contained components.
	 * Creation date: (3/25/2002 6:37:17 PM)
	 * @param parent java.awt.Container
	 */
	private void setSizes(Container parent) {
		Insets insets = parent.getInsets();
		containerWidth = parent.getSize().width - insets.left - insets.right;
		containerHeight = parent.getSize().height - insets.top - insets.bottom;
		int count = parent.getComponentCount();
		rowHeight = new int[count / 2 + 1];
		for (int i = 0; i < count; i++) {
			Component c = parent.getComponent(i);
			Dimension d = c.getPreferredSize();
			if (i % 2 == 0) {
				maxLabelWidth = maxLabelWidth < d.width ? d.width : maxLabelWidth;
			} else {
				maxFieldWidth = maxFieldWidth < d.width ? d.width : maxFieldWidth;
			}
			rowHeight[i / 2] = d.height;
		}
	}
	
	/**
	 * Set the vertical distance between components.
	 */
	public void setVPad(int value){
		vpad = value;
	}
	
	/**
	 * Set the horizontal distance between components.
	 */
	public void setHPad(int value){
		hpad = value;
	}
	
	/**
	 * Sets the column arrangement to single or double coloumn.
	 * The only acceptable values for the
	 * parameter are ONE_COLUMN, and TWO_COLUMNS.
	 */
	public void setColumns(int val){
		if (val == ONE_COLUMN) cols = ONE_COLUMN;
	} 
}