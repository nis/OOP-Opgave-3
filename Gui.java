import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {
	public static void main (String args[]) {
		new Gui();
	}
	
	public Gui () {
		setTitle("OOP Opgave 3");
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setSize(500,250);
	    setVisible(true);
	
		Container container = getContentPane();
		container.setLayout(null); 
		
		//JPanel panel = new JPanel(new BorderLayout());
		JButton addBtn = new JButton("Tilføj");
	    JButton sortBtn = new JButton("Sortér");
		JTextField textField = new JTextField();
	
		container.add(addBtn);
		addBtn.setBounds(20,40,75,25);
		
		container.add(sortBtn);
		sortBtn.setBounds(100,40,75,25);
		
		container.add(textField);
		textField.setBounds(20,10,155,25);
		
		//addBtn.setSize(new Point(200,20));
	}
}