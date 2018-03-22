import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.lang.Runnable;

/* SpeechThread
 * Charlie Sloan (2018)
 *
 * A Runnable which speaks a line
 */
public class SpeechThread implements Runnable
{
	// Constant values
	final static String GENDER = "Female";
	final static String FORMAT = "riff-16khz-16bit-mono-pcm";

	// Variables
	final String text;

	Language language;
	String token;
	float time;

	/* Constructor */
	public SpeechThread(String text, Language language,String token)
	{
		this.text	= text;
		this.language	= language;
		this.token	= token;
	}
	
	public void run() {
		// Get raw audio
		final byte[] speech =
			Speech.generateSpeech( token,
					       text,
					       language.getBingCode(),
					       GENDER,
					       language.getArtist(),
					       FORMAT );	
	
		// Try to play the audio
		try
		{
			InputStream myInputStream =
				new ByteArrayInputStream(speech);
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
}
