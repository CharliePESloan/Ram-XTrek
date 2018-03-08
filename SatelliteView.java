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
 * Menu View.
 *
 * Darya Shyroka 2018.
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
    
  public SatelliteView(Controller controller, SatelliteModel satelliteModel){
     this.controller = controller;
     this.satelliteModel =  satelliteModel;
      
     setLayout(null);
      setBackground(Color.white);
    setSize(width, height);
    setLocation(x_loc,y_loc);
      
//Set the position of the text, relative to the icon:
label1.setVerticalTextPosition(JLabel.BOTTOM);
label1.setHorizontalTextPosition(JLabel.CENTER);
  

  }
}