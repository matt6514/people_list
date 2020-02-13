package people;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class View extends JFrame implements ActionListener {
	
	public enum VIEW_TYPE{FULL,PERSON,NEWPERSON,EDITPERSON};
	
	private JList list; //all JSwing elements needed for full display mode
	private JScrollPane panel;
	private JButton choose_Person, add_Person;
	private JPanel button_Panel;
	
	private VIEW_TYPE type;
	
	private List<String> p_names;
	private List<ViewListener> listeners;
	private Database d;
	private Person selected_Person; //needed for new person and person mode
	
	public View(Database data) {
		type = VIEW_TYPE.FULL;
		
		listeners = new ArrayList<ViewListener>();
		
		d = data;
		p_names = d.getPeopleNames();
		list = new JList(p_names.toArray());
		panel = new JScrollPane(list);
		choose_Person = new JButton("Select");
		add_Person = new JButton("New Person");
		
		setLayout(new BorderLayout());
		
		button_Panel = new JPanel();
		button_Panel.setLayout(new GridLayout(2,2));
		button_Panel.add(choose_Person);
		button_Panel.add(add_Person);
		
		choose_Person.addActionListener(this);
		add_Person.addActionListener(this);
		
		add(panel, BorderLayout.CENTER);
		add(button_Panel, BorderLayout.SOUTH);
		
		type = VIEW_TYPE.FULL;
		createAndShowGui(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.getText().equals("Select")) {
			type = VIEW_TYPE.PERSON;
			selected_Person = d.getPerson((String) list.getSelectedValue());
		} else if (e.getActionCommand().equals("New Person")) {
			type = VIEW_TYPE.NEWPERSON;
			fireEvent(new NewPersonEvent(newPersonNamePrompt()));
		} 
		
	}
	
	private String newPersonNamePrompt() { //prompts user for name of new person
		return JOptionPane.showInputDialog("Full Name? ");
	}
	
	public void addViewListener(ViewListener l) {
		listeners.add(l);
	}

	public void removeViewListener(ViewListener l) {
		listeners.remove(l);
	}
	
	private void createAndShowGui(boolean firstTime) { //displays screen according to what mode view is in
		if (type == VIEW_TYPE.FULL) { //full view means home screen with display of all names in database
			if (firstTime) {
				setPreferredSize(new Dimension(200,200));
				pack();
				setVisible(true);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				return;
			}
			
			removeAll();
			repaint();
			revalidate();
			
			list = new JList(p_names.toArray());
			panel = new JScrollPane(list);
			add(panel, BorderLayout.CENTER);
			add(button_Panel, BorderLayout.SOUTH);
			
			repaint();
			revalidate();
		} else if (type == VIEW_TYPE.NEWPERSON) {
			
			removeAll();
			repaint();
			revalidate();
			
			repaint();
			revalidate();
			
		}
	}
	
	private void fireEvent(ViewEvent e) { //to notify the controller and model of what is happening
		for (ViewListener l: listeners) {
			l.handleViewEvent(e);
		}
	}

}
