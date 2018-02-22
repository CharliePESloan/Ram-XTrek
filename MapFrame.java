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

    public MapFrame(){
        setTitle( "XTrex" );
        setLayout( null );
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setLocation((screenWidth / 3)+150, (screenHeight / 4)-150);
        setSize( 450, 835 ); /* title bar! */
        setResizable( false );
        setVisible(true);

        Model model = new Model(0);
        Controller controller = new Controller(model);
        View view = new View(controller, model);

        view.setLayout( null );
		view.setLocation((screenWidth / 3)+150, (screenHeight / 4)-150); 
        view.setBounds(0,0,450,835);
        add(view);

        PlusButton.setBounds(7, 102, 30, 68);view.add(PlusButton);
        MinusButton.setBounds(8, 170, 27, 64);view.add(MinusButton);
        MenuButton.setBounds(409, 113, 30,84); view.add(MenuButton);
		

        MenuButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                dispose();
                new XTrex();
            }
        });

    }


        // from https://coderanch.com/t/476947/java/Adding-image-frame


  }
