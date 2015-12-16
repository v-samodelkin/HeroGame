import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;

public class Vertex {

	public final int Cost, X, Y;
	public final ICell Cell;
	public final Position Position;
	public final boolean Valid;
	public final Direction FirstStep;
	
	public static final Map<Boolean, IAddAction> ActionByReadyToAdd;
	
	static {
		ActionByReadyToAdd = new HashMap<Boolean, IAddAction>();
		ActionByReadyToAdd.put(false, new EmptyAction());
		ActionByReadyToAdd.put(true, new AddAction());
	}
	
	public Vertex(int newX, int newY, Field field, int cost, Direction firstStep) {
		X = newX;
		Y = newY;
		Cell = field.TryGet(newX, newY);
		Valid = field.IsValid(newX, newY) && (Cell.IsPassable());
		Cost = cost;
		Position = new Position(newX, newY);
		FirstStep = firstStep;
	}
	public void TryToAdd(Queue<Vertex> queue, HashSet<Integer> was) {
		ActionByReadyToAdd.get(!was.contains(PositionHash()) && Valid).Act(this, queue, was);
	}
	public int PositionHash() {
		return X * 10000 + Y;
	}

	

}
