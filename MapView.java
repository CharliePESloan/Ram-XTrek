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
import java.awt.*;
/*
 * View.
 *
 * David Wakeling 2018.
 */
class MapView extends JPanel implements Observer {
 
 
  BufferedImage mapImage; 
  
  public MapView( Controller controller, Model model ) {
    model.addObserver( this );
  }

  public void update( Observable obs, Object obj ) {
	 mapImage = obj; 
    repaint();
  }

  public  void paintComponent( Graphics g  ) {
    super.paintComponent( g );
	g.drawImage( MapImage, 80,225,this); //draws maps on screen
	g.setColor(Color.RED);
	g.fillOval(219,426,15,15);
  }

}
