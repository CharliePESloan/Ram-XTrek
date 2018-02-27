import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeechModePlusController implements ActionListener
{
    private SpeechModeModel myModel;

    public SpeechModeController( SpeechModeModel model )
    {
        myModel = model;
    }

    public void actionPerformed( ActionEvent ev )
    {
        myModel.next();
    }
}
