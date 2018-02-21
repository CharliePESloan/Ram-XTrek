import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
/*
 * Maps obtained using the Google Mapping Service.
 *
 * See https://developers.google.com/maps/documentation/static-maps/intro 
 *
 * David Wakeling, 2018.
 */
public class Maps {
  final static String OUTPUT    = "output.png";  /* Ouput file        */
  final static String LATITUDE  = "50.7184";     /* Exeter, latitude  */
  final static String LONGITUDE = "-3.5339";     /* Exeter, longitude */
  final static String ZOOM      = "10";           /* 0 ... 21           */
  final static String SIZE      = "131x635";     /* Size              */

  static byte[] readData( String latitude
                        , String longitude
                        , String zoom
                        , String size
                        ) {
    final String method = "GET";
    final String url
      = ( "https://maps.googleapis.com/maps/api/staticmap"
        + "?" + "center" + "=" + latitude + "," + longitude
        + "&" + "zoom"   + "=" + zoom
        + "&" + "size"   + "=" + size
        );
    final byte[] body
        = {};
    final String[][] headers
        = {};
    byte[] response = HttpConnect.httpConnect( method, url, headers, body );
    return response;
  }

  /*
   * Write map data.
   */
  static void writeData( String file, byte[] data ) {
    try {
      OutputStream os = new FileOutputStream( file );
      os.write( data, 0, data.length );
      os.close();
    } catch ( IOException ex ) {
      ex.printStackTrace(); System.exit( 1 );
    }
  }

  /*
   * Download map data.
   */
  public static void main( String[] argv ) {
    final byte[] data = readData( LATITUDE, LONGITUDE, ZOOM, SIZE ); 
    writeData( OUTPUT, data ); 
  }
  
}
