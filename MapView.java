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
  
  private int rotation = 45; 
  private int centreCoord = 0;  
  private int xCentreCoord = 127; 
  private int yCentreCoord = 145; 
  private int circleSize = 8; 
  private int HYP; 
  
 
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
	
	int xPosition = (-HYP-getWidth())/4;
	int yPosition = (-HYP-getHeight())/4;
	
	g2d.rotate(Math.toRadians(rotation), getWidth()/2,getHeight()/2 );
	g2d.drawImage(mapImage, xPosition, yPosition, null);
	g.setColor(Color.red);
	g.fillOval(getWidth()/2-circleSize/2,getHeight()/2-circleSize/2, circleSize, circleSize);
  }
  

}