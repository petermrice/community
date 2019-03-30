/*
 * Created on Dec 10, 2004
 *
 */
package com.community.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.community.model.Person;

/**
 * <pre>
 * A parser for csv (comma separated values) files which is dedicated to
 * the data file needed for this application.
 * 
 * A line (bounded by \n) contains fields enclosed in " if the field is a string
 * and no enclosure if it is a number. Fields are separated by ,.
 * 
 * The read() melthod will read the file and produce a List<Person>/
 * The write() method will take a List<Person> and write it out as a csv file.
 * 
 * </pre>
 * @author peter
 */
public class CSVReader implements Debuggable {
	
	private static String[] header = {"FIRSTNAME", "LASTNAME", "ORGAINZATION", "ADDRESS", "CITY", "STATE", "ZIP", "COUNTRY",
			"EMAIL1", "EMAIL2", "LABEL", "PHONE1", "PHONE2", "PHONE3", "PHONE4", "PHONE5", "NOTES", "XMAS", "FOREIGN_ADDRESS", "ID"};

	private CSVReader(){}
	
	public static List<Person> read(File in) {
		List<Person> data = new ArrayList<Person>();
		int n = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(in));
			String line = br.readLine(); // header, skip this
			line = br.readLine();
			if (DEBUG) System.out.println("line " + n);
			n++;
			while (line != null && line.trim().length() > 0) {
				data.add(getPerson(line));
				line = br.readLine();
				if (DEBUG) System.out.println("line " + n);
				n++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e){
				
			}
		}
		return data;
	}
	
	private static Person getPerson(String line){
		Person p = new Person();
		int k = 0;
		int n = -1;
		int key = -1;
		boolean xmas = false;
		boolean foreign = false;
		String val = null;
		for (int i = 0; i < header.length; i++){
			if (i < 17){
				char ch = line.charAt(k);
				if (ch == ',') { // empty field
					k++;
					continue;
				}
				if (Character.isDigit(ch)){ // number field, no ""
					n = line.indexOf(',', k + 1);
					val = line.substring(k, n);
				} else { // ch == "
					n = line.indexOf('"', k + 1);
					val = line.substring(k + 1, n);
					val = denormalize(val);
					n++; // now n points to the comma
				}
				switch(i){
				case 0: p.setFirstName(val); break;
				case 1: p.setLastName(val); break;
				case 2: p.setOrganization(val); break;
				case 3: p.setAddress(val); break;
				case 4: p.setCity(val); break;
				case 5: p.setState(val); break;
				case 6: p.setZip(val); break;
				case 7: p.setCountry(val); break;
				case 8: p.setEmail1(val); break;
				case 9: p.setEmail2(val); break;
				case 10: p.setLabel(val); break;
				case 11: p.setPhone1(val); break;
				case 12: p.setPhone2(val); break;
				case 13: p.setPhone3(val); break;
				case 14: p.setPhone4(val); break;
				case 15: p.setPhone5(val); break;
				case 16: p.setNotes(val); break;
				}
				k = n + 1; // character after the ,
			} else if (i == 17){
				char c = line.charAt(k);
				xmas = c == '1';
				p.setXmas(xmas);
				k = k + 2;
			} else if (i == 18) {
				char c = line.charAt(k);
				foreign = c == '1';
				p.setForeign(foreign);
				k = k + 2;
			} else if (i == 19){
				try {
					key = Integer.parseInt(line.substring(k));
				} catch (Exception e) {
					System.out.println("Error in " + p.getLastName());
					return p;
				}
				p.setKey(key);
			}
		}
		return p;
	}
	
	public static void write(List<Person> data, File out){
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(out));
			bw.write(getHeader());
			for (Person p : data){
				bw.write(toCSVString(p));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (Exception e){
				
			}
		}
	}
		
	private static String getHeader(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < header.length; i++){
			sb.append('"').append(header[i]).append('"').append(',');
		}
		sb.append('\n');
		return sb.toString();
	}
	
	private static String toCSVString(Person p){
		StringBuffer sb = new StringBuffer();
		return sb.append('"').append(p.getFirstName()).append("\",\"").
			append(p.getLastName()).append("\",\"").
			append(p.getOrganization()).append("\",\"").
			append(normalize(p.getAddress())).append("\",\"").
			append(p.getCity()).append("\",\"").
			append(p.getState()).append("\",\"").
			append(p.getZip()).append("\",\"").
			append(p.getCountry()).append("\",\"").
			append(p.getEmail1()).append("\",\"").
			append(p.getEmail2()).append("\",\"").
			append(p.getLabel()).append("\",\"").
			append(p.getPhone1()).append("\",\"").
			append(p.getPhone2()).append("\",\"").
			append(p.getPhone3()).append("\",\"").
			append(p.getPhone4()).append("\",\"").
			append(p.getPhone5()).append("\",\"").
			append(normalize(p.getNotes())).append("\",").
			append(p.isXmas()?1:0).append(",").
			append(p.isForeign()?1:0).append(",").
			append(p.getKey()).append("\n").toString();
	}

	
	/**
	 * replace "u000a" with '\n'
	 * @param in
	 * @return
	 */
	private static String denormalize(String in){
		return in.replace("u000a", "\n");
	}
	
	private static String normalize(String in){
		return in.replace("\n", "u000a");
		
	}
}
