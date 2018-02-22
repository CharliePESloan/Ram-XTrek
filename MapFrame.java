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
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;

public class MapFrame extends BlankXTrex {
    
    final SideButton    PlusButton    = new BlankXTrex.SideButton("PlusButton");
    final SideButton    MinusButton   = new BlankXTrex.SideButton("MinusButton");
    final SideButton    SelectButton  = new BlankXTrex.SideButton("SelectButton");
    final SideButton    MenuButton    = new BlankXTrex.SideButton("MenuButton");
    
    public MapFrame(){
        setTitle( "XTrex" );
        //JLabel content = new JLabel(new ImageIcon("XTrex Background.png"));
        ///content.add("XTrex Background.png");
        //setContentPane(content);
        setLayout( null );
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setLocation((screenWidth / 3)+150, (screenHeight / 4)-150);
        setSize( 450, 835 ); /* title bar! */ 
        setResizable( false );
   
        Model model = new Model(0);
        Controller controller = new Controller(model);
        View view = new View(controller, model);
		
		JFrame frame = new JFrame(); 
        frame.setVisible(true);
        frame.add(view);
		frame.setLocation((screenWidth / 3)+150, (screenHeight / 4)-150);
        frame.setSize( 450, 835 ); /* title bar! */ 
        frame.setResizable( false );

		
        PlusButton.setBounds(9, 102, 30, 68);add(PlusButton);
        MinusButton.setBounds(11, 175, 27, 64);add(MinusButton);
        SelectButton.setBounds(5, 268, 34, 97); add(SelectButton);
        MenuButton.setBounds(409, 113, 30,84); add(MenuButton);
        
        MenuButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                dispose();
                new XTrex();
            }
        });

    }
        
        
        // from https://coderanch.com/t/476947/java/Adding-image-frame
        
    
  }