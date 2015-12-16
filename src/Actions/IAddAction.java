package Actions;
import java.util.HashSet;
import java.util.Queue;

import Helpers.Vertex;

public interface IAddAction {

	void Act(Vertex vertex, Queue<Vertex> queue, HashSet<Integer> was);

}
