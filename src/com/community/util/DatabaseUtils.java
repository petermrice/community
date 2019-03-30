package com.community.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.community.model.Person;

/**
 * Improved 8/30/15
 * 
 * The database is the data.csv file stored in dropbox/community
 * 
 * Methods here read and write the object List<Person>.
 * 
 * @author peterrice
 *
 */
public class DatabaseUtils {
	
	public static Properties config;
	private static String jdbcurl;
	private static String userid;
	private static String password;
	
	public static void init(){
		try {
		     Class.forName("org.hsqldb.jdbcDriver" );
		} catch (Exception e) {
		     System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
		     e.printStackTrace();
		     System.exit(1);
		     return;
		}
		try {
//			if (Config.isMacOS) 
				jdbcurl = "jdbc:hsqldb:file:/Users/" + Config.getUserRoot() + "/Dropbox/community/communitydb;ifexists=true";
//			else 
//				jdbcurl = "jdbc:hsqldb:file:C:\\Users\\" + Config.getUserRoot() + "\\Dropbox\\community\\communitydb;ifexists=true";
			userid = "sa";
			password = "";
		} catch (Exception e) {
			System.err.println("Failed to load community.cfg.");
			System.exit(1);
		}
	}
	
	public static List<Person> getData(){
		Connection c = null;
		List<Person> list = new ArrayList<Person>();
		try {
			c = DriverManager.getConnection(jdbcurl, userid, password);
			ResultSet rs = c.createStatement().executeQuery("SELECT * FROM PERSON");
			while (rs.next()) {
				Person p = new Person(rs);
				list.add(p);
			}
			c.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static int getNextId() {
		try {
			Connection c = DriverManager.getConnection(jdbcurl, userid, password);
			ResultSet rs = c.createStatement().executeQuery("SELECT ID FROM PERSONMAXID WHERE IDKEY = 1");
			rs.next();
			int newid = rs.getInt("ID");
			c.createStatement().execute("UPDATE PERSONMAXID SET ID = " + (++newid) + " WHERE IDKEY = 1;");
			c.close();
			return newid;
		} catch (SQLException e) {
			System.out.println(e);
			return -1;
		}
	}
	
	public static void execute(String sql) {
		try {
			Connection c = DriverManager.getConnection(jdbcurl, userid, password);
			ResultSet rs = c.createStatement().executeQuery(sql);
			c.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}

	public static void outputFile(List<Person> plist, File file) {
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter br = new BufferedWriter(new FileWriter(file));
			br.write("Christams List, Created " + DateUtils.now());
			br.write("\n\n");
			for (Person p : plist) {
				br.write(p.toCSVString() + "\n");
			}
			br.close();
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}
}
