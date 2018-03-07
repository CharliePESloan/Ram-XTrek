
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.ImageIcon;

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

	public void setModel( Model model )
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
			System.out.println("It's a " + b.getName() + "!");
			switch (b.getName())
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
                case "OnOffButton":
                    this.model.pressedOnOff();
			}
		}
	}
	
	public void mousePressed( MouseEvent me )
	{}

	public void mouseEntered( MouseEvent me )
	{
        System.out.println("Mouse Entered");
		Object	o = me.getSource();
		JButton	b = null;

		if(o instanceof JButton)
			b = (JButton)o;

		if(b != null)
		{
			System.out.println("It's a " + b.getName() + "!");
			switch (b.getText())
			{
				case "PlusButton":
					System.out.println("Mouse entered into plus button");
                    MenuModel menuModel = (MenuModel)this.model;
                    menuModel.mainFrame.PlusButton.setIcon(new ImageIcon("Images/PlusButtonSelected.png"));
					break;
				case "MinusButton":
					
					break;
				case "SelectButton":
					
					break;
				case "MenuButton":
					
					break;
                case "OnOffButton":
                    
			}
		}
    }

	public void mouseExited( MouseEvent me )
	{}
	
	public void mouseReleased( MouseEvent me )
	{}

}
