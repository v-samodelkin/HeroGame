import java.util.HashSet;
import java.util.Queue;

public class AddAction implements IAddAction {

	@Override
	public void Act(Vertex vertex, Queue<Vertex> queue, HashSet<Integer> was) {
		if (!was.contains(vertex.PositionHash()) && vertex.Valid) {
			was.add(vertex.PositionHash());
			queue.add(vertex);
		}
	}

}
