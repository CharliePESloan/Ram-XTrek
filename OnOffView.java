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
class OnOffView extends JPanel implements Observer {
  OnOffModel model;
  
  public OnOffView( Controller controller, OnOffModel model ) {
      this.model = model;
      model.addObserver( this );
      setBackground(Color.black);
    
  }
  
  public void update( Observable obs, Object obj ) {
    return;
  }

}