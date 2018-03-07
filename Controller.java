
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
		Object	o = me.getSource();
		JButton	b = null;

		if(o instanceof JButton)
			b = (JButton)o;

		if(b != null)
		{
			System.out.println("It's a " + b.getName() + "!");
            if(this.model instanceof MenuModel){
            System.out.println("The class is MenuModel");
            } else{
            System.out.println("The class is not MenuModel");
            }
			switch (b.getName())
			{
				case "PlusButton":
                    b.setIcon(new ImageIcon("Images/PlusButtonSelected.png"));
					break;
				case "MinusButton":
                    b.setIcon(new ImageIcon("Images/MinusButtonSelected.png"));
					break;
				case "SelectButton":
                    b.setIcon(new ImageIcon("Images/SelectButtonSelected.png"));
					break;
				case "MenuButton":
                    b.setIcon(new ImageIcon("Images/MenuButtonSelected.png"));
                    break;
                case "OnOffButton":
                    break;
			}
		}
    }

	public void mouseExited( MouseEvent me )
	{
		Object	o = me.getSource();
		JButton	b = null;

		if(o instanceof JButton)
			b = (JButton)o;

		if(b != null)
		{
			System.out.println("It's a " + b.getName() + "!");
            if(this.model instanceof MenuModel){
            System.out.println("The class is MenuModel");
            } else{
            System.out.println("The class is not MenuModel");
            }
			switch (b.getName())
			{
				case "PlusButton":
                    b.setIcon(new ImageIcon("Images/PlusButton.png"));
					break;
				case "MinusButton":
                    b.setIcon(new ImageIcon("Images/MinusButton.png"));
					break;
				case "SelectButton":
                    b.setIcon(new ImageIcon("Images/SelectButton.png"));
					break;
				case "MenuButton":
                    b.setIcon(new ImageIcon("Images/MenuButton.png"));
                    break;
                case "OnOffButton":
                    break;
			}
		}
    }
	
	public void mouseReleased( MouseEvent me )
	{}

}
