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
    CycleButton selected;
    MenuFrame XTrek;
    String letters;
    String textDisplay ="";
    
    public WhereToFrameModel(MenuFrame XTrek) {
        XTrek = XTrek;
    }
    public void setSelected(CycleButton newSelected) {
        selected = newSelected;
        selected.select();
    }
    public void pressedPlus() {
        setSelected(selected.prev());
        setChanged(); notifyObservers (letters);
    }
    public void pressedMinus() {
        setSelected(selected.next());
        setChanged(); notifyObservers (letters);
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
            
        }
        else {
            textDisplay += letters;
            setChanged(); notifyObservers(textDisplay);
        }
    }
    public void pressedOnOff()
	{
		XTrek.setMenu("Off");
	}
}
