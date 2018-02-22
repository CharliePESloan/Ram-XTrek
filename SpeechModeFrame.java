import java.awt.Color;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

public class SpeechModeFrame extends BlankXTrex {
	/* Create language buttons */
	final LanguageButton noLanguageButton	= new LanguageButton("Off");
	final LanguageButton englishButton		= new LanguageButton("English");
	final LanguageButton frenchButton		= new LanguageButton("French");
	final LanguageButton germanButton		= new LanguageButton("German");
	final LanguageButton italianButton		= new LanguageButton("Italian");
	final LanguageButton spanishButton		= new LanguageButton("Spanish");

	LanguageButton selected = null;

	private class LanguageButton extends JButton{
		boolean selected;
		LanguageButton prevButton;
		LanguageButton nextButton;
		LanguageButton(String s)
		{
			selected = false;
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


	public SpeechModeFrame() {
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

		/* Setup languageButtons */
		noLanguageButton.setPrevNext(spanishButton,englishButton);
		englishButton.setPrevNext(noLanguageButton,frenchButton);
		frenchButton.setPrevNext(englishButton,germanButton);
		germanButton.setPrevNext(frenchButton,italianButton);
		italianButton.setPrevNext(germanButton,spanishButton);
		spanishButton.setPrevNext(italianButton,noLanguageButton);

		noLanguageButton.select();
		selected = noLanguageButton;

		/* Set position and size of buttons and add to frame */
		noLanguageButton.setBounds(95, 312, 255, 45); add(noLanguageButton);
		englishButton.setBounds(95,362,255,45);add(englishButton);
		frenchButton.setBounds(95,412,255,45); add(frenchButton);
		germanButton.setBounds(95, 462, 255, 45); add(germanButton);
		italianButton.setBounds(95,512,255,45); add(italianButton);
		spanishButton.setBounds(95,562,255,45); add(spanishButton);
		
		Font myFont = new Font("Comic Sans MS",Font.PLAIN, 32);
		noLanguageButton.setFont(myFont);
		englishButton.setFont(myFont);
		frenchButton.setFont(myFont);
		germanButton.setFont(myFont);
		italianButton.setFont(myFont);
		spanishButton.setFont(myFont);

		PlusButton.setBounds(9, 102, 30, 68);add(PlusButton);
		MinusButton.setBounds(11, 175, 27, 64);add(MinusButton);
		SelectButton.setBounds(8, 272, 29, 72); add(SelectButton);
		MenuButton.setBounds(409, 113, 30,84); add(MenuButton);

		/* Click events for interactive buttons */
		PlusButton.addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				/* Cycle backwards through language list */
				selected = selected.prev();
			}
		});
		MinusButton.addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				/* Cycle forwards down language list*/
				selected = selected.next();
			}
		});
		SelectButton.addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				/* Select language */
				System.out.println(selected.getText());
			}
		});
		MenuButton.addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				/* Return to main page when menu button pressed */
				dispose();
				new XTrex();
			}
		});
	}


	public static void main( String[] argv ) {
		JFrame frame = new SpeechModeFrame();
		frame.setLocationRelativeTo( null );
		frame.setSize( 450, 835 ); /* title bar! */
		frame.setResizable( false );
		frame.setVisible( true );
	}
}
