import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Gui extends JFrame {
	
	private JList list;
    private DefaultListModel listModel;
	private Font listFont;
	
	private Font rbFont;
	private JScrollPane listScrollPane;

	private JButton addBtn;
	private JButton sortBtn;
	private JTextField textField;
	private JRadioButton cRBtn;
    private JRadioButton aRBtn;
	private JRadioButton sRBtn;
	private JTextArea tArea;
	private JLabel textFieldLabel = new JLabel("Tekst:");
	
	private String[] stringArray;
	
	public static void main (String args[]) {
		new Gui();
	}
	
	public Gui () {
		setupAndShowGUI();
		buildAndAddListeners();
	}
	
	private void setupAndShowGUI() {
		
		// Set up the frame
		setTitle("OOP Opgave 3: Nis Sarup");
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setSize(545,145);
		setLocation(650,250);
	
		Container container = getContentPane();
		container.setLayout(null); 
		
		// Buttons and radiobuttons
		addBtn = new JButton("Tilføj");
	    sortBtn = new JButton("Sortér");
		textField = new JTextField();
		rbFont = new Font("Courier", Font.PLAIN, 14);
		cRBtn = new JRadioButton("Courier");
		cRBtn.setFont(rbFont);
		rbFont = new Font("Arial", Font.PLAIN, 14);
		aRBtn = new JRadioButton("Arial");
		aRBtn.setFont(rbFont);
		rbFont = new Font("Serif", Font.PLAIN, 14);
		sRBtn = new JRadioButton("Serif");
		sRBtn.setFont(rbFont);
		
		// Groups the radiobuttons
		ButtonGroup rGroup = new ButtonGroup();
		rGroup.add(cRBtn);
		rGroup.add(aRBtn);
		rGroup.add(sRBtn);
		
		tArea = new JTextArea();
		
		textFieldLabel.setLabelFor(textField);
		
		
		// Sets up the list
		listFont = new Font("Courier", Font.PLAIN, 14);
		
		listModel = new DefaultListModel();
		list = new JList(listModel);
		list.setFont(listFont);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
		
		// Add and set bounds for all components
		container.add(textFieldLabel);
		textFieldLabel.setBounds(20,10,45,23);
		
		container.add(textField);
		textField.setBounds(65,10,110,23);
		
		container.add(addBtn);
		addBtn.setBounds(105,50,70,25);
		
		container.add(sortBtn);
		sortBtn.setBounds(105,80,70,25);
		
		container.add(cRBtn);
		cRBtn.setSelected(true);
		cRBtn.setBounds(20,45,155,25);
		
		container.add(aRBtn);
		aRBtn.setBounds(20,65,155,25);
		
		container.add(sRBtn);
		sRBtn.setBounds(20,85,155,25);

		listScrollPane = new JScrollPane(list);
		container.add(listScrollPane);
		listScrollPane.setBounds(200,10,155,100);
		
		JScrollPane tAreaScrollPane = new JScrollPane(tArea);
		container.add(tAreaScrollPane);
		tAreaScrollPane.setBounds(375,10,155,100);
		
		// Show the frame
	    setVisible(true);
	}
	
	private void buildAndAddListeners() {
		
		// FocusListener to keep focus on the textfield
		class textFieldFocusListener implements FocusListener {
			
			public void focusLost(FocusEvent e) {
				textField.requestFocusInWindow();
			}
			
			// Not used
			public void focusGained(FocusEvent e) {}
		}
		
		// Listener for selecting of the fontchooser radiobuttons
		// Changes the font of the JList
		class FontListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				listFont = new Font(e.getActionCommand(), Font.PLAIN, 14);
				list.setFont(listFont);
				list.repaint();
			}
		}
		
		// Listener for changes to the textfield
		// Sets the title of the JFrame to the last entered character
		class CListener implements DocumentListener {
			public void insertUpdate(DocumentEvent e) {
		        String t = textField.getText();
				if (t.length() > 0) {
					setTitle(""+t.charAt(t.length() - 1));
				}
			}
			
			// Not used.
		    public void changedUpdate(DocumentEvent e) {}
			public void removeUpdate(DocumentEvent e) {}
		}
		
		// Listener for the two buttons
		class ButtonListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				
				// Extracts the contens of the list, sorts it and fills the textarea with it.
				if (e.getSource() == sortBtn) {
					int size = listModel.getSize();
					if (size < 1) {
						return;
					}
										
					String[] sA = new String[size];
					for (int i = 0; i < size; i++) {
						sA[i] = listModel.getElementAt(i).toString();
					}
					
					// Bubble sort
					String temp;
					for(int i = 0; i < size - 1; i++)
			        {
			            for(int j = 0; j < size - 1; j++)
			            {
			                if(sA[j].compareTo(sA[j+1]) > 0)
			                {
			                    temp = sA[j];
			                    sA[j] = sA[j+1];
			                    sA[j+1] = temp;
			                }
			            }
			        }
					
					tArea.setText("");
					for (int i = 0; i < size; i++) {
						tArea.append(sA[i] + "\n");
					}
					return;
				}
				
				// Add the textare content to the list
				if (e.getSource() == addBtn || e.getSource() == textField) {
					String t = textField.getText();
					if (!t.equals("")) {
						int index = listModel.getSize();
						listModel.addElement(t);
						textField.setText("");
						
						list.setSelectedIndex(index);
			            list.ensureIndexIsVisible(index);
		            } else {
						Toolkit.getDefaultToolkit().beep();
		                return;
					}
				}
			}
		}
		
		// Add listeners
		addBtn.addActionListener(new ButtonListener());
		sortBtn.addActionListener(new ButtonListener());
		textField.addActionListener(new ButtonListener());
		textField.getDocument().addDocumentListener(new CListener());
		textField.addFocusListener(new textFieldFocusListener());
		cRBtn.addActionListener(new FontListener());
		aRBtn.addActionListener(new FontListener());
		sRBtn.addActionListener(new FontListener());
	}
}