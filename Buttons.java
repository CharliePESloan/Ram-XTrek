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
    
    

    ImageIcon A = new ImageIcon("TestA.png");
    ImageIcon B = new ImageIcon("TestB.png");
    ImageIcon HA = new ImageIcon("HTestA.png");
    ImageIcon HB = new ImageIcon("HTestB.png");
    
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
    //final SelectButton select = new SelectButton("SEL");
    //final ArrowButton leftArrow = new ArrowButton("LEFT");
    
    
    final SideButton buttonPlus = new SideButton("PLUS");
    final SideButton buttonMinus = new SideButton("MINUS");
    
    

    
    public class SideButton extends JButton {
        SideButton(String s) {
            setIcon( new ImageIcon(s + ".png"));
            setBorder(null);
            addMouseListener (new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent me) {
                  setIcon(new ImageIcon(s + "Selected.png"));
                    
                }
                public void mouseExited(java.awt.event.MouseEvent me) {
                    setIcon(new ImageIcon(s + ".png"));
                }
            });
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
    
    
//display
    public Buttons() {
        setTitle("GPS");
        setContentPane(new JLabel( new ImageIcon("border.png") ) );
        setLayout(null);
        
        display.setBounds (95, 312, 260, 50); add(display);//length, width, stretch left/right, up/down
        buttonPlus.setBounds (9, 102, 30, 68); add(buttonPlus);
        
        buttonA.setBounds (95, 365, 65, 45); add(buttonA);
        buttonB.setBounds (160, 365, 65, 45); add(buttonB);
        buttonC.setBounds (225, 365, 65, 45); add(buttonC);
        buttonD.setBounds (290, 365, 65, 45); add(buttonD);//gap of 6
        
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
        //buttonD.setBounds (20, 20, 20, 45); add(buttonD);
        //buttonD.setBounds (20, 20, 20, 45); add(buttonD);
        
        buttonPlus.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked( java.awt.event.MouseEvent me) {
            if (buttonB.getIcon() == HB){
                buttonB.setIcon(B);
                buttonA.setIcon(HA);
            }
            else if(buttonA.getIcon() == HA) {
                buttonA.setIcon(A);
                buttonB.setIcon(HB);
            }
            else {
                buttonA.setIcon(HA);
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
