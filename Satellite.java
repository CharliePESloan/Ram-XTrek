/*Satellite Software
*
*
* Clyde Udunna 2018.
*/

/*This is my satellite class it takes care of parsing the information
*received from the Dongle and transforming it into a more readable format
*which gives the user the longitude and the latitude of their current position*/
public class Satellite{
	
	public Satellite(){};
	
	/*The get position method gets a line passed to it,
	*it checks whether the line passed to it is a GLL sentence that 
	*contains the coordinates we are looking for. If so the 
	*information wanted gets stored in an array for later use.*/
	public String[] getPosition(String coordinate){
	    String[] components; // Array in which values are stored.
		if(coordinate.startsWith("$GPGLL")){
			String message = coordinate.substring(7);
			components = message.split(",");
			if(components.length != 0){
				return components;
			}else{return null;}
		}
	return null;
	}
	
	/* This method scans every line passed in by the dongle 
	*and used getPosition(), to check whether a valid line has been
	*found, if so the array with the contents is returned to be passed 
	*onto the rest of the program*/
	
	public String[] getGLL(String text){
		String [] lines = text.split("\n");
		String[] ifvalid;
		for(int i = 0; i< lines.length; i++){
			ifvalid = getPosition(lines[i]);
			if(ifvalid == null || ifvalid[0].equals("")){continue;}
			return ifvalid;
		}
	return null;
	}
	
	/*Method that returns the speed from the sentences displayed by the Satellite*/
	public String[] getSpeedDistance(String line){
		String[] logistics;
		if(line.startsWith("$GPVTG")){
			String message = line.substring(7);
			logistics = message.split(",");
			if(logistics.length != 0){
				return logistics;
			}else{return null;}
		}
	return null;
	}
	
	/*Scan for sentence that contains speed*/
	public String[] getVTG(String text){
		String[] lines = text.split("\n");
		String[] ifvalid;
		for(int i = 0; i< lines.length; i++){
			ifvalid = getSpeedDistance(lines[i]);
			if(ifvalid == null){continue;}
			return ifvalid;
		}
	return null;
	}
	
	/*Method returns the Azimuth, the bearing from the Earth's 
	 *true North, used to change the orientation of the Map*/
	public String[] getAzimuth(String line){
		String[] bearings;
		if(line.startsWith("$GPGSV")){
			String message = line.substring(7);
			bearings = message.split(",");
			if(bearings.length != 0){
				return bearings;
			}else{return null;}
		}
	return null;
	}
	
	/*Scan for line that contains the bearing from the True North*/
	public String[] getGSV(String text){
		String[] lines = text.split("\n");
		String[] ifvalid;
		for(int i = 0; i<lines.length; i++){
			ifvalid = getAzimuth(lines[i]);
			if(ifvalid == null){continue;}
			return ifvalid;
		}
	return null;
	}
}

