import java.awt.Color;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Dimension; 
import java.awt.*;
import javax.swing.*;

/*Work Package 5*/

public class SatelliteView extends JPanel implements Observable{
	
    
    public SatelliteView(){
		/*This code here has been provided by my team mate in order for me to integrate my 
		 *package with the GUI to display the result of my functions.
		 *They set the up the XTrex look and take attributes and properties from the 
		 *BlankXTrex who is a parent to this class, for me to then add to the work*/
        
        
        //Making a satellite object
		Satellite mySat = new Satellite();
		// Using dummy values from a dongle reading
		String a[] = mySat.getGLL("$GPGLL,5043.94335,N,00330.91606,W,111950.00,A,A*74"); // dummy value
		
		//Inserting labels into our frame which are going to contain our position data
        JLabel longitude = new JLabel(a[0] + " " + a[1], SwingConstants.CENTER); 
		//The values used are those stored and returned from our array
		JLabel latitude = new JLabel(a[2] + " " + a[3], SwingConstants.CENTER );
		
		//Position our label in the page, put them next to each other
		longitude.setBounds(120, 350, 200, 80);
		latitude.setBounds(120, 425, 200, 80);
		
		//The following lines contain information about coloring for label personalisation
		//Will included them into my next sprint, commented out as I am not currently using them
		
		/*longitude.setOpaque(true);
		latitude.setOpaque(true);*/
		
		/*longitude.setBackground(Color.WHITE);
		longitude.setBackground(Color WHITE);*/
		
		add(longitude); //labels added to frame
		add(latitude);
        
		/*This button allows me to move from this page to the Main Menu,
		 *it does so by creating a new instance when clicked. The same procedure
		 *will be used to redirect the user from the main menu to any page*/
        MenuButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                dispose();
                new XTrex();
            }
        });

    }
        
        
        // from https://coderanch.com/t/476947/java/Adding-image-frame
        
    
  
	/*Making an instance of my public class in order to display on the screen
	 *for user interaction. A frame with its components is created*/
	public static void main(String[] args){
		JFrame frame = new SatelliteFrame();
		frame.setLocationRelativeTo(null);
		frame.setSize(450, 835);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}

class Satellite{

	/*Constructor which enables access to methods defined within this class*/
	public Satellite(){};
	
	/*This method return appends the GLL sentence to an array
	which allows us to store the part of the sentence for further purposes*/
	public String[] getPosition(String coordinate){
	    String[] components;
		if(coordinate.startsWith("$GPGLL")){
			String message = coordinate.substring(7); // We ignore the characters which aren't required 
			components = message.split(","); // remove all the commas to get the words
		    return components;
		}
		else{
		    return null;
		}
	}
	
	/*Splits the various GPS messages and stores them inside an array,
	 *this array is then scanned by using the getPosition method to get only
	 *the GLL sentences. At the moments it only returns the first value as we have been using
	 *dummy values due to issues with the dongle. Further sprints will contain an iteration through the array*/
	public String[] getGLL(String text){
		String [] lines = text.split("\n");
		return getPosition(lines[0]);
	}
}