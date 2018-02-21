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

public class WhereToFrame extends BlankXTrex {
    
    public String textDisplay = "";
    //array of buttons
    ImageIcon[] letter = new ImageIcon[26];
    ImageIcon[] hLetter = new ImageIcon[26];
    static String abcd = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    
    ImageIcon SPACE = new ImageIcon("Space.png");
    ImageIcon RIGHT = new ImageIcon("Right.png");
    
    ImageIcon HSPACE = new ImageIcon("HSpace.png");
    ImageIcon HRIGHT = new ImageIcon("HRight.png");
    
    final TextField display = new TextField();
    
    final TextButton buttonA = new TextButton("A");
    final TextButton buttonB = new TextButton("B");
    final TextButton buttonC = new TextButton("C");
    final TextButton buttonD = new TextButton("D");
    
    final TextButton buttonE = new TextButton("E");
    final TextButton buttonF = new TextButton("F");
    final TextButton buttonG = new TextButton("G");
    final TextButton buttonH = new TextButton("H");
    
    final TextButton buttonI = new TextButton("I");
    final TextButton buttonJ = new TextButton("J");
    final TextButton buttonK = new TextButton("K");
    final TextButton buttonL = new TextButton("L");
    
    final TextButton buttonM = new TextButton("M");
    final TextButton buttonN = new TextButton("N");
    final TextButton buttonO = new TextButton("O");
    final TextButton buttonP = new TextButton("P");
    
    final TextButton buttonQ = new TextButton("Q");
    final TextButton buttonR = new TextButton("R");
    final TextButton buttonS = new TextButton("S");
    final TextButton buttonT = new TextButton("T");
    
    final TextButton buttonU = new TextButton("U");
    final TextButton buttonV = new TextButton("V");
    final TextButton buttonW = new TextButton("W");
    final TextButton buttonX = new TextButton("X");
    
    final TextButton buttonY = new TextButton("Y");
    final TextButton buttonZ = new TextButton("Z");
    final SpaceButton buttonSpace = new SpaceButton("SPACE");
    final ArrowButton buttonRight = new ArrowButton("RIGHT");
    
    
    final SideButton buttonPlus = new SideButton("PLUS");
    final SideButton buttonMinus = new SideButton("MINUS");
    final SelectButton buttonSelect = new SelectButton("SEL");
    

    
    public class SideButton extends JButton {
        SideButton(String s) {
            setIcon( new ImageIcon(s + ".png"));
            setBorder(null);
            addMouseListener (new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                  setIcon(new ImageIcon(s + "Selected.png"));  
                }
                public void mouseExited(MouseEvent me) {
                    setIcon(new ImageIcon(s + ".png"));
                }
            });
        }
    }
    
    
    public class SelectButton extends JButton {
        SelectButton (String s) {
            setIcon( new ImageIcon(s + ".png"));
            setBorder(null);
            addMouseListener (new java.awt.event.MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                  setIcon(new ImageIcon(s + "Selected.png"));
                }
                public void mouseExited(MouseEvent me) {
                    setIcon(new ImageIcon(s + ".png"));
                }
            });
        }
    }
    
    public class SpaceButton extends JButton {
        SpaceButton (String s) {
            setIcon( new ImageIcon(s + ".png"));
            setBorder(null);
        }
    }
    
    public class ArrowButton extends JButton {
        ArrowButton (String s) {
            setIcon( new ImageIcon(s + ".png"));
            setBorder(null);
        }
    }
    
    

        public class TextButton extends JButton {
        TextButton (String s) {
            setIcon( new ImageIcon("Test" + s + ".png"));
            setBorder(null);
            addMouseListener (new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent me){
            setIcon (new ImageIcon("HTest" + s + ".png"));
            }
            public void mouseExited(java.awt.event.MouseEvent me) {
               setIcon(new ImageIcon ("Test" + s + ".png"));
            }
            });
        }
    }
    
    public WhereToFrame(){
        setTitle( "XTrex" );
        setContentPane( new JLabel( new ImageIcon( "XTrex Background.png" ) ) );
        setLayout( null );
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setLocation((screenWidth / 3)+150, (screenHeight / 4)-150);
        setSize( 450, 835 ); /* title bar! */ 
        setResizable( false );
        setVisible( true );
        
        PlusButton.setBounds(9, 102, 30, 68);add(PlusButton);
        MinusButton.setBounds(11, 175, 27, 64);add(MinusButton);
        SelectButton.setBounds(5, 268, 34, 97); add(SelectButton);
        MenuButton.setBounds(409, 113, 30,84); add(MenuButton);
        
        for(int i=0; i<26; i++) {
            letter[i] = new ImageIcon("Test" + abcd.charAt(i) + ".png");
        }
        for(int i=0; i<26; i++) {
            hLetter[i] = new ImageIcon("HTest" + abcd.charAt(i) + ".png");
        }
        
        display.setBounds (95, 312, 260, 50); add(display);//length, width, stretch left/right, up/down
        buttonPlus.setBounds (9, 102, 30, 68); add(buttonPlus);
        buttonMinus.setBounds (11, 175, 27, 64); add(buttonMinus);
        buttonSelect.setBounds (8, 272,29, 72); add(buttonSelect);
        
        buttonA.setBounds (95, 365, 65, 45); add(buttonA);
        buttonB.setBounds (160, 365, 65, 45); add(buttonB);
        buttonC.setBounds (225, 365, 65, 45); add(buttonC);
        buttonD.setBounds (290, 365, 65, 45); add(buttonD);
        
        buttonE.setBounds (95, 410, 65, 45); add(buttonE);
        buttonF.setBounds (160, 410, 65, 45); add(buttonF);
        buttonG.setBounds (225, 410, 65, 45); add(buttonG);
        buttonH.setBounds (290, 410, 65, 45); add(buttonH);
        
        buttonI.setBounds (95, 455, 65, 45); add(buttonI);
        buttonJ.setBounds (160, 455, 65, 45); add(buttonJ);
        buttonK.setBounds (225, 455, 65, 45); add(buttonK);
        buttonL.setBounds (290, 455, 65, 45); add(buttonL);
        
        buttonM.setBounds (95, 500, 65, 45); add(buttonM);
        buttonN.setBounds (160, 500, 65, 45); add(buttonN);
        buttonO.setBounds (225, 500, 65, 45); add(buttonO);
        buttonP.setBounds (290, 500, 65, 45); add(buttonP);
        
        buttonQ.setBounds (95, 545, 65, 45); add(buttonQ);
        buttonR.setBounds (160, 545, 65, 45); add(buttonR);
        buttonS.setBounds (225, 545, 65, 45); add(buttonS);
        buttonT.setBounds (290, 545, 65, 45); add(buttonT);
        
        buttonU.setBounds (95, 590, 65, 45); add(buttonU);
        buttonV.setBounds (160, 590, 65, 45); add(buttonV);
        buttonW.setBounds (225, 590, 65, 45); add(buttonW);
        buttonX.setBounds (290, 590, 65, 45); add(buttonX);
        
        buttonY.setBounds (95, 635, 65, 45); add(buttonY);
        buttonZ.setBounds (160, 635, 65, 45); add(buttonZ);
        buttonSpace.setBounds (225, 635, 65, 45); add(buttonSpace);
        buttonRight.setBounds (290, 635, 65, 45); add(buttonRight);
        
        //change the button icon 
        
        buttonPlus.addMouseListener(new MouseAdapter() {
        public void mouseClicked( MouseEvent me) {
            if (buttonB.getIcon() == hLetter[1]){
                buttonB.setIcon(letter[1]);
                buttonC.setIcon(hLetter[2]);
            }
            else if(buttonA.getIcon() == hLetter[0]) {
                buttonA.setIcon(letter[0]);
                buttonB.setIcon(hLetter[1]);
            }
            else if(buttonC.getIcon() == hLetter[2]) {
                buttonC.setIcon(letter[2]);
                buttonD.setIcon(hLetter[3]);
            }
            else if(buttonD.getIcon() == hLetter[3]) {
                buttonD.setIcon(letter[3]);
                buttonE.setIcon(hLetter[4]);
            }
            else if(buttonE.getIcon() == hLetter[4]) {
                buttonE.setIcon(letter[4]);
                buttonF.setIcon(hLetter[5]);
            }
            else if(buttonF.getIcon() == hLetter[5]) {
                buttonF.setIcon(letter[5]);
                buttonG.setIcon(hLetter[6]);
            }
            else if(buttonG.getIcon() == hLetter[6]) {
                buttonG.setIcon(letter[6]);
                buttonH.setIcon(hLetter[7]);
            }
            else if(buttonH.getIcon() == hLetter[7]) {
                buttonH.setIcon(letter[7]);
                buttonI.setIcon(hLetter[8]);
            }
            else if(buttonI.getIcon() == hLetter[8]) {
                buttonI.setIcon(letter[8]);
                buttonJ.setIcon(hLetter[9]);
            }
            else if(buttonJ.getIcon() == hLetter[9]) {
                buttonJ.setIcon(letter[9]);
                buttonK.setIcon(hLetter[10]);
            }
            else if(buttonK.getIcon() == hLetter[10]) {
                buttonK.setIcon(letter[10]);
                buttonL.setIcon(hLetter[11]);
            }
            else if(buttonL.getIcon() == hLetter[11]) {
                buttonL.setIcon(letter[11]);
                buttonM.setIcon(hLetter[12]);
            }
            else if(buttonM.getIcon() == hLetter[12]) {
                buttonM.setIcon(letter[12]);
                buttonN.setIcon(hLetter[13]);
            }
            else if(buttonN.getIcon() == hLetter[13]) {
                buttonN.setIcon(letter[13]);
                buttonO.setIcon(hLetter[14]);
            }
            else if(buttonO.getIcon() == hLetter[14]) {
                buttonO.setIcon(letter[14]);
                buttonP.setIcon(hLetter[15]);
            }
            else if(buttonP.getIcon() == hLetter[15]) {
                buttonP.setIcon(letter[15]);
                buttonQ.setIcon(hLetter[16]);
            }
            else if(buttonQ.getIcon() == hLetter[16]) {
                buttonQ.setIcon(letter[16]);
                buttonR.setIcon(hLetter[17]);
            }
            else if(buttonR.getIcon() == hLetter[17]) {
                buttonR.setIcon(letter[17]);
                buttonS.setIcon(hLetter[18]);
            }
            else if(buttonS.getIcon() == hLetter[18]) {
                buttonS.setIcon(letter[18]);
                buttonT.setIcon(hLetter[19]);
            }
            else if(buttonT.getIcon() == hLetter[19]) {
                buttonT.setIcon(letter[19]);
                buttonU.setIcon(hLetter[20]);
            }
            else if(buttonU.getIcon() == hLetter[20]) {
                buttonU.setIcon(letter[20]);
                buttonV.setIcon(hLetter[21]);
            }
            else if(buttonV.getIcon() == hLetter[21]) {
                buttonV.setIcon(letter[21]);
                buttonW.setIcon(hLetter[22]);
            }
            else if(buttonW.getIcon() == hLetter[22]) {
                buttonW.setIcon(letter[22]);
                buttonX.setIcon(hLetter[23]);
            }
            else if(buttonX.getIcon() == hLetter[23]) {
                buttonX.setIcon(letter[23]);
                buttonY.setIcon(hLetter[24]);
            }
            else if(buttonY.getIcon() == hLetter[24]) {
                buttonY.setIcon(letter[24]);
                buttonZ.setIcon(hLetter[25]);
            }
            else if(buttonZ.getIcon() == hLetter[25]) {
                buttonZ.setIcon(letter[25]);
                buttonSpace.setIcon(HSPACE);
            }
            else if(buttonSpace.getIcon() == HSPACE) {
                buttonSpace.setIcon(SPACE);
                buttonRight.setIcon(HRIGHT);
            }
            else if(buttonRight.getIcon() == HRIGHT) {
                buttonRight.setIcon(RIGHT);
                buttonA.setIcon(hLetter[0]);
            }
            else {
                buttonA.setIcon(hLetter[0]);
            }
        }
    });
        
        buttonMinus.addMouseListener( new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (buttonB.getIcon() == hLetter[1]){
                buttonB.setIcon(letter[1]);
                buttonA.setIcon(hLetter[0]);
            }
                else if(buttonA.getIcon() == hLetter[0]) {
                buttonA.setIcon(letter[0]);
                buttonRight.setIcon(HRIGHT);
            }
                else if(buttonC.getIcon() == hLetter[2]) {
                buttonC.setIcon(letter[2]);
                buttonB.setIcon(hLetter[1]);
            }
            else if(buttonD.getIcon() == hLetter[3]) {
                buttonD.setIcon(letter[3]);
                buttonC.setIcon(hLetter[2]);
            }
            else if(buttonE.getIcon() == hLetter[4]) {
                buttonE.setIcon(letter[4]);
                buttonD.setIcon(hLetter[3]);
            }
            else if(buttonF.getIcon() == hLetter[5]) {
                buttonF.setIcon(letter[5]);
                buttonE.setIcon(hLetter[4]);
            }
            else if(buttonG.getIcon() == hLetter[6]) {
                buttonG.setIcon(letter[6]);
                buttonF.setIcon(hLetter[5]);
            }
            else if(buttonH.getIcon() == hLetter[7]) {
                buttonH.setIcon(letter[7]);
                buttonG.setIcon(hLetter[6]);
            }
            else if(buttonI.getIcon() == hLetter[8]) {
                buttonI.setIcon(letter[8]);
                buttonH.setIcon(hLetter[7]);
            }
            else if(buttonJ.getIcon() == hLetter[9]) {
                buttonJ.setIcon(letter[9]);
                buttonI.setIcon(hLetter[8]);
            }
            else if(buttonK.getIcon() == hLetter[10]) {
                buttonK.setIcon(letter[10]);
                buttonJ.setIcon(hLetter[9]);
            }
            else if(buttonL.getIcon() == hLetter[11]) {
                buttonL.setIcon(letter[11]);
                buttonK.setIcon(hLetter[10]);
            }
            else if(buttonM.getIcon() == hLetter[12]) {
                buttonM.setIcon(letter[12]);
                buttonL.setIcon(hLetter[11]);
            }
            else if(buttonN.getIcon() == hLetter[13]) {
                buttonN.setIcon(letter[13]);
                buttonM.setIcon(hLetter[12]);
            }
            else if(buttonO.getIcon() == hLetter[14]) {
                buttonO.setIcon(letter[14]);
                buttonN.setIcon(hLetter[13]);
            }
            else if(buttonP.getIcon() == hLetter[15]) {
                buttonP.setIcon(letter[15]);
                buttonO.setIcon(hLetter[14]);
            }
            else if(buttonQ.getIcon() == hLetter[16]) {
                buttonQ.setIcon(letter[16]);
                buttonP.setIcon(hLetter[15]);
            }
            else if(buttonR.getIcon() == hLetter[17]) {
                buttonR.setIcon(letter[17]);
                buttonQ.setIcon(hLetter[16]);
            }
            else if(buttonS.getIcon() == hLetter[18]) {
                buttonS.setIcon(letter[18]);
                buttonR.setIcon(hLetter[17]);
            }
            else if(buttonT.getIcon() == hLetter[19]) {
                buttonT.setIcon(letter[19]);
                buttonS.setIcon(hLetter[18]);
            }
            else if(buttonU.getIcon() == hLetter[20]) {
                buttonU.setIcon(letter[20]);
                buttonT.setIcon(hLetter[19]);
            }
            else if(buttonV.getIcon() == hLetter[21]) {
                buttonV.setIcon(letter[21]);
                buttonU.setIcon(hLetter[20]);
            }
            else if(buttonW.getIcon() == hLetter[22]) {
                buttonW.setIcon(letter[22]);
                buttonV.setIcon(hLetter[21]);
            }
            else if(buttonX.getIcon() == hLetter[23]) {
                buttonX.setIcon(letter[23]);
                buttonW.setIcon(hLetter[22]);
            }
            else if(buttonY.getIcon() == hLetter[24]) {
                buttonY.setIcon(letter[24]);
                buttonX.setIcon(hLetter[23]);
            }
            else if(buttonZ.getIcon() == hLetter[25]) {
                buttonZ.setIcon(letter[25]);
                buttonY.setIcon(hLetter[24]);
            }
            else if(buttonSpace.getIcon() == HSPACE) {
                buttonSpace.setIcon(SPACE);
                buttonZ.setIcon(hLetter[25]);
            }
            else if(buttonRight.getIcon() == HRIGHT) {
                buttonRight.setIcon(RIGHT);
                buttonSpace.setIcon(HSPACE);
            }
            else {
                buttonRight.setIcon(HRIGHT);
            }
            }
        });
        
        buttonSelect.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent me) {
               if (buttonA.getIcon() == hLetter[0]) {
               textDisplay += "A";
               display.setText("" + textDisplay);
               }
               else if(buttonB.getIcon() == hLetter[1]) {
               textDisplay += "B";
               display.setText("" + textDisplay);
               }
               else if(buttonC.getIcon() == hLetter[2]) {
               textDisplay += "C";
               display.setText("" + textDisplay);
               }
               else if(buttonD.getIcon() == hLetter[3]) {
               textDisplay += "D";
               display.setText("" + textDisplay);
               }
               else if(buttonE.getIcon() == hLetter[4]) {
               textDisplay += "E";
               display.setText("" + textDisplay);
               }
               else if(buttonF.getIcon() == hLetter[5]) {
               textDisplay += "F";
               display.setText("" + textDisplay);
               }
               else if(buttonG.getIcon() == hLetter[6]) {
               textDisplay += "G";
               display.setText("" + textDisplay);
               }else if(buttonH.getIcon() == hLetter[7]) {
               textDisplay += "H";
               display.setText("" + textDisplay);
               }
               else if(buttonI.getIcon() == hLetter[8]) {
               textDisplay += "I";
               display.setText("" + textDisplay);
               }
               else if(buttonJ.getIcon() == hLetter[9]) {
               textDisplay += "J";
               display.setText("" + textDisplay);
               }
               else if(buttonK.getIcon() == hLetter[10]) {
               textDisplay += "K";
               display.setText("" + textDisplay);
               }
               else if(buttonL.getIcon() == hLetter[11]) {
               textDisplay += "L";
               display.setText("" + textDisplay);
               }
               else if(buttonM.getIcon() == hLetter[12]) {
               textDisplay += "M";
               display.setText("" + textDisplay);
               }
               else if(buttonN.getIcon() == hLetter[13]) {
               textDisplay += "N";
               display.setText("" + textDisplay);
               }
               else if(buttonO.getIcon() == hLetter[14]) {
               textDisplay += "O";
               display.setText("" + textDisplay);
               }
               else if(buttonP.getIcon() == hLetter[15]) {
               textDisplay += "P";
               display.setText("" + textDisplay);
               }
               else if(buttonQ.getIcon() == hLetter[16]) {
               textDisplay += "Q";
               display.setText("" + textDisplay);
               }
               else if(buttonR.getIcon() == hLetter[17]) {
               textDisplay += "R";
               display.setText("" + textDisplay);
               }
               else if(buttonS.getIcon() == hLetter[18]) {
               textDisplay += "S";
               display.setText("" + textDisplay);
               }
               else if(buttonT.getIcon() == hLetter[19]) {
               textDisplay += "T";
               display.setText("" + textDisplay);
               }
               else if(buttonU.getIcon() == hLetter[20]) {
               textDisplay += "U";
               display.setText("" + textDisplay);
               }
               else if(buttonV.getIcon() == hLetter[21]) {
               textDisplay += "V";
               display.setText("" + textDisplay);
               }
               else if(buttonW.getIcon() == hLetter[22]) {
               textDisplay += "W";
               display.setText("" + textDisplay);
               }else if(buttonX.getIcon() == hLetter[23]) {
               textDisplay += "X";
               display.setText("" + textDisplay);
               }
               else if(buttonY.getIcon() == hLetter[24]) {
               textDisplay += "Y";
               display.setText("" + textDisplay);
               }
               else if(buttonZ.getIcon() == hLetter[25]) {
               textDisplay += "Z";
               display.setText("" + textDisplay);
               }
               else if(buttonSpace.getIcon() == HSPACE) {
               textDisplay += " ";
               display.setText("" + textDisplay);
               }
               else if(buttonRight.getIcon() == HRIGHT) {
               }
           } 
});
  }}
