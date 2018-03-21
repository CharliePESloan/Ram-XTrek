import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.lang.Runnable;

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
		try 
		{
			// Get raw audio
			final byte[] speech =
				Speech.generateSpeech( token,
						       text,
						       language.getBingCode(),
						       GENDER,
						       language.getArtist(),
						       FORMAT );
		
			InputStream myInputStream =
				new ByteArrayInputStream(speech);
			//System.out.println(r + "got audio");
		
			// Try to play the audio
			try
			{
				AudioInputStream myAudio =
					AudioSystem.getAudioInputStream(myInputStream);
				//System.out.println(r + "Converted");
				Sound.playStream( myAudio,
					Sound.readStream( myAudio ) );
				//System.out.println(r + "Played");
			}
			catch ( UnsupportedAudioFileException e )
			{
				//System.out.println(r + "Inner error");
				System.out.println(e);
			}
		}
		catch ( IOException e )
		{
			//System.out.println(r + "Outer error");
			System.out.println(e);
		}
	}
}
