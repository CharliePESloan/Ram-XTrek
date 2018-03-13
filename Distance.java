public class Distance
{
	private static float degreesToRadians(float degrees)
	{
		return (degrees * (float)Math.PI) / 180;
	}

	public static double distanceTo(double latitude1,
					double longitude1,
					double latitude2,
					double longitude2)
	{
		final int	EARTHRADIUSKM = 6371;
		
		float		lat1 = latitude1;
		final float	lon1 = longitude1;
		float		lat2 = latitude2;
		final float	lon2 = longitude2;

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
