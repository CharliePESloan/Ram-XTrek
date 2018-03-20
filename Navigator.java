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

public class Navigator implements Observer
{
	/* Constant Values */
	final static int SEARCHDISTANCE = 10000;
	final static float SPEECHDISTANCE = 0.01f;
	final static String URLBASE =
		"https://maps.googleapis.com/maps/api/directions/json";
	final static String KEY	=
		"AIzaSyB9Ky-AaEebyjqE7f8-kBmXEMJL69Cr_Yk";
	final static String METHOD = "GET";
	final static String REGION = "uk";
	final static String MODE   = "TRANSIT";
	final static String ENCODING = "UTF-8";

	/* Variables */
	private SpeechModel speech;
	private SatelliteModel  satellite;
	private WhereToFrameModel whereTo;
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
	public Navigator(SpeechModel speechModel,SatelliteModel satModel,WhereToFrameModel whereModel)
	{
		currentDirection=0;

		speech = speechModel;
		whereTo = whereModel;
		satellite = satModel;
		speech.addObserver(this);
		whereTo.addObserver(this);
		satellite.addObserver(this);
	}

	/*
	 * setOrigin
	 * Sets the start of the route
	 */
	public void setOrigin(float latitude,float longitude)
	{
		try
		{
			origin = String.valueOf(latitude)+","+String.valueOf(longitude);
			encOrigin = URLEncoder.encode(origin,ENCODING);
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
	public void setDest(float latitude,float longitude)
	{
		try
		{
			destination = String.valueOf(latitude)+","+String.valueOf(longitude);
			encDestination = URLEncoder.encode(destination,ENCODING);
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

		// Traverse directionsJSON to get array of steps
		directionsJSON = new JSONObject(new String(directionsRaw));
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
			directions[i] = new Direction(step,language);

		}

	}

	/* getDirection
	 * Return the next direction in sequence until all directions have
	 * been exhausted or the direction at position i
	 */
	public Direction getDirection()
	{
		if (currentDirection<directions.length)
		{
			return directions[currentDirection++];
		} else {
			return null;
		}
	}
	public Direction getDirection(int i)
	{
		if (i<directions.length && i>=0)
		{
			return directions[i];
		} else {
			return null;
			//return language.getDestinationText();
		}
	}

	/* getDirections
	 * Return an array of all directions
	 */
	public Direction[] getDirections()
	{
		return directions;
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
		printRaw();

		// Print origin, directions and destination
		System.out.println("Origin="+origin);
		for (int i=0; i<directions.length; i++)
		{
			System.out.println( getDirection(i).getText() );
		}
		System.out.println("Destination="+destination);
	}

	// Not yet implemented
	public void getClosestNode(float latitude, float longitude)
	{
		for (int i=0; i<directions.length; i++)
		{
			System.out.println( getDirection(i).getText() );

		}
	}
	// Not yet implemented
	public Direction checkNextDir(Coordinate c)
	{
		double smallest = SEARCHDISTANCE;
		Direction closest = null;
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
		if (smallest > SPEECHDISTANCE)
		{
			return null;
		}
		return closest;
	}

	public void update(Observable obs, Object obj)
	{
		/* if (obj instanceof String[])
		{
			String[] arr = (String[])obj;
			System.out.println((arr));
			float lat =
				(arr[1]=="N" ? 1 : -1) * (float)arr[0];
			float lon =
				(arr[3]=="N" ? 1 : -1) * (float)arr[2];
			System.out.println(lat);
			System.out.println(lon);
		}
		else */
		if (obs == satellite && obj instanceof Coordinate)
		{
			Direction d = checkNextDir( (Coordinate)obj );
			if (d != null)
			{
				Speaker.saySomething(d.getText(),language);
			}
		}
		else if (obs == speech && obj instanceof Language)
		{
			language = (Language)obj;
		}
		else if (obs == whereTo && obj instanceof String)
		{
				setDest((String)obj);
		}
	}

	/*public static void main(String args[])
	{
		Navigator myDir = new Navigator();

		//myDir.setOrigin	(50.729042f, -3.531057f);
		//myDir.setDest	(50.742957f, -3.348418f);
		myDir.setOrigin("Exeter");
		myDir.setDest("Glasgow");

		Language lang = new Language("French","fr");
		//Language lang = new Language("English","en");

		myDir.setLang(lang);

		myDir.refreshDirections();
		myDir.printOut();

		//myDir.getClosestNode(50.729042f,-3.531057f);

		Speaker.saySomething(myDir.getDirection(24).getText(),
				    		 lang);
	}*/
}
