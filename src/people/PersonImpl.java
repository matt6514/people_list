package people;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonImpl implements Person {

	String name;

	Map<String, String> info;

	public PersonImpl(String n) {
		name = n;
		info = new HashMap<String, String>();
		info.put("name", name);
	}

	public PersonImpl(String raw, boolean indicator) { // indicator can be true or false simply indicates it should be
														// read from a file
		info = new HashMap<String, String>();
		String[] data = raw.split(",");
		name = data[0];
		for (int i = 0; i < keys.length; i++) {
			if (!data[i].equals(" ")) {
				try {
					info.put(keys[i], astericsToComma(data[i]));
				} catch (Exception e) {
					e.printStackTrace();
					if (keys[i] != null)
						System.out.println("error " + keys[i]);
					if (astericsToComma(data[i]) != null)
						System.out.println("error " + info.get(keys[i]));
					else {
						System.out.println(data[i]);
					}
				}
			}
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setItem(String key, String data) {
		info.put(key, data);
	}

	@Override
	public void editItem(String key, String data) {
		info.replace(key, data);
	}

	@Override
	public boolean isItem(String key) {
		return (info.containsKey(key));
	}

	@Override
	public boolean isEqual(String n) {
		return n.toLowerCase().equals(name.toLowerCase());
	}

	@Override
	public List<String> getActiveKeys() {
		List<String> active_keys = new ArrayList<String>();
		for (String s : keys) {
			if (info.get(s) != null) {
				active_keys.add(s);
			}
		}
		return active_keys;
	}

	@Override
	public List<String> getInactiveKeys() {
		List<String> inactive_keys = new ArrayList<String>();
		for (String s : keys) {
			if (info.get(s) == null) {
				inactive_keys.add(s);
			}
		}
		return inactive_keys;
	}

	@Override
	public String getItem(String key) {
		return (info.get(key));
	}

	@Override
	public String toString() {
		String storage = "";
		for (String s : keys) {
			storage += commaToAsterics(info.getOrDefault(s, " ")) + ",";
		}
		return storage;
	}

	private String commaToAsterics(String s) { // since the info is being put into a comma-seperated value file the
												// comma are going to represented by aterics
		for (int i = 0; i < s.length(); i++) {
			if (s.substring(i, i + 1).equals(",")) {
				s = s.substring(0, i) + "*" + s.subSequence(i + 1, s.length());
			}
		}
		return s;
	}

	private String astericsToComma(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.substring(i, i + 1).equals("*")) {
				s = s.substring(0, i) + "," + s.subSequence(i + 1, s.length());
			}
		}
		return s;
	}

}
