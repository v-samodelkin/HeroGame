package Actions;

import java.util.Comparator;

import Helpers.Vertex;

public class VertexByCostComparator implements Comparator<Vertex> {

	@Override
	public int compare(Vertex arg0, Vertex arg1) {
		return Math.round(Math.signum(arg0.Cost - arg1.Cost));
	}

}
