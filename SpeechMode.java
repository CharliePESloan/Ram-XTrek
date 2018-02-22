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
public class SpeechMode extends BlankXTrex {

	final LanguageButton noLanguageButton	= new LanguageButton("Off");
	final LanguageButton englishButton	= new LanguageButton("English");
	final LanguageButton frenchButton	= new LanguageButton("French");
	final LanguageButton germanButton	= new LanguageButton("German");
	final LanguageButton italianButton	= new LanguageButton("Italian");
	final LanguageButton spanishButton	= new LanguageButton("Spanish");

	LanguageButton selected = null;

	/*final SideButton    PlusButton    = new SideButton("PlusButton");
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
	}*/



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

		/* Setup languageButtons */
		noLanguageButton.setPrevNext(spanishButton,englishButton);
		englishButton.setPrevNext(noLanguageButton,frenchButton);
		frenchButton.setPrevNext(englishButton,germanButton);
		germanButton.setPrevNext(frenchButton,italianButton);
		italianButton.setPrevNext(germanButton,spanishButton);
		spanishButton.setPrevNext(italianButton,noLanguageButton);
		selected = noLanguageButton;
		noLanguageButton.select();

		/* Set position and size of buttons and add to frame */
		noLanguageButton.setBounds(95, 312, 255, 45); add(noLanguageButton);
		englishButton.setBounds(95,362,255,45);add(englishButton);
		frenchButton.setBounds(95,412,255,45); add(frenchButton);
		germanButton.setBounds(95, 462, 255, 45); add(germanButton);
		italianButton.setBounds(95,512,255,45); add(italianButton);
		spanishButton.setBounds(95,562,255,45); add(spanishButton);

		PlusButton.setBounds(9, 102, 30, 68);add(PlusButton);
		MinusButton.setBounds(11, 175, 27, 64);add(MinusButton);
		SelectButton.setBounds(8, 272, 29, 72); add(SelectButton);
        MenuButton.setBounds(409, 113, 30,84); add(MenuButton);

		/* Click events for interactive buttons */
		PlusButton.addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				selected = selected.prev();
			}
		});
		MinusButton.addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				selected = selected.next();
			}
		});
		SelectButton.addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				System.out.println(selected.getText());
			}
		});
		MenuButton.addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				dispose();
				new XTrex();
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
