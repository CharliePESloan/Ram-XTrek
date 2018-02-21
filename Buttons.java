import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author User
 */

//iterate over an array list full of letters to move highlighted keyboard
//highlight are buttons only rest are images
//ignore the images
public class Buttons extends JFrame {
    
    public String textDisplay = "";
    //array of buttons
    ImageIcon[] letter = new ImageIcon[28]; 
    static String abcd = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
/*
    ImageIcon A = new ImageIcon("TestA.png");
    ImageIcon B = new ImageIcon("TestB.png");
    ImageIcon C = new ImageIcon("TestC.png");
    ImageIcon D = new ImageIcon("TestD.png");
    ImageIcon E = new ImageIcon("TestE.png");
    ImageIcon F = new ImageIcon("TestF.png");
    ImageIcon G = new ImageIcon("TestG.png");
    ImageIcon H = new ImageIcon("TestH.png");
    ImageIcon I = new ImageIcon("TestI.png");
    ImageIcon J = new ImageIcon("TestJ.png");
    ImageIcon K = new ImageIcon("TestK.png");
    ImageIcon L = new ImageIcon("TestL.png");
    ImageIcon M = new ImageIcon("TestM.png");
    ImageIcon N = new ImageIcon("TestN.png");
    ImageIcon O = new ImageIcon("TestO.png");
    ImageIcon P = new ImageIcon("TestP.png");
    ImageIcon Q = new ImageIcon("TestQ.png");
    ImageIcon R = new ImageIcon("TestR.png");
    ImageIcon S = new ImageIcon("TestS.png");
    ImageIcon T = new ImageIcon("TestT.png");
    ImageIcon U = new ImageIcon("TestU.png");
    ImageIcon V = new ImageIcon("TestV.png");
    ImageIcon W = new ImageIcon("TestW.png");
    ImageIcon X = new ImageIcon("TestX.png");
    ImageIcon Y = new ImageIcon("TestY.png");
    ImageIcon Z = new ImageIcon("TestZ.png");
  */  
    ImageIcon SPACE = new ImageIcon("Space.png");
    ImageIcon RIGHT = new ImageIcon("Right.png");
    
    ImageIcon HA = new ImageIcon("HTestA.png");
    ImageIcon HB = new ImageIcon("HTestB.png");
    ImageIcon HC = new ImageIcon("HTestC.png");
    ImageIcon HD = new ImageIcon("HTestD.png");
    ImageIcon HE = new ImageIcon("HTestE.png");
    ImageIcon HF = new ImageIcon("HTestF.png");
    ImageIcon HG = new ImageIcon("HTestG.png");
    ImageIcon HH = new ImageIcon("HTestH.png");
    ImageIcon HI = new ImageIcon("HTestI.png");
    ImageIcon HJ = new ImageIcon("HTestJ.png");
    ImageIcon HK = new ImageIcon("HTestK.png");
    ImageIcon HL = new ImageIcon("HTestL.png");
    ImageIcon HM = new ImageIcon("HTestM.png");
    ImageIcon HN = new ImageIcon("HTestN.png");
    ImageIcon HO = new ImageIcon("HTestO.png");
    ImageIcon HP = new ImageIcon("HTestP.png");
    ImageIcon HQ = new ImageIcon("HTestQ.png");
    ImageIcon HR = new ImageIcon("HTestR.png");
    ImageIcon HS = new ImageIcon("HTestS.png");
    ImageIcon HT = new ImageIcon("HTestT.png");
    ImageIcon HU = new ImageIcon("HTestU.png");
    ImageIcon HV = new ImageIcon("HTestV.png");
    ImageIcon HW = new ImageIcon("HTestW.png");
    ImageIcon HX = new ImageIcon("HTestX.png");
    ImageIcon HY = new ImageIcon("HTestY.png");
    ImageIcon HZ = new ImageIcon("HTestZ.png");
    
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
        }
    }
    
    
//display
    public Buttons() {
        for(int i=0; i<26; i++) {
            letter[i] = new ImageIcon("Test" + abcd.charAt(i) + ".png");
        }
        
        setTitle("GPS");
        setContentPane(new JLabel( new ImageIcon("border.png") ) );
        setLayout(null);
        
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
            if (buttonB.getIcon() == HB){
                buttonB.setIcon(letter[1]);
                buttonC.setIcon(HC);
            }
            else if(buttonA.getIcon() == HA) {
                buttonA.setIcon(letter[0]);
                buttonB.setIcon(HB);
            }
            else if(buttonC.getIcon() == HC) {
                buttonC.setIcon(letter[2]);
                buttonD.setIcon(HD);
            }
            else if(buttonD.getIcon() == HD) {
                buttonD.setIcon(letter[3]);
                buttonE.setIcon(HE);
            }
            else if(buttonE.getIcon() == HE) {
                buttonE.setIcon(letter[4]);
                buttonF.setIcon(HF);
            }
            else if(buttonF.getIcon() == HF) {
                buttonF.setIcon(letter[5]);
                buttonG.setIcon(HG);
            }
            else if(buttonG.getIcon() == HG) {
                buttonG.setIcon(letter[6]);
                buttonH.setIcon(HH);
            }
            else if(buttonH.getIcon() == HH) {
                buttonH.setIcon(letter[7]);
                buttonI.setIcon(HI);
            }
            else if(buttonI.getIcon() == HI) {
                buttonI.setIcon(letter[8]);
                buttonJ.setIcon(HJ);
            }
            else if(buttonJ.getIcon() == HJ) {
                buttonJ.setIcon(letter[9]);
                buttonK.setIcon(HK);
            }
            else if(buttonK.getIcon() == HK) {
                buttonK.setIcon(letter[10]);
                buttonL.setIcon(HL);
            }
            else if(buttonL.getIcon() == HL) {
                buttonL.setIcon(letter[11]);
                buttonM.setIcon(HM);
            }
            else if(buttonM.getIcon() == HM) {
                buttonM.setIcon(letter[12]);
                buttonN.setIcon(HN);
            }
            else if(buttonN.getIcon() == HN) {
                buttonN.setIcon(letter[13]);
                buttonO.setIcon(HO);
            }
            else if(buttonO.getIcon() == HO) {
                buttonO.setIcon(letter[14]);
                buttonP.setIcon(HP);
            }
            else if(buttonP.getIcon() == HP) {
                buttonP.setIcon(letter[15]);
                buttonQ.setIcon(HQ);
            }
            else if(buttonQ.getIcon() == HQ) {
                buttonQ.setIcon(letter[16]);
                buttonR.setIcon(HR);
            }
            else if(buttonR.getIcon() == HR) {
                buttonR.setIcon(letter[17]);
                buttonS.setIcon(HS);
            }
            else if(buttonS.getIcon() == HS) {
                buttonS.setIcon(letter[18]);
                buttonT.setIcon(HT);
            }
            else if(buttonT.getIcon() == HT) {
                buttonT.setIcon(letter[19]);
                buttonU.setIcon(HU);
            }
            else if(buttonU.getIcon() == HU) {
                buttonU.setIcon(letter[20]);
                buttonV.setIcon(HV);
            }
            else if(buttonV.getIcon() == HV) {
                buttonV.setIcon(letter[21]);
                buttonW.setIcon(HW);
            }
            else if(buttonW.getIcon() == HW) {
                buttonW.setIcon(letter[22]);
                buttonX.setIcon(HX);
            }
            else if(buttonX.getIcon() == HX) {
                buttonX.setIcon(letter[23]);
                buttonY.setIcon(HY);
            }
            else if(buttonY.getIcon() == HY) {
                buttonY.setIcon(letter[24]);
                buttonZ.setIcon(HZ);
            }
            else if(buttonZ.getIcon() == HZ) {
                buttonZ.setIcon(letter[25]);
                buttonSpace.setIcon(HSPACE);
            }
            else if(buttonSpace.getIcon() == HSPACE) {
                buttonSpace.setIcon(SPACE);
                buttonRight.setIcon(HRIGHT);
            }
            else if(buttonRight.getIcon() == HRIGHT) {
                buttonRight.setIcon(RIGHT);
                buttonA.setIcon(HA);
            }
            else {
                buttonA.setIcon(HA);
            }
        }
    });
        
        buttonMinus.addMouseListener( new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (buttonB.getIcon() == HB){
                buttonB.setIcon(letter[1]);
                buttonA.setIcon(HA);
            }
                else if(buttonA.getIcon() == HA) {
                buttonA.setIcon(letter[0]);
                buttonRight.setIcon(HRIGHT);
            }
                else if(buttonC.getIcon() == HC) {
                buttonC.setIcon(letter[2]);
                buttonB.setIcon(HB);
            }
            else if(buttonD.getIcon() == HD) {
                buttonD.setIcon(letter[3]);
                buttonC.setIcon(HC);
            }
            else if(buttonE.getIcon() == HE) {
                buttonE.setIcon(letter[4]);
                buttonD.setIcon(HD);
            }
            else if(buttonF.getIcon() == HF) {
                buttonF.setIcon(letter[5]);
                buttonE.setIcon(HE);
            }
            else if(buttonG.getIcon() == HG) {
                buttonG.setIcon(letter[6]);
                buttonF.setIcon(HF);
            }
            else if(buttonH.getIcon() == HH) {
                buttonH.setIcon(letter[7]);
                buttonG.setIcon(HG);
            }
            else if(buttonI.getIcon() == HI) {
                buttonI.setIcon(letter[8]);
                buttonH.setIcon(HH);
            }
            else if(buttonJ.getIcon() == HJ) {
                buttonJ.setIcon(letter[9]);
                buttonI.setIcon(HI);
            }
            else if(buttonK.getIcon() == HK) {
                buttonK.setIcon(letter[10]);
                buttonJ.setIcon(HJ);
            }
            else if(buttonL.getIcon() == HL) {
                buttonL.setIcon(letter[11]);
                buttonK.setIcon(HK);
            }
            else if(buttonM.getIcon() == HM) {
                buttonM.setIcon(letter[12]);
                buttonL.setIcon(HL);
            }
            else if(buttonN.getIcon() == HN) {
                buttonN.setIcon(letter[13]);
                buttonM.setIcon(HM);
            }
            else if(buttonO.getIcon() == HO) {
                buttonO.setIcon(letter[14]);
                buttonN.setIcon(HN);
            }
            else if(buttonP.getIcon() == HP) {
                buttonP.setIcon(letter[15]);
                buttonO.setIcon(HO);
            }
            else if(buttonQ.getIcon() == HQ) {
                buttonQ.setIcon(letter[16]);
                buttonP.setIcon(HP);
            }
            else if(buttonR.getIcon() == HR) {
                buttonR.setIcon(letter[17]);
                buttonQ.setIcon(HQ);
            }
            else if(buttonS.getIcon() == HS) {
                buttonS.setIcon(letter[18]);
                buttonR.setIcon(HR);
            }
            else if(buttonT.getIcon() == HT) {
                buttonT.setIcon(letter[19]);
                buttonS.setIcon(HS);
            }
            else if(buttonU.getIcon() == HU) {
                buttonU.setIcon(letter[20]);
                buttonT.setIcon(HT);
            }
            else if(buttonV.getIcon() == HV) {
                buttonV.setIcon(letter[21]);
                buttonU.setIcon(HU);
            }
            else if(buttonW.getIcon() == HW) {
                buttonW.setIcon(letter[22]);
                buttonV.setIcon(HV);
            }
            else if(buttonX.getIcon() == HX) {
                buttonX.setIcon(letter[23]);
                buttonW.setIcon(HW);
            }
            else if(buttonY.getIcon() == HY) {
                buttonY.setIcon(letter[24]);
                buttonX.setIcon(HX);
            }
            else if(buttonZ.getIcon() == HZ) {
                buttonZ.setIcon(letter[25]);
                buttonY.setIcon(HY);
            }
            else if(buttonSpace.getIcon() == HSPACE) {
                buttonSpace.setIcon(SPACE);
                buttonZ.setIcon(HZ);
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
               if (buttonA.getIcon() == HA) {
               textDisplay += "A";
               display.setText("" + textDisplay);
               }
               else if(buttonB.getIcon() == HB) {
               textDisplay += "B";
               display.setText("" + textDisplay);
               }
               else if(buttonC.getIcon() == HC) {
               textDisplay += "C";
               display.setText("" + textDisplay);
               }
               else if(buttonD.getIcon() == HD) {
               textDisplay += "D";
               display.setText("" + textDisplay);
               }
               else if(buttonE.getIcon() == HE) {
               textDisplay += "E";
               display.setText("" + textDisplay);
               }
               else if(buttonF.getIcon() == HF) {
               textDisplay += "F";
               display.setText("" + textDisplay);
               }
               else if(buttonG.getIcon() == HG) {
               textDisplay += "G";
               display.setText("" + textDisplay);
               }else if(buttonH.getIcon() == HH) {
               textDisplay += "H";
               display.setText("" + textDisplay);
               }
               else if(buttonI.getIcon() == HI) {
               textDisplay += "I";
               display.setText("" + textDisplay);
               }
               else if(buttonJ.getIcon() == HJ) {
               textDisplay += "J";
               display.setText("" + textDisplay);
               }
               else if(buttonK.getIcon() == HK) {
               textDisplay += "K";
               display.setText("" + textDisplay);
               }
               else if(buttonL.getIcon() == HL) {
               textDisplay += "L";
               display.setText("" + textDisplay);
               }
               else if(buttonM.getIcon() == HM) {
               textDisplay += "M";
               display.setText("" + textDisplay);
               }
               else if(buttonN.getIcon() == HN) {
               textDisplay += "N";
               display.setText("" + textDisplay);
               }
               else if(buttonO.getIcon() == HO) {
               textDisplay += "O";
               display.setText("" + textDisplay);
               }
               else if(buttonP.getIcon() == HP) {
               textDisplay += "P";
               display.setText("" + textDisplay);
               }
               else if(buttonQ.getIcon() == HQ) {
               textDisplay += "Q";
               display.setText("" + textDisplay);
               }
               else if(buttonR.getIcon() == HR) {
               textDisplay += "R";
               display.setText("" + textDisplay);
               }
               else if(buttonS.getIcon() == HS) {
               textDisplay += "S";
               display.setText("" + textDisplay);
               }
               else if(buttonT.getIcon() == HT) {
               textDisplay += "T";
               display.setText("" + textDisplay);
               }
               else if(buttonU.getIcon() == HU) {
               textDisplay += "U";
               display.setText("" + textDisplay);
               }
               else if(buttonV.getIcon() == HV) {
               textDisplay += "V";
               display.setText("" + textDisplay);
               }
               else if(buttonW.getIcon() == HW) {
               textDisplay += "W";
               display.setText("" + textDisplay);
               }else if(buttonX.getIcon() == HX) {
               textDisplay += "X";
               display.setText("" + textDisplay);
               }
               else if(buttonY.getIcon() == HY) {
               textDisplay += "Y";
               display.setText("" + textDisplay);
               }
               else if(buttonZ.getIcon() == HZ) {
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
    }
                
    public static void main( String[] argv) {
        JFrame frame = new Buttons();
        frame.setLocationRelativeTo(null);
        frame.setSize(450, 835);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
