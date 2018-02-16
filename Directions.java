import java.net.URLEncoder;

public class Directions
{
	/* Constant Values */
	final static String URLBASE = "https://maps.googleapis.com/maps/api/directions/json";
	final static String method = "GET";

	/* Navigation Variables */
	private      String origin;
	private      String destination;
	private      String region;
	private      String mode;

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
		origin = String.valueOf(latitude)+","+String.valueOf(longitude);
	}
	
	/*
	 * setDest
	 * Sets the destination of the route
	 */
	public void setDest(float latitude,float longitude)
	{
		destination = String.valueOf(latitude)+","+String.valueOf(longitude);
	}

	static void refreshDirections()
	{
		try
		{
			final String encOrigin = URLEncoder.encode(origin,"UTF-8");
			final String encDestination = URLEncoder.encode(destination,"UTF-8");
			
			final String url = (URLBASE
					   +"?origin="	   +encOrigin
					   +"&destination="+encDestination
					   +"&region="	   +region)
		} catch (Exception ex)
		{
			System.out.println( ex ); System.exit( 1 );
	}

	public void PrintOut()
	{
		System.out.println("Origin="+origin);
		System.out.println("Destination="+destination);
	}
}
