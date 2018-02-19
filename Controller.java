import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities; 
/*
 * Controller.
 *
 * David Wakeling 2018.
 */
class Controller implements MouseListener{
  private Model model;

  public Controller( Model model ) {	
    this.model = model;
  }

  public void mouseClicked( MouseEvent me) {
	if (SwingUtilities.isLeftMouseButton(me))
	{
		model.zoomOut(); 
	}
	else if (SwingUtilities.isRightMouseButton(me))
	{
		model.zoomIn();
	}
  }
  
  
  
  public void mousePressed (MouseEvent me) {
	model.zoomOut(); 
  }
  

  public void mouseEntered ( MouseEvent me ) { /* nothing */ }
  public void mouseExited  ( MouseEvent me ) { /* nothing */ }
  public void mouseReleased( MouseEvent me ) { /* nothing */ }
}
