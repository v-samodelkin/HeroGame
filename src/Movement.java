// +
public class Movement {
	public final Direction Direction;
	public final int X, Y;
	public final IMovable Mover;
	public final int CurrentWaiting;
	public final boolean Impatience;
	
	public Movement(Direction direction, int x, int y, IMovable mover, int currentWaiting, int maxWaiting) {
		Direction = direction;
		X = x;
		Y = y;
		Mover = mover;
		CurrentWaiting = currentWaiting;
		Impatience = CurrentWaiting > maxWaiting;
	}
	
	public Movement  (Direction direction, int x, int y, IMovable mover) {
		this(direction, x, y, mover, 0 ,0);
	}

	public Movement Waiting(int maxWaiting) {
		return new Movement(Direction, X, Y, Mover, CurrentWaiting + 1, maxWaiting);
	}
}
