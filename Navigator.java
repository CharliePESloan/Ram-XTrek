import java.net.URLEncoder;
import org.json.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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
	final static Speaker mySpeaker = new Speaker();

	/* Navigation Variables */
	private	String		origin;
	private	String		destination;

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
	private JSONObject	distance;
	private String		distanceStr;
	private String		html;
	private Document	doc;

	private int		currentDirection;
	private String	directionsStr[];


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
		//origin = String.valueOf(latitude)+","+String.valueOf(longitude);
		origin = String.valueOf(latitude)+","+String.valueOf(longitude);
		encOrigin = URLEncoder.encode(origin,"UTF-8");
	}
	public void setOrigin(String origin)
	{
		this.origin = origin;
		encOrigin = URLEncoder.encode(origin,"UTF-8");
	}

	/*
	 * setDest
	 * Sets the destination of the route
	 */
	public void setDest(float latitude,float longitude)
	{
		destination = String.valueOf(latitude)+","+String.valueOf(longitude);
	}
	public void setDest(String newDest)
	{
		this.destination = destination;
	}

	/* setLang
	 * Set the current language
	 */
	public void setLang(String language)
	{
		this.language = language
	}

	/* refreshdirectionsStr
	 * Connects to Google and updates route with latest origin and destination
	 */
	public void refreshdirections()
	{
		try
		{
			System.out.println("Attempting to fetch directions");
			final String encDestination =	URLEncoder.encode(destination,"UTF-8");

			final String url = (URLBASE
					   +"?origin="	   +encOrigin
					   +"&destination="+encDestination
					   +"&language="   +language
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

			//
			directionsStr = new String[steps.length()];
			for (int i=0; i<steps.length(); i++)
			{
				step = steps.getJSONObject(i);

				distance = step.getJSONObject("distance");

				// Read distances larger than 1000m in km
				if (distance.getInt("value") >= 1000)
				{
					distanceStr = String.format("In %.1f kilometers ",
												(float)distance.getInt("value") / 1000);
				}
				else
				{
					distanceStr = String.format("In %d meters ",
												distance.getInt("value"));
				}

				html = step.getString("html_instructions");
				doc = Jsoup.parse(html);
				directionsStr[i] = distanceStr + doc.text();
			}

		} catch (Exception ex)
		{
			System.out.println( ex ); System.exit( 1 );
		}
	}

	/* getDirection
	 * Return the next direction in sequence until all directions have
	 * been exhausted
	 */
	public String getDirection()
	{
		if (currentDirection<directionsStr.length)
		{
			mySpeaker.saySomething(directionsStr[currentDirection]);
			return directionsStr[currentDirection++];
		} else {
			return "You have reached your destination";
		}
	}

	/* getdirectionsStr
	 * Return a string array containing most recent instructions
	 */
	public String[] getdirectionsStr()
	{
		return directionsStr;
	}

	/* printOut
	 * Output directions
	 */
	public void printOut()
	{
		// Temporary debug stuff
		if (false)
		{
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
		//myDir.setOrigin	(50.7236f, -3.52751f);
		//myDir.setDest	(51.3758f,  2.3599f);
		myDir.setOrigin("Exeter");
		myDir.setDest("Bath");
		myDir.refreshdirections();
		myDir.printOut();

		while (myDir.getDirection()!="You have reached your destination")
		{}
	}
}
