import java.util.Observable;
import java.lang.Thread;

/* Timer
 * Keeps track of how long journey has been going on
 */
public class Timer extends Observable implements Runnable
{
	long time;

	public Timer()
	{
		reset();
	}

	// Thread
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

	// Reset timer
	public void reset()
	{
		time = System.currentTimeMillis();
	}
}
