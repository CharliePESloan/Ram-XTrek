import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.JButton;

/*
 * Controller
 *
 * Charlie Sloan (2018)
 */
class Controller implements MouseListener
{
	private Model model;

	public Controller( Model model )
	{
		this.model = model;
	}

	public void switchModel( Model model )
	{
		this.model = model;
	}

	public void mouseClicked( MouseEvent me)
	{
		System.out.println("Clicked!");
		Object	o = me.getSource();
		JButton	b = null;

		if(o instanceof JButton)
			b = (JButton)o;

		if(b != null)
		{
			System.out.println("It's a " + b.getText() + "!");
			switch (b.getText())
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
					this.model.pressedMenu();
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
