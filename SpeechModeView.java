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
 * Charlie Sloan (2018)
 *
 * A view representing the speech mode screen
 */
public class SpeechModeView extends JPanel implements Observer
{
	/* Static Variables */
	final static int NUM_BUTTONS = 6;
	final static int FONT_SIZE = 32;

	/* ModelViewController Objects */
	final Controller	controller;
	final SpeechModeModel	model;

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
	int selected = 0;

	/* Constructor */
	public SpeechModeView( Controller controller,
			       SpeechModeModel model )
	{
		/* Setup ModelViewController */
		this.controller = controller;
		this.model = model;
		model.addObserver(this);

		/* Setup JPanel */
		setLayout( new GridLayout(NUM_BUTTONS,1) );
		setBackground(Color.black);

		/* Setup buttons */
		buttons[0].select();
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

