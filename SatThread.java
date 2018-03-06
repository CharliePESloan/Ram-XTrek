import java.awt.Color;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.Dimension; 
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.InputStream;

public class SatThread extends BlankXTrex implements Runnable{
    
    final SideButton    PlusButton    = new BlankXTrex.SideButton("PlusButton");
    final SideButton    MinusButton   = new BlankXTrex.SideButton("MinusButton");
    final SideButton    SelectButton  = new BlankXTrex.SideButton("SelectButton");
    final SideButton    MenuButton    = new BlankXTrex.SideButton("MenuButton");
	JLabel longitude = new JLabel();
	JLabel latitude = new JLabel();
	
	/*Getting data from the Satellite Dongle*/
	Satellite mySat = new Satellite();
	/*Instance of Win7Ublox7*/
	Win7Ublox7 uBlox = new Win7Ublox7();
	
    
    public SatThread(){
        setTitle( "XTrex" );
        JLabel content = new JLabel(new ImageIcon("XTrex Background.png"));
        //content.add("XTrex Background.png");
        setContentPane(content);
        setLayout( null );
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setLocation((screenWidth / 3)+150, (screenHeight / 4)-150);
        setSize( 450, 835 ); /* title bar! */ 
        setResizable( false );
       
		longitude.setBounds(120, 350, 200, 80);
		latitude.setBounds(120, 425, 200, 80);
		
		longitude.setOpaque(true);
		latitude.setOpaque(true);
		
		add(longitude);
		add(latitude);
		
        PlusButton.setBounds(9, 102, 30, 68);add(PlusButton);
        MinusButton.setBounds(11, 175, 27, 64);add(MinusButton);
        SelectButton.setBounds(5, 268, 34, 97); add(SelectButton);
        MenuButton.setBounds(409, 113, 30,84); add(MenuButton);
        
        MenuButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                dispose();
                new XTrex();
            }
        });

    }
	
	public void run(){
			try{
				while(true){
					// Using dummy values from a dongle reading
					String a[] = mySat.getGLL(uBlox.Message);
					
					latitude.setText(a[0] + " " + a[1]);
					longitude.setText(a[2] + " " + a[3]);
					Thread.sleep(2000);
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}catch(IndexOutOfBoundsException e){
				System.out.println(e);
			}
		}  
        
        
    // from https://coderanch.com/t/476947/java/Adding-image-frame
        
    
	public static void main(String[] args){
		JFrame frame = new SatThread();
		frame.setLocationRelativeTo(null);
		frame.setSize(450, 835);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
}

class Win7Ublox7{
  final static String PORT_NAME = "COM4"; /* found via Computer->Devices */
  final static int    BAUD_RATE =  9600;  /* bps */
  final static int    TIMEOUT   =  2000;  /* ms  */
  final static int    BUFF_SIZE =  1024;
  public String Message;

  
	//Dongle Reader starts here//
  public Win7Ublox7(){};
  
		public void reader( String portName ) {
		try {
			String s;
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
			int    n;
					
			while ( ( n = in.read( buffer ) ) > -1 ) {
			  s = new String( buffer, 0, n );     
			  setNMEA(s);
			  /*System.out.print( s );*/
			}
		  } else {
			System.out.println( "not a serial port" ); System.exit( 1 );
		  }
		} catch ( Exception ex ) {
		  System.out.println( ex ); System.exit( 1 );
		}
	  }
		//Dongle Reader ends here, modified version.//
			
	  void setNMEA(String NMEA){
		  Message = NMEA;
	  }
  
  
	} 

class Satellite{
	
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
}