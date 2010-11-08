import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Gui extends JFrame {
	
	private JList list;
    private DefaultListModel listModel;

	private JButton addBtn;
	private JButton sortBtn;
	private JTextField textField;
	private JRadioButton cRBtn;
    private JRadioButton aRBtn;
	private JRadioButton sRBtn;
	
	public static void main (String args[]) {
		new Gui();
	}
	
	public Gui () {
		setupAndShowGUI();
		addListeners();
	}
	
	private void setupAndShowGUI() {
		setTitle("OOP Opgave 3");
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setSize(545,165);
		setLocation(650,250);
	
		Container container = getContentPane();
		container.setLayout(null); 
		
		addBtn = new JButton("Tilføj");
	    sortBtn = new JButton("Sortér");
		textField = new JTextField();
		cRBtn = new JRadioButton("Courier");
		aRBtn = new JRadioButton("Arial");
		sRBtn = new JRadioButton("Serif");
		
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
		
	    setVisible(true);
	}
	
	private void addListeners() {
		
		class CListener implements DocumentListener {
			
			public void insertUpdate(DocumentEvent e) {
		        updateTitle();
			}
			
			public void updateTitle() {
				String t = textField.getText();
				if (t.length() > 0) {
					setTitle(""+t.charAt(t.length() - 1));
				}
			}
			
			// Not used.
		    public void changedUpdate(DocumentEvent e) {}
			public void removeUpdate(DocumentEvent e) {}
		}
		
		class ButtonListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				if (e.getSource() == addBtn) {
					String t = textField.getText();
					if (!t.equals("")) {
						int index = listModel.getSize();
						listModel.addElement(t);
						textField.requestFocusInWindow();
						textField.setText("");
						
						list.setSelectedIndex(index);
			            list.ensureIndexIsVisible(index);
		            } else {
						Toolkit.getDefaultToolkit().beep();
						textField.requestFocusInWindow();
		                textField.selectAll();
		                return;
					}
				}
			}
		}
		
		addBtn.addActionListener(new ButtonListener());
		textField.getDocument().addDocumentListener(new CListener());
	}
	

}