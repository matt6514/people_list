package people;

import java.util.List;

public interface Database {
	
	Person getPerson(String name); //return single person object
	List<Person> getPeople(); //return list of all people
	List<String> getPeopleNames(); //return list of all names
	
	boolean save(); //save data to file the file type must be '.csv'
	
	void addPerson(Person p); //adds person to the list of all people
	void removePerson(Person p); //removes person from the list of all peoples
	
	String getName();
}
