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
public class WhereToFrameView extends JPanel implements Observer {
    ImageIcon[] letter = new ImageIcon[26]; //Array of images  A-Z
    ImageIcon[] hLetter = new ImageIcon[26]; //Array of images A-Z (highlighted)
    CycleButton[] buttons = new CycleButton[26]; //Array of buttons
    static String abcd = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final JTextField display = new JTextField();
    final CycleButton buttonSpace = new CycleButton("SPACE");
    final CycleButton buttonRight = new CycleButton("RIGHT");
    CycleButton isSelected;
    
    public WhereToFrameView (Controller controller, WhereToFrameModel model){
    
    for(int i=0; i<26; i++) {
            letter[i] = new ImageIcon("Test" + abcd.charAt(i) + ".png"); //Creates the images
			hLetter[i] = new ImageIcon("HTest" + abcd.charAt(i) + ".png"); //Creates the highlighted images
			buttons[i] = new CycleButton(Character.toString(abcd.charAt(i))); //Creates the buttons
			add(buttons[i]);
        }
    for (int i=1; i<25;i++) {
			buttons[i].setPrevNext(buttons[i-1],buttons[i+1]);
		}
		buttonRight.setPrevNext(buttonSpace, buttons[0]);
		buttonSpace.setPrevNext(buttons[25], buttonRight);
		buttons[0].setPrevNext(buttonRight, buttons[1]);
		buttons[25].setPrevNext(buttons[24], buttonSpace);

                model.setSelected(buttons[0]);
    
        display.setBounds (95, 312, 260, 50); add(display);

        buttons[0].setBounds (95, 365, 65, 45);//x,y, width, length
        buttons[1].setBounds (160, 365, 65, 45);
        buttons[2].setBounds (225, 365, 65, 45);
        buttons[3].setBounds (290, 365, 65, 45);

        buttons[4].setBounds (95, 410, 65, 45);
        buttons[5].setBounds (160, 410, 65, 45);
        buttons[6].setBounds (225, 410, 65, 45);
        buttons[7].setBounds (290, 410, 65, 45);

        buttons[8].setBounds (95, 455, 65, 45);
        buttons[9].setBounds (160, 455, 65, 45);
        buttons[10].setBounds (225, 455, 65, 45);
        buttons[11].setBounds (290, 455, 65, 45);

        buttons[12].setBounds (95, 500, 65, 45);
        buttons[13].setBounds (160, 500, 65, 45);
        buttons[14].setBounds (225, 500, 65, 45);
        buttons[15].setBounds (290, 500, 65, 45);

        buttons[16].setBounds (95, 545, 65, 45);
        buttons[17].setBounds (160, 545, 65, 45);
        buttons[18].setBounds (225, 545, 65, 45);
        buttons[19].setBounds (290, 545, 65, 45);

        buttons[20].setBounds (95, 590, 65, 45);
        buttons[21].setBounds (160, 590, 65, 45);
        buttons[22].setBounds (225, 590, 65, 45);
        buttons[23].setBounds (290, 590, 65, 45);

        buttons[24].setBounds (95, 635, 65, 45);
        buttons[25].setBounds (160, 635, 65, 45);
        buttonSpace.setBounds (225, 635, 65, 45); add(buttonSpace);
        buttonRight.setBounds (290, 635, 65, 45); add(buttonRight);
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
