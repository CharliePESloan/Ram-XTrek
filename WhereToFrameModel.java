/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.util.*;
import javax.swing.*;
/**
 *
 * @author User
 */
public class WhereToFrameModel extends Observable implements Model {
	public String removeChar(String s, int a) {
        return s.substring(0, a) + s.substring(a + 1);
    }
    CycleButton selected;
    MenuFrame XTrek;
    String letters;
    String textDisplay ="";
    
    public WhereToFrameModel(MenuFrame XTrek) {
        this.XTrek = XTrek;
    }
    public void setSelected(CycleButton newSelected) {
        selected = newSelected;
        selected.select();
    }
    public void pressedPlus() {
        setSelected(selected.next());
        setChanged(); 
    }
    public void pressedMinus() {
        setSelected(selected.prev());
        setChanged();
    }
    public void pressedMenu() {
        XTrek.setMenu("Menu");
    }
    public void pressedSelect() {
        letters =  (String) selected.getData();
        if (letters == "SPACE") {
            textDisplay += " ";
            setChanged(); notifyObservers(textDisplay);
        }
        else if (letters == "RIGHT") {
			selected.deselect();
            setChanged(); notifyObservers(false);
        }
		else if (letters == "LEFT") {
			selected.deselect();
            setChanged(); notifyObservers(true);
        }
		else if (letters == "DEL") {
            textDisplay = removeChar(textDisplay, textDisplay.length()-1);
			setChanged(); notifyObservers(textDisplay);
        }
        else {
            textDisplay += letters;
            setChanged(); notifyObservers(textDisplay);
        }
    }
    public void pressedOnOff()
	{
		XTrek.setMenu("OnOff");
		reset();
	}
	public void reset () {
		textDisplay = "";
        setChanged(); notifyObservers("Dev is a twat");
	}
}
