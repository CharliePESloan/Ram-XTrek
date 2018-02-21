import javax.swing.JFrame;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
/*
 * Model-View-Controller.
 *
 * David Wakeling 2018.
 */
public class ModelViewController{
	
  public static void main( String[] argv ) {
    Model      model      = new Model(0);
    Controller controller = new Controller( model );
    View       view       = new View( controller, model );

    JFrame frame = new JFrame();
	frame.add(view); 
	frame.setVisible(true);
    frame.add( view );
	//frame.add(XTrex);
	//frame.setContentPane( new JLabel( new ImageIcon( "XTrex Background.jpep" ) ) );
	//frame.setSize(1200,1200); 
  }
}
