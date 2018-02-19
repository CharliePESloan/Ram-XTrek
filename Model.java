import java.util.Observable;
/*
 * Model.
 *
 * David Wakeling 2018.
 */
public class Model extends Observable {	
  private int zoomer; 

  public Model(int zoomer ) {
	this.zoomer = zoomer; 
    setChanged(); notifyObservers(zoomer);
  }

  public void zoomIn() {
	zoomer = zoomer + 1;
	setChanged(); notifyObservers(zoomer); 
  }
  
  public void zoomOut(){
	 zoomer = zoomer - 1;  
	 setChanged(); notifyObservers(zoomer); 
	  
  }
}
