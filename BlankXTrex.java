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
/*
 * Simple calculator. David Wakeling, 03/01/2016.
 */
  public class BlankXTrex extends JFrame{
      public class SideButton extends JButton{
         SideButton(String s){
             setIcon(new ImageIcon(s + ".png"));
             setBorder( null );
             addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    setIcon(new ImageIcon(s + "Selected" + ".png"));
            }
             public void mouseExited(java.awt.event.MouseEvent evt) {
                setIcon(new ImageIcon(s + ".png"));
            }
            });

         }
     }
  final SideButton    PlusButton    = new SideButton("PlusButton");
  final SideButton    MinusButton   = new SideButton("MinusButton");
  final SideButton    SelectButton  = new SideButton("SelectButton");
  final SideButton    MenuButton    = new SideButton("MenuButton");

      
      public BlankXTrex() {
        setTitle( "XTrex" );
        //setContentPane( new JLabel( new ImageIcon( "XTrex Background.png" ) ) );
        setLayout( null );
        
        /**  
        PlusButton.setBounds(9, 102, 30, 68);add(PlusButton);
        MinusButton.setBounds(11, 175, 27, 64);add(MinusButton);
        SelectButton.setBounds(5, 268, 34, 97); add(SelectButton);
      
        SelectButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                System.out.println("You have clicked the select button!");
            }  
        });
      
        PlusButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("You have clicked the plus button!");
            }
        });
        MinusButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("You have clicked the minus button!");
            }
        });
        */
    }
  }

    


  //public class 