import java.awt.Color;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.util.Observable;
import java.util.Observer;

/* SpeechModeView
 * A view representing the speech mode screen
 */
public class SpeechModeView extends JPanel implements Observer
{
	/* Static Variables */
	final static int BUTTON_WIDTH  = 255;
	final static int BUTTON_HEIGHT =  45;
	final static int NUM_BUTTONS = 6;

	/* ModelViewController Objects */
	Controller	myController;
	SpeechModeModel	myModel;

	/* Create language buttons */
	final CycleButton[] buttons = new CycleButton[]
		{
			new CycleButton("Off",langEnglish),
			new CycleButton("English", langEnglish),
                	new CycleButton("Francais",langFrench),
                	new CycleButton("Deutsch", langGerman),
                	new CycleButton("Italiano",langItalian),
                	new CycleButton("Espanol", langSpanish)
		};

	/*final public CycleButton buttonOff = new CycleButton("Off",  langEnglish);
	final CycleButton buttonEnglish	= new CycleButton("English", langEnglish);
	final CycleButton buttonFrench	= new CycleButton("Francais",langFrench);
	final CycleButton buttonGerman	= new CycleButton("Deutsch", langGerman);
	final CycleButton buttonItalian	= new CycleButton("Italiano",langItalian);
	final CycleButton buttonSpanish	= new CycleButton("Espanol", langSpanish);
*/
	
/*	final Language[] languages = new Language[]
		{
			new Language("English","en"),
			new Language("French","fr"),
			new Language("German","de"),
			new Language("Italian","it"),
			new Language("Spanish","es")
		};*/

	


	/* update */
	public void update( Observable obs, Object obj )
	{
		int selectedButton = (int)obj;
		buttons[selectedButton].select();
	}

	/* Constructor */
	public SpeechModeView( Controller controller, SpeechModeModel model )
	{
		/* Setup model */
		this.controller = controller;
		this.model = model;
		model.addObserver(this);
		//myModel.setSelected(buttonOff);

		// Use GridLayout
		setLayout( new GridLayout(NUM_BUTTONS,1) );
		// Set background colour
		setBackground(Color.black);

		/* Setup CycleButtons *
		buttonOff.	setPrevNext(buttonSpanish,	buttonEnglish);
		buttonEnglish.	setPrevNext(buttonOff,		buttonFrench);
		buttonFrench.	setPrevNext(buttonEnglish,	buttonGerman);
		buttonGerman.	setPrevNext(buttonFrench,	buttonItalian);
		buttonItalian.	setPrevNext(buttonGerman,	buttonSpanish);
		buttonSpanish.	setPrevNext(buttonItalian,	buttonOff);
*/

		/* Set position and size of buttons and add to frame *
		buttonOff.setBounds		(0,  0,buttonWidth,buttonHeight);
		buttonEnglish.setBounds	(0, 50,buttonWidth,buttonHeight);
		buttonFrench.setBounds	(0,100,buttonWidth,buttonHeight);
		buttonGerman.setBounds	(0,150,buttonWidth,buttonHeight);
		buttonItalian.setBounds	(0,200,buttonWidth,buttonHeight);
		buttonSpanish.setBounds	(0,250,buttonWidth,buttonHeight);*/

		/* Set up fonts */
		Font myFont = new Font("Trebuchet MS",Font.PLAIN, 32);
		buttonOff.setFont	(myFont);
		buttonEnglish.setFont	(myFont);
		buttonFrench.setFont	(myFont);
		buttonGerman.setFont	(myFont);
		buttonItalian.setFont	(myFont);
		buttonSpanish.setFont	(myFont);

		/* Add the buttons to the panel */
		add(buttonOff);
		add(buttonEnglish);
		add(buttonFrench);
		add(buttonGerman);
		add(buttonItalian);
		add(buttonSpanish);
	}
}
