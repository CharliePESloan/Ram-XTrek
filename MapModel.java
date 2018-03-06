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

/**
 *
 * @author Devash Patel 
 
 */
public class MapModel extends Observable implements Model {

    MenuFrame myXTrek;
	final static String OUTPUT    = "Images/output.png";  /* Ouput file        */
	final static String LATITUDE  = "50.7184";     /* Exeter, latitude  */
	final static String LONGITUDE = "-3.5339";     /* Exeter, longitude */
	final static String SIZE      = "278x402";     /* Size              */
    private int zoomVal = 5; 
	byte[] mapImage; 
	
	BufferedImage img; 
	
    public MapModel(MenuFrame XTrek) {
        myXTrek = XTrek;
    }
  
    public void pressedPlus() {
		zoomVal ++; 
		mapImage = Maps.readData(LATITUDE, LONGITUDE, Integer.toString(zoomVal), SIZE);
		try{
		ImageIO.read(new ByteArrayInputStream(mapImage)); 
		}
		catch (Exception e){
			System.out.println("help"); 
		}
        setChanged(); notifyObservers (img);
    }
    public void pressedMinus() {
		zoomVal --; 
		mapImage = Maps.readData(LATITUDE, LONGITUDE, Integer.toString(zoomVal), SIZE); 
		try {
		ImageIO.read(new ByteArrayInputStream(mapImage));
		}
        catch (Exception e){
			System.out.println("help");
		}
		setChanged(); notifyObservers (img);
    }
    public void pressedMenu() {
        myXTrek.setMenu("Menu");
    }
    public void pressedSelect() {
       
    }
}
