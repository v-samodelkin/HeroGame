// +
import java.util.ArrayList;
import java.util.HashMap;

public class Game  {
	public Field Field;
	private int NextHeroId = 0;
	
	public static final HashMap<Boolean, IMovementAction> ActByReadyToWaiting;
	
	static {
		ActByReadyToWaiting = new HashMap<Boolean, IMovementAction>();
		ActByReadyToWaiting.put(false, new ProcessMovementAction());
		ActByReadyToWaiting.put(true, new AddToMovementsAction());
	}
	
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
				} catch (ClassCastException e) {}
			}
		}
		Field newField = Field.EmptyClone();
		
		for (int i = 0; i < movements.size(); i++) {
			Movement movement = movements.get(i);
			Position pos = new Position(movement.X + movement.Direction.Dx, movement.Y + movement.Direction.Dy); // newPos 1
			int wishers = 0;
			for (int j = i + 1; j < movements.size(); j++) {
				Movement next = movements.get(j);
				wishers += BoolMapper.Get(next.X == pos.X && next.Y == pos.Y);
			}
			ActByReadyToWaiting.get(wishers > 0 && !movement.Waiting(5).Impatience).Act(movements, movement.Waiting(5), newField, Field);
		}
		newField.Merge(Field);
		newField.Fill();
		Field = newField;
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
