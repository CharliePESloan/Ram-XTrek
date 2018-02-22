import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.InputStream;
import java.util.Scanner;

/* 
 * Win7 Ublox7 reader.
 * 
 * David Wakeling, 2018.
 *
 * From rxtx-2.2pre2-bins.zip, extract RXTXcomm.jar and
 * the win64 version of rxtxSerial.dll.
 *
 * RXTXcomm.jar should be added to the CLASSPATH and 
 * rxtxSerial.dll should be placed in current directory.
 */
 
 /*made some changes to the code still working on it*/
public class SatelliteDisplay {
  final static String PORT_NAME = "COM4"; /* found via Computer->Devices */
  final static int    BAUD_RATE =  9600;  /* bps */
  final static int    TIMEOUT   =  2000;  /* ms  */
  final static int    BUFF_SIZE =  1024;

  /*
   * Reader.
   */
  public static String reader( String portName ) {
	String nmea = null;
    try {
      CommPortIdentifier portId =
        CommPortIdentifier.getPortIdentifier( portName );
    
      if ( portId.isCurrentlyOwned() ) {
        System.out.println( "port in use" ); System.exit( 1 );
      }
        
      CommPort commPort = portId.open( "whatever", TIMEOUT );
            
      if ( commPort instanceof SerialPort ) {
        SerialPort serialPort = (SerialPort) commPort;
        serialPort.setSerialPortParams( BAUD_RATE
                                      , SerialPort.DATABITS_8
                                      , SerialPort.STOPBITS_1
                                      , SerialPort.PARITY_NONE
                                      );
                
        serialPort.setFlowControlMode( SerialPort.FLOWCONTROL_RTSCTS_IN );
        serialPort.setRTS( true );

        InputStream in = serialPort.getInputStream();
	byte[] buffer  = new byte[ BUFF_SIZE ];
        String s;
        int    n;
                
        while ( ( n = in.read( buffer ) ) > -1 ) {
          s = new String( buffer, 0, n );       
          System.out.print( s );
		  nmea = s;
		}
      } else {
        System.out.println( "not a serial port" ); System.exit( 1 );
      }
    } catch ( Exception ex ) {
      System.out.println( ex ); System.exit( 1 );
    }
	return nmea;
  }
   
  /*
   * Win7 Ublox7 reader.
   */   
  public static void main( String[] argv ) {
    /*reader( PORT_NAME );*/
	Satellite mySat = new Satellite();
	mySat.getGLL(reader( PORT_NAME));
  } 
}

/*Satellite that gets the nmea message "s", 
 *splits the message in lines and the codes the GLL sentence,
 *which gives position and latitude required by the gps*/
class Satellite{
	
	public Satellite(){};
	
	public void getPosition(String coordinate){
		if(coordinate.startsWith("$GPGLL")){
			String message = coordinate.substring(7);
			String[] components = message.split(",");
			for(int i = 0; i < 4; i++){
				System.out.println(components[i]);
			}
		}
	}
	
	public void getGLL(String text){
		String [] lines = text.split("\n");
		for(int i =0; i<lines.length; i++){
			getPosition(lines[i]);
		}
	}
	
}

/*class Position{
	public float latitude = 0.0f;
	public String latDir = null;
	public float longitude = 0.0f;
	public String longDir = null;
}*/