import java.awt.Color;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Font;
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
	final static int buttonWidth  = 255;
	final static int buttonHeight =  45;

	/* ModelViewController Objects */
	Controller	myController;
	SpeechModeModel	myModel;

	/* Create language buttons */
	final public CycleButton buttonOff = new CycleButton("Off",  "en-GB");
	final CycleButton buttonEnglish	= new CycleButton("English", "en-GB");
	final CycleButton buttonFrench	= new CycleButton("Francais","fr-FR");
	final CycleButton buttonGerman	= new CycleButton("Deutsch", "de-DE");
	final CycleButton buttonItalian	= new CycleButton("Italiano","it-IT");
	final CycleButton buttonSpanish	= new CycleButton("Espanol", "es-ES");

	/* update */
	public void update( Observable obs, Object obj )
	{

	}

	/* Constructor */
	public SpeechModeView( Controller controller, SpeechModeModel model )
	{
		/* Setup model */
		myController = controller;
		myModel = model;
		myModel.setSelected(buttonOff);

		// Use absolute positioning
		setLayout( null );
		// Set background colour
		setBackground(Color.black);

		/* Setup CycleButtons */
		buttonOff.	setPrevNext(buttonSpanish,	buttonEnglish);
		buttonEnglish.	setPrevNext(buttonOff,		buttonFrench);
		buttonFrench.	setPrevNext(buttonEnglish,	buttonGerman);
		buttonGerman.	setPrevNext(buttonFrench,	buttonItalian);
		buttonItalian.	setPrevNext(buttonGerman,	buttonSpanish);
		buttonSpanish.	setPrevNext(buttonItalian,	buttonOff);

		/* Set position and size of buttons and add to frame */
		buttonOff.setBounds		(0,  0,buttonWidth,buttonHeight);
		buttonEnglish.setBounds	(0, 50,buttonWidth,buttonHeight);
		buttonFrench.setBounds	(0,100,buttonWidth,buttonHeight);
		buttonGerman.setBounds	(0,150,buttonWidth,buttonHeight);
		buttonItalian.setBounds	(0,200,buttonWidth,buttonHeight);
		buttonSpanish.setBounds	(0,250,buttonWidth,buttonHeight);

		/* Set up fonts */
		Font myFont = new Font("Trebuchet MS",Font.PLAIN, 32);
		buttonOff.setFont		(myFont);
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
