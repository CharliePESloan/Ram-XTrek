import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
/*
 * Maps obtained using the Google Mapping Service.
 *
 * See https://developers.google.com/maps/documentation/static-maps/intro
 *  
 * Produced by Devash Patel 2018.
 * Code used is adpated from that written by David Wakeling 2018. 
 */
public class Maps {
  static byte[] readData( String latitude
                        , String longitude
                        , String zoom
                        , String size
						, String key
                        ) {
    final String method = "GET";
    final String url
      = ( "https://maps.googleapis.com/maps/api/staticmap"
        + "?" + "center" + "=" + latitude + "," + longitude
        + "&" + "zoom"   + "=" + zoom
        + "&" + "size"   + "=" + size
        + "&" + "key"    + "=" + key);
    final byte[] body
        = {};
    final String[][] headers
        = {};
    byte[] response = HttpConnect.httpConnect( method, url, headers, body );
    return response;
  }


}
