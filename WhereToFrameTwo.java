import java.awt.Color;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.*;
import javax.swing.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class WhereToFrameTwo extends BlankXTrex {
    
    final TextField display = new TextField();
    TextButton[] numberButtons = new TextButton[10];
    ExtractButton selected;
    public String numberDisplay ="";
    
    final TextButton buttonLeft = new TextButton("LEFT");
    final TextButton buttonDel = new TextButton("DEL");
    
    
    public static String removeChar(String s, int a) {
        return s.substring(0, a) + s.substring(a + 1);
    }
    
    	public class TextButton extends ExtractButton {
        TextButton (String s) {
            super(s);
            setIcon( new ImageIcon("Images/Test" + s + ".png")); //Sets the images to each button
            setBorder(null);
        }
    }
        
                public class ExtractButton extends JButton {
		boolean selected;
		ExtractButton prevButton;
		ExtractButton nextButton;
		ExtractButton(String s)
		{
			selected = false;
			setIcon( new ImageIcon("Images/Test" + s + ".png"));
                        setBorder(null);
			setBorder( BorderFactory.createLineBorder(Color.black, 3 ) );
			setBackground(Color.black);
			setText(s);
		}
		public void setPrevNext(ExtractButton prev,ExtractButton next)
		{
			prevButton = prev;
			nextButton = next;
		}
		public void select() //highlights the selected button
		{
			selected = true;
			setIcon(new ImageIcon("Images/HTest" + getText() + ".png"));
		}
		public void deselect() //sets the button back to it's original image
		{
			selected = false;
			setIcon(new ImageIcon("Images/Test" + getText() + ".png"));
		}
		public ExtractButton prev()
		{
			deselect();
			prevButton.select(); //Selects the previous button
			return prevButton;
		}
		public ExtractButton next()
		{
			deselect();
			nextButton.select(); //selects the next button
			return nextButton;
		}
	}
    
    public WhereToFrameTwo() {
        super();
        
        for (int i=0; i<10;++i){
            numberButtons[i] = new TextButton(Integer.toString(i));
            add(numberButtons[i]);
        }
        PlusButton.setBounds(9, 102, 30, 68);add(PlusButton);
        MinusButton.setBounds(11, 175, 27, 64);add(MinusButton);
        SelectButton.setBounds(5, 268, 34, 97); add(SelectButton);
        MenuButton.setBounds(409, 113, 30,84); add(MenuButton);
        display.setBounds (95, 312, 260, 50); add(display);
        
        for (int i=2; i<9;i++) {
			numberButtons[i].setPrevNext(numberButtons[i-1],numberButtons[i+1]);
		}
        numberButtons[0].setPrevNext(numberButtons[9], buttonLeft);
        buttonLeft.setPrevNext(numberButtons[0], buttonDel);
        numberButtons[1].setPrevNext(buttonDel, numberButtons[2]);
        buttonDel.setPrevNext(buttonLeft, numberButtons[1]);
        numberButtons[9].setPrevNext(numberButtons[8], numberButtons[0]);
        
        selected = (ExtractButton) numberButtons[1];
        numberButtons[1].select();
                                
        numberButtons[1].setBounds (95, 365, 86, 63);
        numberButtons[2].setBounds (181, 365, 86, 63);
        numberButtons[3].setBounds (267, 365, 86, 63);
        
        numberButtons[4].setBounds (95, 428, 86, 63);
        numberButtons[5].setBounds (181, 428, 86, 63);
        numberButtons[6].setBounds (267, 428, 86, 63);
        
        numberButtons[7].setBounds (95, 491, 86, 63);
        numberButtons[8].setBounds (181, 491, 86, 63);
        numberButtons[9].setBounds (267, 491, 86, 63);
        
        numberButtons[0].setBounds (95, 554, 86, 63);
        buttonLeft.setBounds(95, 617, 86, 63); add(buttonLeft);
        buttonDel.setBounds(181, 554, 172, 126); add(buttonDel);
        
        
        MenuButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                dispose();
                new XTrex();
            }
        });
        
        PlusButton.addMouseListener(new MouseAdapter() {
        public void mouseClicked( MouseEvent me) {
			selected = selected.next();
		}
    });

        MinusButton.addMouseListener( new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                selected = selected.prev();
            }
        });
        SelectButton.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent me) {
                        if (selected == buttonLeft) {
                            dispose();
                            new WhereToFrame();
                        }
                        if (selected == buttonDel) {
                            if (numberDisplay.length() != 0){
                                numberDisplay = removeChar(numberDisplay, numberDisplay.length()-1);
                                display.setText("" + numberDisplay);
                            }
                        }
			else {
                            numberDisplay += selected.getText();
                            display.setText("" + numberDisplay);
                        }
           }
});
    }
}