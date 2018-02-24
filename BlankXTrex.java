import java.awt.Color;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

public class BlankXTrex extends JFrame {
    // The class for navigation buttons
    public class SideButton extends JButton
    {
        SideButton(String s)
        {
            setIcon(new ImageIcon("Images/" + s + ".png"));
            setBorder( null );
            addMouseListener(new java.awt.event.MouseAdapter()
            {
                public void mouseEntered(java.awt.event.MouseEvent evt)
                {
                    setIcon(new ImageIcon("Images/" + s + "Selected" + ".png"));
                }
                public void mouseExited(java.awt.event.MouseEvent evt)
                {
                    setIcon(new ImageIcon("Images/" + s + ".png"));
                }
            });
        }
    }
    final SideButton    PlusButton    = new SideButton("PlusButton");
    final SideButton    MinusButton   = new SideButton("MinusButton");
    final SideButton    SelectButton  = new SideButton("SelectButton");
    final SideButton    MenuButton    = new SideButton("MenuButton");

    /*public void windowOpened(WindowEvent e) {}
    WindowAdapter adapter new WindowAdapter()
    {
        public void windowClosing(WindowEvent e)
        {
            System.out.println("Closing");
            System.exit(0);
        }
        public void windowClosed(WindowEvent e) {
            System.out.println("Closed");
        }
        public void windowActivated(WindowEvent e) {}
        public void windowDeactivated(WindowEvent e) {}
        public void windowIconified(WindowEvent e) {}
        public void windowDeiconified(WindowEvent e) {}
    };
    addWindowListener(adapter);*/

    public BlankXTrex()
    {
        setTitle( "XTrex" );
        setContentPane( new JLabel( new ImageIcon( "Images/XTrex Background.png" ) ) );
        setLayout( null );
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setLocation((screenWidth / 3)+150, (screenHeight / 4)-150);
        setSize( 450, 835 ); /* title bar! */
        setResizable( false );
        setVisible( true );

        WindowAdapter adapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("Closing");
                System.exit(0);
            }
        };
        addWindowListener(adapter);

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
