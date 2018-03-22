import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.*;
/**
 *
 * @author Nathan Painter
 *
 * Images for the number and letter keyboard created by Nathan Painter
 */
public class WhereToFrameView extends JPanel implements Observer {
	final WhereToFrameModel model;
	final static int fontSize = 24; //Size of the font inside the text display
	final static int displaySize = 12; //Size of the display
	final JTextField display = new JTextField("Enter Address",displaySize);
    ImageIcon[] letter = new ImageIcon[26]; //Array of images  A-Z
    ImageIcon[] hLetter = new ImageIcon[26]; //Array of images A-Z (highlighted)
    CycleButton[] buttons = new CycleButton[26]; //Array of letter buttons
    static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    
	final CycleButton buttonSpace; 
    ImageIcon letterSpace = new ImageIcon("Images/TestSpace.png");
    ImageIcon letterHSpace = new ImageIcon("Images/HTestSpace.png");
    final CycleButton buttonRight;
    ImageIcon letterRight = new ImageIcon("Images/TestRight.png");
    ImageIcon letterHRight = new ImageIcon("Images/HTestRight.png");
    
    CycleButton isSelected;
    
	GridLayout textLayout = new GridLayout(7,4); //Layout for the text keyboard
	GridLayout grid = new GridLayout(1,0);
    GridBagLayout numberLayout = new GridBagLayout(); //Layout for the number keyboard
	GridBagConstraints constraints = new GridBagConstraints();
	
    CardLayout cardLayout = new CardLayout();
    
	JPanel displayPanel = new JPanel(grid);
    JPanel cards = new JPanel(cardLayout);
    JPanel textPanel = new JPanel(textLayout);
    JPanel numberPanel = new JPanel(numberLayout);
	
    
    CycleButton[] numberButtons = new CycleButton[10];//Array of number buttons
    ImageIcon[] number = new ImageIcon[10];//Array of images 0-9
    ImageIcon[] hNumber = new ImageIcon[10];//Array of images 0-9 (highlighted)
    final CycleButton buttonLeft;
	ImageIcon letterLeft = new ImageIcon("Images/TestLeft.png");
    ImageIcon letterHLeft = new ImageIcon("Images/HTestLeft.png");
    final CycleButton buttonDel;
	ImageIcon letterDel = new ImageIcon("Images/TestDel.png");
    ImageIcon letterHDel = new ImageIcon("Images/HTestDel.png");
	Font bigFont = display.getFont().deriveFont(Font.PLAIN, fontSize);
    
        
    
 
    public WhereToFrameView (Controller controller, WhereToFrameModel model){
		
		displayPanel.add(display);
		cards.add(textPanel, "TextKeyboard");
		cards.add(numberPanel, "NumberKeyboard");
		add(displayPanel);
		add(cards);
		
		this.model = model;
		model.addObserver(this);
		display.setFont(bigFont);
		display.setEditable(false);
		for(int i=0; i<26; i++) {
				letter[i] = new ImageIcon("Images/Test" + alphabet.charAt(i) + ".png"); //Creates the images for the letter keyboard
				hLetter[i] = new ImageIcon("Images/HTest" + alphabet.charAt(i) + ".png"); //Creates the highlighted images for the letter keyboard
				buttons[i] = new CycleButton(Character.toString(alphabet.charAt(i)), letter[i], hLetter[i]); //Creates the buttons for the letter keyboard
				textPanel.add(buttons[i]);
			}
		buttonSpace = new CycleButton(WhereToEnum.SPACE, letterSpace, letterHSpace);
		buttonRight = new CycleButton(WhereToEnum.RIGHT, letterRight, letterHRight);
		textPanel.add(buttonSpace);
		textPanel.add(buttonRight);
			
	   
		buttonDel = new CycleButton(WhereToEnum.DEL,letterDel, letterHDel);
		buttonLeft = new CycleButton(WhereToEnum.LEFT,letterLeft, letterHLeft);
		
		for (int i=1; i<25;i++) {
				buttons[i].setPrevNext(buttons[i-1],buttons[i+1]);
			}
			buttonRight.setPrevNext(buttonSpace, buttons[0]);
			buttonSpace.setPrevNext(buttons[25], buttonRight);
			buttons[0].setPrevNext(buttonRight, buttons[1]);
			buttons[25].setPrevNext(buttons[24], buttonSpace);

		
		
					
		 for (int i=0; i<10;++i){
				number[i] = new ImageIcon("Images/Test" + Integer.toString(i) + ".png"); //Creates the images for the number keyboard
				hNumber[i] = new ImageIcon("Images/HTest" + Integer.toString(i) + ".png");//Creates the highlighted images for the number keyboard
				numberButtons[i] = new CycleButton(Integer.toString(i), number[i], hNumber[i]);//Creates the buttons for the number keyboard
			}
			//Layout for the number keyboard
			constraints.gridx = 0;
			constraints.gridy = 0;
			numberPanel.add(numberButtons[1],constraints);
			
			constraints.gridx = 1;
			numberPanel.add(numberButtons[2],constraints);
			
			constraints.gridx = 2;
			numberPanel.add(numberButtons[3],constraints);
			
			constraints.gridx = 0;
			constraints.gridy = 1;
			numberPanel.add(numberButtons[4],constraints);
			
			constraints.gridx = 1;
			numberPanel.add(numberButtons[5],constraints);
		
			constraints.gridx = 2;
			numberPanel.add(numberButtons[6],constraints);
			
			constraints.gridx = 0;
			constraints.gridy = 2;
			numberPanel.add(numberButtons[7],constraints);
			
			constraints.gridx = 1;
			numberPanel.add(numberButtons[8],constraints);
			
			constraints.gridx = 2;
			numberPanel.add(numberButtons[9],constraints);
			
			constraints.gridx = 0;
			constraints.gridy = 3;
			numberPanel.add(numberButtons[0],constraints);
			
			constraints.gridx = 0;
			constraints.gridy = 4;
			numberPanel.add(buttonLeft, constraints);
			
			constraints.gridx = 1;
			constraints.gridy = 3;
			
			constraints.gridwidth = 2;
			constraints.gridheight = 2;
			numberPanel.add(buttonDel, constraints);
		 
		 for (int i=2; i<9;i++) {
				numberButtons[i].setPrevNext(numberButtons[i-1],numberButtons[i+1]);
			}
			numberButtons[0].setPrevNext(numberButtons[9], buttonLeft);
			buttonLeft.setPrevNext(numberButtons[0], buttonDel);
			numberButtons[1].setPrevNext(buttonDel, numberButtons[2]);
			buttonDel.setPrevNext(buttonLeft, numberButtons[1]);
			numberButtons[9].setPrevNext(numberButtons[8], numberButtons[0]);
		
		setKeyboard("TextKeyboard"); //Sets the default keyboard  to the text keyboard
	}
    public void setKeyboard( String type ){
        switch (type) {
        case "TextKeyboard":
		model.setSelected(buttons[0]); //Sets the selected button at A
            break;
        case "NumberKeyboard":
		model.setSelected(numberButtons[1]); //Sets the selected button at 1
            break;
    }
        cardLayout.show(cards, type);
    }
    @Override
    public void update(Observable o, Object arg) {
		if (arg instanceof Boolean) {
			boolean bean = (boolean) arg;
			if (bean == true) {
				setKeyboard("TextKeyboard");
			}
			else {
				setKeyboard("NumberKeyboard");
			}
		}
		else {
            display.setText("" +  arg);
        }
    }
}
