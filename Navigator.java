import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import org.json.*;

/*
 * Navigator
 * Charlie Sloan 2018 (adapted from code by David Wakeling)
 *
 * Gets directions from the Google Maps api and parses them into
 * a usable format
 */

public class Navigator
{
	/* Constant Values */
	final static String URLBASE =
		"https://maps.googleapis.com/maps/api/directions/json";
	final static String KEY	=
		"AIzaSyB9Ky-AaEebyjqE7f8-kBmXEMJL69Cr_Yk";
	final static String METHOD = "GET";
	final static String REGION = "uk";
	final static String MODE   = "TRANSIT";
	final static String ENCODING = "UTF-8";

	/* Variables */
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
	public Navigator()
	{
		currentDirection=0;
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
			//this.destination = destination;
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
			directions[i] = new Direction(step);

		}

	}

	/* getDirection
	 * Return the next direction in sequence until all directions have
	 * been exhausted or the direction at position i
	 */
	public String getDirection()
	{
		if (currentDirection<directions.length)
		{
			return directions[currentDirection++].getText();
		} else {
			return "You have reached your destination";
		}
	}	
	public String getDirection(int i)
	{
		if (i<directions.length && i>=0)
		{
			return directions[i].getText();
		} else {
			return language.getDestinationText();
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
			System.out.println( getDirection(i) );
		}
		System.out.println("Destination="+destination);
	}

	// Not yet implemented
	public void getClosestNode(float latitude, float longitude)
	{
		for (int i=0; i<directions.length; i++)
		{
			System.out.println( getDirection(i) );
			
		}
	}

	public static void main(String args[])
	{
		Navigator myDir = new Navigator();
		
		//myDir.setOrigin("Exeter");
		myDir.setOrigin	(50.729042f, -3.531057f);
		myDir.setDest	(50.742957f, -3.348418f);
		//myDir.setDest("Bath");
		
		//myDir.setLang(new Language("French","fr-FR"));
		
		myDir.refreshDirections();
		myDir.printOut();

		myDir.getClosestNode(50.729042f,-3.531057f);

		Speaker.saySomething(myDir.getDirection(),
				     new Language("en"));
	}
}
