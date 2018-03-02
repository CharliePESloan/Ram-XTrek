import java.util.Observable;
import java.lang.Runnable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class SpeechModeModel extends Observable implements Model
{
	/* Initialise variables */
	CycleButton selected = null;
	XTrex2	    myXTrek;
	String	    language;
	String	    artist;

	public SpeechModeModel(XTrex2 XTrek)
	{
		myXTrek = XTrek;
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
		myXTrek.setMenu("Menu");
	}
	public void pressedSelect()
	{
		/* Get selected language code and choose artist */
		language = selected.getData();
		switch (language)
		{
			case "fr-FR":
				artist = "(fr-FR, Julie, Apollo)";
				break;
			case "de-DE":
				artist = "(de-DE, Hedda)";
				break;
			case "it-IT":
				artist = "(it-IT, Cosimo, Apollo)";
				break;
			case "es-ES":
				artist = "(es-ES, Laura, Apollo)";
				break;
			default:
				artist = "(en-GB, Susan, Apollo)";
				break;
		}
		/* Start speaking a different thread */
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute( new Runnable() {
			public void run() {
				Speaker.saySomething(selected.getText(),language,artist);
			}
		} );
		executor.shutdown();
	}
}
