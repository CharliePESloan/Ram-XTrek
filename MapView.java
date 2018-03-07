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
/*
 * View.
 * Class controls what is displayed on the screen. 
 *
 * Devash Patel 2018.
 * Code adapted from that produced by David Wakeling
 */
class MapView extends JPanel implements Observer {
 
  BufferedImage mapImage; 
  
  private int centreCoord = 0;  
  private int xCentreCoord = 127; 
  private int yCentreCoord = 145; 
  private int circleSize = 15; 
  
  
  public MapView( Controller controller, MapModel model ) {
    model.addObserver(this);
	model.imageLoader();
  }

  public void update( Observable obs, Object obj ) {
    mapImage = (BufferedImage) obj; 
    repaint(); // image updated when zoomed in or out
  }

  public  void paintComponent( Graphics g  ) {
    super.paintComponent( g );
	g.drawImage( mapImage, centreCoord,centreCoord,this); //draws maps on screen
	g.setColor(Color.RED);
	g.fillOval(xCentreCoord,yCentreCoord,circleSize,circleSize); //displays a red dot at the centre of the map
  }

}