import java.util.Random;

// +
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

	public static Direction Move(Position heroPosition, Position position) {
		int Dx = position.X - heroPosition.X;
		int Dy = position.Y - heroPosition.Y;
		return new Direction(Dx, Dy).RandomNormalize();
	}

	public Direction RandomNormalize() {
		Random rand = new Random();
		int dx = Math.round(Math.signum(Dx));
		int dy = Math.round(Math.signum(Dy));
		dx = dx * (int)(Math.signum(rand.nextInt(2) + (1 - Math.abs(dy))));
		dy = dy * (1 - Math.abs(dx));
		return new Direction(dx, dy);
	}

}
