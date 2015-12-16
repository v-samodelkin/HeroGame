package AIs;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;

import Actions.VertexByCostComparator;
import FieldObjects.*;
import Helpers.*;
import BindingTies.*;
@SuppressWarnings("rawtypes")
public class PriorityBfsAi implements IAi {
	public static final int 
		Dx[] = {1, 0, -1, 0}, 
		Dy[] = {0, -1, 0, 1};
	
	public final static Map<Class, Integer> CostByCell;

	static {
		CostByCell = new HashMap<Class, Integer>();
		CostByCell.put(Wall.class, 100000);
		CostByCell.put(Hero.class, 1000);
		CostByCell.put(EmptyCell.class, 1);
		CostByCell.put(Trap.class, 10000);
		CostByCell.put(Bonus.class, 0);
	}
	
	@Override
	public Direction GetTurnDirection(Hero hero, Field viewField) {
		Comparator<Vertex> comparator = new VertexByCostComparator();
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(comparator);
		Position heroPosition = hero.GetPosition(viewField);
		HashSet<Integer> was = new HashSet<Integer>();
		for (int i = 0; i < 4; i++) {
			int newX = heroPosition.X + Dx[i];
			int newY = heroPosition.Y + Dy[i];
			Vertex next = new Vertex(newX, newY, viewField, TryGetCost(viewField.TryGet(newX, newY)), new Direction(Dx[i], Dy[i]));
			next.TryToAdd(queue, was);
		}
		while (!queue.isEmpty()) {
			Vertex current = queue.poll();
			try {
				@SuppressWarnings("unused")
				Bonus bonus = (Bonus)current.Cell;
				return current.FirstStep;
			} catch (ClassCastException e) {}
			for (int i = 0; i < 4; i++) {
				int newX = current.X + Dx[i];
				int newY = current.Y + Dy[i];
				Vertex next = new Vertex(newX, newY, viewField, current.Cost + TryGetCost(viewField.TryGet(newX, newY)), current.FirstStep);
				next.TryToAdd(queue, was);
			}
		}
		return Direction.None;
	}
	


	private int TryGetCost(ICell tryGet) {
		try {
			return CostByCell.get(tryGet.getClass());
		} catch (NullPointerException e) {
			return 10;
		}
	}



	@Override
	public String GetAiName() {
		return "BF";
	}
	
	public int PositionHash(Vertex vertex) {
		return vertex.PositionHash();
	}

}
