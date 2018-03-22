import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.*;

public class AboutModel extends Observable implements Model {
	MenuFrame mainFrame;
	CycleButton selected;
	
	public AboutModel (MenuFrame mainFrame) {
		this.mainFrame = mainFrame;
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
	public void pressedSelect()  {
	}
	public void pressedOnOff() {
		mainFrame.setMenu(MenuEnum.ONOFF);
		reset();
	}
	public void reset() {
	}
}