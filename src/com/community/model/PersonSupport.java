/*
 * Created on Mar 31, 2005
 *
 */
package com.community.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.community.util.DatabaseUtils;
import com.community.util.OrderedList;

/**
 * @author peter rice
 * Support for the Person class. Includes methods for moving data into and out of the database.
 */
public class PersonSupport {
	
	private static PersonSupport instance;
	private static List<Person> input;
	private static int maxid = -1;
	
	private PersonSupport(){}
		
	public static void deletePerson(Person p){
		DatabaseUtils.execute("DELETE FROM PERSON WHERE ID = " + p.getKey() + ";");
	}
	
	public static void addPerson(Person p) {
		int id = DatabaseUtils.getNextId();
		p.setKey(id);
		DatabaseUtils.execute(p.savePerson());
	}
	
	public static void replacePerson(Person p) {
		DatabaseUtils.execute(p.update());
	}
		
	public static PersonTreeModel getNewTreeModel(){
		input = DatabaseUtils.getData();
		Map orgs = new HashMap();
		List list = new OrderedList();
		PersonRootNode root = new PersonRootNode(list);
		for (Person p : input){
			if (p.getOrganization() == null || p.getOrganization().length() == 0)
				list.add(new PersonTreeModelMember(root,
						p.getFirstName(),
						p.getLastName(),
						p.getKey()));
			else{
				Object o = orgs.get(p.getOrganization());
				if (o == null){
					o = new Organization(root, p.getOrganization());
					orgs.put(p.getOrganization(), o);
					list.add(o);
				}
				((Organization)o).add(new PersonTreeModelMember(root,
						p.getFirstName(),
						p.getLastName(),
						p.getKey()));
			}
		}
		return new PersonTreeModel(list, root);
	}
	
	public static Person getPerson(int key){
		for (int i = 0; i < input.size(); i++){
			if (input.get(i).getKey() == key) return input.get(i);
		}
		return null;
	}
	
	public static void outputXmasList(String filename){
		File file = new File(filename);
		List<Person> plist = new ArrayList<Person>();
		for (int i = 0; i < input.size(); i++){
			Person p = input.get(i);
			if (p.isXmas()) plist.add(p);
		}
		DatabaseUtils.outputFile(plist, file);
	}

}
