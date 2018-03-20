import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
/*
 * View.
 *
 * David Wakeling 2018.
 */
class RotateView extends JPanel implements Observer {
  private BufferedImage mapImage;
  private int rotation;

  public RotateView( Controller controller, MapModel model ) {
    model.addObserver( this );
	model.imageLoader(); 
  }

  public void update( Observable obs, Object obj ) {
	mapImage = (BufferedImage) obj;
    rotation = (int) obj;
    repaint();
  }

  public void paintComponent( Graphics g ) {
    super.paintComponent( g );
    double radians = Math.toRadians( (double) rotation );
    Graphics2D g2d = (Graphics2D) g;
    g2d.clearRect( 0, 0, getWidth(), getHeight() );
    g2d.rotate( radians, mapImage.getWidth() / 2, mapImage.getHeight() / 2 );
    g2d.drawImage( mapImage, 0, 0, this );
  }

  public Dimension getPreferredSize() {
    return new Dimension( mapImage.getWidth(), mapImage.getHeight() );
  }
}
