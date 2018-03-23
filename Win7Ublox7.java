import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.InputStream;
import java.util.Observable;

/*
 * Satellite View.
 *
 * Clyde Udunna 2018 taken from David Wakeling.
 */

 /*This program takes care of running the USB dongle which allows
 *us to get information relative to our current position and via the 
 *use of a thread it allows us to parse and pass on the information 
 *received to the rest of our program.*/
public class Win7Ublox7 extends Observable implements Runnable{
	final static String PORT_NAME = "COM4"; /* found via Computer->Devices */
	final static int    BAUD_RATE =  9600;  /* bps */
	final static int    TIMEOUT   =  2000;  /* ms  */
	final static int    BUFF_SIZE =  1024;
	String[] mapLatLon;
	String[] tripVelocity;
	String[] tripRotation;
	String[] savedMapLatLon;
	String[] savedTripVelocity;
	String[] savedTripRotation;
	SatelliteView myView;
	double distance = 0.0;
	Coordinate previousCoordinate;

	//Dongle Reader starts here//
	public Win7Ublox7(){};
	
	//Code inserted into run method to enable thread to run code segment
	public void run(){
		Satellite mySat = new Satellite(){};
		try {
			String s;
			CommPortIdentifier portId =
				CommPortIdentifier.getPortIdentifier( "COM4");
			
			if ( portId.isCurrentlyOwned() ) {
				System.out.println( "port in use" ); System.exit( 1 );
			}
				
			System.err.close();
			CommPort commPort = portId.open("whatever", TIMEOUT );
			
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
				int n;
			
				while ( ( n = in.read( buffer ) ) > -1 ) {
					s = new String( buffer, 0, n );     
					mapLatLon = mySat.getGLL(s); //updating our array to contain new value
					//tripVelocity = new String[] {"0", "0", "0", "0", "0", "0", "9.78"};
					tripVelocity = mySat.getVTG(s);
					tripRotation = mySat.getGSV(s);
					if(mapLatLon != null)
					{
						savedMapLatLon = mapLatLon;
					}
					if (tripRotation != null)
					{
						savedTripRotation = tripRotation;
					}
					if (tripVelocity != null)
					{
						savedTripVelocity = tripVelocity;
					}
					if (savedMapLatLon == null || savedTripRotation == null || savedTripVelocity == null)
					{
						continue;
					}
					
					Coordinate c =
						new Coordinate(savedMapLatLon,
									   savedTripVelocity,
									   savedTripRotation, distance);	
					
					if (previousCoordinate != null)
					{
						distance += c.distanceTo(previousCoordinate);
					}	
					
					previousCoordinate = c;
					
					setChanged(); //Notifying the observer that a change has occurred
					notifyObservers(c);//Passing on our values to the observer for further use	
				}
			}else {System.out.println( "not a serial port" );}
			
		} catch ( Exception ex ) {
			setChanged();
			notifyObservers("Cannot display location");
			ex.printStackTrace();
			System.out.println( ex );
	
		}
	}

	
	//Dongle Reader ends here, modified version.//
		
	/*This method returns the string array which contains the coordinates
	*of our position. Created for the use of other team-mates to further
	*develop their work packages*/
	public String[] getCoordinates(){
		return mapLatLon;
	}
}