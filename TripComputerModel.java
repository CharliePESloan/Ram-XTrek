import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.*;
public class TripComputerModel extends Observable implements Model {
	
	CycleButton selected;
	MenuFrame XTrek;
	
	public TripComputerModel (MenuFrame XTrek) {
		this.XTrek = XTrek;
	}
	public void setSelected(CycleButton newSelected) {
		
	}
	public void pressedPlus() {
		
	}
	public void pressedMinus() {
		
	}
	public void pressedMenu() {
		XTrek.setMenu("Menu");
	}
	public void pressedSelect() {
		
	}
	public void pressedOnOff() {
		XTrek.setMenu("OnOff");
		reset();
	}
	public void reset() {
		
	}
}