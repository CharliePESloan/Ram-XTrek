import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.lang.Runnable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import java.util.Random;

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
		Random rand = new Random();
		float r = 10*rand.nextFloat();
		try 
		{
			// Get raw audio
			final String token  = Speech.renewAccessToken( KEY1 );
			final byte[] speech =
				Speech.generateSpeech( token,
						       text,
						       lang,
						       GENDER,
						       artist,
						       FORMAT );
		
			InputStream myInputStream =
				new ByteArrayInputStream(speech);
			System.out.println(r + "got audio");
		
			// Try to play the audio
			try
			{
				AudioInputStream myAudio =
					AudioSystem.getAudioInputStream(myInputStream);
				System.out.println(r + "Converted");
				Sound.playStream( myAudio,
					Sound.readStream( myAudio ) );
				System.out.println(r + "Played");
			}
			catch ( UnsupportedAudioFileException e )
			{
				System.out.println(r + "Inner error");
				System.out.println(e);
			}
		}
		catch ( IOException e )
		{
			System.out.println(r + "Outer error");
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
	public static void saySomething(String text)
	{
		Language language = new Language("en");
		/* Start speaking a different thread */
		ExecutorService executor =
			Executors.newSingleThreadExecutor();
		
		executor.execute( new Speaker(text,language) );

		executor.shutdown();
	}

	public static void main(String[] args)
	{
		Speaker.saySomething("HELP");
	}
}
