//5043.94335,N,00330.91606,W

public class Coordinates{
	
	static long[] latLon(String[] coordinates){
		long lat = Long.parseLong(coordinates[0])
		if(coordinates[1].equals("S"){lat = -lat;};
		long lon = Long.parseLong(coordinates[2])
		if(coordinates[3].equals("W"){lon = -lon;}
		long[] coord = {lat, lon}
		return coord;
	}
	
	
}