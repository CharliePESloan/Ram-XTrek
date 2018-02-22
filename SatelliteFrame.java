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

public class SatelliteFrame extends BlankXTrex {
    
    final SideButton    PlusButton    = new BlankXTrex.SideButton("PlusButton");
    final SideButton    MinusButton   = new BlankXTrex.SideButton("MinusButton");
    final SideButton    SelectButton  = new BlankXTrex.SideButton("SelectButton");
    final SideButton    MenuButton    = new BlankXTrex.SideButton("MenuButton");
    
    public SatelliteFrame(){
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
        
        /*Getting data from the Satellite Dongle*/
		Satellite mySat = new Satellite();
		// Using dummy values from a dongle reading
		String a[] = mySat.getGLL("$GPGLL,5043.94335,N,00330.91606,W,111950.00,A,A*74");
		
        JLabel longitude = new JLabel(a[0] + " " + a[1], SwingConstants.CENTER);
		JLabel latitude = new JLabel(a[2] + " " + a[3], SwingConstants.CENTER );
		
		longitude.setBounds(120, 350, 200, 80);
		latitude.setBounds(120, 425, 200, 80);
		
		longitude.setOpaque(true);
		latitude.setOpaque(true);
		
		/*longitude.setBackground(Color.WHITE);
		longitude.setBackground(Color WHITE);*/
		
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
        
        
        // from https://coderanch.com/t/476947/java/Adding-image-frame
        
    
  
  
	public static void main(String[] args){
		JFrame frame = new SatelliteFrame();
		frame.setLocationRelativeTo(null);
		frame.setSize(450, 835);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}

class Satellite{
	
	/*public static void main(String[] args){
		Satellite mySat = new Satellite();
		String[] a = mySat.getGLL("$GPGLL,5043.94335,N,00330.91606,W,111950.00,A,A*74\n$GPGLL,5043.94335,N,00330.91606,W,111950.00,A,A*74\n");
		for(int i = 0; i < a.length; i++){
		    System.out.println(a[i]);
		}
	}*/
	
	public Satellite(){};
	
	public String[] getPosition(String coordinate){
	    String[] components;
		if(coordinate.startsWith("$GPGLL")){
			String message = coordinate.substring(7);
			components = message.split(",");
		    return components;
		}
		else{
		    return null;
		}
	}
	
	public String[] getGLL(String text){
		String [] lines = text.split("\n");
		return getPosition(lines[0]);
	}
}