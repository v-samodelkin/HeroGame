import java.awt.List;
import java.util.ArrayList;
import java.util.Random;
// fub
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
		ArrayList<Movement> movements = new ArrayList<Movement>();
		for (int x = 0; x < Field.GetWidth(); x++) {
			for (int y = 0; y < Field.GetLength(); y++) {
				if (Field.Cells[x][y] instanceof IMovable) {
					IMovable mover = (IMovable)Field.Cells[x][y];
					Direction direction = mover.GetTurnDirection();
					movements.add(new Movement(direction, x, y, mover));
					Field.Cells[x][y] = mover.GetStayOn();
				}
			}
		}
		Field newField = Field.EmptyClone();
		
		for (int i = 0; i < movements.size(); i++) {
			Movement movement = movements.get(i);
			int newX = movement.X + movement.Direction.Dx;
			int newY = movement.Y + movement.Direction.Dy;
			if (!newField.IsValid(newX, newY)) {
				newX = movement.X;
				newY = movement.Y;
			}
			ICell previous = newField.Cells[newX][newY];
			if (previous != null && !(movement.Mover instanceof IKiller)) {
				newX = movement.X;
				newY = movement.Y;
			}
			previous = newField.Cells[newX][newY];
			if (previous == null)
				previous = Field.Cells[newX][newY];
			newField.Cells[newX][newY] = previous.Action(movement.Mover);
		}
		newField.Merge(Field);
		newField.Fill();
		Field = newField;
		GenerateBonuses();
	}
	
	private void GenerateBonuses() {
		if (GetBonusCount() < 2) {
			InsertBonus();
		}
	}
	
	public int GetBonusCount() {
		int count = 0;
		for (int x = 0; x < Field.GetWidth(); x++)
			for (int y = 0; y < Field.GetLength(); y++)
				if (Field.Cells[x][y] instanceof Bonus)
					count+=1;
		return count;
	}

	private void Ticks() {
		for (int x = 0; x < Field.GetWidth(); x++)
			for (int y = 0; y < Field.GetLength(); y++)
				Field.Cells[x][y] = Field.Cells[x][y].Tick();
	}

	public Field GetViewField(Hero hero) {
		ICell ViewField[][] = new ICell[Field.GetWidth()][Field.GetLength()];
		for (int x = 0; x < Field.GetWidth(); x++) {
			for (int y = 0; y < Field.GetLength(); y++) {
				ViewField[x][y] = Field.Cells[x][y].View(hero);
			}
		}
		return new Field(ViewField);
	}
	
	public boolean InsertBonus() {
		return Insert(new Bonus());
	}
	
	public boolean Insert(ICell object) {
		Random rand = new Random();
		if (Field.EmptyCellsCount() > 0) {
			while (true) {
				int newx = rand.nextInt(Field.GetWidth());
				int newy = rand.nextInt(Field.GetLength());
				if (Field.Cells[newx][newy] instanceof IEmpty) {
					Field.Cells[newx][newy] = object;
					return true;
				}
			}
		}
		return false;
	}

	public boolean InsertHero(IAi ai) {
		return Insert(new Hero(this, ai));
	}
}
