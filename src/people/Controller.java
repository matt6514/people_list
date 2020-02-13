package people;

public class Controller {
	
	Model m;
	View v;
	Database d;
	
	public Controller(Model model,View view,Database data) {
		m = model;
		v = view;
		d = data;
	}

}
