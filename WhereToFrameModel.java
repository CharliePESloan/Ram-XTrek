import java.awt.*;
import java.util.*;
import javax.swing.*;
/**
 *
 * @author Nathan Painter
 */
public class WhereToFrameModel extends Observable implements Model, Observer {
	public String removeChar(String s, int a) //Method to delete the last entered text
	{ 
        return s.substring(0, a) + s.substring(a + 1);
    }
    CycleButton selected;
    MenuFrame mainFrame;
    String letters;
	String delete = "Delete";
	String text = "Text Keyboard";
	String number = "Number Keyboard";
    String textDisplay ="";
	Language language = new Language ("en");
    public void update(Observable obs, Object obj){
		if (obj instanceof Language)
		{
			language = (Language) obj; 
		}
	}
    public WhereToFrameModel(MenuFrame mainFrame, SpeechModel speechModel) {
        this.mainFrame = mainFrame;
		speechModel.addObserver(this); 
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
		Speaker.saySomething(textDisplay,language);
        mainFrame.setMenu(MenuEnum.MENU);
    }
    public void pressedSelect() {
        letters =  (String) selected.getData();
        if (letters == "SPACE") {
            textDisplay += " ";
			Speaker.saySomething(letters, language);
            setChanged(); notifyObservers(textDisplay);
        }
        else if (letters == "RIGHT") {
			selected.deselect();
			Speaker.saySomething(number, language);
            setChanged(); notifyObservers(false);
        }
		else if (letters == "LEFT") {
			selected.deselect();
			Speaker.saySomething(text,language);
            setChanged(); notifyObservers(true);
        }
		else if (letters == "DEL") {
			Speaker.saySomething(delete,language);
            textDisplay = removeChar(textDisplay, textDisplay.length()-1);
			setChanged(); notifyObservers(textDisplay);
        }
        else {
            textDisplay += letters;
			Speaker.saySomething(letters,language );
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
