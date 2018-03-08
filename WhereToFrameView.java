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
    final JTextField display = new JTextField("Enter Address",12);
    final CycleButton buttonSpace; 
    ImageIcon letterSpace = new ImageIcon("Images/TestSpace.png");
    ImageIcon letterHSpace = new ImageIcon("Images/HTestSpace.png");
    final CycleButton buttonRight;
    ImageIcon letterRight = new ImageIcon("Images/TestRight.png");
    ImageIcon letterHRight = new ImageIcon("Images/HTestRight.png");
    
    CycleButton isSelected;
    GridLayout background = new GridLayout(7,4);
	GridLayout testOne = new GridLayout(1,0);
    GridBagLayout backgroundTwo = new GridBagLayout();
	GridBagConstraints test = new GridBagConstraints();
	
    CardLayout cardLayout = new CardLayout();
    Container abc;
    
	JPanel a = new JPanel(testOne);
    JPanel cards = new JPanel(cardLayout);
    JPanel b = new JPanel(background);
    JPanel c = new JPanel(backgroundTwo);
	
    
    CycleButton[] numberButtons = new CycleButton[10];
    ImageIcon[] number = new ImageIcon[10];
    ImageIcon[] hNumber = new ImageIcon[10];
    final CycleButton buttonLeft;
	ImageIcon letterLeft = new ImageIcon("Images/TestLeft.png");
    ImageIcon letterHLeft = new ImageIcon("Images/HTestLeft.png");
    final CycleButton buttonDel;
	ImageIcon letterDel = new ImageIcon("Images/TestDel.png");
    ImageIcon letterHDel = new ImageIcon("Images/HTestDel.png");
	Font bigFont = display.getFont().deriveFont(Font.PLAIN, 24f);
    
     public String removeChar(String s, int a) {
        return s.substring(0, a) + s.substring(a + 1);
    }   
    
    public void actionPerformed(ActionEvent e) {
	cardLayout.next(abc);
	}
    
    
    
    public WhereToFrameView (Controller controller, WhereToFrameModel model){
		//255,36
		
		a.add(display);
		cards.add(b, "TextKeyboard");
		cards.add(c, "NumberKeyboard");
		add(a);
		add(cards);
		
		model.addObserver(this);
		display.setFont(bigFont);
		
		for(int i=0; i<26; i++) {
				letter[i] = new ImageIcon("Images/Test" + abcd.charAt(i) + ".png"); //Creates the images
				hLetter[i] = new ImageIcon("Images/HTest" + abcd.charAt(i) + ".png"); //Creates the highlighted images
				buttons[i] = new CycleButton(Character.toString(abcd.charAt(i)), letter[i], hLetter[i]); //Creates the buttons
				b.add(buttons[i]);
			}
		buttonSpace = new CycleButton("SPACE", letterSpace, letterHSpace);
		buttonRight = new CycleButton("RIGHT", letterRight, letterHRight);
		b.add(buttonSpace);
		b.add(buttonRight);
			
	   
		buttonDel = new CycleButton("DEL",letterDel, letterHDel);
		buttonLeft = new CycleButton("DEL",letterLeft, letterHLeft);
		
		for (int i=1; i<25;i++) {
				buttons[i].setPrevNext(buttons[i-1],buttons[i+1]);
			}
			buttonRight.setPrevNext(buttonSpace, buttons[0]);
			buttonSpace.setPrevNext(buttons[25], buttonRight);
			buttons[0].setPrevNext(buttonRight, buttons[1]);
			buttons[25].setPrevNext(buttons[24], buttonSpace);

		model.setSelected(buttons[0]);
		setKeyboard("TextKeyboard");
		
					
		 for (int i=0; i<10;++i){
				number[i] = new ImageIcon("Images/Test" + Integer.toString(i) + ".png"); //Creates the images
				hNumber[i] = new ImageIcon("Images/HTest" + Integer.toString(i) + ".png");
				numberButtons[i] = new CycleButton(Integer.toString(i), number[i], hNumber[i]);
			}
			test.gridx = 0;
			test.gridy = 0;
			c.add(numberButtons[1],test);
			
			test.gridx = 1;
			c.add(numberButtons[2],test);
			
			test.gridx = 2;
			c.add(numberButtons[3],test);
			
			test.gridx = 0;
			test.gridy = 1;
			c.add(numberButtons[4],test);
			
			test.gridx = 1;
			c.add(numberButtons[5],test);
		
			test.gridx = 2;
			c.add(numberButtons[6],test);
			
			test.gridx = 0;
			test.gridy = 2;
			c.add(numberButtons[7],test);
			
			test.gridx = 1;
			c.add(numberButtons[8],test);
			
			test.gridx = 2;
			c.add(numberButtons[9],test);
			
			test.gridx = 0;
			test.gridy = 3;
			c.add(numberButtons[0],test);
			
			test.gridx = 0;
			test.gridy = 4;
			c.add(buttonLeft, test);
			
			test.gridx = 1;
			test.gridy = 3;
			
			test.gridwidth = 2;
			test.gridheight = 2;
			c.add(buttonDel, test);
		 
		 for (int i=2; i<9;i++) {
				numberButtons[i].setPrevNext(numberButtons[i-1],numberButtons[i+1]);
			}
			numberButtons[0].setPrevNext(numberButtons[9], buttonLeft);
			buttonLeft.setPrevNext(numberButtons[0], buttonDel);
			numberButtons[1].setPrevNext(buttonDel, numberButtons[2]);
			buttonDel.setPrevNext(buttonLeft, numberButtons[1]);
			numberButtons[9].setPrevNext(numberButtons[8], numberButtons[0]);
			
			
	}
    public void setKeyboard( String type ){
		System.out.println(type);
		System.out.println(cards);
		System.out.println(cardLayout);
        switch (type) {
        case "TextKeyboard":
            break;
        
        case "NumberKeyboard":
            break;
    }
        cardLayout.show(cards, type);
    }
    @Override
    public void update(Observable o, Object arg) {
		
            display.setText("" +  arg);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void add(CardLayout card) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
