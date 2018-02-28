import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities; 
/*
 * Controller.
 *
 * By Charlie Sloan and David Wakeling (2018)
 */
class Controller implements MouseListener{
	private Model model;

	public Controller( Model model ) {	
		this.model = model;
	}

	public void switchModel( Model model )
	{
		this.model = model;
	}

	public void mouseClicked( MouseEvent me) {
		/*if (SwingUtilities.isLeftMouseButton(me))
		{
			model.zoomOut(); 
		}
		else if (SwingUtilities.isRightMouseButton(me))
		{
			model.zoomIn();
		}*/
		switch (me.button.getText())
		{
			case "PlusButton":
				this.model.pressedPlus();
				break;
			case "MinusButton":
				this.model.pressedMinus();
				break;
			case "SelectButton":
				this.model.pressedSelect();
				break;
			case "MenuButton":
				this.model.pressedPlus();
				break;
		}
	}


	public void mousePressed (MouseEvent me)
	{
		model.zoomOut(); 
	}


	public void mouseEntered ( MouseEvent me ) { /* nothing */ }
	public void mouseExited  ( MouseEvent me ) { /* nothing */ }
	public void mouseReleased( MouseEvent me ) { /* nothing */ }
}
