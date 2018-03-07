/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO; 

/*
 * @author Devash Patel  
 */
public class MapModel extends Observable implements Model {

    MenuFrame myXTrek;
	final static String LATITUDE  = "50.7184";     /* latitude  */
	final static String LONGITUDE = "-3.5339";     /* longitude */
	final static String SIZE      = "278x402";     /* Size      */
    private int zoomVal = 10; 					   /* zoom Value */	
	byte[] mapImage; 
	
	BufferedImage img; 
	
    public MapModel(MenuFrame XTrek) {
        myXTrek = XTrek;
		imageLoader();
    }
	
	public void imageLoader () {
		mapImage = Maps.readData(LATITUDE, LONGITUDE, Integer.toString(zoomVal), SIZE); 
		try {
		img = ImageIO.read(new ByteArrayInputStream(mapImage));
		}
        catch (Exception e){
			System.out.println("help");
		}
		setChanged(); notifyObservers (img);
	}
  
    public void pressedPlus() {
		zoomVal ++; 
		System.out.println(zoomVal); 
		imageLoader(); 
    }
    public void pressedMinus() {
		zoomVal --; 
		System.out.println(zoomVal);
		imageLoader();
    }
    public void pressedMenu() {
        myXTrek.setMenu("Menu");
    }
    public void pressedSelect() {
    }
	public void pressedOnOff() {
		
	}
}
