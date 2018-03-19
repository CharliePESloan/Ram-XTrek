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
		currentButton = selected.getData().toString();
		switch (currentButton)
		{
			case "WhereToButton":
                mainFrame.setMenu(MenuEnum.WHERETO);
				break;
			case "MapButton":
                mainFrame.setMenu(MenuEnum.MAPS);
				break;
			case "SatelliteButton":
				mainFrame.setMenu(MenuEnum.SATELLITE);
			case "TripCompButton":
                mainFrame.setMenu(MenuEnum.TRIP);
				break;
            case "SpeechButton":
                mainFrame.setMenu(MenuEnum.SPEECH);
                break;
			default:
				break;
		}
    }
    public void pressedOnOff(){
        //System.out.println("Changing view to on off");
        mainFrame.setMenu(MenuEnum.ONOFF);
        mainFrame.menuModel.reset();
    }
    
    public void reset(){
        
    }
//Speaker.saySomething(selected.getText(),language,artist);
}