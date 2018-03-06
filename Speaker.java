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
 *
 * Charlie Sloan 2018
 */

public class Speaker implements Runnable
{
	// Constant values
	final static String KEY1 = "b43e10841e0448dd96fda3fbd3110ff8";
	final static String KEY2 = "1be7b3ec099d461582bb194df5bd03de";

	final static String TEXT   = "Hello world!";
	final static String GENDER = "Female";
	final static String ARTIST = "(en-CA, Linda)";
	final static String OUTPUT = "output.wav";
	final static String FORMAT = "riff-16khz-16bit-mono-pcm";

	final String text;
	final String lang;
	final String artist;

	public Speaker(String text, String lang, String artist)
	{
		this.text = text;
		this.lang = lang;
		this.artist = artist;
	}
	
	public void run() {
		//Speaker.saySomething(selected.getText(),language,artist);
		// Get raw audio
		System.out.println("Running thread");
		final String token  = Speech.renewAccessToken( KEY1 );
		final byte[] speech = Speech.generateSpeech( token,
							     text,
							     lang,
							     GENDER,
							     artist,
							     FORMAT );
		InputStream myInputStream = new ByteArrayInputStream(speech);
		try
		{
			// Try to play the audio
			AudioInputStream myAudio =
				AudioSystem.getAudioInputStream(myInputStream);
			Sound.playStream( myAudio, Sound.readStream( myAudio ) );
		}
		catch ( IOException | UnsupportedAudioFileException e )
		{
			System.out.println(e);
		}
	}

	/* Method which outputs the sound of a voice speaking the text argument
	 * in the chosen language
	 */
	public static void saySomething(String text,String lang,String artist)
	{
		System.out.println("trying to speak");
		/* Start speaking a different thread */
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		executor.execute( new Speaker(text,lang,artist) );

		executor.shutdown();
	}

	// Test to ensure speech module is functional
	public static void main(String[] argv)
	{
		//Speaker mySpeaker = new Speaker();
		Speaker.saySomething(TEXT,"en-GB",ARTIST);
	}
}
