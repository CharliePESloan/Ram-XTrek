import java.awt.Color;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.util.Observable;
import java.util.Observer;

public class SpeechModeView extends JPanel implements Observer
{
	Controller	myController;
	SpeechModeModel	myModel;

	/* Create language buttons */
	final public CycleButton buttonOff = new CycleButton("Off", "en-GB");
	final CycleButton buttonEnglish	= new CycleButton("English","en-GB");
	final CycleButton buttonFrench	= new CycleButton("Francais", "fr-FR");
	final CycleButton buttonGerman	= new CycleButton("Deutsch", "de-DE");
	final CycleButton buttonItalian	= new CycleButton("Italiano","it-IT");
	final CycleButton buttonSpanish	= new CycleButton("Espanol","es-ES");

	public void showMe()
	{
		setVisible( true );
	}
	public void hideMe()
	{
		setVisible( false );
	}

	public void update( Observable obs, Object obj )
	{
		//jTextField.setText("" + obj );
	}


	public SpeechModeView( Controller controller, SpeechModeModel model )
	{
		myController = controller;
		myModel = model;
		myModel.setSelected(buttonOff);

		//super();
		//setBackground(Color.red);
		setLayout( null );
		//setSize( 250, 300 );
        //setVisible( true );

		/* Setup CycleButtons */
		buttonOff.		setPrevNext(buttonSpanish,	buttonEnglish);
		buttonEnglish.	setPrevNext(buttonOff,		buttonFrench);
		buttonFrench.	setPrevNext(buttonEnglish,	buttonGerman);
		buttonGerman.	setPrevNext(buttonFrench,	buttonItalian);
		buttonItalian.	setPrevNext(buttonGerman,	buttonSpanish);
		buttonSpanish.	setPrevNext(buttonItalian,	buttonOff);


		/* Set position and size of buttons and add to frame */
		buttonOff.setBounds(0, 0, 255, 45);
		//buttonOff.repaint();
		buttonEnglish.setBounds(0,50,255,45);
		buttonFrench.setBounds(0,100,255,45);
		buttonGerman.setBounds(0, 150, 255, 45);
		buttonItalian.setBounds(0,200,255,45);
		buttonSpanish.setBounds(0,250,255,45);

		Font myFont = new Font("Trebuchet MS",Font.PLAIN, 32);
		buttonOff.setFont(myFont);
		buttonEnglish.setFont(myFont);
		buttonFrench.setFont(myFont);
		buttonGerman.setFont(myFont);
		buttonItalian.setFont(myFont);
		buttonSpanish.setFont(myFont);

		add(buttonOff);
		add(buttonEnglish);
		add(buttonFrench);
		add(buttonGerman);
		add(buttonItalian);
		add(buttonSpanish);

		//pack();
		//repaint();
		//setVisible(true);
	}
}




















/*PlusButton.setBounds(9, 102, 30, 68);add(PlusButton);
MinusButton.setBounds(11, 175, 27, 64);add(MinusButton);
SelectButton.setBounds(8, 272, 29, 72); add(SelectButton);
MenuButton.setBounds(409, 113, 30,84); add(MenuButton);*

/* Click events for interactive buttons *
PlusButton.addMouseListener(new java.awt.event.MouseAdapter()
{
	public void mouseClicked(java.awt.event.MouseEvent evt)
	{
		/* Cycle backwards through language list *
		selected = selected.prev();
	}
});
MinusButton.addMouseListener(new java.awt.event.MouseAdapter()
{
	public void mouseClicked(java.awt.event.MouseEvent evt)
	{
		/* Cycle forwards down language list*
		selected = selected.next();
	}
});
SelectButton.addMouseListener(new java.awt.event.MouseAdapter()
{
	public void mouseClicked(java.awt.event.MouseEvent evt)
	{
		/* Select language *
		System.out.println(selected.getText());
	}
});
MenuButton.addMouseListener(new java.awt.event.MouseAdapter()
{
	public void mouseClicked(java.awt.event.MouseEvent evt)
	{
		/* Return to main page when menu button pressed *
		dispose();
		new XTrex();
	}
});*/
