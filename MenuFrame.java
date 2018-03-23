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
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
/*
* Darya Shyroka, 2018.
* 
* The XTrek main frame, this is where the XTrek is run from. 
* 
* All images were modified using GIMP by Darya Shyroka. 
*/ 

public class MenuFrame extends JFrame {
    
    /* Setting the cardlayout for the different modes of the XTrek */
    MyCardLayout cardlayout = new MyCardLayout();
    
    /* Adding the cardlayout to a JPanel */
    JPanel cards = new JPanel(cardlayout);
    
    /* ImageIcons for the utility/navigation buttons */
    ImageIcon plusIcon = new ImageIcon("Images/PlusButton.png");
    ImageIcon minusIcon = new ImageIcon("Images/MinusButton.png");
    ImageIcon selectIcon = new ImageIcon("Images/SelectButton.png");
    ImageIcon menuIcon = new ImageIcon("Images/MenuButton.png");
    ImageIcon onOffIcon = new ImageIcon("Images/OnOffButton.png");
    
    /* Initializing the utility/navigation buttons */
    JButton PlusButton = new JButton();
    JButton MinusButton = new JButton();
    JButton SelectButton = new JButton();
    JButton MenuButton = new JButton();
    JButton OnOffButton = new JButton();
    
    /* The enumeration we use to encode state */
    MenuEnum menuEnum;

    /* This is required to connect to the dongle */
    Win7Ublox7 win7u7 = new Win7Ublox7();
    Thread thread = new Thread(this.win7u7);
    
    public Win7Ublox7 getWin7Ublox7(){
        return this.win7u7;
    }

    /* Creating the models for all of the modes of the XTrek simulation */
    OnOffModel onOffModel = new OnOffModel(this);
    MenuModel menuModel = new MenuModel(this);
    SpeechModel speechModel = new SpeechModel(this);
    WhereToFrameModel whereToModel = new WhereToFrameModel(this, speechModel);
    SatelliteModel satModel = new SatelliteModel(this);
	TripComputerModel tripModel = new TripComputerModel(this);
	AboutModel aboutModel = new AboutModel(this);
    MapModel mapModel = new MapModel(this, speechModel, satModel);

    Timer time = new Timer();
    /* Setting the controller for the entire simulation */
    Controller controller = new Controller(onOffModel);
    
    /* Creating the views for all of the modes of the XTrel simulation */
    JPanel onOffView = new OnOffView(controller, onOffModel);
    JPanel menuView = new MenuView(controller, menuModel);
    JPanel speechView = new SpeechView(controller, speechModel);
    JPanel whereToView = new WhereToFrameView(controller, whereToModel);
	JPanel tripView = new TripComputerView(controller,this, tripModel, time);
    JPanel mapView = new MapView(controller, mapModel);
    JPanel satView = new SatelliteView(controller, satModel);
    JPanel aboutView = new AboutView(controller, aboutModel);

    /* Creating the navigation for the simulation */
    Navigator nav = new Navigator(this,speechModel,win7u7,whereToModel);

    /* Creating the speaker for the simulation */
    Speaker speaker = new Speaker(null, speechModel);

    Language language = new Language("en");
    Speaker speaker = new Speaker(language, speechModel);
    String currentView = "OnOff";
    
    /* Toolkit taken from http://www.java2s.com/Code/JavaAPI/javax.swing/JFramesetLocationintxinty.htm */
    /* Used to get the screensize */

    public Toolkit tk = Toolkit.getDefaultToolkit();
    public Dimension screenSize = tk.getScreenSize();
    public int SCREENHEIGHT = screenSize.height;
    public int SCREENWIDTH = screenSize.width;

  /* Constructor method, which is called from the main method when MenuFrame is run. */
    public MenuFrame() {
        /* Setting the specifications of the frame */
        setTitle( "XTrex" );
        setContentPane( new JLabel( new ImageIcon( "Images/XTrex Background x.png" ) ) );
        setLayout( null );
        setLocation((SCREENWIDTH / 3)+150, (SCREENHEIGHT / 4)-150);
        setSize( 450, 835 ); /* title bar! */
        setResizable( false );
        /* Make sure that the program exits when we close it */
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /* Setting the names for the buttons, so that they can be accessed by other functions easily */
        PlusButton.setName("PlusButton");
        MinusButton.setName("MinusButton");
        SelectButton.setName("SelectButton");
        MenuButton.setName("MenuButton");
        OnOffButton.setName("OnOffButton");

        /* We set the start/default coordinates */
        nav.setOrigin("50.722845","-3.5250755");
        /* We start the thread for the dongle */
        thread.start();
 
	    ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(time);
		executor.shutdown();
    
	
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
	cards.add(aboutView, MenuEnum.ABOUT);
    
        /* Placing the navigation buttons and setting the icons */
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
      
        /* Adding the cardlayout to the main frame and specifying its size and location */
        add(cards);
        cards.setSize(255, 293);
        cards.setLocation(90,300);
        
        /* Adding all of the different modes' views as cards into the card layout */
        cards.add(onOffView, MenuEnum.ONOFF);
        cards.add(menuView, MenuEnum.MENU);
        cards.add(speechView, MenuEnum.SPEECH);
        cards.add(whereToView, MenuEnum.WHERETO);
	    cards.add(tripView, MenuEnum.TRIP);
        cards.add(mapView, MenuEnum.MAPS);
        cards.add(satView, MenuEnum.SATELLITE);
        cards.add(aboutView, MenuEnum.ABOUT);
    /*
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int SCREENHEIGHT = screenSize.height;
        int SCREENWIDTH = screenSize.width;*/

      
        PlusButton.addMouseListener(controller);
        MinusButton.addMouseListener(controller);
        SelectButton.addMouseListener(controller);
        MenuButton.addMouseListener(controller);
        OnOffButton.addMouseListener(controller);
  }
  public void setMenu(MenuEnum menu){
      switch(menu){
              case SPEECH:
                saySomething("Speech");
                controller.setModel(speechModel);
              break;
              case MENU:
				nav.refreshDirections();
                //nav.printOut();
                if(controller.getModel() == onOffModel){
                saySomething("Turning On");}
                else if (controller.getModel() != whereToModel) {
                saySomething("Back to main menu");}
                controller.setModel(menuModel);
              break;
              case WHERETO:
                saySomething("Where to?");
                controller.setModel(whereToModel);
              break;
			  case TRIP:
                saySomething("Trip Computer");
                controller.setModel(tripModel);
              break;
              case MAPS:
                saySomething("Maps");
                controller.setModel(mapModel);
              break;
			  case ABOUT:
                saySomething("About");
                controller.setModel(aboutModel);
              break;
              case ONOFF:
                if(controller.getModel() == menuModel){
                //System.out.println("We are in menu model and we clicked OnOff");
                saySomething("Turning off");}
                controller.setModel(onOffModel);
              break;
              case SATELLITE:
                saySomething("Satellite");
                controller.setModel(satModel);
              break;
              default:
              break;
      }
      cardlayout.show(cards, menu);
      setVisible(true);
  }
  public void saySomething(String speech){
      this.speaker.saySomething(speech);
  }
  public static void main( String[] argv ) {    
    MenuFrame menuFrame = new MenuFrame();
      
  }
}
