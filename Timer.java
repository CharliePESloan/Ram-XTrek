import java.util.Observable;
import java.lang.Thread;
//import java.lang.Runnable;

public class Timer extends Observable implements Runnable
{
	long time;
	
	public Timer()
	{
		time = System.currentTimeMillis();
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
<<<<<<< HEAD

	public void reset()
	{
		time = System.currentTimeMillis();
	}
=======
>>>>>>> parent of b5ebd89... Added reset timer
}
