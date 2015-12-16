import java.util.ArrayList;
import java.util.List;
// @#&^@$&@%#^&$%#$@
public class Field {
	public ICell Cells[][];
	public Field (int width, int height) {
		Cells = new ICell[width][height];
		Fill();
	}
	
	public Field(ICell cells[][]) {
		Cells = cells;
	}
	
	
	public int GetWidth() {
		return (Cells != null ? Cells.length : 0);
	}
	
	
	public int GetLength() {
		return (GetWidth() > 0 ? Cells[0].length : 0);
	}

	public int EmptyCellsCount() {
		int counter = 0;
		for (int x = 0; x < GetWidth(); x++)
			for (int y  = 0; y < GetLength(); y++)
				if (Cells[x][y] instanceof IEmpty)
					counter += 1;
		return counter;
	}

	public boolean IsValid(int x, int y) {
		return (x >= 0 && y >= 0 && x < GetWidth() && y < GetLength());
	}

	public Field Clone() {
		ICell[][] newCells = new ICell[GetWidth()][GetLength()];
		for (int x = 0; x < GetWidth(); x++)
			for (int y  = 0; y < GetLength(); y++)
				newCells[x][y] = Cells[x][y];
		return new Field(newCells);
	}

	public void Merge(Field field) {
		for (int x = 0; x < GetWidth(); x++) {
			for (int y = 0; y < GetLength(); y++) {
				if (Cells[x][y] == null)
					Cells[x][y] = field.Cells[x][y];
			}
		}
	}

	public void Fill() {
		for (int x = 0; x < GetWidth(); x++) {
			for (int y = 0; y < GetLength(); y++) {
				if (Cells[x][y] == null)
					Cells[x][y] = new EmptyCell();
			}
		}
	}

	public List<Position> GetBonusPositions() {
		List<Position> bonusPositions = new ArrayList<Position>();
		for (int x = 0; x < GetWidth(); x++)
			for (int y = 0; y < GetLength(); y++)
				if (Cells[x][y] instanceof Bonus)
					bonusPositions.add(new Position(x, y));
		return bonusPositions;
	}
	
	public List<Hero> GetHeroes() {
		List<Hero> heroes = new ArrayList<Hero>();
		for (int x = 0; x < GetWidth(); x++)
			for (int y = 0; y < GetLength(); y++)
				if (Cells[x][y] instanceof Hero)
					heroes.add((Hero)Cells[x][y]);
		return heroes;
	}

	public void MoversJump() {
		for (int x = 0; x < GetWidth(); x++)
			for (int y = 0; y < GetLength(); y++)
				if (Cells[x][y] instanceof IMovable)
					Cells[x][y] = ((IMovable)Cells[x][y]).GetStayOn();
	}

	public Field EmptyClone() {
		ICell[][] newCells = new ICell[GetWidth()][GetLength()];
		return new Field(newCells);
	}
}
