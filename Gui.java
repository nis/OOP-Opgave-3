import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {
	
	private JList list;
    private DefaultListModel listModel;
	
	public static void main (String args[]) {
		new Gui();
	}
	
	public Gui () {
		setTitle("OOP Opgave 3");
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setSize(545,165);
		setLocation(650,250);
	    setVisible(true);
	
		Container container = getContentPane();
		container.setLayout(null); 
		
		JButton addBtn = new JButton("Tilføj");
	    JButton sortBtn = new JButton("Sortér");
		JTextField textField = new JTextField();
		JRadioButton cRBtn = new JRadioButton("Courier");
		JRadioButton aRBtn = new JRadioButton("Arial");
		JRadioButton sRBtn = new JRadioButton("Serif");
		
		ButtonGroup rGroup = new ButtonGroup();
		rGroup.add(cRBtn);
		rGroup.add(aRBtn);
		rGroup.add(sRBtn);
	
		listModel = new DefaultListModel();
		list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
	
		JTextArea tArea = new JTextArea();
	
		container.add(addBtn);
		addBtn.setBounds(100,40,75,25);
		
		container.add(sortBtn);
		sortBtn.setBounds(100,70,75,25);
		
		container.add(textField);
		textField.setBounds(20,10,155,25);
		
		container.add(cRBtn);
		cRBtn.setSelected(true);
		cRBtn.setBounds(20,35,155,25);
		
		container.add(aRBtn);
		aRBtn.setBounds(20,55,155,25);
		
		container.add(sRBtn);
		sRBtn.setBounds(20,75,155,25);

		JScrollPane listScrollPane = new JScrollPane(list);
		container.add(listScrollPane);
		listScrollPane.setBounds(200,10,155,120);
		
		JScrollPane tAreaScrollPane = new JScrollPane(tArea);
		container.add(tAreaScrollPane);
		tAreaScrollPane.setBounds(375,10,155,120);
	}
}