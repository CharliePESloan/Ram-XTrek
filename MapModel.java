import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO; 
import java.awt.Graphics;
import java.awt.Graphics2D;

/*
 * @author Devash Patel  
 */
public class MapModel extends Observable implements Model, Observer {

    MenuFrame mainFrame;
	private static String LATITUDE  = "50.7184";     /* latitude  */
	private static String LONGITUDE = "-3.5339";     /* longitude */
	
	final static String SIZE      = "254x292";     /* Size      */
	final static String KEY       = "AIzaSyBDqXQupiOoXyFBQMu7cju5AozteVS8agU"; 
    private int zoomVal = 10; 					   /* zoom Value */	
	private int maxZoom = 21;                      /* maximum zoom value */
	private int minZoom = 2;                       /* minimum zoom value */ 
	private Language language = new Language ("en"); 
	
	byte[] mapImage; 
	
	BufferedImage img; 
	
    public MapModel(MenuFrame XTrek, SpeechModeModel speechModel, SatelliteModel satModel) {
        mainFrame = XTrek;
		speechModel.addObserver(this); 
		satModel.addObserver(this); 
		imageLoader();
    }
	
	public void update(Observable obs, Object obj){
		if (obj instanceof Language)
		{
			language = (Language) obj; 
			imageLoader();
		}
		else if (obj instanceof String [])
		{
			String[] a = (String[]) obj;
			LATITUDE = (a[0] + " " + a[1]);
			LONGITUDE = (a[2] + " " + a[3]);
		}
	}
	
	
	public void imageLoader () {   // Loads the map image
		
		mapImage = Maps.readData(LATITUDE, LONGITUDE, Integer.toString(zoomVal), SIZE, KEY, language.getBingCode()); 
	
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
        mainFrame.setMenu(MenuEnum.MENU);
    }
    public void pressedSelect() { //Select button able to be pressed, but no functionality
    }
	public void pressedOnOff() { //Changes the XTrek's on/off state 
		mainFrame.setMenu(MenuEnum.ONOFF);	
		reset();
	}
	public void reset () {
		zoomVal = 10; 
		imageLoader();
	}
	
}
