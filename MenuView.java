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
public class MenuView extends JPanel /*implements Observer*/ {
    JFrame mainFrame;
    int width = 260;
    int height = 294;
    int x_loc = 90;
    int y_loc = 300;
    int lb_x = 0;
    int rb_x = 130;
    Controller controller;
    MenuModel menuModel;
    
  public MenuView(Controller menuController, MenuModel menuModel){
     menuController = menuController;
      menuModel =  menuModel;
      
     setLayout(null);
      setBackground(Color.black);
    setSize(width, height);
    setLocation(x_loc,y_loc);
  // Creating the icons for the menu buttons 
  ImageIcon whereToIcon = new ImageIcon("Images/WhereToButton.png");
    ImageIcon whereToIconSelected = new ImageIcon("Images/WhereToButtonSelected.png");
  ImageIcon mapIcon = new ImageIcon("Images/MapButton.png");
    ImageIcon mapIconSelected = new ImageIcon("Images/MapButtonSelected.png");
  ImageIcon satelliteIcon = new ImageIcon("Images/SatelliteButton.png");
    ImageIcon satelliteIconSelected = new ImageIcon("Images/SatelliteButtonSelected.png");
  ImageIcon tripCompIcon = new ImageIcon("Images/TripCompButton.png");
    ImageIcon tripCompIconSelected = new ImageIcon("Images/TripCompButtonSelected.png");
  ImageIcon speechIcon = new ImageIcon("Images/SpeechButton.png");
    ImageIcon speechIconSelected = new ImageIcon("Images/SpeechButtonSelected.png");
  ImageIcon aboutIcon = new ImageIcon("Images/InfoButton.png");
    ImageIcon aboutIconSelected = new ImageIcon("Images/InfoButtonSelected.png");
  ImageIcon plusIcon = new ImageIcon("Images/PlusButton.png");
    // Creating the menu buttons
  final CycleButton WhereToButton      = new CycleButton("WhereToButton", whereToIcon, whereToIconSelected);
  final CycleButton MapButton          = new CycleButton("MapButton", mapIcon, mapIconSelected);
  final CycleButton SatelliteButton    = new CycleButton("SatelliteButton", satelliteIcon, satelliteIconSelected);
  final CycleButton TripComputerButton = new CycleButton("TripCompButton", tripCompIcon, tripCompIconSelected);
  final CycleButton SpeechButton       = new CycleButton("SpeechButton", speechIcon, speechIconSelected);
  final CycleButton AboutButton        = new CycleButton("AboutButton", aboutIcon, aboutIconSelected);
      
      
  WhereToButton.setPrevNext(MapButton, TripComputerButton);
  MapButton.setPrevNext(SatelliteButton, WhereToButton);
  SatelliteButton.setPrevNext(AboutButton, MapButton);
  TripComputerButton.setPrevNext(WhereToButton, SpeechButton);
  SpeechButton.setPrevNext(TripComputerButton, AboutButton);
  AboutButton.setPrevNext(SpeechButton, SatelliteButton);
    
  /** Creating the side/navigation buttons    
  final SideButton    PlusButton    = new BlankXTrex.SideButton("PlusButton");
  final SideButton    MinusButton   = new BlankXTrex.SideButton("MinusButton");
  final SideButton    SelectButton  = new BlankXTrex.SideButton("SelectButton");
  final SideButton    MenuButton    = new BlankXTrex.SideButton("MenuButton"); **/ 
      
  // Placing the menu buttons   
  WhereToButton.setBounds(lb_x, 0, 125, 93); add(WhereToButton);
      
  MapButton.setBounds(lb_x,100,125,93); add(MapButton);
  MapButton.select();
      menuModel.setSelected(MapButton);
  SatelliteButton.setBounds(lb_x,200,125,93); add(SatelliteButton);

  TripComputerButton.setBounds(rb_x, 0, 125, 93); add(TripComputerButton);

  SpeechButton.setBounds(rb_x,100,125,93); add(SpeechButton);

  AboutButton.setBounds(rb_x,200,125,93); add(AboutButton);
  }
}