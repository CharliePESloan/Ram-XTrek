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
	
	
    public MapModel(MenuFrame XTrek) {
        myXTrek = XTrek;
    }
  
    public void pressedPlus() {
		zoomVal ++; 
		mapImage = Maps.readData(LATITUDE, LONGITUDE, zoomVal.toString(zoomVal), SIZE);
		BufferedImage img = Image.IO.read(new ByteArrayInputStream(mapImage)); 
        setSelected(selected.prev());
        setChanged(); notifyObservers (img);
    }
    public void pressedMinus() {
		zoomVal --; 
		mapImage = Maps.readData(LATITUDE, LONGITUDE, zoomVal.toString(zoomVal), SIZE); 
		BufferedImage img = Image.IO.read(new ByteArrayInputStream(mapImage));
        setSelected(selected.next());
        setChanged(); notifyObservers (img);
    }
    public void pressedMenu() {
        myXTrek.setMenu("Menu");
    }
    public void pressedSelect() {
       
    }
}
