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
class View extends JPanel implements Observer {
  private BufferedImage image;
  private int zooming; 

  public View( Controller controller, Model model ) {

    
  
    try {
      image = ImageIO.read( new File( "output.png" ) );
    } catch ( Exception ex ) {
      System.out.println( ex ); System.exit( 1 );
    }
    addMouseListener( controller ); 
    model.addObserver( this );
  }

  public void update( Observable obs, Object obj ) {
	zooming = (int) obj;
    repaint();
  }
  
  public  void paintComponent( Graphics g ) {
    super.paintComponent( g );
    Graphics2D g2d = (Graphics2D) g;
	g2d.drawImage( image, 0, 0, this );	
	g2d.setColor(Color.RED);
	g2d.fillOval(image.getWidth()/2, image.getHeight()/2, 15, 15);
	//g2d.drawImage( image, 0, 0, this );					
	g2d.translate(-image.getWidth(),-image.getHeight());
	g2d.scale(zooming, zooming); 
	//g2d.translate((-image.getWidth()/2),(-image.getHeight()/2));
    g2d.clearRect( 0, 0, getWidth(), getHeight() );
    g2d.drawImage( image, 0, 0, this );	
  }

  

  public Dimension getPreferredSize() {
    return new Dimension( image.getWidth()*zooming, image.getHeight()*zooming);
  }
}
