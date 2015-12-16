package AIs;
import java.util.HashSet;
import java.util.LinkedList;
import FieldObjects.*;
import Helpers.*;
import BindingTies.*;
public class BfsAi implements IAi {
	public static final int 
		Dx[] = {1, 0, -1, 0}, 
		Dy[] = {0, -1, 0, 1};
	
	static {
	}
	
	@Override
	public Direction GetTurnDirection(Hero hero, Field viewField) {
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		Position heroPosition = hero.GetPosition(viewField);
		HashSet<Integer> was = new HashSet<Integer>();
		for (int i = 0; i < 4; i++) {
			int newX = heroPosition.X + Dx[i];
			int newY = heroPosition.Y + Dy[i];
			Vertex next = new Vertex(newX, newY, viewField, 1, new Direction(Dx[i], Dy[i]));
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
				Vertex next = new Vertex(newX, newY, viewField, current.Cost + 1, current.FirstStep);
				next.TryToAdd(queue, was);
			}
		}
		return Direction.None;
	}
	


	@Override
	public String GetAiName() {
		return "BF";
	}
	
	public int PositionHash(Vertex vertex) {
		return vertex.PositionHash();
	}

}
