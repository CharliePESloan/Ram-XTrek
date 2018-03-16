import javax.swing.*;
import java.util.*;
/*
 * Menu Model.
 *
 * Darya Shyroka 2018.
 */
public class OnOffModel extends Observable implements Model {	
  
	MenuFrame mainFrame;

	public OnOffModel(MenuFrame mainFrame)
	{
		this.mainFrame = mainFrame;
        //selected = MenuView.MapButton;
	}

	/* Side buttons */
	public void pressedPlus()
	{
        return;
	}
	public void pressedMinus()
	{
		return;
	}
	public void pressedMenu()
	{
		return;
	}
	public void pressedSelect()
	{
        return;
		}
    
    public void pressedOnOff(){
        mainFrame.setMenu(MenuEnum.MENU);
    }
    
    public void reset(){
        
}
//Speaker.saySomething(selected.getText(),language,artist);
}