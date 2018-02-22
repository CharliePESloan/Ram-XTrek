public class Satellite{
	
	public static void main(String[] args){
		Satellite mySat = new Satellite();
		mySat.getGLL("$GPGLL,5043.94335,N,00330.91606,W,111950.00,A,A*74\n$GPGLL,5043.94335,N,00330.91606,W,111950.00,A,A*74\n");
	}
	
	public Satellite(){};
	
	public void getPosition(String coordinate){
		if(coordinate.startsWith("$GPGLL")){
			String message = coordinate.substring(7);
			String[] components = message.split(",");
			for(int i = 0; i < 4; i++){
				System.out.println(components[i]);
			}
		}
	}
	
	public void getGLL(String text){
		String [] lines = text.split("\n");
		for(int i =0; i<lines.length; i++){
			getPosition(lines[i]);
		}
	}
	
}

/*variable stored in the class which correspond 
*to the GLL Message components, may or may not use them.*/
class Position{
	public float latitude = 0.0f;
	public String latDir = null;
	public float longitude = 0.0f;
	public String longDir = null;
}