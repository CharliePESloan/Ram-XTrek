import java.io.File;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
/*
 * Sound generation. David Wakeling, 2018.
 * Improvements by Charlie Sloan
 */
public class Sound {
  private final static String FILENAME = "eastwood.wav";

  /*
   * Set up stream. 
   */
  static AudioInputStream setupStream( String name ) {
    try {
      File             file = new File( name );
      AudioInputStream stm  = AudioSystem.getAudioInputStream( file );
      return stm;
    } catch ( Exception ex ) {
      System.out.println( ex ); return null;
    }
  }

  /*
   * Read stream.
   */
  static ByteArrayOutputStream readStream( AudioInputStream stm ) {
    try {
      AudioFormat           af  = stm.getFormat();
      ByteArrayOutputStream bos = new ByteArrayOutputStream();

      int  bufferSize = (int) af.getSampleRate() * af.getFrameSize();
      byte buffer[]   = new byte[ bufferSize ];

      for (;;) {
        int n = stm.read( buffer, 0, buffer.length );
        if ( n > 0 ) {
           bos.write( buffer, 0, n );
        } else {
          break;
        }
      }

      return bos;
    } catch ( Exception ex ) {
      System.out.println( ex ); return null;
    }
  }

  /*
   * Play stream.
   */
  static void playStream( AudioInputStream stm, ByteArrayOutputStream bos ) {
    try {
      AudioFormat    af   = stm.getFormat();
      byte[]         ba   = bos.toByteArray();
      DataLine.Info  info = new DataLine.Info( SourceDataLine.class, af );
      SourceDataLine line = (SourceDataLine) AudioSystem.getLine( info );
      line.open( af );
      line.start();
      line.write( ba, 0, ba.length );
    } catch ( Exception ex ) {
      System.out.println( ex );
    }
  }

  /*
   * Generate sound.
   */
  public static void main( String[] argv ) {
    AudioInputStream stm = setupStream( FILENAME );
    playStream( stm, readStream( stm ) );
  }
}
