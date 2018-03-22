import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import org.json.*;
import java.util.Observable;
import java.util.Observer;

/*
 * Navigator
 * Charlie Sloan 2018 (adapted from code by David Wakeling)
 *
 * Gets directions from the Google Maps api and parses them into
 * a usable format
 */

public class Navigator extends Observable implements Observer
{
	/* Constant Values */
	final static String URLBASE =
		"https://maps.googleapis.com/maps/api/directions/json";
	final static String KEY	=
		"AIzaSyB9Ky-AaEebyjqE7f8-kBmXEMJL69Cr_Yk";
	final static String METHOD = "GET";
	final static String REGION	   = "uk";
	final static String MODE	   = "TRANSIT";
	final static String ENCODING	   = "UTF-8";
	final static int    SEARCHDISTANCE = 10000;
	final static float  SPEECHDISTANCE = 0.01f;

	/* Variables */
	private MenuFrame mainFrame;
	//private SpeechModel speech;
	//private SatelliteModel  satellite;
	//private WhereToFrameModel whereTo;
	private Language	language = new Language("English", "en");

	private	String		origin;
	private	String		destination;
	private	String		encOrigin;
	private	String		encDestination;

	/* Variables which store the directions */
	private	byte[]		directionsRaw = {};
	private JSONObject	directionsJSON;
	private Direction[]	directions;

	/* Variables for traversing directionsJSON */
	private JSONArray	routes;
	private JSONObject	route;
	private JSONArray	legs;
	private JSONObject	leg;
	private JSONArray	steps;
	private JSONObject	step;

	private int		currentDirection;


	/*
	 * Constructor
	 */
	public Navigator(MenuFrame mainFrame, SpeechModel speechModel,Win7Ublox7 ublox7,WhereToFrameModel whereModel)
	{
		currentDirection=0;

		this.mainFrame = mainFrame;
		//speech = speechModel;
		//whereTo = whereModel;
		//satellite = satModel;
		speechModel.addObserver(this);
		whereModel.addObserver(this);
		ublox7.addObserver(this);
		//satellite.addObserver(this);
	}

	/*
	 * setOrigin
	 * Sets the start of the route
	 */
	public void setOrigin(String latitude,String longitude)
	{
		try
		{
			origin = latitude+","+longitude;
			//encOrigin =
			URLEncoder.encode(origin,ENCODING);
			encOrigin = origin;
			// Debug
			System.out.println(origin + " -> " + encOrigin);
		} catch (UnsupportedEncodingException ex)
		{
			System.out.println( ex ); System.exit( 1 );
		}
	}
	public void setOrigin(String origin)
	{
		try
		{
			encOrigin = URLEncoder.encode(origin,ENCODING);
		} catch (UnsupportedEncodingException ex)
		{
			System.out.println( ex ); System.exit( 1 );
		}
	}

	/*
	 * setDest
	 * Sets the destination of the route
	 */
	public void setDest(String latitude,String longitude)
	{
		try
		{
			destination = latitude+","+longitude;
			//encDestination =
			URLEncoder.encode(destination,ENCODING);
			encDestination = destination;
		} catch (UnsupportedEncodingException ex)
		{
			System.out.println( ex ); System.exit( 1 );
		}
	}
	public void setDest(String destination)
	{
		try
		{
			encDestination = URLEncoder.encode(destination,"UTF-8");
		} catch (UnsupportedEncodingException ex)
		{
			System.out.println( ex ); System.exit( 1 );
		}
	}

	/* setLang
	 * Set the current language
	 */
	public void setLang(Language language)
	{
		this.language = language;
	}

	/* refreshDirections
	 * Connects to Google and updates route with latest origin and
	 * destination
	 */
	public void refreshDirections()
	{
		// Construct URL
		final String url = (URLBASE
				   +"?origin="	   +encOrigin
				   +"&destination="+encDestination
				   +"&language="   +language.getGoogleCode()
				   +"&region="	   +REGION
				   +"&mode="	   +MODE
				   +"&key="	   +KEY);

		// Get raw direction data
		final byte[] body = {};
		final String[][] headers = {};
		directionsRaw = HttpConnect.httpConnect( METHOD, url, headers, body );
		System.out.println(url);
		//printRaw();

		// Traverse directionsJSON to get array of steps
		directionsJSON = new JSONObject(new String(directionsRaw));
		try
		{
			routes = (JSONArray)directionsJSON.get("routes");
			route  = routes.getJSONObject(0);
			legs   = (JSONArray)route.get("legs");
			leg    = legs.getJSONObject(0);
			steps  = (JSONArray)leg.get("steps");

			// Store steps in directions array
			directions = new Direction[steps.length()];
			for (int i=0; i<steps.length(); i++)
			{
				step = steps.getJSONObject(i);
				directions[i] =
					new Direction(step,language);
			}
			setChanged();
			notifyObservers(directions[directions.length - 1].getCoordinateEnd());
		}
		catch (JSONException e)
		{
			directions = null;
			System.out.println("Could not get directions");
		}
	}

	/* getDirection
	 * Return the next direction in sequence until all directions have
	 * been exhausted or the direction at position i
	 */
	public Direction getDirection()
	{
		if (directions != null &&
			currentDirection<directions.length)
		{
			return directions[currentDirection++];
		}
		else {
			return null;
		}
	}
	public Direction getDirection(int i)
	{
		if (directions != null &&
			i<directions.length && i>=0)
		{
			return directions[i];
		} else {
			return null;
		}
	}

	/* getDirections
	 * Return an array of all directions
	 */
	public Direction[] getDirections()
	{
		return directions;
	}

	/* checkNextDir
	 * Takes the current location as a coordinate object and
	 * returns the closest direction (if it is within the
	 * search distance.
	 */
	public Direction checkNextDir(Coordinate c)
	{
		// Return null if there are no directions
		if (directions == null)
		{ return null; }
		
		// Information about the current closest direction
		double smallest = SEARCHDISTANCE;
		Direction closest = null;
		
		// Loop through directions to find the closest
		for (int i=0; i<directions.length; i++)
		{
			Direction dir = getDirection(i);
			double dist =
				c.distanceTo(dir.getCoordinateStart());
			if (dist < smallest)
			{
				smallest = dist;
				closest  = dir;
			}
		}

		// Debug
		if (closest != null)
		{ System.out.println(closest.getText()); }
		
		// Only return directions within a certain radius
		if (smallest > SPEECHDISTANCE)
		{ return null; }
		
		return closest;
	}

	/* update
	 * Updates location, language or destination
	 */
	public void update(Observable obs, Object obj)
	{
		if (obs instanceof Win7Ublox7 && obj instanceof Coordinate)
		{
			// Get coordinate
			Coordinate c = (Coordinate)obj;

			System.out.println("Coordinates " + c.getLatStr() + ", " + c.getLonStr());

			// Set journey origin
			setOrigin(c.getLatStr(),c.getLonStr());

			// Check if next direction is ready to be spoken
			Direction d = checkNextDir( c );
			if (d != null && d.getRead())
			{
				d.setRead(true);
				mainFrame.saySomething(d.getText());
			}
		}
		else if (obs instanceof SpeechModel && obj instanceof Language)
		{
			language = (Language)obj;
		}
		else if (obs instanceof WhereToFrameModel && obj instanceof String)
		{
			setDest((String)obj);
		}
	}

	/* printRaw
	 * Print full JSON structure for directions
	 */
	public void printRaw()
	{
		System.out.println("Full directions:");
		if (directionsRaw.length == 0)
		{
			System.out.println("No directions");
		} else
		{
			// Print JSON character-by-character
			for (int i=0; i<directionsRaw.length; i++)
			{
				System.out.print( (char) directionsRaw[i]);
			}
		}
	}

	/* printOut
	 * Output directions
	 */
	public void printOut()
	{
		// Print directions, origin and destination
		
		// Debug
		//printRaw();

		if (directions != null)
		{
			for (int i=0; i<directions.length; i++)
			{
				System.out.println(
					getDirection(i).getText() );
			}
		}

		System.out.println("Origin="+origin);
		System.out.println("Destination="+destination);
		System.out.println("Origin="+encOrigin);
		System.out.println("Destination="+encDestination);
	}
}
