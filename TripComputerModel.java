import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Nathan Painter
 *
 * 
 */
public class TripComputerModel extends Observable implements Model {
	
	CycleButton selected;
	MenuFrame mainFrame;
	long time;

public long getTimer (){
return System.currentTimeMillis() - time;
}	
	
	public TripComputerModel (MenuFrame mainFrame) {
		this.mainFrame = mainFrame;
		time = System.currentTimeMillis();
		
	}
	public void setSelected(CycleButton newSelected) {
	}
	public void pressedPlus() {
	}
	public void pressedMinus() {
	}
	public void pressedMenu() {
		mainFrame.setMenu(MenuEnum.MENU);
	}
	public void pressedSelect() {
	}
	public void pressedOnOff() {
		mainFrame.setMenu(MenuEnum.ONOFF);
		reset();
	}
	public void reset() {
	}
}