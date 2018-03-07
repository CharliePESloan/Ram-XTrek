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
	final static String SIZE      = "254x292";     /* Size      */
    private int zoomVal = 10; 					   /* zoom Value */	
	private int maxZoom = 21;                      /* maximum zoom value */
	private int minZoom = 2;                       /* minimum zoom value */ 
	
	byte[] mapImage; 
	
	BufferedImage img; 
	
    public MapModel(MenuFrame XTrek) {
        myXTrek = XTrek;
		imageLoader();
    }
	
	public void imageLoader () {   // Loads the map image
		mapImage = Maps.readData(LATITUDE, LONGITUDE, Integer.toString(zoomVal), SIZE); 
		try {
		img = ImageIO.read(new ByteArrayInputStream(mapImage));
		}
        catch (Exception e){
			System.out.println(e);
		}
		setChanged(); notifyObservers (img);
	}
  
    public void pressedPlus() {  //Zoom in method
		zoomVal ++;  
		if (zoomVal >= maxZoom){
			zoomVal = maxZoom;
		}
		imageLoader(); 
    }
    public void pressedMinus() { //Zoom out method
		zoomVal --; 
		if (zoomVal <= minZoom){
			zoomVal = minZoom; 
		}
		imageLoader();
    }
    public void pressedMenu() { //Returns to the menu screen
        myXTrek.setMenu("Menu");
    }
    public void pressedSelect() { //Select button able to be pressed, but no funcationality
    }
	public void pressedOnOff() { //Changes the XTrek's on/off state 
		myXTrek.setMenu("OnOff");
	}
}
