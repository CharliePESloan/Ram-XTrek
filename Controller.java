import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/*
 * Controller
 *
 * Charlie Sloan (2018) and Nathan Painter 
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
    
    public Object getModel(){
        return this.model;
    }
    
	public void mouseClicked( MouseEvent me)
	{
		Object	o = me.getSource();
		JButton	b = null;

		if(o instanceof JButton)
			b = (JButton)o;

		if(b != null)
		{
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
	{    }

	public void mouseEntered( MouseEvent me )
	{
		Object	o = me.getSource();
		JButton	b = null;

		if(o instanceof JButton)
			b = (JButton)o;

		if(b != null)
		{if(!(this.model instanceof OnOffModel)){ 
			switch (b.getName())
			{
				case "PlusButton":
                    if(!((this.model instanceof TripComputerModel) || (this.model instanceof SatelliteModel) || (this.model instanceof AboutModel))){
                    b.setIcon(new ImageIcon("Images/PlusButtonSelected.png"));}
					break;
				case "MinusButton":
                    if(!((this.model instanceof TripComputerModel) || (this.model instanceof SatelliteModel) || (this.model instanceof AboutModel))){
                    b.setIcon(new ImageIcon("Images/MinusButtonSelected.png"));}
					break;
				case "SelectButton":
                    if(!((this.model instanceof TripComputerModel) || (this.model instanceof SatelliteModel)|| (this.model instanceof AboutModel))){
                    b.setIcon(new ImageIcon("Images/SelectButtonSelected.png"));}
					break;
				case "MenuButton":
                    b.setIcon(new ImageIcon("Images/MenuButtonSelected.png"));
                    break;
                case "OnOffButton":
                    break;
			}}
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
