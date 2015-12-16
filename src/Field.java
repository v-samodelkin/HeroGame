// +
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
				counter += BoolMapper.Get(Cells[x][y].IsEmpty());
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
				try {
					Cells[x][y] = Cells[x][y].TryHideBehind(field.Cells[x][y]);
				} catch (Exception e) {
					Cells[x][y] = field.Cells[x][y];
				}
			}
		}
	}

	public void Fill() {
		for (int x = 0; x < GetWidth(); x++) {
			for (int y = 0; y < GetLength(); y++) {
				try {
					Cells[x][y] = Cells[x][y].TryHideBehind(new EmptyCell());
				} catch (Exception e) {
					Cells[x][y] = new EmptyCell();
				}
			}
		}
	}

	public List<Position> GetBonusPositions() {
		List<Position> bonusPositions = new ArrayList<Position>();
		List<Bonus> Bonuses = new ArrayList<Bonus>();
		for (int x = 0; x < GetWidth(); x++)
			for (int y = 0; y < GetLength(); y++)
				try {
					Bonuses.add((Bonus)Cells[x][y]);
					bonusPositions.add(new Position(x, y));
				} catch (Exception e) {}
		return bonusPositions;
	}
	
	public List<Hero> GetHeroes() {
		List<Hero> heroes = new ArrayList<Hero>();
		for (int x = 0; x < GetWidth(); x++)
			for (int y = 0; y < GetLength(); y++)
				try {
					heroes.add((Hero)Cells[x][y]);
				} catch (Exception e) {}
		return heroes;
	}

	public void MoversJump() {
		for (int x = 0; x < GetWidth(); x++)
			for (int y = 0; y < GetLength(); y++)
				Cells[x][y] = Cells[x][y].GetStayOn();
	}

	public Field EmptyClone() {
		return new Field(new ICell[GetWidth()][GetLength()]);
	}
	
	public boolean InsertBonus() {
		return Insert(new Bonus());
	}
	
	public boolean Insert(ICell object) {
		Random rand = new Random();
		int newx, newy;
		do {
			newx = rand.nextInt(GetWidth());
			newy = rand.nextInt(GetLength());
		} while (!Cells[newx][newy].IsEmpty());
		Cells[newx][newy] = object;
		return true;
	}

	public boolean InsertHero(IAi ai, Game game) {
		return Insert(new Hero(game, ai));
	}

	public Position GetPosition(ICell cell) {
		Map<ICell, Position> positions = new HashMap<ICell, Position>();
		for (int x = 0; x < GetWidth(); x++)
			for (int y = 0 ; y < GetLength(); y++)
				positions.put(Cells[x][y], new Position(x, y));
		return positions.get(cell);
	}
	
	public void GenerateBonuses() {
		while (GetBonusCount() < 2)
			InsertBonus();
	}
	
	public int GetBonusCount() {
		return GetBonusPositions().size();
	}
}
