// +
import java.util.ArrayList;

public class Game  {
	public Field Field;
	private int NextHeroId = 0;
	public Game(int width, int height) {
		Field = new Field(width, height);
	}
	
	public Game(ICell arr[][]) {
		Field = new Field(arr);
	}
	
	public int GetNewHeroId() {
		int nextHeroId =  NextHeroId;
		NextHeroId += 1;
		return nextHeroId;
	}
	
	public void MakeTurn() {
		Ticks();
		Moves();
		Field.GenerateBonuses();
	}
	
	private void Moves() {
		ArrayList<Movement> movements = new ArrayList<Movement>();
		for (int x = 0; x < Field.GetWidth(); x++) {
			for (int y = 0; y < Field.GetLength(); y++) {
				try {
					IMovable mover = (IMovable)Field.Cells[x][y];
					Direction direction = mover.GetTurnDirection();
					movements.add(new Movement(direction, x, y, mover));
					Field.Cells[x][y] = mover.GetStayOn();
				} catch (Exception e) {}
			}
		}
		Field newField = Field.EmptyClone();
		
		for (int i = 0; i < movements.size(); i++) {
			Movement movement = movements.get(i);
			Position pos = new Position(movement.X + movement.Direction.Dx, movement.Y + movement.Direction.Dy); // newPos 1
			ICell previous = newField.Cells[pos.X][pos.Y];
			pos = movement.Mover.IsSurrender(pos, previous, new Position(movement.X, movement.Y));
			previous = InvisibleCheck(newField.Cells[pos.X][pos.Y], Field.Cells[pos.X][pos.Y]);
			newField.Cells[pos.X][pos.Y] = previous.Action(movement.Mover);
		}
		newField.Merge(Field);
		newField.Fill();
		Field = newField;
	}
	
	public ICell InvisibleCheck(ICell verifiable, ICell replacement) {
		try {
			return verifiable.TryHideBehind(replacement);
		} catch (Exception e) {
			return replacement;
		}
	}

	private void Ticks() {
		for (int x = 0; x < Field.GetWidth(); x++)
			for (int y = 0; y < Field.GetLength(); y++)
				Field.Cells[x][y] = Field.Cells[x][y].Tick();
	}

	public Field GetViewField(Hero hero) {
		ICell VisibleCells[][] = new ICell[Field.GetWidth()][Field.GetLength()];
		for (int x = 0; x < Field.GetWidth(); x++)
			for (int y = 0; y < Field.GetLength(); y++)
				VisibleCells[x][y] = Field.Cells[x][y].ViewBy(hero);
		return new Field(VisibleCells);
	}
}
