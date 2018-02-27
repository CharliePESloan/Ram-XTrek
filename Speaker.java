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
	final static String KEY1 = "b43e10841e0448dd96fda3fbd3110ff8";
	final static String KEY2 = "1be7b3ec099d461582bb194df5bd03de";

	final static String TEXT   = "Hello world! I am just testing out how this voice sounds, okay?";
	final static String LANG   = "en-US";
	final static String GENDER = "Female";
	// final static String ARTIST = "(en-GB, Susan, Apollo)";
	// final static String ARTIST = "(en-AU, HayleyRUS)";
	final static String ARTIST = "(en-CA, Linda)";
	// final static String ARTIST = "(en-US, ZiraRUS)";
	final static String OUTPUT = "output.wav";
	final static String FORMAT = "riff-16khz-16bit-mono-pcm";

	final static Speech mySpeech	= new Speech();
	final static Sound  mySound		= new Sound();

	public Speaker()
	{}

	public static void saySomething(String text)
	{
		final String token  = Speech.renewAccessToken( KEY1 );
		final byte[] speech = Speech.generateSpeech( token,		text,	LANG,
													 GENDER,	ARTIST,	FORMAT );
		InputStream myInputStream = new ByteArrayInputStream(speech);
		try
		{
			AudioInputStream myAudio =
				AudioSystem.getAudioInputStream(myInputStream);
			Sound.playStream( myAudio, Sound.readStream( myAudio ) );
		}
		catch ( IOException | UnsupportedAudioFileException e )
		{
			System.out.println(e);
		}
	}

	public static void main(String[] argv)
	{
		Speaker mySpeaker = new Speaker();
		mySpeaker.saySomething(TEXT);
	}
}
