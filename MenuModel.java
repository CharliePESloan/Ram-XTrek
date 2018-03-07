import javax.swing.*;
import java.util.*;
/*
 * Menu Model.
 *
 * Darya Shyroka 2018.
 */
public class MenuModel extends Observable implements Model {	
  
    CycleButton selected = null;
    String currentButton;
	MenuFrame mainFrame;

	public MenuModel(MenuFrame mainFrame)
	{
		this.mainFrame = mainFrame;
        //selected = MenuView.MapButton;
	}
	public void setSelected(CycleButton newSelected)
	{
		selected = newSelected;
		selected.select();
	}

	/* Side buttons */
	public void pressedPlus()
	{
        System.out.println("The selected button is: " + selected.getText());
		setSelected(selected.next());
	}
	public void pressedMinus()
	{
		setSelected(selected.prev());
	}
	public void pressedMenu()
	{
		return;
	}
	public void pressedSelect()
	{
		currentButton = (String)selected.getData();
		switch (currentButton)
		{
			case "WhereToButton":
                mainFrame.setMenu("WhereTo");
				break;
			case "MapButton":
                mainFrame.setMenu("Map");
				break;
			case "SatelliteButton":
				break;
			case "TripCompButton":
				break;
            case "SpeechButton":
                mainFrame.setMenu("Speech");
                break;
			default:
				break;
		}
    }
    public void pressedOnOff(){
        System.out.println("Changing view to on off");
        mainFrame.setMenu("OnOff");
    }
//Speaker.saySomething(selected.getText(),language,artist);
}