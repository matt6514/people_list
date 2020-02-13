package people;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class DatabaseImpl implements Database {
	
	String name;
	List<Person> p_list;
	
	public DatabaseImpl(String name) {
		p_list = new ArrayList<Person>();
		this.name = name;
	}
	
	public DatabaseImpl(String name, String fileName) {
		this.name = name;
		p_list = new ArrayList<Person>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			name = reader.readLine();
			reader.readLine();
			String info;
			while(true) {
				info = reader.readLine();
				if (info == null) break;
				p_list.add(new PersonImpl(info, true));
			}
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Person getPerson(String name) {
		for (Person p: p_list) {
			if (p.isEqual(name)) {
				return p;
			}
		}
		return null;
	}

	@Override
	public List<Person> getPeople() {
		return p_list;
	}
	
	@Override
	public List<String> getPeopleNames(){
		List<String> name_list = new ArrayList<String>();
		for (Person p: p_list) {
			name_list.add(p.getName());
		}
		return name_list;
	}

	@Override
	public boolean save(String fileName) {
		String storage = name + "\n";
		for (String s: Person.keys) {
			storage += s + ",";
		}
		storage = storage.substring(0,storage.length()-1) + "\n";
		
		for (Person p: p_list) {
			storage += p.toString() + "\n";
		}
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(storage);
			writer.close();
			return true;
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(storage);
			return false;
		}
	}

	@Override
	public void addPerson(Person p) {
		p_list.add(p);
	}

	@Override
	public void removePerson(Person p) {
		p_list.remove(p);
		
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	
}
