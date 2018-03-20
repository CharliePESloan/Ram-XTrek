import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class TripComputerView extends JPanel implements Observer {
	
	GridLayout tripComp = new GridLayout(3,0);
	JLabel tripD = new JLabel("test one");
	JLabel tripS = new JLabel("test two");
	JLabel tripMT = new JLabel("test three");
	Border blackline = BorderFactory.createLineBorder(Color.black,5);
	Font font = new Font("Trebuchet MS",Font.PLAIN, 50);


	
	public TripComputerView (Controller controller, TripComputerModel model) {
		setLayout(tripComp);
		setBackground(Color.black);
		tripD.setBorder(blackline);
		tripS.setBorder(blackline);
		tripMT.setBorder(blackline);
		tripD.setOpaque(true);
		tripS.setOpaque(true);
		tripMT.setOpaque(true);
		tripD.setFont(font);
		tripS.setFont(font);
		tripMT.setFont(font);
		add(tripD);
		add(tripS);
		add(tripMT);
		
		
	}
	public void update(Observable o, Object arg) {
		
	}
}