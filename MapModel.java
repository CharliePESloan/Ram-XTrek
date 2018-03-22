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
 * MapModel.
 * @author Devash Patel
 * Class that controls the funtionaliy of the map menu, sets all 
 * the requiremments for the map ready to be produced by the map view.
 *
 */
public class MapModel extends Observable implements Model, Observer {

    MenuFrame mainFrame;
	private String latitude = "50.5039";     /*  default latitude  */
	private String longitude  = "3.4699";     /* default longitude */
	private String goingToLatitude; 
	private String goingToLongitutde; 
	
	private final static String SIZE      = "558x640";     /* Size */
	private final static String KEY       = "AIzaSyBDqXQupiOoXyFBQMu7cju5AozteVS8agU";  /* Api key */
    private static int zoomVal = 10; 					   /* zoom Value */	
	private final static int MAXZOOM = 21;                      /* maximum zoom value */
	private final static int MINZOOM = 2;                       /* minimum zoom value */ 
	private int counter = 1; 					   /* incrementor */
	private int rotation = 0; 				       /* rotation value */
	private String maptype;						   /* map type */ 
	private Language language = new Language ("en");  /* default language */
	
	 
	byte[] mapImage; 
	
	BufferedImage img; 
	
    public MapModel(MenuFrame XTrek, SpeechModel speechModel, SatelliteModel satModel, Navigator navigator) {
		/* 
		* Constructor that sets up the map
		*/
        mainFrame = XTrek;
		imageLoader();
		XTrek.getWin7Ublox7().addObserver(this); 
		satModel.addObserver(this);
		speechModel.addObserver(this);
		navigator.addObserver(this); 
	
    }
	
	public void update(Observable obs, Object obj){
		/*
		*Method that updates the map based on location and observers the to see if the language has been changed
		*/
		
		if (obj instanceof Language)
		{ 
			language = (Language) obj; 
		}
		else if (obj instanceof Coordinate)
		{	
			Coordinate a = (Coordinate) obj;
			
			if (obs instanceof Navigator){
				goingToLatitude = a.getLatStr();
				goingToLongitutde = a.getLonStr();
				
			}else{
				
			
			rotation = a.getRotation(); 
			latitude = a.getLatStr(); 
			longitude = a.getLonStr(); 
			}
		}
		imageLoader();
	}
	
	public void imageLoader () {   // Loads the map image
		
		try {
		mapImage = Maps.readData(latitude, longitude, Integer.toString(zoomVal), SIZE, KEY, language.getBingCode(), maptype, "colour:blue|weight:5|"+latitude +"," +longitude + "|"+goingToLatitude+ "," + goingToLongitutde); 
		img = ImageIO.read(new ByteArrayInputStream(mapImage));
		}
        catch (Exception e){
			System.out.println(e);
		} 
		setChanged(); notifyObservers (img); setChanged(); notifyObservers(rotation); 
	}
  
    public void pressedPlus() {  //Zoom in method
		zoomVal ++;  
		if (zoomVal >= MAXZOOM){
			zoomVal = MAXZOOM;
			mainFrame.saySomething("maximum zoom!"); //warns user that the maximum zoom value has been reached.
		}
		imageLoader(); 
    }
    public void pressedMinus() { //Zoom out method
		zoomVal --; 
		if (zoomVal <= MINZOOM){
			zoomVal = MINZOOM; 
			mainFrame.saySomething("minimum zoom!"); //warns user that the minimum zoom value has been reached.
		}
		imageLoader();
    }
    public void pressedMenu() { //Returns to the menu screen
        mainFrame.setMenu(MenuEnum.MENU);
    }
    public void pressedSelect() { 
	
	/*
	*Select button changes the map type (extra functionality)
	*/
		counter++; 
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
		mainFrame.saySomething(maptype);
		imageLoader();
    }
	public void pressedOnOff() { //Changes the XTrek's on/off state 
		mainFrame.setMenu(MenuEnum.ONOFF);	
		reset();
	}
	public void reset () {
		zoomVal = 10; 
		maptype = "roadmap";
		imageLoader();
	}
	
}
