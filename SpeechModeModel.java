import java.util.Observable;

public class SpeechModeModel extends Observable implements Model
{
	/* Constants */
	final int NUM_BUTTONS = 6;

	/* Initialise variables */
	//CycleButton selected = null;
	int selected = 0;
	MenuFrame	XTrek;
	Language	language;
	String		artist;

	Language[] languages = new Language[]
		{
			new Language("English","en")
                        new Language("French","fr");
                        new Language("German","de");
                        new Language("Italian","it")
                        new Language("Spanish","es")

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
		selected++;
		if (selected>6) {selected=0};
		//setSelected(selected.prev());
	}
	public void pressedMinus()
	{
		selected--;
		if (selected<0) {selected=6};
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
