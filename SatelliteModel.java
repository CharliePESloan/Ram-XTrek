import javax.swing.*;
import java.util.*;
/*
 * Satellite Model.
 *
 * Clyde Udunna 2018.
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
		mainFrame.setMenu(MenuEnum.MENU);
	}
	public void pressedSelect()
	{
				
    }
    public void pressedOnOff(){
        //System.out.println("Changing view to on off");
        mainFrame.setMenu(MenuEnum.ONOFF);
    }
    
    public void reset(){
        
    }
	
	public MenuFrame getMainFrame(){
		return mainFrame;
	}
//Speaker.saySomething(selected.getText(),language,artist);
}
