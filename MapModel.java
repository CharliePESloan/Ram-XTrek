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
	private String latitude = "50.7184";     /* latitude  */
	private String longitude  = "-3.5339";     /* longitude */
	
	final static String SIZE      = "558x640";     /* Size   254x292    */
	final static String KEY       = "AIzaSyBDqXQupiOoXyFBQMu7cju5AozteVS8agU"; 
    private int zoomVal = 10; 					   /* zoom Value */	
	private int maxZoom = 21;                      /* maximum zoom value */
	private int minZoom = 2;                       /* minimum zoom value */ 
	private int counter = 1; 
	private int rotation = 0; 
	private String maptype;
	private Language language = new Language ("en"); 
	
	byte[] mapImage; 
	
	BufferedImage img; 
	
    public MapModel(MenuFrame XTrek, SpeechModel speechModel, SatelliteModel satModel) {
        mainFrame = XTrek;
		XTrek.getWin7Ublox7().addObserver(this); 
		satModel.addObserver(this); 
		speechModel.addObserver(this);
		imageLoader();
    }
	
	public void update(Observable obs, Object obj){
		
		if (obj instanceof Language)
		{ 
			language = (Language) obj; 
		}
		else if (obj instanceof Coordinate)
		{
			Coordinate a = (Coordinate) obj;
			rotation = a.getRotation(); 
			latitude = a.getLatStr(); 
			longitude = a.getLonStr(); 
		}
		imageLoader();
	}
	

	public void imageLoader () {   // Loads the map image
		
		try {

		mapImage = Maps.readData(latitude, longitude, Integer.toString(zoomVal), SIZE, KEY, language.getBingCode(), maptype); 
		img = ImageIO.read(new ByteArrayInputStream(mapImage));
		
		}
        catch (Exception e){
			System.out.println(e);
		} 
		setChanged(); notifyObservers (img); setChanged(); notifyObservers(rotation); 
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
		counter++; 
		System.out.println(zoomVal);
		switch(counter){
			case 1: maptype = "roadmap";
					break; 
			case 2: maptype = "terrain"; 
					break; 
			case 3: maptype = "hybrid";
					break; 
			default: counter = 1; 
					 maptype = "roadmap";
					 break; 		
		}
		System.out.println(counter); 
		System.out.println(latitude); 
		imageLoader();
    }
	public void pressedOnOff() { //Changes the XTrek's on/off state 
		mainFrame.setMenu(MenuEnum.ONOFF);	
		reset();
	}
	public void reset () {
		zoomVal = zoomVal; 
		imageLoader();
	}
	
}
