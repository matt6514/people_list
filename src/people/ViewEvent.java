package people;

abstract public class ViewEvent {
	
	public boolean isPersonEvent() { //choosing person from data base
		return false;
	}
	
	public boolean isEditPersonEvent() { //some information about a person needs to be edited
		return false;
	}
	
	public boolean isNewPersonEvent() { //new person is added to database
		return false;
	}
	
	public boolean isSearchEvent() {
		return false;
	}
}

class NewPersonEvent extends ViewEvent{
	
	String n;
	
	public NewPersonEvent(String name) {
		n = name;
	}
	
	public String getName() {
		return n;
	}
	
	public boolean isNewPersonEvent() {
		return true;
	}
}

class PersonEvent extends ViewEvent{
	String n;
	
	public PersonEvent(String name) {
		n = name;
	}
	
	public String getName() {
		return n;
	}
	
	public boolean isPersonEvent() {
		return true;
	}
}

class EditPersonEvent extends ViewEvent{
	String key;
	String data;
	
	public EditPersonEvent(String key, String data) {
		this.key = key;
		this.data = data;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getData() {
		return data;
	}
	
	public boolean isEditPersonEvent() {
		return true;
	}
}

class SearchEvent extends ViewEvent{
	
	public enum SearchType{NAME,CIRCLE,DATE,NUMBER};
	
	String search;
	SearchType type;
	
	public SearchEvent(SearchType s, String info) {
		search = info;
		type = s;
	}
	
	public SearchType getType() {
		return type;
	}
	
	public String getInfo() {
		return search;
	}
	
	public boolean isSearchEvent() {
		return true;
	}
}