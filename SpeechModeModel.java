import java.util.Observable;

public class SpeechModeModel extends Observable
{
    CycleButton selected = null;

    public SpeechModeModel()
    {}

    public void setSelected(CycleButton newSelected)
    {
        selected = newSelected;
        selected.select();
    }
    public void next()
    {
        selected = selected.next();
    }
    public void prev()
    {
        selected = selected.prev();
    }
}
