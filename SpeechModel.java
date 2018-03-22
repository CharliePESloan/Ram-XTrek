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
	Language  language = null;
	int	  selected = 0;


	/* Constructor */
	public SpeechModel(MenuFrame XTrek)
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
	// Set language and read out which button was pressed
	public void pressedSelect()
	{
		String text;
		if (selected == 0)
		{
			language = null;
			text     = "Off";
			mainFrame.saySomething(text);
		} else
		{
			language = languages[selected-1];
			text     = language.getName();
			mainFrame.saySomething(text);
		}
		setChanged(); notifyObservers(language);
	}
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
		selected = 0;
		language = null;
		
		setChanged(); notifyObservers(selected);
		setChanged(); notifyObservers(language);
	}
}
