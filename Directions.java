import java.net.URLEncoder;

public class Directions
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
	private	byte[] rawDirections = {};
	//private      String region;

	/*
	 * Constructor
	 */
	public Directions()
	{}

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
			
			rawDirections = HttpConnect.httpConnect( METHOD, url, headers, body );
			System.out.println(rawDirections);
		
		} catch (Exception ex)
		{
			System.out.println( ex ); System.exit( 1 );
		}
	}

	public void printOut()
	{
		System.out.println("Origin="+origin);
		System.out.println("Destination="+destination);
		System.out.println("Directions:");
		if (rawDirections.length == 0)
		{
			System.out.println("No directions");
		} else
		{
			for (int i=0; i < rawDirections.length; i++)
			{
				System.out.print( (char) rawDirections[i]);
			}
		}
	}

	public static void main(String args[])
	{
		Directions myDir = new Directions();
		//myDir.setOrigin	(50.7236f, -3.52751f);
		//myDir.setDest	(51.3758f,  2.3599f);
		myDir.setOrigin("Exeter");
		myDir.setDest("Bath");
		myDir.refreshDirections();
		myDir.printOut();
	}
}
