package BindingTies;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import FieldObjects.*;
public class FieldParser {
	
	List<String> stringCells;
	
	public static Map<Character, ICell> FromCharMapper;

	
	static {
		FromCharMapper = new HashMap<Character, ICell>();
		FromCharMapper.put('#', new Wall());
		FromCharMapper.put('.', new EmptyCell());
		FromCharMapper.put('X', new Trap());
	}
	
	public FieldParser(String filename) {
		stringCells = new ArrayList<String>();
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(filename));
			while ((sCurrentLine = br.readLine()) != null) {
				stringCells.add(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public ICell[][] Parse() {
		int height = stringCells.size();
		int width = stringCells.get(0).length();
		ICell cells[][] = new ICell[width][height];
		for (int y = 0; y < height; y++) {
			String current = stringCells.get(y);
			for (int x = 0; x < width; x++) {
				cells[x][y] = FromCharMapper.get(current.charAt(x));
			}
		}
		return cells;
	}
}
