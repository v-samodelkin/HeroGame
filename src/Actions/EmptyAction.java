package Actions;
import java.util.HashSet;
import java.util.Queue;

import Helpers.Vertex;

public class EmptyAction implements IAddAction {

	@Override
	 public void Act(Vertex vertex, Queue<Vertex> queue, HashSet<Integer> was) {
		return;
	}

}
