package people;

public class Main {

	public static void main(String[] args) {
		
		Database d = new DatabaseImpl("People List", "testInfo.csv");
		
		View view = new View(d);
	}

}
