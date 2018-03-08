import java.awt.Color;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.util.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.Dimension;

/*
 * Satellite View.
 *
 * Clyde Udunna 2018.
 */
public class SatelliteView extends JPanel /*implements Observer*/ {
    JFrame mainFrame;
    int width = 255;
    int height = 293;
    int x_loc = 90;
    int y_loc = 300;
    int lb_x = 0;
    int rb_x = 130;
    Controller controller;
    SatelliteModel satelliteModel;
	JLabel longitude = new JLabel();
	JLabel latitude = new JLabel();
    
  public SatelliteView(Controller controller, SatelliteModel satelliteModel){
     this.controller = controller;
     this.satelliteModel =  satelliteModel;
      
     setLayout(null);
      setBackground(Color.white);
    setSize(width, height);
<<<<<<< HEAD
    setLocation(x_loc,y_loc);  
=======
    setLocation(x_loc,y_loc);
  

>>>>>>> b97ecc066a17c80e7b4ad4cecb3919e235f5619a
  }
}