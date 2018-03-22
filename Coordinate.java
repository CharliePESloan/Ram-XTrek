/* Coordinate
 * Clyde Udunna 2018
 */

public class Coordinate {
	
	final static int EARTHRADIUSKM = 6371;

	double lat = 0.0;
	double lon = 0.0;
	double velocity = 0.0;
	double[] coord;
	int rotation;
	
	public Coordinate(double lat, double lon){
		this.lat = lat;
		this.lon = lon;
	}
		
	public Coordinate(String[] coordinates, String[] trips, String[] bearings){
		double latitude = mapFormat(coordinates[0]);
		if(coordinates[1].equals("S")){lat = -latitude;}
		else{lat = latitude;}
		
		double longitude = mapFormat(coordinates[2]);
		if(coordinates[3].equals("W")){lon = -longitude;}
		else{lon = longitude;}
		
		velocity = tripFormat(trips[6]);
		
		rotation = bearingFormat(bearings[5]);
		
		coord = new double[] {lat, lon};
	}
	
	public double mapFormat(String coordinate){
		double number = Double.parseDouble(coordinate);
		double finalValue = number/100.0;
		return finalValue;
	}
	
	public double tripFormat(String mySpeed){
		double finalValue = Double.parseDouble(mySpeed);
		return finalValue;
	}
	
	public int bearingFormat(String myBearing){
		int finalValue = Integer.parseInt(myBearing);
		return finalValue;
	}
	
	private static double degreesToRadians(double degrees){
		return (degrees * (double)Math.PI) / 180;
	}

	public static double between(double latitude1,
				    double longitude1,
				    double latitude2,
				    double longitude2){
						
		double		lat1 = (double)latitude1;
		final double	lon1 = (double)longitude1;
		double		lat2 = (double)latitude2;
		final double	lon2 = (double)longitude2;

		double latD = degreesToRadians(lat2-lat1);
		double lonD = degreesToRadians(lon2-lon1);

		lat1 = degreesToRadians(lat1);
		lat2 = degreesToRadians(lat2);

		double a = Math.sin(latD/2) * Math.sin(latD/2) +
			 Math.sin(lonD/2) * Math.sin(lonD/2) *
			 Math.cos(lat1)   * Math.cos(lat2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		return (double)EARTHRADIUSKM * c;
	}

	public double distanceTo(Coordinate coordinate){
		final int	EARTHRADIUSKM = 6371;
		
		double	lat1 = lat;
		final double lon1 = lon;
		double		lat2 = coordinate.getLat();
		final double	lon2 = coordinate.getLon();

		double latD = degreesToRadians(lat2-lat1);
		double lonD = degreesToRadians(lon2-lon1);

		lat1 = degreesToRadians(lat1);
		lat2 = degreesToRadians(lat2);

		double a = Math.sin(latD/2) * Math.sin(latD/2) +
			  Math.sin(lonD/2) * Math.sin(lonD/2) *
			  Math.cos(lat1)   * Math.cos(lat2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		return (double)EARTHRADIUSKM * c;
	}
	
	public double[] getPos(){
		return coord;
	}

	public double getLat(){
		return lat;
	}
	
	public double getLon(){
		return lon;
	}

	public String getLatStr(){
		return Double.toString(lat);
	}
	
	public String getLonStr(){
		return Double.toString(lon);
	}
	
	public int getRotation(){
		return rotation;
	}
}
