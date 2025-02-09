import java.util.Observable;
/*
 * Model
 * Charlie Sloan (2018)
 *
 * An interface for models ensuring each screen can be controlled by
 * the same controller
 */
public interface Model
{
  public void pressedPlus();
  public void pressedMinus();
  public void pressedSelect();
  public void pressedMenu();
  public void pressedOnOff();
  public void reset();
}
