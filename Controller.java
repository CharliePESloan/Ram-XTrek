import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import javax.swing.JButton;

/*
 * Controller
 * Charlie Sloan (2018)
 * 
 * Listens for MouseEvents on the side buttons and calls the appropriate
 * function of the current model
 */
class Controller implements MouseListener
{
	// The currently selected model
	private Model model;

	/* Constructor */
	public Controller( Model model )
	{
		this.model = model;
	}

	/* setModel
	 * Change the current model
	 */
	public void setModel( Model model )
	{
		this.model = model;
	}

	/* mouseClicked
	 * Handle click events for all buttons on the XTrek device 
	 */
	public void mouseClicked( MouseEvent me )
	{
		// Try to get the source of the event as a button
		Object	o = me.getSource();
		JButton	b = null;
		if(o instanceof JButton)
			b = (JButton)o;

		// If the event came from a button then handle it
		if(b != null)
		{
			// Debug print statement
			System.out.println("It's a " + b.getText() + "!");

			// The model handles each button with a specfific function
			switch (b.getText())
			{
				case "PlusButton":
					model.pressedPlus();
					break;
				case "MinusButton":
					model.pressedMinus();
					break;
				case "SelectButton":
					model.pressedSelect();
					break;
				case "MenuButton":
					model.pressedMenu();
					break;
			}
		}
	}
	
	public void mousePressed( MouseEvent me )
	{}
	public void mouseEntered( MouseEvent me )
	{}
	public void mouseExited( MouseEvent me )
	{}
	public void mouseReleased( MouseEvent me )
	{}

}
