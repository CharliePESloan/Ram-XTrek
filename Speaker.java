import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * Speaker
 *
 * Charlie Sloan 2018
 */

public class Speaker
{
	// Constant values
	final static String KEY1 = "b43e10841e0448dd96fda3fbd3110ff8";
	final static String KEY2 = "1be7b3ec099d461582bb194df5bd03de";

	final static String TEXT   = "Hello world!";
	final static String GENDER = "Female";
	final static String ARTIST = "(en-CA, Linda)";
	final static String OUTPUT = "output.wav";
	final static String FORMAT = "riff-16khz-16bit-mono-pcm";

	// Classes used to produce speech and play sound
	final static Speech mySpeech	= new Speech();
	final static Sound  mySound	= new Sound();

	public Speaker()
	{}

	/* Method which outputs the sound of a voice speaking the text argument
	 * in the chosen language
	 */
	public static void saySomething(String text,String lang,String artist)
	{
		// Get raw audio
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

	// Test to ensure speech module is functional
	public static void main(String[] argv)
	{
		Speaker mySpeaker = new Speaker();
		mySpeaker.saySomething(TEXT,"en-GB",ARTIST);
	}
}
