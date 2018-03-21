import java.io.File;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
/*
 * Speech generation using Microsoft Cognitive Services.
 *
 * See http://www.microsoft.com/cognitive-services/en-us/speech-api
 *
 * David Wakeling, 2018.
 */
public class Speech {
  final static String KEY1 = "0127b00c948440f38cd751ceee594c92";
  final static String KEY2 = "5190ee0163b5498a94308ab1496ed04d";

  final static String TEXT   = "Frankly, my dear, I don't give a damn!";
  final static String LANG   = "en-US";
  final static String GENDER = "Female";
  final static String ARTIST = "(en-GB, Susan, Apollo)";
  // final static String ARTIST = "(en-AU, HayleyRUS)";
  // final static String ARTIST = "(en-CA, Linda)";
  // final static String ARTIST = "(en-US, ZiraRUS)";
  final static String OUTPUT = "output.wav";
  final static String FORMAT = "riff-16khz-16bit-mono-pcm";

  /*
   * Renew an access token --- they expire after 10 minutes.
   */
  static String renewAccessToken( String key1 ) {
    try
    {
    final String method = "POST";
    final String url = 
      "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";
    final byte[] body = {}; 
    final String[][] headers
      = { { "Ocp-Apim-Subscription-Key", key1                          }
        , { "Content-Length"           , String.valueOf( body.length ) }
        };
    byte[] response = HttpConnect.httpConnect( method, url, headers, body );
    return new String( response ); 
    }
    catch (Exception e)
    {
	    System.out.println("Error getting token\n");
	    return null;
    }
  }

  /*
   * Synthesize speech.
   */
  static byte[] generateSpeech( String token,  String text
                              , String lang,   String gender
                              , String artist, String format ) {
    try
    {
    final String method = "POST";
    final String url = "https://speech.platform.bing.com/synthesize";
    final byte[] body
      = ( "<speak version='1.0' xml:lang='en-us'>"
        + "<voice xml:lang='" + lang   + "' "
        + "xml:gender='"      + gender + "' "
        + "name='Microsoft Server Speech Text to Speech Voice "
        + artist + "'>"
        + text
        + "</voice></speak>" ).getBytes(); 
    final String[][] headers
      = { { "Content-Type"             , "application/ssml+xml"        }
        , { "Content-Length"           , String.valueOf( body.length ) }
        , { "Authorization"            , "Bearer " + token             }
        , { "X-Microsoft-OutputFormat" , format                        }
        };
    byte[] response = HttpConnect.httpConnect( method, url, headers, body );
    return response;
    }
    catch (Exception e)
    {
	    System.out.println("Problem generating speech");
	    System.out.println(e);
	    return null;
    }
  } 

  /*
   * Write data to file.
   */
  static void writeData( byte[] buffer, String name ) {
    try {
      File             file = new File( name );
      FileOutputStream fos  = new FileOutputStream( file );
      DataOutputStream dos  = new DataOutputStream( fos ); 
      dos.write( buffer );
      dos.flush();
      dos.close();
    } catch ( Exception ex ) {
      System.out.println( ex ); return;
    }
  }

  /*
   * Generate speech.
   */
  public static void main( String[] argv ) {
    final String token  = renewAccessToken( KEY1 );
    final byte[] speech = generateSpeech( token,  TEXT,   LANG
                                        , GENDER, ARTIST, FORMAT );
    writeData( speech, OUTPUT );
  }
}
