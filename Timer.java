import java.util.Observable;
import java.lang.Thread;
//import java.lang.Runnable;

public class Timer extends Observable implements Runnable
{
	long time;
	
	public Timer()
	{
		reset();
	}

	public void run()
	{
		while (true)
		{
			try
			{
				setChanged();
				notifyObservers(System.currentTimeMillis() - time);
				Thread.currentThread().sleep(1000);
			}
			catch (InterruptedException e)
			{
				System.exit(1);
			}
		}
	}

	public void reset()
	{
		time = System.currentTimeMillis();
	}
}
