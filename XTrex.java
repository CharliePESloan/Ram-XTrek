import java.awt.Color;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;


public class XTrex extends BlankXTrex {
  private static int    d  = 0;
  private static int    e  = 0;
  private static String op = "?";

  /* Taken from http://www.java2s.com/Code/JavaAPI/javax.swing/JFramesetLocationintxinty.htm
  */

  public Toolkit tk = Toolkit.getDefaultToolkit();
  public Dimension screenSize = tk.getScreenSize();
  public int screenHeight = screenSize.height;
  public int screenWidth = screenSize.width;


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


  // Creating the menu buttons
  final DisplayButton WhereToButton      = new DisplayButton();
  final DisplayButton MapButton          = new DisplayButton();
  final DisplayButton SatelliteButton    = new DisplayButton();
  final DisplayButton TripComputerButton = new DisplayButton();
  final DisplayButton SpeechButton       = new DisplayButton();
  final DisplayButton AboutButton        = new DisplayButton();

  // Creating the side/navigation buttons
  final SideButton    PlusButton    = new BlankXTrex.SideButton("PlusButton");
  final SideButton    MinusButton   = new BlankXTrex.SideButton("MinusButton");
  final SideButton    SelectButton  = new BlankXTrex.SideButton("SelectButton");
  final SideButton    MenuButton    = new BlankXTrex.SideButton("MenuButton");

  // A separate class for menu buttons, that could be useful later
     private class DisplayButton extends JButton{
         DisplayButton(){
             setBorder( null );
         }
     }

  // What is constructed when XTrex() is called
  public XTrex() {
    // The frame specifications
    setTitle( "XTrex" );
    setContentPane( new JLabel( new ImageIcon( "Images/XTrex Background.png" ) ) );
    setLayout( null );
    setLocation((screenWidth / 3)+150, (screenHeight / 4)-150);
    setSize( 450, 835 ); /* title bar! */
    setResizable( false );
    setVisible( true );

    // Placing the menu buttons
    WhereToButton.setBounds(95, 312, 125, 93); add(WhereToButton);
      WhereToButton.setIcon(whereToIcon);
    MapButton.setBounds(95,415,125,93);add(MapButton);
      MapButton.setIcon(mapIconSelected);
    SatelliteButton.setBounds(95,518,125,93); add(SatelliteButton);
      SatelliteButton.setIcon(satelliteIcon);
    TripComputerButton.setBounds(226, 312, 125, 93); add(TripComputerButton);
      TripComputerButton.setIcon(tripCompIcon);
    SpeechButton.setBounds(226,415,125,93); add(SpeechButton);
      SpeechButton.setIcon(speechIcon);
    AboutButton.setBounds(226,518,125,93); add(AboutButton);
      AboutButton.setIcon(aboutIcon);

    // Placing the navigation buttons
    PlusButton.setBounds(9, 102, 30, 68);add(PlusButton);
    MinusButton.setBounds(11, 175, 27, 64);add(MinusButton);
    SelectButton.setBounds(5, 260, 34, 97); add(SelectButton);
    MenuButton.setBounds(409, 113, 30,84); add(MenuButton);

    /**
    Adding functionality to the select navigation button
    The prints statements are there to ensure that the code is working,
    even if the new frame is not yet created (temporary).
    */
    SelectButton.addMouseListener(new java.awt.event.MouseAdapter(){
        public void mouseClicked(java.awt.event.MouseEvent evt){
            if(MapButton.getIcon() == mapIconSelected){
                System.out.println("Redirecting to Map page");
                dispose();
                new MapFrame();
            } else if(WhereToButton.getIcon() == whereToIconSelected){
                System.out.println("Redirecting to Where To page");
                dispose();
                new WhereToFrame();
            } else if(TripComputerButton.getIcon() == tripCompIconSelected){
                System.out.println("Redirecting to Trip Computer page");
            } else if(SpeechButton.getIcon() == speechIconSelected){
                System.out.println("Redirecting to Speech page");
                //dispose();
                //new SpeechModeFrame();
            } else if(AboutButton.getIcon() == aboutIconSelected){
                System.out.println("Redirecting to About page");
            } else if (SatelliteButton.getIcon() == satelliteIconSelected){
                System.out.println("Redirecting to Satellite page");
            }
        }
    });

    // Adding functionality to the plus navigation button
    PlusButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if(MapButton.getIcon() == mapIconSelected){
                MapButton.setIcon(mapIcon);
                WhereToButton.setIcon(whereToIconSelected);
            } else if(WhereToButton.getIcon() == whereToIconSelected){
                WhereToButton.setIcon(whereToIcon);
                TripComputerButton.setIcon(tripCompIconSelected);
            } else if(TripComputerButton.getIcon() == tripCompIconSelected){
                TripComputerButton.setIcon(tripCompIcon);
                SpeechButton.setIcon(speechIconSelected);
            } else if(SpeechButton.getIcon() == speechIconSelected){
                SpeechButton.setIcon(speechIcon);
                AboutButton.setIcon(aboutIconSelected);
            } else if(AboutButton.getIcon() == aboutIconSelected){
                AboutButton.setIcon(aboutIcon);
                SatelliteButton.setIcon(satelliteIconSelected);
            } else if (SatelliteButton.getIcon() == satelliteIconSelected){
                SatelliteButton.setIcon(satelliteIcon);
                MapButton.setIcon(mapIconSelected);
            }
        }
    });

    // Adding functionality to the minus navigation button
    MinusButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if(WhereToButton.getIcon() == whereToIconSelected){
                WhereToButton.setIcon(whereToIcon);
                MapButton.setIcon(mapIconSelected);
            } else if(MapButton.getIcon() == mapIconSelected){
                MapButton.setIcon(mapIcon);
                SatelliteButton.setIcon(satelliteIconSelected);
            } else if(SatelliteButton.getIcon() == satelliteIconSelected){
                SatelliteButton.setIcon(satelliteIcon);
                AboutButton.setIcon(aboutIconSelected);
            } else if(AboutButton.getIcon() == aboutIconSelected){
                AboutButton.setIcon(aboutIcon);
                SpeechButton.setIcon(speechIconSelected);
            } else if(SpeechButton.getIcon() == speechIconSelected){
                SpeechButton.setIcon(speechIcon);
                TripComputerButton.setIcon(tripCompIconSelected);
            } else if(TripComputerButton.getIcon() == tripCompIconSelected){
                TripComputerButton.setIcon(tripCompIcon);
                WhereToButton.setIcon(whereToIconSelected);
            }
        }
    });
  }

  public static void main( String[] argv ) {

    // Taken from http://www.java2s.com/Code/JavaAPI/javax.swing/JFramesetLocationintxinty.htm
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    JFrame menuFrame = new XTrex();
    menuFrame.setLocation((screenWidth / 3)+150, (screenHeight / 4)-150);
    menuFrame.setSize( 450, 835 ); /* title bar! */
    menuFrame.setResizable( false );
    menuFrame.setVisible( true );
  }
}
