import java.awt.Color;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
* This class should basically just have a main method that implements Model, View and Controller. 
*/ 

public class MenuFrame extends BlankXTrex {
    CardLayout cardlayout = new CardLayout();
    JPanel cards = new JPanel(cardlayout);
    
    
    MenuModel menuModel = new MenuModel(this);
    SpeechModeModel speechModel = new SpeechModeModel(this);
    WhereToFrameModel whereToModel = new WhereToFrameModel(this);
   // MenuController	menuController = new MenuController(menuModel);
    Controller controller = new Controller(menuModel);
    JPanel menuView = new MenuView(controller, menuModel);
    JPanel speechView = new SpeechModeView(controller, speechModel);
    JPanel whereToView = new WhereToFrameView(controller, whereToModel);
  /* Taken from http://www.java2s.com/Code/JavaAPI/javax.swing/JFramesetLocationintxinty.htm
  */

  
  public Toolkit tk = Toolkit.getDefaultToolkit();
  public Dimension screenSize = tk.getScreenSize();
  public int screenHeight = screenSize.height;
  public int screenWidth = screenSize.width;
  // A separate class for menu buttons, that could be useful later 
     private class DisplayButton extends JButton{
         DisplayButton(){
             setBorder( null );
         }
     }

  // What is constructed when MenuFrame() is called
  public MenuFrame() {
    // The frame specifications
    setTitle( "XTrex" );
    setContentPane( new JLabel( new ImageIcon( "XTrex Background.png" ) ) );
    setLayout( null );
    setLocation((screenWidth / 3)+150, (screenHeight / 4)-150);
    setSize( 450, 835 ); /* title bar! */
    setResizable( false );
    setVisible( true );

    // Placing the navigation buttons
    PlusButton.setBounds(9, 102, 30, 68);add(PlusButton);
    MinusButton.setBounds(11, 175, 27, 64);add(MinusButton);
    SelectButton.setBounds(5, 260, 34, 97); add(SelectButton);
    MenuButton.setBounds(409, 113, 30,84); add(MenuButton);
      
    add(cards);
    cards.setSize(255, 293);
    cards.setLocation(90,300);
    cards.add(menuView, "Menu");
    cards.add(speechView, "Speech");
    //menuFrame.add(menuView);
    //JPanel menuView = new MenuView(menuFrame);
    
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    
    
    setLocation((screenWidth / 3)+150, (screenHeight / 4)-150);
    setSize( 450, 835 ); /* title bar! */
    setResizable( false );
    setVisible( true );
      
    PlusButton.addMouseListener(controller);
    MinusButton.addMouseListener(controller);
    SelectButton.addMouseListener(controller);
    MenuButton.addMouseListener(controller);
  }
  public void setMenu(String menu){
      switch(menu){
              case "Speech":
              System.out.println("Changing model to speechModel");
              controller.setModel(speechModel);
              break;
              case "Menu":
              controller.setModel(menuModel);
              break;
              case "WhereTo":
              controller.setModel(whereToModel);
              break;
      }
      cardlayout.show(cards, menu);
  }
  public static void main( String[] argv ) {
      
    MenuFrame menuFrame = new MenuFrame();
      
  }
}
