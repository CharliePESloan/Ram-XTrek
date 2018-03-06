import java.util.Observable;

public class SpeechModeModel extends Observable implements Model
{
	/* Constants */
	final int NUM_BUTTONS = 5;

	/* Initialise variables */
	//CycleButton selected = null;
	int selected = 0;
	MenuFrame	XTrek;
	Language	language;
	String		artist;

	Language[] languages = new Language[]
		{
			new Language("English","en"),
                        new Language("Francais","fr"),
                        new Language("Deutsch","de"),
                        new Language("Italiano","it"),
                        new Language("Espanol","es")

		};

	public SpeechModeModel(MenuFrame XTrek)
	{
		this.XTrek = XTrek;
	}
	/*public void setSelected(CycleButton newSelected)
	{
		selected = newSelected;
		selected.select();
	}*/

	/* Side buttons */
	public void pressedPlus()
	{
		selected--;
		if (selected<0)
		{selected=NUM_BUTTONS;}
		setChanged(); notifyObservers(selected);
		//setSelected(selected.prev());
	}
	public void pressedMinus()
	{
		selected++;
		if (selected>NUM_BUTTONS)
		{selected=0;}
		setChanged(); notifyObservers(selected);
		//setSelected(selected.next());
	}
	public void pressedMenu()
	{
		XTrek.setMenu("Menu");
	}
	public void pressedSelect()
	{
		String text;
		/* Get selected language code and choose artist */
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
	public void pressedOnOff()
	{
		XTrek.setMenu("Off");
	}
}
