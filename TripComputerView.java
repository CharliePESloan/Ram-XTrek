import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author Nathan Painter
 *
 * 
 */

public class TripComputerView extends JPanel implements Observer {
	final TripComputerModel model;
	GridLayout tripComp = new GridLayout(3,0); //sets layout for trip computer
	JLabel tripD = new JLabel(); //distance
	JLabel tripS = new JLabel(); //speed
	JLabel tripMT = new JLabel(); //time
	Border blackline = BorderFactory.createLineBorder(Color.black,5);
	Font font = new Font("Trebuchet MS",Font.PLAIN, 20);
	
	
	public TripComputerView (Controller controller, MenuFrame XTrek, TripComputerModel model , Timer time) {
		this.model = model;
		time.addObserver(this);
		model.addObserver(this);
		XTrek.getWin7Ublox7().addObserver(this);
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
		if (arg instanceof Long) {
		long timeValue = (long) arg;
		long minutes = (long) (timeValue/60000);
		long seconds = (long) (timeValue/1000);
		seconds -= minutes;
		//Coordinate coord[] = (Coordinate) arg;
		//tripD.setText("Trip odem" + Double.toString(coord.get) + "KM";
		//tripS.setText("Speed" + Double.toString(coord.get);
		tripMT.setText(String.format("Moving time %dm %ds",minutes,seconds));
		}
	}
}