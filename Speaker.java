import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.lang.Runnable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/*
 * Speaker
 * Charlie Sloan (2018)
 *
 * Combines Speech and Sound workshops to implement dynamic text-to-speech
 */

public class Speaker implements Runnable
{
	// Constant values
	final static String KEY1 = "b43e10841e0448dd96fda3fbd3110ff8";
	final static String KEY2 = "1be7b3ec099d461582bb194df5bd03de";
	final static String GENDER = "Female";
	final static String FORMAT = "riff-16khz-16bit-mono-pcm";

	// Variables
	final String text;
	final String lang;
	final String artist;

	/* Constructor */
	public Speaker(String text, Language language)
	{
		this.text   = text;
		this.lang   = language.getBingCode();
		this.artist = language.getArtist();
	}
	
	public void run() {
		
		// Get raw audio
		final String token  = Speech.renewAccessToken( KEY1 );
		final byte[] speech = Speech.generateSpeech( token,
							     text,
							     lang,
							     GENDER,
							     artist,
							     FORMAT );
		InputStream myInputStream =
			new ByteArrayInputStream(speech);
		
		// Try to play the audio
		try
		{
			AudioInputStream myAudio =
				AudioSystem.getAudioInputStream(myInputStream);
			Sound.playStream( myAudio,
				Sound.readStream( myAudio ) );
		}
		catch ( IOException | UnsupportedAudioFileException e )
		{
			System.out.println(e);
		}
	}

	/* saySomething
	 * Method which outputs from the device's speakers the sound of a
	 * voice speaking the string argument in a specified language.
	 */
	public static void saySomething(String text,Language language)
	{
		/* Start speaking a different thread */
		ExecutorService executor =
			Executors.newSingleThreadExecutor();
		
		executor.execute( new Speaker(text,language) );

		executor.shutdown();
	}
}
