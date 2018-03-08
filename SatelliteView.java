import java.awt.Color;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
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
import java.util.Observer;

/*
 * Satellite View.
 *
 * Clyde Udunna 2018.
 */
public class SatelliteView extends JPanel implements Observer {
    JFrame mainFrame;
    int width = 255;
    int height = 293;
    int x_loc = 20;
    int y_loc = 20;
    int lb_x = 0;
    int rb_x = 130;
    Controller controller;
    SatelliteModel satelliteModel;
	JLabel longitude = new JLabel("ahahha");
	JLabel latitude = new JLabel("hahah");
    
  public SatelliteView(Controller controller, SatelliteModel satelliteModel){
     this.controller = controller;
     this.satelliteModel =  satelliteModel;
      
     setLayout(null);
      setBackground(Color.white);
    setSize(width, height);
    //setLocation(x_loc,y_loc);  
    //setLocation(x_loc,y_loc);
	longitude.setBounds(120, 350, 200, 80);
	latitude.setBounds(120, 425, 200, 80);
	add(latitude);
	add(longitude);
 
  }
  
  public void update(Observable obs, Object obj){
	  String[] a = (String[]) obj;
	  longitude.setText(a[0] + " " + a[1]);
	  latitude.setText(a[2] + " " + a[3]);
  }
}