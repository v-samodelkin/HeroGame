import java.util.HashMap;

// +
public class Movement {
	public final Direction Direction;
	public final int X, Y;
	public final IMovable Mover;
	public final int CurrentWaiting;
	public final boolean Impatience;
	public final HashMap<Boolean, Position>	dependOnPatience;
	public Movement(Direction direction, int x, int y, IMovable mover, int currentWaiting, int maxWaiting) {
		Direction = direction;
		X = x;
		Y = y;
		Mover = mover;
		CurrentWaiting = currentWaiting;
		Impatience = CurrentWaiting > maxWaiting;
		dependOnPatience = new HashMap<Boolean, Position>();
		dependOnPatience.put(true, new Position(X, Y));
		dependOnPatience.put(false, new Position(X + Direction.Dx, Y + Direction.Dy));
	}
	
	public Movement  (Direction direction, int x, int y, IMovable mover) {
		this(direction, x, y, mover, 0 ,0);
	}

	public Movement Waiting(int maxWaiting) {
		return new Movement(Direction, X, Y, Mover, CurrentWaiting + 1, maxWaiting);
	}

	public Position DependOnPatience() {
		return dependOnPatience.get(Impatience);
	}
}
