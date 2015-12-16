// +
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameVisualizer {
	public static Map<Integer, Character> CharMapper;
	
	public static final int V_HERO = 0, V_R_TRAP = 1, V_TRAP = 2, V_WALL = 3, V_EMPTY = 4, V_BONUS = 5;
	
	
	static {
		CharMapper = new HashMap<Integer, Character>();
		CharMapper.put(V_HERO, '@');
		CharMapper.put(V_WALL, '#');
		CharMapper.put(V_EMPTY, '.');
		CharMapper.put(V_BONUS, '+');
		CharMapper.put(V_TRAP, 'X');
		CharMapper.put(V_R_TRAP, 'O');
	}
	
	public static void Visualize (Game game) {
		for (int i = 0; i < 30; i++)
			System.out.println();
		List<Hero> heroes = game.Field.GetHeroes();
		heroes.sort(new HeroesByPointsComparator());
		for (int i = 0; i < heroes.size(); i++) {
			Hero hero = heroes.get(i);
			System.out.println("Герой [" + hero.GetId() + " , " + hero.Ai.GetAiName() + "] набрал очков: " + hero.GetBonusesCount() + ". У него осталось единиц здоровья: " + hero.GetLivesCount() + ".");
		}
		
		for (int y = 0; y < game.Field.GetLength(); y++) {
			for (int x = 0; x < game.Field.GetWidth(); x++) {
				System.out.print(CharMapper.get(game.Field.Cells[x][y].getViewId()));
			}
			System.out.println();
		}
	}
}
