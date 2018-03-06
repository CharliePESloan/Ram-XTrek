import java.util.Observable;

public class SpeechModeModel extends Observable implements Model
{
	/* Initialise variables */
	CycleButton selected = null;
	MenuFrame	XTrek;
	Language	language;
	String		artist;

	public SpeechModeModel(MenuFrame XTrek)
	{
		this.XTrek = XTrek;
	}
	public void setSelected(CycleButton newSelected)
	{
		selected = newSelected;
		selected.select();
	}

	/* Side buttons */
	public void pressedPlus()
	{
		setSelected(selected.prev());
	}
	public void pressedMinus()
	{
		setSelected(selected.next());
	}
	public void pressedMenu()
	{
		XTrek.setMenu("Menu");
	}
	public void pressedSelect()
	{
		/* Get selected language code and choose artist */
		language = (Language)selected.getData();
		Speaker.saySomething(selected.getText(),language.getBingCode(),language.getArtist());
		/* Start speaking a different thread *
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute( new Runnable() {
			public void run() {
				Speaker.saySomething(selected.getText(),language,artist);
			}
		} );*/
	}
}
