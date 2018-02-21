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
        
        PlusButton.addMouseListener(new java.awt.event.MouseAdapter() {
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
        
        MenuButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                dispose();
                new XTrex();
            }
        });
        // put the other packages' code here
    }
  }
