package people;

import java.util.List;

public interface Person {
	
	public static String[] keys = {"name","circles","met","date","number","email","friends","notes","opinion","like","kiss","2nd","3rd","4th"};
	// this order cannot be changed and if new metrics are added they must be added to the end

	/*
	 * List of Keys/Information Stored in List :
	 * 
	 * -Full Name (Notation is a space between every name, use a dash if it is part of the same name) "Name"
	 * -Circles (Notation is to use a comma between every circle name) "Circles"
	 * -First Place We Met "Met"
	 * -Date of first meeting or first time I knew who they were, dd/mm/yyyy "Date"
	 * -Cell Number (703-276-1843) "Number"
	 * -Email "Email"
	 * Friends (Yes/No) "Friends"
	 * Notes "Notes"
	 * Opinion "Opinion"
	 * Like (Yes/No) "Like"
	 * Kiss (Yes/No) "Kiss"
	 * 2nd (Yes/No) "2nd"
	 * 3rd (Yes/No) "3rd"
	 * 4th (Yes/No) "4th"
	 * 
	 *  dash denotes info that will show in public mode
	 */
	
	String getName(); //returns value of their name for listing purposes
	
	void setItem(String key, String data); //sets key to new item
	void editItem(String key, String data); //edits value of specified key
	
	boolean isItem(String key); //check to see if specific item is present 
	String getItem(String key); //assumes item is there
	
	boolean isEqual(String n); //checks name against name in document
	
	List<String> getActiveKeys(); //returns all keys that currently have data logged in them
	List<String> getInactiveKeys(); //return all keys that currently are not in use
	
	String toString(); //sends string for file storage purposes
}
