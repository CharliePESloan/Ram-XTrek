import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.InputStream;

public class Satellite{
	
	public Satellite(){};
	
	public String[] getPosition(String coordinate){
	    String[] components;
		if(coordinate.startsWith("$GPGLL")){
			String message = coordinate.substring(7);
			components = message.split(",");
			if(components.length != 0){
				return components;
			}else{return null;}
		}
	return null;
	}
	
	public String[] getGLL(String text){
		String [] lines = text.split("\n");
		String[] ifvalid;
		for(int i = 0; i< lines.length; i++){
			ifvalid = getPosition(lines[i]);
			if(ifvalid == null){continue;}
			return ifvalid;
		}
	return null;
	}
		
	public static void main(String[] args){
		Win7Ublox7 uBlox = new Win7Ublox7();
		Thread thread1 = new Thread(uBlox);
		thread1.start();
	}
}

class Win7Ublox7 implements Runnable{
  final static String PORT_NAME = "COM4"; /* found via Computer->Devices */
  final static int    BAUD_RATE =  9600;  /* bps */
  final static int    TIMEOUT   =  2000;  /* ms  */
  final static int    BUFF_SIZE =  1024;

	//Dongle Reader starts here//
  public Win7Ublox7(){};
  
	public void run() {
	Satellite mySat = new Satellite(){};
	try {
		String s;
		CommPortIdentifier portId =
			CommPortIdentifier.getPortIdentifier( "COM4");
		
		if ( portId.isCurrentlyOwned() ) {
			System.out.println( "port in use" ); System.exit( 1 );
		 }
			
		System.err.close();
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
			int    n;
					
			while ( ( n = in.read( buffer ) ) > -1 ) {
				s = new String( buffer, 0, n );     
				/*System.out.print( s );*/
				String[] a = mySat.getGLL(s);
				if(a == null){continue;}
				System.out.println(a[0] + " " + a[1]);
				System.out.println(a[2] + " " + a[3]);		
			}
		}else {
			System.out.println( "not a serial port" ); System.exit( 1 );
		}
	}catch ( Exception ex ) {
		System.out.println( ex ); System.exit( 1 );
		}
	}
		//Dongle Reader ends here, modified version.//
		
  
	}