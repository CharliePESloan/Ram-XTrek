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
 * MapView.
 * Class controls what is displayed on the screen. 
 *
 * @ author Devash Patel 2018.
 * Code adapted from that produced by David Wakeling
 */
class MapView extends JPanel implements Observer {
 
  BufferedImage mapImage; 
  
  private final static  int CIRCLESIZE = 8; 
  private int  HYP; 
  private int rotation;
  
  public MapView( Controller controller, MapModel model ) {
    model.addObserver(this);
	model.imageLoader();
  }
  
  public void update( Observable obs, Object obj ) {
	if (obj instanceof BufferedImage){
		mapImage = (BufferedImage) obj; 
	}
    else if (obj instanceof Integer){
		rotation = (int) obj;
	}
    repaint(); // image updated when zoomed in or out
  }

  public  void paintComponent( Graphics g  ) {
	super.paintComponent(g); 
	Graphics2D g2d = (Graphics2D) g; 
	HYP = (int) Math.sqrt(getWidth()*getWidth() + getHeight()*getHeight() ); 
	g2d.rotate(Math.toRadians(rotation), getWidth()/2,getHeight()/2 );              //rotates the map at the center of the panel 
	g2d.drawImage(mapImage, (-HYP-getWidth())/4, (-HYP-getHeight())/4, null);       //draws map centered  
	g.setColor(Color.red);
	g.fillOval(getWidth()/2-CIRCLESIZE/2,getHeight()/2-CIRCLESIZE/2, CIRCLESIZE, CIRCLESIZE);
  }
 
}