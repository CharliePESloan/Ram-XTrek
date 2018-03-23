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

/* SpeechView
 * Charlie Sloan (2018)
 *
 * A view representing the speech mode screen
 */
public class SpeechView extends JPanel implements Observer
{
	/* Static Variables */
	//final static int NUM_BUTTONS = 6;
	final static int FONT_SIZE = 32;
	final static int DEFAULT_BUTTON = 1;

	/* ModelViewController Objects */
	final Controller	controller;
	final SpeechModel	model;

	/* Create language buttons */
	final CycleButton[] buttons = new CycleButton[]
		{
			new CycleButton("Off"),
			new CycleButton("English"),
			new CycleButton("Francais"),
			new CycleButton("Deutsch"),
			new CycleButton("Italiano"),
			new CycleButton("Espanol")
		};
	int selected = DEFAULT_BUTTON;

	/* Constructor */
	public SpeechView( Controller controller,
			       SpeechModel model )
	{
		/* Setup ModelViewController */
		this.controller = controller;
		this.model = model;
		model.addObserver(this);

		/* Setup JPanel
		 * A zero in either width or height of a gridlayout
		 * means any number of objects can be placed in it
		 */
		setLayout( new GridLayout(0,1) );
		setBackground(Color.black);

		/* Setup buttons */
		buttons[DEFAULT_BUTTON].select();
		Font myFont = new Font("Trebuchet MS",Font.PLAIN, FONT_SIZE);
		for (CycleButton cb : buttons)
		{
			cb.setFont(myFont);
			add(cb);
		}
	}

	/* Select appropriate button */
	public void update( Observable obs, Object obj )
	{
		if (obj instanceof Integer)
		{
			buttons[selected].deselect();
			selected = (int)obj;
			buttons[selected].select();
		}
	}
}

