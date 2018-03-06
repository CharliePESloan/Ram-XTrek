import org.json.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Direction
{
	private String text;
	private float latitude;
	private float longitude;

	public Direction(JSONObject direction)
	{
		JSONObject distance;
		String distanceStr;

		distance = direction.getJSONObject("distance"); 

		distanceStr = distance.getString("text");
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
		text = distanceStr + doc.text();
	}

	public String getText()
	{
		return text;
	}

	public float distanceTo(float latitude,float longitude)
	{
		return this.latitude;
	}
}
