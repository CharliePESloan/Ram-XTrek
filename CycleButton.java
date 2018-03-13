import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;

/* CycleButton
 * Charlie Sloan (2018)
 * A UI item which acts as a label which can be selected. Selection is
 * shown either by changing the colour if it is text-based or
 * by changing the image if it contains images
 */

public class CycleButton extends JLabel
{
	/* Declare variables */
	boolean		selected;
	boolean		hasImages;
	Object		data;
	CycleButton	prevButton;
	CycleButton	nextButton;
	ImageIcon	imageNormal;
	ImageIcon	imageSelected;

	/* Constructors */
	CycleButton(String label)
	{
		selected = false;
		hasImages = false;
		setOpaque(true);
		setBackground(Color.white);
		setForeground(Color.black);
		setText(label);
	}
	CycleButton(String label,Object data)
	{
		this(label);
		this.data = data;
	}
	CycleButton(Object data, ImageIcon normal, ImageIcon selected)
	{
		this.selected	= false;
		hasImages	= true;
		imageNormal	= normal;
		imageSelected	= selected;
		this.data	= data;
		setOpaque(false);
		setIcon(normal);
	}

	/*
	 * setPrevNext
	 * Sets the previous and next buttons in button sequence
	 */
	public void setPrevNext(CycleButton prev,CycleButton next)
	{
		prevButton = prev;
		nextButton = next;
	}

	/*
	 * select
	 * Selects this CycleButton either by changing its icon
	 * or its background colour
	 */
	public void select()
	{
		selected = true;
		if (hasImages)
		{
			setIcon(imageSelected);
		} else
		{
			setBackground(Color.orange);
		}
	}

	/*
	 * deselect
	 * Deselects this CycleButton either by changing its icon
	 * or its background colour
	 */
	public void deselect()
	{
		selected = false;
		if (hasImages)
		{
			setIcon(imageNormal);
		} else
		{
			setBackground(Color.white);
		}
	}

	/*
	 * prev
	 * Returns the previous CycleButton in the sequence, selects it
	 * and deselects this one
	 */
	public CycleButton prev()
	{
		deselect();
		prevButton.select();
		return prevButton;
	}

	/* next
	 * Returns the next CycleButton in the sequence, selects it
	 * and deselects this one
	 */
	public CycleButton next()
	{
		deselect();
		nextButton.select();
		return nextButton;
	}

	public boolean isSelected()
	{
		return selected;
	}

	public Object getData()
	{
		return data;
	}
}
