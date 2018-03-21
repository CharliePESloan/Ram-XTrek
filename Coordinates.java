//5043.94335,N,00330.91606,W

/*Coordinates 
 *Clyde Udunna 2018
 */
import java.lang.Math;
 
public class Coordinates{

	double lat = 0.0;
	double	lon = 0.0;
	double[] coord;
	
	public Coordinates(String[] coordinates){
		double latitude = convertToDegrees(coordinates[0]);
		if(coordinates[1].equals("S")){lat = -latitude;}
		else{lat = latitude;}
		
		double longitude = convertToDegrees(coordinates[2]);
		if(coordinates[3].equals("W")){lon = -longitude;}
		else{lon = longitude;}
		
		coord = new double[] {lat, lon};
	}
		
	public double convertToDegrees(String coordinate){
		double number = Double.parseDouble(coordinate);
		number = number/100.0;
		double finalValue = Math.round(number*10000.0) / 10000.0;
		return finalValue;
	}
	
	public static void main(String[] args){
		Coordinates testCase = new Coordinates(new String[] {"5043.94335","N","00330.91606","W"});
		System.out.println(testCase.coord[0]);
		System.out.println(testCase.coord[1]);
	}
}
