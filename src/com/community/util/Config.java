/*
 * Created on Nov 5, 2004
 *
 */
package com.community.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.swing.JOptionPane;

/**
 * @author peter rice
 * 
 * Central location for all configuration information.
 */
public class Config {

	public static Properties config;
	public static File configfile;
	public static String userroot;
	private static int fontSize = -1;
	public static boolean isMacOS;
	
	private static boolean DEBUG = true;

	public static void init() {
		String userDir = System.getProperty("user.dir");
		Path path = Paths.get(userDir);
		File file = new File("/Users");
		if (!file.exists()) {
			errormessage("The folder /Users does not exist. This cannot be a Mac or a PC!");
			System.exit(1);;
		}
		file = new File("/Users/" + path.getName(1) + "/Dropbox/community");
		if (!file.exists()) {
			errormessage("\"Dropbox does not seem to be installed or the folder 'community'" + 
					" is not in it. \nWe need to have it to access the database.");
			System.exit(1);
		}
		configfile = new File("/Users/" + path.getName(1) + "/Dropbox/community/community.cfg");
		if (!configfile.exists()) errormessage("The config file: /Dropbox/community/community.cfg"
				+ " does not seem to exist. It will be created, along with a new database.");
		userroot = path.getName(1).toString();
		try {
			if (!file.exists()) file.createNewFile();
			config = new Properties();
			config.load(new FileInputStream(configfile));
		} catch (Exception e) {
			errormessage("Error: " + e);
			System.exit(1);
		} 
	}
	
	public static Properties getConfig() throws FileNotFoundException {
		if (config == null) throw new FileNotFoundException("community.cfg not found.");
		return config;
	}

	public static String getConfigProperty(String key) {
		return config.getProperty(key);
	}
	
	public static void saveConfig(int x, int y) throws IOException {
		config.setProperty("location.x", Integer.toString(x));
		config.setProperty("location.y", Integer.toString(y));
		saveConfigFile();
	}
	
	public static int getX() {
		return Integer.parseInt(config.getProperty("location.x"));
	}

	public static int getY() {
		return Integer.parseInt(config.getProperty("location.y"));
	}

	private static void saveConfigFile() throws IOException {
		config.store(new FileOutputStream(configfile),"Required configuration file for Community ");
	}

	public static int getFontSize() {
		if (fontSize == -1) 
			fontSize = Integer.parseInt(config.getProperty("font.size"));
		return fontSize;
	}
	
	public static void setFontSize(int fontSize) {
		Config.fontSize = fontSize;
		config.setProperty("font.size", fontSize + "");
	}
		
	/**
	 * The 'root' location is the root of the user's space, i.e. /Users/peter for a user
	 * named peter on a Mac.
	 * @return
	 */
	public static String getUserRoot() {
		return userroot;
	}
	
	public static void errormessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}
}