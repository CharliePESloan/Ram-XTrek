import org.json.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.Map;
import java.util.HashMap;

/*
 * Direction
 * Charlie Sloan (2018)
 *
 * Stores information about a specfici direction such as its text and
 * coordinates
 */

public class Direction
{
	/* Variables */
	private final String text;
	private final double latStart;
	private final double lngStart;
	private final double latEnd;
	private final double lngEnd;
	private final Coordinate cStart;
	private final Coordinate cEnd;

	private boolean read;

	/* Set up word replacements */
	private static final Map<String, String> REPLACERS =
		createMap();
	private static Map<String,String> createMap()
	{
		Map<String, String> map = new HashMap<>();
		map.put(" N ", "North ");
		map.put(" E "," East ");
		map.put(" S "," South ");
		map.put(" W "," West ");

		return map;
	}

	/* Constructor */
	public Direction(JSONObject direction, Language lang)
	{
		JSONObject distance;
		JSONObject location;
		String	   distanceStr;
		int	   distanceInt;

		distance = direction.getJSONObject("distance");
		distanceInt = distance.getInt("value");

		//distanceStr = JObject.getString("text");

		/* Read distances larger than 1000m in km */
		if (distanceInt >= 1000)
		{
			distanceStr = String.format(lang.getKilometerText(),
						    (float)distanceInt / 1000);
		}
		else{
			distanceStr = String.format(lang.getMeterText(),
						    distanceInt);
		}
		
		location = direction.getJSONObject("start_location");
		latStart = location.getDouble("lat");
		lngStart = location.getDouble("lng");
		cStart	 = new Coordinate(latStart,lngStart);

		location = direction.getJSONObject("end_location");
		latEnd	 = location.getDouble("lat");
		lngEnd	 = location.getDouble("lng");
		cEnd	 = new Coordinate(latEnd,lngEnd);

		String   html = direction.getString("html_instructions");
		Document doc = Jsoup.parse(html);
		text = distanceStr + " " + doc.text();
		for (Map.Entry<String, String> entry : REPLACERS.entrySet())
		{
			text = text.replaceAll( entry.getKey(),
						entry.getValue() );
		}
	}

	public String getText()
	{
		return text;
	}

	public double getLat1()
	{
		return latStart;
	}
	public double getLon1()
	{
		return lngStart;
	}
	public double getLat2()
	{
		return latEnd;
	}
	public double getLon2()
	{
		return lngEnd;
	}
	public Coordinate getCoordinateStart()
	{
		return cStart;
	}
	public Coordinate getCoordinateEnd()
	{
		return cEnd;
	}

	public boolean getRead()
	{
		return read;
	}
	public void setRead(boolean read)
	{
		this.read = read;
	}

	private float degreesToRadians(float degrees)
	{
		return (degrees * (float)Math.PI) / 180;
	}

	public double distanceTo(float latitude,float longitude)
	{
		return Distance.between(this.latStart,this.lngStart,
					latitude,     longitude);
	}
	public double distanceTo(Coordinate c)
	{
		return cStart.distanceTo(c);
	}
}
