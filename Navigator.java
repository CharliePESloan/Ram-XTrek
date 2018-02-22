import java.net.URLEncoder;
import org.json.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Navigator
{
	/* Constant Values */
	final static String URLBASE = "https://maps.googleapis.com/maps/api/directions/json";
	final static String KEY	    = "AIzaSyB9Ky-AaEebyjqE7f8-kBmXEMJL69Cr_Yk";
	final static String METHOD  = "GET";
	final static String REGION  = "uk";
	final static String MODE    = "TRANSIT";

	/* Navigation Variables */
	private	String origin;
	private	String destination;
	private JSONObject myJSON;
	private	byte[] directionsRaw = {};
	private JSONObject directionsJSON;

	private JSONArray  routes;
	private JSONObject route;
	private JSONArray  legs;
	private JSONObject leg;
	private JSONArray  steps;

	private JSONObject step;
	private String	   html;
	private Document   doc;

	private int	   currentDirection;
	private String	   directions[];


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
	}
	public void setOrigin(String newOrigin)
	{
		origin = newOrigin;
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
		destination = newDest;
	}

	/* refreshDirections
	 * Connects to Google and updates route with latest origin and destination
	 */
	public void refreshDirections()
	{
		try
		{
			System.out.println("Attempting to fetch directions");
			final String encOrigin =	URLEncoder.encode(origin,"UTF-8");
			final String encDestination =	URLEncoder.encode(destination,"UTF-8");

			final String url = (URLBASE
					   +"?origin="	   +encOrigin
					   +"&destination="+encDestination
					   +"&region="	   +REGION
					   +"&mode="	   +MODE
					   +"&key="	   +KEY);
			System.out.println(url);

			final byte[] body = {};
			final String[][] headers = {};

			directionsRaw = HttpConnect.httpConnect( METHOD, url, headers, body );
			directionsJSON = new JSONObject(new String(directionsRaw));
			routes = (JSONArray)directionsJSON.get("routes");
			route  = routes.getJSONObject(0);
			legs   = (JSONArray)route.get("legs");
			leg    = legs.getJSONObject(0);
			steps  = (JSONArray)leg.get("steps");

			directions = new String[steps.length()];

			for (int i=0; i<steps.length(); i++)
			{
				step = steps.getJSONObject(i);
				html = (String)step.get("html_instructions");
				doc = Jsoup.parse(html);
				directions[i] = doc.text();
				//System.out.println(doc.text());
				//System.out.println(step.get("html_instructions"));
			}

		} catch (Exception ex)
		{
			System.out.println( ex ); System.exit( 1 );
		}
	}

	public String getDirection()
	{
		if (currentDirection<directions.length)
		{
			return directions[currentDirection++];
		} else {
			return "You have reached your destination";
		}
	}

	public void printOut()
	{
		if (false)
		{
			System.out.println("Full Directions:");
			if (directionsRaw.length == 0)
			{
				System.out.println("No directions");
			} else
			{
				for (int i=0; i < directionsRaw.length; i++)
				{
					System.out.print( (char) directionsRaw[i]);
				}
			}
		}

		System.out.println("Origin="+origin);
		for (String d : directions)
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
		myDir.refreshDirections();
		myDir.printOut();
	}
}
