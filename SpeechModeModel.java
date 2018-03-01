import java.util.Observable;
import java.lang.Runnable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class SpeechModeModel extends Observable implements Model
{
	//ExecutorService executor = Executors.newFixedThreadPool(1);
	ExecutorService executor = Executors.newSingleThreadExecutor();
	CycleButton selected = null;
	String	    language;
	String	    artist;

	public SpeechModeModel()
	{}
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
	{}
	public void pressedSelect()
	{
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
		System.out.println(artist);
		executor.execute( new Runnable() {
			public void run() {
				Speaker.saySomething(selected.getText(),language,artist);
			}
		} );
		executor.shutdown();

		//Speaker.saySomething(selected.getText(),language,artist);
	}
}
