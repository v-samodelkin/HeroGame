
public class Direction {
	public static final Direction Up, Down, Left, Right, None;
	
	public final int Dx, Dy;
	
	static {
		Up = new Direction(0, -1);
		Down = new Direction(0, 1);
		Left  = new Direction(-1, 0);
		Right = new Direction(1, 0);
		None = new Direction(0, 0);
	}
	
	public Direction(int dx, int dy) {
		Dx = dx;
		Dy = dy;
	}

	public boolean IsNone() {
		return (Dx == 0) && (Dy == 0);
	}

}
