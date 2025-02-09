import java.util.Observable;

/*
 * SpeechModel
 * Charlie Sloan (2018)
 *
 * Model for speech mode of XTrek; allows the user to select a language
 * and reads out their choice
 */
public class SpeechModel extends Observable implements Model
{
	/* Constants */
	final static int DEFAULT_BUTTON = 1;
	final static int NUM_BUTTONS = 5;
	final static Language[] languages = new Language[]
		{
			new Language("English","en"),
                        new Language("Francais","fr"),
                        new Language("Deutsch","de"),
                        new Language("Italiano","it"),
                        new Language("Espanol","es")
		};

	/* Declare variables */
	MenuFrame mainFrame;
	Language  language = languages[0];
	int	  selected = DEFAULT_BUTTON;


	/* Constructor */
	public SpeechModel(MenuFrame XTrek)
	{
		mainFrame = XTrek;
	}

	/* Move between languages */
	public void pressedPlus()
	{
		selected--;
		if (selected<0)
		{selected=NUM_BUTTONS;}

		setChanged(); notifyObservers(selected);
	}
	public void pressedMinus()
	{
		selected++;
		if (selected>NUM_BUTTONS)
		{selected=0;}

		setChanged(); notifyObservers(selected);
	}

	/* Set language and read out which button was pressed */
	public void pressedSelect()
	{
		String text;
		if (selected == 0)
		{
			language = null;
			text     = "Off";
		} else
		{
			language = languages[selected-1];
			text     = language.getName();
		}
		setChanged(); notifyObservers(language);
		mainFrame.saySomething(text);
	}

	/* Go to menu or onoff modes */
	public void pressedMenu()
	{
		mainFrame.setMenu(MenuEnum.MENU);
	}
	public void pressedOnOff()
	{
		reset();
		mainFrame.setMenu(MenuEnum.ONOFF);
	}

	public void reset()
	{
		// Reset language to default
		selected = DEFAULT_BUTTON;
		language = languages[0];
		
		// Notify other parts of the program
		setChanged(); notifyObservers(selected);
		setChanged(); notifyObservers(language);
	}
}
