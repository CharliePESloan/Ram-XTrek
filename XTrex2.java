import java.awt.Color;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;


public class XTrex2 extends JFrame/*extends BlankXTrex*/
{

	public Toolkit tk = Toolkit.getDefaultToolkit();
	public Dimension screenSize = tk.getScreenSize();
	final public int screenHeight = screenSize.height;
	final public int screenWidth  = screenSize.width;


	CardLayout cardLayout = new CardLayout();
	JPanel	   cardPanel  = new JPanel(cardLayout);


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


	/*ImageIcon plusButton = new ImageIcon("Images/SpeechButton.png");
	ImageIcon plusButtonSelected = new ImageIcon("Images/SpeechButtonSelected.png");
	ImageIcon plusButton = new ImageIcon("Images/SpeechButton.png");
	ImageIcon plusButtonSelected = new ImageIcon("Images/SpeechButtonSelected.png");
	ImageIcon selectButton =
		new ImageIcon("Images/SpeechButton.png");
	ImageIcon selectButtonSelected =
		new ImageIcon("Images/SpeechButtonSelected.png");
	ImageIcon menuButton =
		new ImageIcon("Images/SpeechButton.png");
	ImageIcon menuButtonSelected =
		new ImageIcon("Images/SpeechButtonSelected.png");*/

	public class SideButton extends JButton
	{
		ImageIcon normal;
		ImageIcon selected;

		SideButton(String s)
		{
			normal	 = new ImageIcon("Images/" + s + ".png");
			selected = new ImageIcon("Images/" + s + "Selected" + ".png");
			setIcon(normal);
			setBorder( null );
			setText(s);
			addMouseListener(new java.awt.event.MouseAdapter()
			{
				public void mouseEntered(java.awt.event.MouseEvent evt)
				{
					setIcon(selected);
				}
				public void mouseExited(java.awt.event.MouseEvent evt)
				{
					setIcon(normal);
				}
			});
		}
	}

  // Creating the menu buttons
  final DisplayButton WhereToButton      = new DisplayButton();
  final DisplayButton MapButton          = new DisplayButton();
  final DisplayButton SatelliteButton    = new DisplayButton();
  final DisplayButton TripComputerButton = new DisplayButton();
  final DisplayButton SpeechButton       = new DisplayButton();
  final DisplayButton AboutButton        = new DisplayButton();

  // Creating the side/navigation buttons
  final SideButton    PlusButton    = new SideButton("PlusButton");
  final SideButton    MinusButton   = new SideButton("MinusButton");
  final SideButton    SelectButton  = new SideButton("SelectButton");
  final SideButton    MenuButton    = new SideButton("MenuButton");

  final SpeechModeModel	speechModel	= new SpeechModeModel(this);
  final Controller	controller 	= new Controller(speechModel); //Should be menu model at first */
  final JPanel		menuView	= new JPanel();
  final SpeechModeView	speechView	= new SpeechModeView(controller, speechModel);

  // A separate class for menu buttons, that could be useful later
     private class DisplayButton extends JButton{
         DisplayButton(){
             setBorder( null );
         }
     }

	public void setMenu(String menu)
	{
		// Switches CardLayout to show the specified menu
		cardLayout.show(cardPanel,menu);
	}

  // What is constructed when XTrex() is called
  public XTrex2() {
	// The frame specifications
	setTitle( "XTrex" );
	setContentPane( new JLabel( new ImageIcon( "Images/XTrex Background.png" ) ) );
	setLayout( null );
	setLocation((screenWidth / 3)+150, (screenHeight / 4)-150);
	setResizable( false );

	menuView.setBackground(Color.red);
	
        WindowAdapter adapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("Closing");
                System.exit(0);
            }
        };
        addWindowListener(adapter);

	// Placing the navigation buttons
	PlusButton.setBounds(9, 102, 30, 68);
	MinusButton.setBounds(11, 175, 27, 64);
	SelectButton.setBounds(5, 260, 34, 97);
	MenuButton.setBounds(409, 113, 30,84);

	MenuButton.addMouseListener(controller);
	PlusButton.addMouseListener(controller);
	MinusButton.addMouseListener(controller);
	SelectButton.addMouseListener(controller);


	//speechView.setBounds(0,0,250, 300);
	menuView.setSize(250, 300);
	speechView.setSize(250, 300);
	cardPanel.setBounds(95,305,250, 300);

	add(PlusButton);
	add(MinusButton);
	add(SelectButton);
	add(MenuButton);
	
	cardPanel.add(menuView,"Menu");
	cardPanel.add(speechView,"Speech");
	add(cardPanel);

	cardLayout.show(cardPanel,"Speech");
	//pack();

	setSize( 450, 835 );
	setVisible( true );

    /*
     * Adding functionality to the select navigation button
     * The prints statements are there to ensure that the code is working,
     * even if the new frame is not yet created (temporary).
     *
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
                smv.showMe();
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
    });*

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
    });*/
  }

  public static void main( String[] argv ) {

    // Taken from http://www.java2s.com/Code/JavaAPI/javax.swing/JFramesetLocationintxinty.htm
    /*Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;*/

    JFrame XTrek = new XTrex2();
    //menuFrame.setLocation((screenWidth / 3)+150, (screenHeight / 4)-150);
    //menuFrame.setSize( 450, 835 ); /* title bar! */
    //menuFrame.setResizable( false );
    //menuFrame.setVisible( true );
  }
}
