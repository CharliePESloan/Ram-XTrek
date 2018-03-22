import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class AboutView extends JPanel implements Observer {
	GridLayout aboutLayout = new GridLayout(1,0);
	JTextArea aboutArea = new JTextArea(4,20);
	Font font = new Font("Trebuchet MS",Font.PLAIN, 25);
	String newline = "\n";
	
	public AboutView (Controller controller, AboutModel model) {
		setLayout(aboutLayout);
		aboutArea.setEditable(false);
		aboutArea.setFont(font);
		aboutArea.append("\n\nXTrek version 7.01a\n(c)2018.\n\nCreated by,");
		aboutArea.append("\nCharlie, Clyde, Darya\nDevash and Nathan"); 
		add(aboutArea);
		
	}
	public void update(Observable o, Object arg) {
		
	}
}