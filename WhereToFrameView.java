/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.*;
/**
 *
 * @author User
 */
public class WhereToFrameView extends JPanel implements Observer {
    ImageIcon[] letter = new ImageIcon[26]; //Array of images  A-Z
    ImageIcon[] hLetter = new ImageIcon[26]; //Array of images A-Z (highlighted)
    CycleButton[] buttons = new CycleButton[26]; //Array of buttons
    static String abcd = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final JTextField display = new JTextField(80);
    final CycleButton buttonSpace = new CycleButton("SPACE");
    final CycleButton buttonRight = new CycleButton("RIGHT");
    CycleButton isSelected;
    GridLayout background = new GridLayout(7,4);
    GridBagLayout backgroundTwo = new GridBagLayout();
    CardLayout card;
    Container abc;
    JPanel a = new JPanel();
    JPanel cards = new JPanel(card);
    JPanel b = new JPanel(background);
    JPanel c = new JPanel(backgroundTwo);
    
    CycleButton[] numberButtons = new CycleButton[10];
    ImageIcon[] number = new ImageIcon[10];
    ImageIcon[] hNumber = new ImageIcon[10];
    final CycleButton buttonLeft = new CycleButton("LEFT");
    final CycleButton buttonDel = new CycleButton("DEL");
    
     public String removeChar(String s, int a) {
        return s.substring(0, a) + s.substring(a + 1);
    }   
    
    public void actionPerformed(ActionEvent e) {
	card.next(abc);
	}
    
    
    
    public WhereToFrameView (Controller controller, WhereToFrameModel model){
    //255,36
    display.setSize(255,36);
    
    add(a);
    add(cards);
    a.add(display);
    add(b, "TextKeyboard");
    cards.add(c, "NumberKeyboard");
    
    
    model.addObserver(this);
    for(int i=0; i<26; i++) {
            letter[i] = new ImageIcon("Images/Test" + abcd.charAt(i) + ".png"); //Creates the images
            hLetter[i] = new ImageIcon("Images/HTest" + abcd.charAt(i) + ".png"); //Creates the highlighted images
            buttons[i] = new CycleButton(Character.toString(abcd.charAt(i)), letter[i], hLetter[i]); //Creates the buttons
            b.add(buttons[i]);
        }
    for (int i=1; i<25;i++) {
			buttons[i].setPrevNext(buttons[i-1],buttons[i+1]);
		}
		buttonRight.setPrevNext(buttonSpace, buttons[0]);
		buttonSpace.setPrevNext(buttons[25], buttonRight);
		buttons[0].setPrevNext(buttonRight, buttons[1]);
		buttons[25].setPrevNext(buttons[24], buttonSpace);

                model.setSelected(buttons[0]);
                b.add(buttonSpace);
                b.add(buttonRight);
                
     for (int i=0; i<10;++i){
            number[i] = new ImageIcon("Images/Test" + Integer.toString(i) + ".png"); //Creates the images
            hNumber[i] = new ImageIcon("Images/HTest" + Integer.toString(i) + ".png");
            numberButtons[i] = new CycleButton(Integer.toString(i), number[i], hNumber[i]);
            c.add(numberButtons[i]);
        }
     c.add(buttonLeft);
     c.add(buttonDel);
     
     for (int i=2; i<9;i++) {
			numberButtons[i].setPrevNext(numberButtons[i-1],numberButtons[i+1]);
		}
        numberButtons[0].setPrevNext(numberButtons[9], buttonLeft);
        buttonLeft.setPrevNext(numberButtons[0], buttonDel);
        numberButtons[1].setPrevNext(buttonDel, numberButtons[2]);
        buttonDel.setPrevNext(buttonLeft, numberButtons[1]);
        numberButtons[9].setPrevNext(numberButtons[8], numberButtons[0]);
        
        
    }
    public void setKeyboard(String type ){
        switch (type) {
        case "TextKeyboard":
            type = "TextKeyboard";
            break;
        
        case "NumberKeyboard":
            break;
    }
        //card.show(cards, type);
    }
    @Override
    public void update(Observable o, Object arg) {
            display.setText("" + arg);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void add(CardLayout card) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
