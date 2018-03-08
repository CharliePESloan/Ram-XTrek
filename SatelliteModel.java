import javax.swing.*;
import java.util.*;
/*
 * Menu Model.
 *
 * Darya Shyroka 2018.
 */
public class SatelliteModel extends Observable implements Model {	

	MenuFrame mainFrame;

	public SatelliteModel(MenuFrame mainFrame)
	{
		this.mainFrame = mainFrame;
        //selected = MenuView.MapButton;
	}

	/* Side buttons */
	public void pressedPlus()
	{
		
	}
	public void pressedMinus()
	{
		
	}
	public void pressedMenu()
	{
		mainFrame.setMenu("Menu");
	}
	public void pressedSelect()
	{
				
    }
    public void pressedOnOff(){
        //System.out.println("Changing view to on off");
        mainFrame.setMenu("OnOff");
    }
    
    public void reset(){
        
    }
//Speaker.saySomething(selected.getText(),language,artist);
}