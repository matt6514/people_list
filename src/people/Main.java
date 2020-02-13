package people;

public class Main {

	public static void main(String[] args) {
		
		Database d = new DatabaseImpl("People List", "testInfo.csv");
		
		Person p = new PersonImpl("Matthew McKnight");
		
		p.setItem("like", "yes");
		p.setItem("circles", "UNC, UNC-Ultimate, Frisbee");
		
		//d.addPerson(p);
		
		Person p1 = new PersonImpl("Jason Manning");
		
		p1.setItem("met", "yccs 2018");
		p1.setItem("date", "03/08/2019");
		
		//d.addPerson(p1);
		
		//d.save("testInfo.csv");
		
		if (d.getPerson("Matthew McKnight") != null) {
			System.out.println(d.getPerson("Matthew McKnight").toString());
		} else {
			System.out.println("error!");
		}
		
		Model model = new Model(d);
		View view = new View(d);
		Controller controller = new Controller(model,view,d);
	}

}
