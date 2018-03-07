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
 *
 * David Wakeling 2018.
 */
class MapView extends JPanel implements Observer {
 
 
  BufferedImage mapImage; 
  Toolkit tk = Toolkit.getDefaultToolkit();
  Dimension screenSize = tk.getScreenSize();
  int screenHeight = screenSize.height;
  int screenWidth = screenSize.width;
  
  public MapView( Controller controller, MapModel model ) {
    model.addObserver(this);
	model.imageLoader();
  }

  public void update( Observable obs, Object obj ) {
    mapImage = (BufferedImage)obj; 
    repaint();
  }

  @Override 
  public  void paintComponent( Graphics g  ) {
    super.paintComponent( g );
	g.drawImage( mapImage, 0,0,this); //draws maps on screen
	g.setColor(Color.RED);
	g.fillOval(260/2,294/2,15,15);
  }

}