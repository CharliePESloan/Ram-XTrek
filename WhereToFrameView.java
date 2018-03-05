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
    GridLayout background = new GridLayout(7,4);
    CardLayout card;
    Container d;
    JPanel a = new JPanel();
    JPanel b = new JPanel();
    GridLayout backgroundTwo = new GridLayout(2,0);
    
    
    
    public WhereToFrameView (Controller controller, WhereToFrameModel model){
    //d = getContentPane();
    //255,36
    a.setSize(255,36);
    b.setSize(255,252);
    add(a);
    add(b);
    a.add(display);
    b.setLayout(background);
    //setLayout(backgroundTwo);
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
    
        //display.setBounds (0, 0, 255, 40); 

        /*buttons[0].setBounds (-2, 40, 64, 40);//x,y, width, length
        buttons[1].setBounds (62, 40, 64, 40);//64,40
        buttons[2].setBounds (126, 40, 64, 40);
        buttons[3].setBounds (190, 40, 64, 40);

        buttons[4].setBounds (-2, 80, 64, 40);
        buttons[5].setBounds (62, 80, 64, 40);
        buttons[6].setBounds (126, 80, 64, 40);
        buttons[7].setBounds (190, 80, 64, 40);

        buttons[8].setBounds (-2, 120, 64, 40);
        buttons[9].setBounds (62, 120, 64, 40);
        buttons[10].setBounds (126, 120, 64, 40);
        buttons[11].setBounds (190, 120, 64, 40);

        buttons[12].setBounds (-2, 160, 64, 40);
        buttons[13].setBounds (62, 160, 64, 40);
        buttons[14].setBounds (126, 160, 64, 40);
        buttons[15].setBounds (190, 160, 64, 40);

        buttons[16].setBounds (-2, 200, 64, 40);
        buttons[17].setBounds (62, 200, 64, 40);
        buttons[18].setBounds (126, 200, 64, 40);
        buttons[19].setBounds (190, 200, 64, 40);

        buttons[20].setBounds (-2, 240, 64, 40);
        buttons[21].setBounds (62, 240, 64, 40);
        buttons[22].setBounds (126, 240, 64, 40);
        buttons[23].setBounds (190, 240, 64, 40);

        buttons[24].setBounds (-2, 280, 64, 40);
        buttons[25].setBounds (62, 280, 64, 40);
       */ 
        //buttonSpace.setBounds (126, 280, 64, 40); 
       b.add(buttonSpace);
        //buttonRight.setBounds (190, 280, 64, 40); 
        b.add(buttonRight);
    }

    @Override
    public void update(Observable o, Object arg) {
            display.setText("" + arg);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
