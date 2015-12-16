// +
public class Position {
	public final int X, Y;
	public final boolean Found;
	
	public Position(int x, int y) {
		X = x;
		Y = y;
		Found = true;
	}
	
	public Position() {
		Found = false;
		X = Y = 0;
	}

	public int Distantion(Position position) {
		return Math.abs(position.X - X) + Math.abs(position.Y - Y);
	}

	public boolean Same(Position position) {
		return (X == position.X && Y == position.Y);
	}
	
	
}
