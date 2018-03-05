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

/** 
This class is the superclass to all frames.
It is meant to contain all of the things common to all frames, 
to reduce duplicate code. 
*/

public class BlankXTrex extends JFrame{
    // The class for navigation buttons
    public class SideButton extends JButton
    {
        SideButton(String s)
        {
            setIcon(new ImageIcon(s + ".png"));
            setBorder( null );
            setText(s);
            addMouseListener(new java.awt.event.MouseAdapter()
            {
                public void mouseEntered(java.awt.event.MouseEvent evt)
                {
                    setIcon(new ImageIcon(s + "Selected" + ".png"));
                }
                public void mouseExited(java.awt.event.MouseEvent evt)
                {
                    setIcon(new ImageIcon(s + ".png"));
                }
            });
        }
    }
    final JButton    PlusButton    = new JButton("PlusButton");
    final JButton    MinusButton   = new JButton("MinusButton");
    final JButton    SelectButton  = new JButton("SelectButton");
    final JButton    MenuButton    = new JButton("MenuButton");


    public BlankXTrex()
    {
        setTitle( "XTrex" );
        setContentPane( new JLabel( new ImageIcon( "XTrex Background.png" ) ) );
        setLayout( null );
        setVisible(true);

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
    
   public static void main(String[] args){
       BlankXTrex onoff = new BlankXTrex();
   }
}
