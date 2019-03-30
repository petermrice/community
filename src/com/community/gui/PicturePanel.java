/*
 * Created on Dec 11, 2004
 *
 */
package com.community.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * @author peter
 *
 */
public class PicturePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7799334243089760309L;
	Image image;
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g) {
		if (image != null)
			g.drawImage(image,0,0,Color.white, this);
		else
			g.drawString("Image not found", 5,30);
	}
	
	public void setImage(Image image){
		this.image = image;
		if (image == null) setPreferredSize(new Dimension(200, 50));
		else setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
	}
}
