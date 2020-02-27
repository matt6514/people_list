package people;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class View extends JFrame implements ActionListener {
	
	public enum VIEW_TYPE{FULL,PERSON,NEWPERSON,EDITPERSON};
	
	private static int TOPX = 500;
	private static int TOPY = 200;
	private static int WIDTH = 300;
	private static int HEIGHT = 550;
	
	private JPanel full_panel, newPerson_panel, person_panel, edit_panel; //panels to contain different windows
	
	//all GUI elements needed for full display mode
	private JList list;
	private JScrollPane panel;
	private JButton choose_Person, add_Person;
	private JPanel button_Panel;
	
	//all GUI elements needed for new Person
    private JLabel[] newPerson_labels;
    private JTextField[] newPerson_textfields;
	private JLabel title_label;
    private JButton add, cancel;
    private JPanel newButton_panel, newText_panel;
	
    //all GUI elements needed for person
    private JLabel[] person_labels;
    private JTextField[] person_textfields;
    private JButton edit, delete, home;
    private JLabel personTitle_label;
    private JPanel personButton_panel, personText_panel;
    
    //all GUI elements need for editPerson
    private JLabel[] edit_labels;
    private JTextField[] edit_textfields;
    private JButton edit_cancel, save;
    private JLabel editTitle_label;
    private JPanel editButton_panel, editText_panel;
    
	private VIEW_TYPE type;
	
	private List<String> p_names;
	private Database d;
	private Person selected_Person; //needed for new person and person mode
	private DefaultListModel model;
	
	public View(Database data) {
		type = VIEW_TYPE.FULL;
		full_panel = new JPanel();
		newPerson_panel = new JPanel();
		person_panel = new JPanel();
		edit_panel = new JPanel();
		
		newPerson_labels = new JLabel[Person.keys.length];
		newPerson_textfields = new JTextField[Person.keys.length];
		person_labels = new JLabel[Person.keys.length];
		person_textfields = new JTextField[Person.keys.length];
		edit_labels = new JLabel[Person.keys.length];
		edit_textfields = new JTextField[Person.keys.length];
		
		d = data;
		p_names = d.getPeopleNames();
		model = new DefaultListModel();
		for (String s: p_names) {
			model.addElement(s);
		}
		
		// Construction of Full Person Panel
		
		list = new JList(model);
		panel = new JScrollPane(list);
		choose_Person = new JButton("Select");
		add_Person = new JButton("New Person");
		
		full_panel.setLayout(new BorderLayout());
		full_panel.setBounds(TOPX, TOPY, WIDTH, HEIGHT);
		
		button_Panel = new JPanel();
		button_Panel.setLayout(new GridLayout(2,2));
		button_Panel.add(choose_Person);
		button_Panel.add(add_Person);
		
		choose_Person.addActionListener(this);
		add_Person.addActionListener(this);
		
		full_panel.add(panel, BorderLayout.CENTER);
		full_panel.add(button_Panel, BorderLayout.SOUTH);
		
		type = VIEW_TYPE.FULL;
		
		createAndShowGui(VIEW_TYPE.FULL,true);
		
		//Construction of newPersonPanel

        newPerson_panel.setBounds(TOPX, TOPY, WIDTH, HEIGHT);
        newPerson_panel.setLayout(new BorderLayout());
        newText_panel = new JPanel();
        newText_panel.setLayout(null);
  
        title_label = new JLabel("New Person", SwingConstants.CENTER);
        title_label.setFont(new Font("Arial", Font.PLAIN, 25)); 
        title_label.setSize(300, 30); 
        title_label.setLocation(5, 5); 
        newText_panel.add(title_label); 
        
        for (int i = 0; i < Person.keys.length; i++) {
        	newPerson_labels[i] = createLabel(Person.keys[i],5,50+(i*30));
        	newPerson_textfields[i] = createTextField("",80,50+(i*30));
        	newText_panel.add(newPerson_labels[i]);
        	newText_panel.add(newPerson_textfields[i]);
        }
        
        add = new JButton("Add");
        cancel = new JButton("Cancel");
        add.addActionListener(this);
        cancel.addActionListener(this);
        newButton_panel = new JPanel();
        newButton_panel.setLayout(new GridLayout(2,1));
        newButton_panel.add(add);
        newButton_panel.add(cancel);
        
        newPerson_panel.add(newText_panel,BorderLayout.CENTER);
        newPerson_panel.add(newButton_panel,BorderLayout.SOUTH);
        
        //Construction of personPanel
        
        person_panel.setBounds(TOPX, TOPY, WIDTH, HEIGHT);
        person_panel.setLayout(new BorderLayout());
        personText_panel = new JPanel();
        personText_panel.setLayout(null);
        
        personTitle_label = new JLabel("Person", SwingConstants.CENTER);
        personTitle_label.setFont(new Font("Arial", Font.PLAIN, 25));
        personTitle_label.setSize(300,30);
        personTitle_label.setLocation(5, 5);
        personText_panel.add(personTitle_label);
        
        for (int i = 0; i < Person.keys.length;i++) {
        	person_labels[i] = createLabel(Person.keys[i],5,50+(i*30));
        	person_textfields[i] = createTextField("",80,50+(i*30));
        	personText_panel.add(person_labels[i]);
        	personText_panel.add(person_textfields[i]);
        }
        
        delete = new JButton("Delete");
        edit = new JButton("Edit");
        home = new JButton("Home");
        delete.addActionListener(this);
        edit.addActionListener(this);
        home.addActionListener(this);
        personButton_panel = new JPanel();
        personButton_panel.setLayout(new GridLayout(3,1));
        personButton_panel.add(delete);
        personButton_panel.add(edit);
        personButton_panel.add(home);
        
        person_panel.add(personText_panel,BorderLayout.CENTER);
        person_panel.add(personButton_panel,BorderLayout.SOUTH);
        
        //Construction of editPerson
        
        edit_panel.setBounds(TOPX, TOPY, WIDTH, HEIGHT);
        edit_panel.setLayout(new BorderLayout());
        editText_panel = new JPanel();
        editText_panel.setLayout(null);
        
        editTitle_label = new JLabel("Edit Person", SwingConstants.CENTER);
        editTitle_label.setFont(new Font("Arial",Font.PLAIN, 25));
        editTitle_label.setSize(300, 30);
        editTitle_label.setLocation(5, 5);
        editText_panel.add(editTitle_label);
        
        for (int i= 0; i < Person.keys.length;i++) {
        	edit_labels[i] = createLabel(Person.keys[i],5,50+(i*30));
        	edit_textfields[i] = createTextField("",80,50+(i*30));
        	editText_panel.add(edit_labels[i]);
        	editText_panel.add(edit_textfields[i]);
        }
        
        edit_cancel = new JButton("Cancel");
        save = new JButton("Save");
        edit_cancel.addActionListener(this);
        save.addActionListener(this);
        editButton_panel = new JPanel();
        editButton_panel.setLayout(new GridLayout(1,2));
        editButton_panel.add(edit_cancel);
        editButton_panel.add(save);
        
        edit_panel.add(editButton_panel, BorderLayout.SOUTH);
        edit_panel.add(editText_panel,BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.getText().equals("Select")) {
			//button on home screen -- changes screen to person view
			selected_Person = d.getPerson((String) list.getSelectedValue());
			createAndShowGui(VIEW_TYPE.PERSON,false);
		} else if (e.getActionCommand().equals("New Person")) {
			//button on home screen -- changes screen to new person and initiates new person creation
			String temp = newPersonNamePrompt();
			if (temp != null) {
				selected_Person = new PersonImpl(temp);
				createAndShowGui(VIEW_TYPE.NEWPERSON,false);
			}
		}  else if (e.getActionCommand().equals("Cancel") && type == VIEW_TYPE.NEWPERSON) {
			//button on new person screen -- changes back to home screen and delete information on person
			selected_Person = null;
			clearNewPerson();
			createAndShowGui(VIEW_TYPE.FULL,false);
		} else if (e.getActionCommand().equals("Add")) {
			//button on new person screen -- changes back to home screen and add person to database
			for (int i = 0; i < Person.keys.length; i++) {
				if (!newPerson_textfields[i].getText().equals("")) {
					selected_Person.setItem(Person.keys[i], newPerson_textfields[i].getText());
				}
			}
			d.addPerson(selected_Person);
			model.addElement(selected_Person.getName());
			selected_Person = null;
			clearNewPerson();
			createAndShowGui(VIEW_TYPE.FULL,false);
		} else if (e.getActionCommand().equals("Edit")) {
			//button on the person screen -- changes to edit person screen
			createAndShowGui(VIEW_TYPE.EDITPERSON,false);
		} else if (e.getActionCommand().equals("Delete")) {
			//button on the person screen -- changes to the home screen -- deletes selected person from database
			if (JOptionPane.showConfirmDialog(null, "Delete " + selected_Person.getName() + "? ","WARNING",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				model.removeElement(selected_Person.getName());
				d.removePerson(selected_Person);
				selected_Person = null;
				clearPerson();
				createAndShowGui(VIEW_TYPE.FULL,false);
			}
		} else if (e.getActionCommand().equals("Cancel") && type == VIEW_TYPE.EDITPERSON) {
			//button on the edit screen -- changes to the home screen -- clears edit
			clearEditPerson();
			selected_Person = null;
			createAndShowGui(VIEW_TYPE.EDITPERSON,false);
		} else if (e.getActionCommand().equals("Save")) {
			//button on the edit screen -- changes to home screen -- save all changes made to selected change
			for (int i = 0; i < Person.keys.length; i++) {
				if (!edit_textfields[i].getText().equals("") || !selected_Person.getItem(Person.keys[i]).equals("")) {
					selected_Person.setItem(Person.keys[i], edit_textfields[i].getText());
				}
			}
			d.removePerson(selected_Person);
			d.addPerson(selected_Person);
			selected_Person = null;
			clearEditPerson();
			createAndShowGui(VIEW_TYPE.FULL,false);
			
		} else if (e.getActionCommand().equals("Home")) {
			//button on the person screen -- changes to the home screen -- does not save changes made
			selected_Person = null;
			createAndShowGui(VIEW_TYPE.FULL,false);
		}
		
	}
	
	private String newPersonNamePrompt() { //prompts user for name of new person
		return JOptionPane.showInputDialog("Full Name? ");
	}
	
	private JLabel createLabel(String name, int x, int y) {
		JLabel label = new JLabel(name);
		label.setFont(new Font("Arial", Font.PLAIN, 15));
		label.setSize(50,20);
		label.setLocation(x, y);
		return label;
	}
	
	private JTextField createTextField(String info, int x, int y) {
		JTextField text = new JTextField();
		text.setText(info);
		text.setFont(new Font("Arial", Font.PLAIN, 15));
		text.setSize(200,20);
		text.setLocation(x, y);
		return text;
	}
	
	private void createAndShowGui(VIEW_TYPE t, boolean firstTime) { //displays screen according to what mode view is in
		type = t;
		if (type == VIEW_TYPE.FULL) { //full view means home screen with display of all names in database
			setTitle("People");
			if (firstTime) {
				setContentPane(full_panel);
				setBounds(TOPX, TOPY, WIDTH, HEIGHT);
				
				Dimension d = new Dimension(WIDTH,HEIGHT);
				setPreferredSize(d);
				pack();
				setVisible(true);
				setResizable(true);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				return;
			}
			
			setContentPane(full_panel);
			
			pack();
			repaint();
			revalidate();
			
		} else if (type == VIEW_TYPE.NEWPERSON) {
			
			setTitle("New Person");
			setContentPane(newPerson_panel);
			
			newPerson_textfields[0].setText(selected_Person.getName()); //set name into first text field
			
			pack();
			repaint();
			revalidate();
			
		} else if (type == VIEW_TYPE.PERSON) {
			
			setTitle("Person");
			setContentPane(person_panel);
			
			for (int i = 0; i < newPerson_textfields.length; i++) {
				person_textfields[i].setText(selected_Person.getItem(Person.keys[i]));
			}
			
			pack();
			repaint();
			revalidate();
			
		}
	}
	
	private void clearNewPerson() {
		for (int i = 0; i < newPerson_textfields.length;i++) {
			newPerson_textfields[i].setText("");
		}
	}
	
	private void clearEditPerson() {
		for (int i = 0; i < edit_textfields.length; i ++) {
			edit_textfields[i].setText("");
		}
	}
	
	private void clearPerson() {
		for (int i = 0; i < person_textfields.length; i++) {
			person_textfields[i].setText("" );
		}
	}

}
