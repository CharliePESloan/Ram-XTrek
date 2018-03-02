import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;

public class CycleButton extends JLabel
{
	boolean     isSelected;
	boolean     hasImages;
	String	data = "";
	CycleButton prevButton;
	CycleButton nextButton;
	ImageIcon   imageNormal;
	ImageIcon   imageSelected;

	CycleButton(String label)
	{
		isSelected = false;
		hasImages = false;
		setOpaque(true);
		setBackground(Color.white);
		setForeground(Color.black);
		setText(label);
	}
	CycleButton(String label,String data)
	{
		this(label);
		this.data = data;
	}
	CycleButton(String label, ImageIcon normal, ImageIcon selected)
	{
		isSelected      = false;
		hasImages       = true;
		setOpaque(false);
		imageNormal     = normal;
		imageSelected   = selected;
		setIcon(normal);
	}
	public void setPrevNext(CycleButton prev,CycleButton next)
	{
		prevButton = prev;
		nextButton = next;
	}
	public void select()
	{
		isSelected = true;
		if (hasImages)
		{
			setIcon(imageSelected);
		} else
		{
			setBackground(Color.orange);
		}
	}
	public void deselect()
	{
		isSelected = false;
		if (hasImages)
		{
			setIcon(imageNormal);
		} else
		{
			setBackground(Color.white);
		}
	}
	public CycleButton prev()
	{
		deselect();
		prevButton.select();
		return prevButton;
	}
	public CycleButton next()
	{
		deselect();
		nextButton.select();
		return nextButton;
	}
	public String getData()
	{
		return data;
	}
}
