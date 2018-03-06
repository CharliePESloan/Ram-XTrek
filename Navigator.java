import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import org.json.*;

/*
 * Navigator
 *
 * Charlie Sloan 2018 (adapted from code by David Wakeling)
 */

public class Navigator
{
	/* Constant Values */
	final static String URLBASE = "https://maps.googleapis.com/maps/api/directions/json";
	final static String KEY	    = "AIzaSyB9Ky-AaEebyjqE7f8-kBmXEMJL69Cr_Yk";
	final static String METHOD  = "GET";
	final static String REGION  = "uk";
	final static String MODE    = "TRANSIT";
	private      Language language     = new Language("English", "en-GB");
	//final static Speaker mySpeaker = new Speaker();

	/* Navigation Variables */
	private	String		origin;
	private	String		destination;
	private	String		encOrigin;
	private	String		encDestination;

	private Direction[]	directions;
	private	byte[]		directionsRaw = {};
	private JSONObject	directionsJSON;

	// Variables for traversing directionsJSON
	private JSONArray	routes;
	private JSONObject	route;
	private JSONArray	legs;
	private JSONObject	leg;
	private JSONArray	steps;

	// Variables for traversing individual steps
	private JSONObject	step;

	private int		currentDirection;
	private String		directionsStr[];


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
			encOrigin = URLEncoder.encode(origin,"UTF-8");
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
			//this.origin = origin;
			encOrigin = URLEncoder.encode(origin,"UTF-8");
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
			encDestination = URLEncoder.encode(destination,"UTF-8");
			//encDestination = destination;
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

	public void getClosestNode(float latitude, float longitude)
	{
		
	}

	/* refreshdirectionsStr
	 * Connects to Google and updates route with latest origin and destination
	 */
	public void refreshdirections()
	{
		System.out.println("Attempting to fetch directions");
		//final String encDestination =	URLEncoder.encode(destination,"UTF-8");

		final String url = (URLBASE
				   +"?origin="	   +encOrigin
				   +"&destination="+encDestination
				   +"&language="   +language.getGoogleCode()
				   +"&region="	   +REGION
				   +"&mode="	   +MODE
				   +"&key="	   +KEY);
		System.out.println(url);

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

		//directionsStr = new String[steps.length()];
		directions = new Direction[steps.length()];
		for (int i=0; i<steps.length(); i++)
		{
			step = steps.getJSONObject(i);

			directions[i] = new Direction(step);

		}

	}

	/* getDirection
	 * Return the next direction in sequence until all directions have
	 * been exhausted
	 */
	public String getDirection()
	{
		if (currentDirection<directions.length)
		{
			Speaker.saySomething(directions[currentDirection].getText(),language.getBingCode(),language.getArtist());
			return directions[currentDirection++].getText();
		} else {
			return "You have reached your destination";
		}
	}
	
	public String getDirection(int i)
	{
		if (i<directions.length && i>0)
		{
			Speaker.saySomething(directions[i].getText(),language.getBingCode(),language.getArtist());
			return directions[i].getText();
		} else {
			return language.getDestinationText();
		}
	}

	/* getdirectionsStr
	 * Return a string array containing most recent instructions
	 */
	public String[] getdirectionsStr()
	{
		return directionsStr;
	}

	public void printRaw()
	{
		// Temporary debug stuff
		System.out.println("Full directionsStr:");
		if (directionsRaw.length == 0)
		{
			System.out.println("No directionsStr");
		} else
		{
			for (int i=0; i < directionsRaw.length; i++)
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
		//printRaw();

		// Print origin, directions and destination
		System.out.println("Origin="+origin);
		for (String d : directionsStr)
		{
			System.out.println(d);
		}
		System.out.println("Destination="+destination);
	}

	public static void main(String args[])
	{
		Navigator myDir = new Navigator();
		
		//myDir.setOrigin("Exeter");
		//myDir.setDest	(51.3758f,  2.3599f);
		//myDir.setOrigin("Bath");
		myDir.setOrigin	(50.729042f, -3.531057f);
		myDir.setDest	(50.742957f, -3.348418f);
		//myDir.setDest("Bath");
		
		myDir.setLang(new Language("French","fr-FR"));
		
		myDir.refreshdirections();
		myDir.printOut();

		//while (myDir.getDirection()!="You have reached your destination")
		//{}
		myDir.getDirection();
	}
}
