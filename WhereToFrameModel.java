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
    Object letters;
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
		mainFrame.saySomething(textDisplay);
        mainFrame.setMenu(MenuEnum.MENU);
    }
    public void pressedSelect() {
        letters = selected.getData();
		if (letters instanceof WhereToEnum) {
        if (letters == WhereToEnum.SPACE) {
            textDisplay += " ";
			mainFrame.saySomething(letters.toString());
            setChanged(); notifyObservers(textDisplay);
        }
        else if (letters == WhereToEnum.RIGHT) {
			selected.deselect();
			mainFrame.saySomething(number);
            setChanged(); notifyObservers(false);
        }
		else if (letters == WhereToEnum.LEFT) {
			selected.deselect();
			mainFrame.saySomething(text);
            setChanged(); notifyObservers(true);
        }
		else if (letters == WhereToEnum.DEL) {
            try {
				mainFrame.saySomething(delete);
				textDisplay = removeChar(textDisplay, textDisplay.length()-1);
				setChanged(); notifyObservers(textDisplay);
			} catch (java.lang.StringIndexOutOfBoundsException e) {}
        }}
        else {
            textDisplay += letters;
			mainFrame.saySomething((String)letters);
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
