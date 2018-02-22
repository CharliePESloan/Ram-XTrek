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
	TextButton[] buttons = new TextButton[26];
    static String abcd = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	ExtractButton selected;
      
    ImageIcon SPACE = new ImageIcon("Space.png");
    ImageIcon RIGHT = new ImageIcon("Right.png");
    ImageIcon HSPACE = new ImageIcon("HSpace.png");
    ImageIcon HRIGHT = new ImageIcon("HRight.png");
    
    final TextField display = new TextField();
    
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
    
    public class SpaceButton extends ExtractButton {
        SpaceButton (String s) {
			super (s);
            setIcon( new ImageIcon(s + ".png"));
            setBorder(null);
        }
    }
	 public class TextButton extends ExtractButton {
        TextButton (String s) {
			super (s);
            setIcon( new ImageIcon(s + ".png"));
            setBorder(null);
        }
    }
    
    public class ArrowButton extends ExtractButton {
        ArrowButton (String s) {
            super (s);
			setIcon( new ImageIcon(s + ".png"));
            setBorder(null);
        }
    }
    
        public class ExtractButton extends JButton {
		boolean selected;
		ExtractButton prevButton;
		ExtractButton nextButton;
		ExtractButton(String s)
		{
			selected = false;
			setIcon( new ImageIcon("Test" + s + ".png"));
            setBorder(null);
			setBorder( BorderFactory.createLineBorder(Color.black, 3 ) );
			setBackground(Color.white);
			setText(s);
		}
		public void setPrevNext(ExtractButton prev,ExtractButton next)
		{
			prevButton = prev;
			nextButton = next;
		}
		public void select()
		{
			selected = true;
			setBackground(Color.orange);
		}
		public void deselect()
		{
			selected = false;
			setBackground(Color.white);
		}
		public ExtractButton prev()
		{
			deselect();
			prevButton.select();
			return prevButton;
		}
		public ExtractButton next()
		{
			deselect();
			nextButton.select();
			return nextButton;
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
        
        MenuButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                dispose();
                new XTrex();
            }
        });
        
        for(int i=0; i<26; i++) {
            letter[i] = new ImageIcon("Test" + abcd.charAt(i) + ".png");
			hLetter[i] = new ImageIcon("HTest" + abcd.charAt(i) + ".png");
			buttons[i] = new TextButton(Character.toString(abcd.charAt(i)));
			add(buttons[i]);
        }
		for (int i=1; i<25;i++) {
			buttons[i].setPrevNext(buttons[i-1],buttons[i+1]);
		}
		buttonRight.setPrevNext(buttonSpace, buttons[0]);
		buttonSpace.setPrevNext(buttons[25], buttonRight);
		buttons[0].setPrevNext(buttonRight, buttons[1]);
		buttons[25].setPrevNext(buttons[24], buttonSpace);
        
		selected = (ExtractButton) buttons[0];
		buttons[0].select();
        
        display.setBounds (95, 312, 260, 50); add(display);//length, width, stretch left/right, up/down
        buttonPlus.setBounds (9, 102, 30, 68); add(buttonPlus);
        buttonMinus.setBounds (11, 175, 27, 64); add(buttonMinus);
        buttonSelect.setBounds (8, 272,29, 72); add(buttonSelect);
        
        buttons[0].setBounds (95, 365, 65, 45); 
        buttons[1].setBounds (160, 365, 65, 45);
        buttons[2].setBounds (225, 365, 65, 45); 
        buttons[3].setBounds (290, 365, 65, 45); 
        
        buttons[4].setBounds (95, 410, 65, 45); 
        buttons[5].setBounds (160, 410, 65, 45); 
        buttons[6].setBounds (225, 410, 65, 45); 
        buttons[7].setBounds (290, 410, 65, 45);
        
        buttons[8].setBounds (95, 455, 65, 45); 
        buttons[9].setBounds (160, 455, 65, 45); 
        buttons[10].setBounds (225, 455, 65, 45); 
        buttons[11].setBounds (290, 455, 65, 45); 
        
        buttons[12].setBounds (95, 500, 65, 45); 
        buttons[13].setBounds (160, 500, 65, 45); 
        buttons[14].setBounds (225, 500, 65, 45); 
        buttons[15].setBounds (290, 500, 65, 45); 
        
        buttons[16].setBounds (95, 545, 65, 45); 
        buttons[17].setBounds (160, 545, 65, 45); 
        buttons[18].setBounds (225, 545, 65, 45); 
        buttons[19].setBounds (290, 545, 65, 45); 
        
        buttons[20].setBounds (95, 590, 65, 45); 
        buttons[21].setBounds (160, 590, 65, 45); 
        buttons[22].setBounds (225, 590, 65, 45); 
        buttons[23].setBounds (290, 590, 65, 45); 
        
        buttons[24].setBounds (95, 635, 65, 45); 
        buttons[25].setBounds (160, 635, 65, 45); 
        buttonSpace.setBounds (225, 635, 65, 45); add(buttonSpace);
        buttonRight.setBounds (290, 635, 65, 45); add(buttonRight);
        
        //change the button icon 
        
        PlusButton.addMouseListener(new MouseAdapter() {
        public void mouseClicked( MouseEvent me) {
			selected = selected.next(); 
		}
    });
        
        MinusButton.addMouseListener( new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                selected = selected.prev();
            }
        });
        
        SelectButton.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent me) {
			   if (selected == buttonSpace) {
				   textDisplay += " ";
				   display.setText("" + textDisplay);
			   }
			   else {
               textDisplay += selected.getText();
               display.setText("" + textDisplay);
			   }
           } 
});
}}
