// +
public class Movement {
	public final Direction Direction;
	public final int X, Y;
	public final IMovable Mover;
	
	public Movement(Direction direction, int x, int y, IMovable mover) {
		Direction = direction;
		X = x;
		Y = y;
		Mover = mover;
	}
}
