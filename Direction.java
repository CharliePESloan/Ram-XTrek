import org.json.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/*
 * Direction
 * Charlie Sloan (2018)
 *
 * Stores information about a specfici direction such as its text and
 * coordinates
 */

public class Direction
{
	private String text;
	private float latStart;
	private float lonStart;
	private float latEnd;
	private float lonEnd;

	public Direction(JSONObject direction)
	{
		JSONObject jObject;
		String distanceStr;

		JObject = direction.getJSONObject("distance"); 

		distanceStr = JObject.getString("text");

		JObject = direction.getJSONObject("start_location");

		/*/ Read distances larger than 1000m in km
		if (distance.getInt("value") >= 1000)
		{distanceStr = String.format("In %.1f kilometers ",
			       (float)distance.getInt("value") / 1000);
		}
		else{
		distanceStr = String.format("In %d meters ",
			      distance.getInt("value"));
		 }*/

		String   html = direction.getString("html_instructions");
		Document doc = Jsoup.parse(html);
		text = distanceStr + " " + doc.text();
	}

	public String getText()
	{
		return text;
	}

	public float getLat1()
	{
		return latStart;
	}
	public float getLon1()
	{
		return lonStart;
	}
	public float getLat2()
	{
		return latEnd;
	}
	public float getLon2()
	{
		return lonEnd;
	}
	
	private float degreesToRadians(float degrees)
	{
		return (degrees * (float)Math.PI) / 180;
	}

	public double distanceTo(float latitude,float longitude)
	{
		float lat1 = this.latitude;
		final float lon1 = this.longitude;
		float lat2 = latitude;
		final float lon2 = longitude;
		final int   earthRadiusKm = 6371;

		float dLat = degreesToRadians(lat2-lat1);
		float dLon = degreesToRadians(lon2-lon1);

		lat1 = degreesToRadians(lat1);
		lat2 = degreesToRadians(lat2);

		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
			  Math.sin(dLon/2) * Math.sin(dLon/2) *
			  Math.cos(lat1)   * Math.cos(lat2); 
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		return (double)earthRadiusKm * c;
	}
}
