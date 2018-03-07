import java.awt.Color;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
* This class should basically just have a main method that implements Model, View and Controller. 
*/ 

public class MenuFrame extends JFrame{
    CardLayout cardlayout = new CardLayout();
    JPanel cards = new JPanel(cardlayout);
        ImageIcon plusIcon = new ImageIcon("Images/PlusButton.png");
    ImageIcon minusIcon = new ImageIcon("Images/MinusButton.png");
    ImageIcon selectIcon = new ImageIcon("Images/SelectButton.png");
    ImageIcon menuIcon = new ImageIcon("Images/MenuButton.png");
    ImageIcon onOffIcon = new ImageIcon("Images/OnOffButton.png");


    
    MenuModel menuModel = new MenuModel(this);
    SpeechModeModel speechModel = new SpeechModeModel(this);
    WhereToFrameModel whereToModel = new WhereToFrameModel(this);
    MapModel mapModel = new MapModel(this);
    OnOffModel onOffModel = new OnOffModel(this);
   // MenuController	menuController = new MenuController(menuModel);
    Controller controller = new Controller(menuModel);
    JPanel menuView = new MenuView(controller, menuModel);
    JPanel speechView = new SpeechModeView(controller, speechModel);
    JPanel whereToView = new WhereToFrameView(controller, whereToModel);
    JPanel mapView = new MapView(controller, mapModel);
    JPanel onOffView = new OnOffView(controller, onOffModel);
    
    JButton PlusButton = new JButton();
    JButton MinusButton = new JButton();
    JButton SelectButton = new JButton();
    JButton MenuButton = new JButton();
    JButton OnOffButton = new JButton();
  /* Taken from http://www.java2s.com/Code/JavaAPI/javax.swing/JFramesetLocationintxinty.htm
  */

  
  public Toolkit tk = Toolkit.getDefaultToolkit();
  public Dimension screenSize = tk.getScreenSize();
  public int screenHeight = screenSize.height;
  public int screenWidth = screenSize.width;
  // A separate class for menu buttons, that could be useful later 
     private class DisplayButton extends JButton{
         DisplayButton(){
             setBorder( null );
         }
     }
    
  

  // What is constructed when MenuFrame() is called
  public MenuFrame() {
    // The frame specifications
    setTitle( "XTrex" );
    setContentPane( new JLabel( new ImageIcon( "Images/XTrex Background x.png" ) ) );
    setLayout( null );
    setLocation((screenWidth / 3)+150, (screenHeight / 4)-150);
    setSize( 450, 835 ); /* title bar! */
    setResizable( false );
    setVisible( true );

PlusButton.setName("PlusButton");
MinusButton.setName("MinusButton");
SelectButton.setName("SelectButton");
MenuButton.setName("MenuButton");
OnOffButton.setName("OnOffButton");
      
    
    // Placing the navigation buttons
    PlusButton.setBounds(9, 102, 29, 68);add(PlusButton);
      PlusButton.setIcon(plusIcon);
      PlusButton.setBorder(null);
    MinusButton.setBounds(11, 175, 26, 64);add(MinusButton);
      MinusButton.setIcon(minusIcon);
      MinusButton.setBorder(null);
    SelectButton.setBounds(5, 260, 25, 97); add(SelectButton);
      SelectButton.setIcon(selectIcon);
      SelectButton.setBorder(null);
    MenuButton.setBounds(408, 110, 30,84); add(MenuButton);
      MenuButton.setIcon(menuIcon);
      MenuButton.setBorder(null);
    OnOffButton.setBounds(280,185,75,75); add(OnOffButton);
      OnOffButton.setIcon(onOffIcon);
      OnOffButton.setBorder(null);
      
    add(cards);
    cards.setSize(255, 293);
    cards.setLocation(90,300);
    cards.add(menuView, "Menu");
    cards.add(speechView, "Speech");
    cards.add(whereToView, "WhereTo");
    cards.add(mapView, "Map");
    cards.add(onOffView, "OnOff");
    //menuFrame.add(menuView);
    //JPanel menuView = new MenuView(menuFrame);
    
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLocation((screenWidth / 3)+150, (screenHeight / 4)-150);
    setSize( 450, 835 ); /* title bar! */
    setResizable( false );
    setVisible( true );
      
    PlusButton.addMouseListener(controller);
    MinusButton.addMouseListener(controller);
    SelectButton.addMouseListener(controller);
    MenuButton.addMouseListener(controller);
    OnOffButton.addMouseListener(controller);
  }
  public void setMenu(String menu){
      switch(menu){
              case "Speech":
                controller.setModel(speechModel);
              break;
              case "Menu":
                controller.setModel(menuModel);
              break;
              case "WhereTo":
                controller.setModel(whereToModel);
              break;
              case "Map":
                controller.setModel(mapModel);
              break;
              case "OnOff":
                controller.setModel(onOffModel);
              break;
      }
      cardlayout.show(cards, menu);
  }
  public static void main( String[] argv ) {
      
    MenuFrame menuFrame = new MenuFrame();
      
  }
}
