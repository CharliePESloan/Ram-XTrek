import java.util.Observable;

/* SpeechModeModel
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
	MenuFrame XTrek;
	Language  language;
	int	  selected = 0;

	
	/* Constructor */
	public SpeechModeModel(MenuFrame XTrek)
	{
		this.XTrek = XTrek;
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
		if (selected == 0)
		{
			language = languages[0];
			text     = "Off";
		} else
		{
			language = languages[selected-1];
			text     = language.getName();;
		}

		// Read out which button was pressed
		Speaker.saySomething(text,language);
	}
	public void pressedMenu()
	{
		XTrek.setMenu("Menu");
	}
	public void pressedOnOff()
	{
		XTrek.setMenu("OnOff");
	}
}
