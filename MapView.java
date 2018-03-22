import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.*;
import java.awt.geom.AffineTransform; 
import java.lang.Math; 

/*
 * View.
 * Class controls what is displayed on the screen. 
 *
 * @ author Devash Patel 2018.
 * Code adapted from that produced by David Wakeling
 */
class MapView extends JPanel implements Observer {
 
  BufferedImage mapImage; 
  
  private int rotation = 0; 
  private int centreCoord = 0;  
  private int xCentreCoord = 127; 
  private int yCentreCoord = 145; 
  private int circleSize = 15; 
  private int HYP; 
  
  
  
  public int getScreenPositionX() {
	  float x  = (-1/2)*(mapImage.getWidth() - getWidth()); 
	  return (int) x; 
  }
  
  public int getScreenPositionY (){
	  float y = (-1/2)*(mapImage.getHeight() - getHeight()); 
	  return (int) y; 
  }
 
  
  
  public MapView( Controller controller, MapModel model ) {
    model.addObserver(this);
	model.imageLoader();
	
  }

  public void update( Observable obs, Object obj ) {
	if (obj instanceof BufferedImage){
		mapImage = (BufferedImage) obj; 
		HYP = (int) Math.sqrt(getWidth()*getWidth() + getHeight()*getHeight() ); 
	}
    else if (obj instanceof BufferedImage){
		rotation = (int) obj;
	}
    repaint(); // image updated when zoomed in or out
  }

  public  void paintComponent( Graphics g  ) {
	super.paintComponent(g); 
	Graphics2D g2d = (Graphics2D) g; 
	g2d.rotate(Math.toRadians(rotation), mapImage.getWidth()/2,mapImage.getHeight()/2 );
	g2d.drawImage(mapImage, (-HYP-getWidth())/8, (-HYP-getHeight())/8, null);
	g.setColor(Color.red);
	g.fillOval(getWidth()/2,getHeight()/2, 15, 15);
	System.out.println(HYP); 
	System.out.println(mapImage.getWidth());
	System.out.println("height"+mapImage.getHeight()); 
	
  }
  
  public Dimension getPreferredSize() {
    return new Dimension( mapImage.getWidth(), mapImage.getHeight() );
  }

}