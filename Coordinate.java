//5043.94335,N,00330.91606,W

public class Coordinate {

	long	lat;
	long	lon;
	long[]	coord;
		
	public Coordinate(String[] coordinates)
	{
		lat = Long.parseLong(coordinates[0]);
		if( coordinates[1].equals("S") )
		{
			lat = -lat;
		}

		lon = Long.parseLong(coordinates[2]);
		if( coordinates[3].equals("W") )
		{
			lon = -lon;
		}

		coord = new long[]{lat, lon};
	}
		
	private static float degreesToRadians(float degrees)
	{
		return (degrees * (float)Math.PI) / 180;
	}

	public static double between(double latitude1,
				     double longitude1,
				     double latitude2,
				     double longitude2)
	{
		final int	EARTHRADIUSKM = 6371;
		
		float		lat1 = (float)latitude1;
		final float	lon1 = (float)longitude1;
		float		lat2 = (float)latitude2;
		final float	lon2 = (float)longitude2;

		float latD = degreesToRadians(lat2-lat1);
		float lonD = degreesToRadians(lon2-lon1);

		lat1 = degreesToRadians(lat1);
		lat2 = degreesToRadians(lat2);

		double a = Math.sin(latD/2) * Math.sin(latD/2) +
			  Math.sin(lonD/2) * Math.sin(lonD/2) *
			  Math.cos(lat1)   * Math.cos(lat2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		return (double)EARTHRADIUSKM * c;
	}

	public double distanceTo(Coordinate c)
	{
		final int	EARTHRADIUSKM = 6371;
		
		float		lat1 = lat;
		final float	lon1 = lon;
		float		lat2 = c.getLat();
		final float	lon2 = c.getLon();

		float latD = degreesToRadians(lat2-lat1);
		float lonD = degreesToRadians(lon2-lon1);

		lat1 = degreesToRadians(lat1);
		lat2 = degreesToRadians(lat2);

		double a = Math.sin(latD/2) * Math.sin(latD/2) +
			  Math.sin(lonD/2) * Math.sin(lonD/2) *
			  Math.cos(lat1)   * Math.cos(lat2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		return (double)EARTHRADIUSKM * c;
	}
	
	public long[] getPos()
	{
		return coord;
	}

	public long getLat()
	{
		return lat;
	}
	public long getLon()
	{
		return lon;
	}

	public String getLatStr()
	{
		return Long.toString(lat);
	}
	public String getLonStr()
	{
		return Long.toString(lon);
	}
}
