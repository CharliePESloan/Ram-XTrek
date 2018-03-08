/*Satellite Software
*
*
* Clyde Udunna 2018.
*/

public class Satellite{
	
	public Satellite(){};
	
	public String[] getPosition(String coordinate){
	    String[] components;
		if(coordinate.startsWith("$GPGLL")){
			String message = coordinate.substring(7);
			components = message.split(",");
			if(components.length != 0){
				return components;
			}else{return null;}
		}
	return null;
	}
	
	public String[] getGLL(String text){
		String [] lines = text.split("\n");
		String[] ifvalid;
		for(int i = 0; i< lines.length; i++){
			ifvalid = getPosition(lines[i]);
			if(ifvalid == null){continue;}
			return ifvalid;
		}
	return null;
	}
		
	public static void main(String[] args){
		Win7Ublox7 uBlox = new Win7Ublox7();
		Thread thread1 = new Thread(uBlox);
		thread1.start();
	}
}

