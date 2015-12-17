package BindingTies;
// +
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import FieldObjects.*;
import Actions.*;
public class GameVisualizer {
	public static Map<Integer, Character> CharMapper;
	
	public static final int V_HERO = 0, V_R_TRAP = 1, V_TRAP = 2, V_WALL = 3, V_EMPTY = 4, V_BONUS = 5;
	
	
	static {
		CharMapper = new HashMap<Integer, Character>();
		CharMapper.put(V_HERO, '@');
		CharMapper.put(V_WALL, '#');
		CharMapper.put(V_EMPTY, '.');
		CharMapper.put(V_BONUS, '+');
		CharMapper.put(V_TRAP, 'T');
		CharMapper.put(V_R_TRAP, 'O');
	}


	public static void Visualize(Field viewField) {
		for (int i = 0; i < 30; i++)
			System.out.println();
		List<Hero> heroes = viewField.GetHeroes();
		heroes.sort(new HeroesByPointsComparator());
		for (int i = 0; i < heroes.size(); i++) {
			Hero hero = heroes.get(i);
			System.out.println("����� [" + hero.GetId() + " , " + hero.Ai.GetAiName() + "] ������ �����: " + hero.GetBonusesCount() + ". � ���� �������� ������ ��������: " + hero.GetLivesCount() + ".");
		}
		for (int y = 0; y < viewField.GetLength(); y++) {
			for (int x = 0; x < viewField.GetWidth(); x++) {
				System.out.print(CharMapper.get(viewField.Cells[x][y].getViewId()));
			}
			System.out.println();
		}
	}
}
