//5043.94335,N,00330.91606,W

public class Coordinates{

	long	lat;
	long	lon;
	long[]	coord = {lat, lon};
		
	public Coordinate(String[] coordinates)
	{
		lat = Long.parseLong(coordinates[0])
		if(coordinates[1].equals("S"){lat = -lat;};
		lon = Long.parseLong(coordinates[2])
		if(coordinates[3].equals("W"){lon = -lon;}
		coord = {lat, lon};
	}
	
	static long[] getPos()
	{
		return coord;
	}
	
	
}
