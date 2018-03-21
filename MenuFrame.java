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
* Darya Shyroka, 2018.
* 
* The XTrek main frame, this is where the XTrek is run from. 
* 
* All images were modified using GIMP by Darya Shyroka. 
*/ 

public class MenuFrame extends JFrame {
    
    MyCardLayout cardlayout = new MyCardLayout();
    JPanel cards = new JPanel(cardlayout);
    ImageIcon plusIcon = new ImageIcon("Images/PlusButton.png");
    ImageIcon minusIcon = new ImageIcon("Images/MinusButton.png");
    ImageIcon selectIcon = new ImageIcon("Images/SelectButton.png");
    ImageIcon menuIcon = new ImageIcon("Images/MenuButton.png");
    ImageIcon onOffIcon = new ImageIcon("Images/OnOffButton.png");
    MenuEnum menuEnum;

    Win7Ublox7 win7u7 = new Win7Ublox7();
    Thread thread = new Thread(this.win7u7);

    OnOffModel onOffModel = new OnOffModel(this);
    MenuModel menuModel = new MenuModel(this);
    SpeechModel speechModel = new SpeechModel(this);
    WhereToFrameModel whereToModel = new WhereToFrameModel(this, speechModel);
    SatelliteModel satModel = new SatelliteModel(this);
	TripComputerModel tripModel = new TripComputerModel(this);
    MapModel mapModel = new MapModel(this, speechModel, satModel);
    
    Controller controller = new Controller(onOffModel);
    JPanel onOffView = new OnOffView(controller, onOffModel);
    JPanel menuView = new MenuView(controller, menuModel);
    JPanel speechView = new SpeechView(controller, speechModel);
    JPanel whereToView = new WhereToFrameView(controller, whereToModel);
	JPanel tripView = new TripComputerView(controller, tripModel);
    JPanel mapView = new MapView(controller, mapModel);
    JPanel satView = new SatelliteView(controller, satModel);
    
    JButton PlusButton = new JButton();
    JButton MinusButton = new JButton();
    JButton SelectButton = new JButton();
    JButton MenuButton = new JButton();
    JButton OnOffButton = new JButton();

    Navigator nav = new Navigator(speechModel,satModel,whereToModel);
     Language language = new Language("en");
    Speaker speaker = new Speaker("Speaker", language);
    
  /* Taken from http://www.java2s.com/Code/JavaAPI/javax.swing/JFramesetLocationintxinty.htm
  */

  
  public Toolkit tk = Toolkit.getDefaultToolkit();
  public Dimension screenSize = tk.getScreenSize();
  public int SCREENHEIGHT = screenSize.height;
  public int SCREENWIDTH = screenSize.width;
  // A separate class for menu buttons, that could be useful later 
     private class DisplayButton extends JButton{
         DisplayButton(){
             setBorder( null );
         }
     }
  public Win7Ublox7 getWin7Ublox7(){
      return this.win7u7;
  }
  

  // What is constructed when MenuFrame() is called
  public MenuFrame() {
    // The frame specifications
    setTitle( "XTrex" );
    setContentPane( new JLabel( new ImageIcon( "Images/XTrex Background x.png" ) ) );
    setLayout( null );
    setLocation((SCREENWIDTH / 3)+150, (SCREENHEIGHT / 4)-150);
    setSize( 450, 835 ); /* title bar! */
    setResizable( false );

PlusButton.setName("PlusButton");
MinusButton.setName("MinusButton");
SelectButton.setName("SelectButton");
MenuButton.setName("MenuButton");
OnOffButton.setName("OnOffButton");

       nav.setOrigin("Exeter");
      
      thread.start();
    
    // Placing the navigation buttons
    PlusButton.setBounds(7, 96, 32, 72);add(PlusButton);
      PlusButton.setIcon(plusIcon);
      PlusButton.setBorder(null);
    MinusButton.setBounds(4, 167, 32, 75);add(MinusButton);
      MinusButton.setIcon(minusIcon);
      MinusButton.setBorder(null);
    SelectButton.setBounds(8, 260, 25, 97); add(SelectButton);
      SelectButton.setIcon(selectIcon);
      SelectButton.setBorder(null);
    MenuButton.setBounds(408, 110, 30,84); add(MenuButton);
      MenuButton.setIcon(menuIcon);
      MenuButton.setBorder(null);
    OnOffButton.setBounds(270,185,75,75); add(OnOffButton);
      OnOffButton.setIcon(onOffIcon);
      OnOffButton.setBorder(null);
      
    add(cards);
    cards.setSize(255, 293);
    cards.setLocation(90,300);
    cards.add(onOffView, MenuEnum.ONOFF);
    cards.add(menuView, MenuEnum.MENU);
    cards.add(speechView, MenuEnum.SPEECH);
    cards.add(whereToView, MenuEnum.WHERETO);
	cards.add(tripView, MenuEnum.TRIP);
    cards.add(mapView, MenuEnum.MAPS);
    cards.add(satView, MenuEnum.SATELLITE);
    
    //this.menuEnum = ONOFF;
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
  public void setMenu(MenuEnum menu){
      //MenuEnum menu;
      switch(menu){
              case SPEECH:
                saySomething(this.speaker, "Speech");
                controller.setModel(speechModel);
              break;
              case MENU:
	        //nav.refreshDirections();
             // nav.printOut();
                controller.setModel(menuModel);
              break;
              case WHERETO:
                controller.setModel(whereToModel);
              break;
			  case TRIP:
                controller.setModel(tripModel);
              break;
              case MAPS:
                controller.setModel(mapModel);
              break;
              case ONOFF:
                controller.setModel(onOffModel);
              break;
              case SATELLITE:
              controller.setModel(satModel);
              break;
              default:
              break;
      }
      cardlayout.show(cards, menu);
      setVisible(true);
  }
  public void saySomething(Speaker speaker, String speech){
      speaker.saySomething(speech);
  }
  public static void main( String[] argv ) {    
    MenuFrame menuFrame = new MenuFrame();
      
  }
}
