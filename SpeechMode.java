import java.awt.Color;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

/*
 * Simple calculator. David Wakeling, 03/01/2016.
 */
public class SpeechMode extends JFrame {
	
	private static int    d  = 0;
	private static int    e  = 0;
	private static String op = "?";
  
	ImageIcon whereToIcon = new ImageIcon("noLanguageButton.png");
	ImageIcon whereToIconSelected = new ImageIcon("noLanguageButtonSelected.png");
	ImageIcon mapIcon = new ImageIcon("englishButton.png");
	ImageIcon mapIconSelected = new ImageIcon("englishButtonSelected.png");
	ImageIcon satelliteIcon = new ImageIcon("frenchButton.png");
	ImageIcon satelliteIconSelected = new ImageIcon("frenchButtonSelected.png");
	ImageIcon tripCompIcon = new ImageIcon("TripCompButton.png");
	ImageIcon tripCompIconSelected = new ImageIcon("TripCompButtonSelected.png");
	ImageIcon speechIcon = new ImageIcon("italianButton.png");
	ImageIcon speechIconSelected = new ImageIcon("italianButtonSelected.png");
	ImageIcon aboutIcon = new ImageIcon("InfoButton.png");
	ImageIcon aboutIconSelected = new ImageIcon("InfoButtonSelected.png");

	final LanguageButton noLanguageButton	= new LanguageButton("None");
	final LanguageButton englishButton	= new LanguageButton("English");
	final LanguageButton frenchButton	= new LanguageButton("French");
	final LanguageButton germanButton	= new LanguageButton("German");
	final LanguageButton italianButton	= new LanguageButton("Italian");
	final LanguageButton spanishButton	= new LanguageButton("Spanish");

	LanguageButton selected = null;
  
	final SideButton    PlusButton    = new SideButton("PlusButton");
	final SideButton    MinusButton   = new SideButton("MinusButton");
	final SideButton    SelectButton  = new SideButton("SelectButton");
	final SideButton    MenuButton    = new SideButton("MenuButton");

    
	private class SideButton extends JButton{
		SideButton(String s){
			setBorder( null );
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
    
    
    
	private class LanguageButton extends JButton{
		boolean selected;
		LanguageButton prevButton;
		LanguageButton nextButton;
		LanguageButton(String s)
		{
			selected = false;
			//setBorder( null );
			setBorder( BorderFactory.createLineBorder(Color.black, 3 ) );
			setBackground(Color.white);
			setText(s);
		}
		public void setPrevNext(LanguageButton prev,LanguageButton next)
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
		public LanguageButton prev()
		{
			deselect();
			prevButton.select();
			return prevButton;
		}
		public LanguageButton next()
		{
			deselect();
			nextButton.select();
			return nextButton;
		}
	}
  

	public SpeechMode() {
		setTitle( "XTrex" );
		setContentPane( new JLabel( new ImageIcon( "XTrex Background.png" ) ) );
		setLayout( null );

		noLanguageButton.setPrevNext(spanishButton,englishButton);
		englishButton.setPrevNext(noLanguageButton,frenchButton);
		frenchButton.setPrevNext(englishButton,germanButton);
		germanButton.setPrevNext(frenchButton,italianButton);
		italianButton.setPrevNext(germanButton,spanishButton);
		spanishButton.setPrevNext(italianButton,noLanguageButton);
  
		noLanguageButton.setBounds(95, 312, 250, 45); add(noLanguageButton);
		//noLanguageButton.setIcon(whereToIcon);
		englishButton.setBounds(95,362,250,45);add(englishButton);
		//englishButton.setIcon(mapIconSelected);
		frenchButton.setBounds(95,422,250,45); add(frenchButton);
		//frenchButton.setIcon(satelliteIcon);
		germanButton.setBounds(95, 472, 250, 45); add(germanButton);
		//germanButton.setIcon(tripCompIcon);
		italianButton.setBounds(95,522,250,45); add(italianButton);
		//italianButton.setIcon(speechIcon);
		spanishButton.setBounds(95,572,250,45); add(spanishButton);
		//spanishButton.setIcon(aboutIcon);
// x, y, length, width

		selected = noLanguageButton;
		noLanguageButton.select();

		PlusButton.setBounds(9, 102, 30, 68);add(PlusButton);
		MinusButton.setBounds(11, 175, 27, 64);add(MinusButton);
		SelectButton.setBounds(8, 272, 29, 72); add(SelectButton);
  
		PlusButton.addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				selected = selected.next();
			}
		});
		MinusButton.addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				selected = selected.prev();
			}
		});
	}
  

	public static void main( String[] argv ) {
		JFrame frame = new SpeechMode();
		frame.setLocationRelativeTo( null );
		frame.setSize( 450, 835 ); /* title bar! */ 
		frame.setResizable( false );
		frame.setVisible( true );
	}
}
				/*if(englishButton.getIcon() == mapIconSelected){
					englishButton.setIcon(mapIcon);
					noLanguageButton.setIcon(whereToIconSelected);
				} else if(noLanguageButton.getIcon() == whereToIconSelected){
		    noLanguageButton.setIcon(whereToIcon);
		    germanButton.setIcon(tripCompIconSelected);
				} else if(germanButton.getIcon() == tripCompIconSelected){
		    germanButton.setIcon(tripCompIcon);
		    italianButton.setIcon(speechIconSelected);
				} else if(italianButton.getIcon() == speechIconSelected){
		    italianButton.setIcon(speechIcon);
		    spanishButton.setIcon(aboutIconSelected);
				} else if(spanishButton.getIcon() == aboutIconSelected){
					spanishButton.setIcon(aboutIcon);
					frenchButton.setIcon(satelliteIconSelected);
				} else if (frenchButton.getIcon() == satelliteIconSelected){
		    frenchButton.setIcon(satelliteIcon);
		    englishButton.setIcon(mapIconSelected);*/
