import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Direction
{
	private String directionText;
	private float latitude;
	private float longitude;

	private JSONObject	distance;
	private String		distanceStr;
	private String		html;
	private Document	doc;


	public Direction(JSONObject direction)
	{
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
		directionText = distanceStr + doc.text();
	}

	public float distanceTo(float latitude,float longitude)
	{
		this
	}
}
