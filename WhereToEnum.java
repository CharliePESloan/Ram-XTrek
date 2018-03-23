/**
 *
 * @author Nathan Painter
 *
 * 
 */
public enum WhereToEnum {
	SPACE, RIGHT, LEFT, DEL;
	@Override
	public String toString() {
		switch (this) {
			case SPACE: return "SPACE"; 
			case RIGHT: return "Right";
			case LEFT: return "Left";
			case DEL: return "Del";
			default: throw new IllegalArgumentException();
		}
	}
}
