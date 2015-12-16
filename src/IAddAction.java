import java.util.HashSet;
import java.util.Queue;

public interface IAddAction {

	void Act(Vertex vertex, Queue<Vertex> queue, HashSet<Integer> was);

}
