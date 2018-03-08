import java.util.Observable;

/*
 * SpeechModeModel
 * Charlie Sloan (2018)
 *
 * Model for speech mode of XTrek; allows the user to select a language
 * and reads out their choice
 */
public class SpeechModeModel extends Observable implements Model
{
	/* Constants */
	final int NUM_BUTTONS = 5;
	final Language[] languages = new Language[]
		{
			new Language("English","en"),
                        new Language("Francais","fr"),
                        new Language("Deutsch","de"),
                        new Language("Italiano","it"),
                        new Language("Espanol","es")

		};

	/* Declare variables */
	MenuFrame mainFrame;
	Language  language;
	int	  selected = 0;

	
	/* Constructor */
	public SpeechModeModel(MenuFrame XTrek)
	{
		mainFrame = XTrek;
	}

	/* Side buttons */
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
	public void pressedSelect()
	{
		/* Find selected language code and name of button */
		String text;
		// Set language and read out which button was pressed
		if (selected == 0)
		{
			//language = languages[0];
			language = null;
			text     = "Off";
			Speaker.saySomething(text,languages[0]);
		} else
		{
			language = languages[selected-1];
			text     = language.getName();;
			Speaker.saySomething(text,language);
		}
	}
	public void pressedMenu()
	{
		mainFrame.setMenu("Menu");
	}
	public void pressedOnOff()
	{
		mainFrame.setMenu("OnOff");
	}

	public void reset()
	{
		selected = 0;
		language = null;
	}
}
