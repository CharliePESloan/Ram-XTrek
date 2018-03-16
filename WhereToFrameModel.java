import java.awt.*;
import java.util.*;
import javax.swing.*;
/**
 *
 * @author Nathan Painter
 */
public class WhereToFrameModel extends Observable implements Model {
	public String removeChar(String s, int a) //Method to delete the last entered text
	{ 
        return s.substring(0, a) + s.substring(a + 1);
    }
    CycleButton selected;
    MenuFrame mainFrame;
    String letters;
    String textDisplay ="";
    
    public WhereToFrameModel(MenuFrame mainFrame) {
        this.mainFrame = mainFrame;
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
		Speaker.saySomething(textDisplay);
        mainFrame.setMenu("Menu");
    }
    public void pressedSelect() {
        letters =  (String) selected.getData();
        if (letters == "SPACE") {
            textDisplay += " ";
			Speaker.saySomething(letters);
            setChanged(); notifyObservers(textDisplay);
        }
        else if (letters == "RIGHT") {
			selected.deselect();
			Speaker.saySomething("Number Keyboard");
            setChanged(); notifyObservers(false);
        }
		else if (letters == "LEFT") {
			selected.deselect();
			Speaker.saySomething("Text Keyboard");
            setChanged(); notifyObservers(true);
        }
		else if (letters == "DEL") {
			Speaker.saySomething("Delete");
            textDisplay = removeChar(textDisplay, textDisplay.length()-1);
			setChanged(); notifyObservers(textDisplay);
        }
        else {
            textDisplay += letters;
			Speaker.saySomething(letters);
            setChanged(); notifyObservers(textDisplay);
        }
    }
    public void pressedOnOff()
	{
		mainFrame.setMenu(MenuEnum.ONOFF);
		reset();
	}
	public void reset () {
		textDisplay = "";
		selected.deselect();
		setChanged(); notifyObservers(true);
        setChanged(); notifyObservers("Enter Address");
	}
}
