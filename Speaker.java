import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.lang.Runnable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.Observer;
import java.util.Observable;

/*
 * Speaker
 * Charlie Sloan (2018)
 *
 * Combines Speech and Sound workshops to implement dynamic text-to-speech
 */

public class Speaker implements Observer
{
	// Constant values
	final static String KEY1 = "b43e10841e0448dd96fda3fbd3110ff8";
	final static String KEY2 = "1be7b3ec099d461582bb194df5bd03de";
	final static int EIGHTMINUTES = 48000;

	// Variables
	Language language;
	String token;
	long time;

	/* Constructor */
	public Speaker(Language language, SpeechModel speechModel)
	{
		this.language = language;
		speechModel.addObserver(this);
		renew();
	}

	/* saySomething
	 * Method which outputs from the device's speakers the sound of a
	 * voice speaking the string argument in a specified language.
	 */
	public void saySomething(String text,Language language)
	{
		/* Renew token if needed */
		if (System.currentTimeMillis() - time > EIGHTMINUTES)
		{
			renew();
		}
		/* Start speaking a different thread */
		ExecutorService executor =
			Executors.newSingleThreadExecutor();
		
		executor.execute( new SpeechThread(text,
						   language,
						   token) );

		executor.shutdown();
	}
	public void saySomething(String text)
	{ saySomething(text,this.language); }

	/* renew
	 * Method which renews the access token
	 */
	private void renew()
	{
		time = System.currentTimeMillis();
		token = Speech.renewAccessToken( KEY1 );
	}

	public void update(Observable obs, Object obj)
	{
		if (obs instanceof SpeechModel && obj instanceof Language)
		{
			language = (Language)obj;
		}
	}
}
