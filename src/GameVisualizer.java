import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameVisualizer {
	public static Map<Class, Character> CharMapper;
	
	static {
		CharMapper = new HashMap<Class, Character>();
		CharMapper.put(Hero.class, '@');
		CharMapper.put(Wall.class, '#');
		CharMapper.put(EmptyCell.class, '.');
		CharMapper.put(Bonus.class, '+');
		CharMapper.put(Trap.class, 'X');
	}
	
	public static void Visualize (Game game) {
		for (int i = 0; i < 30; i++)
			System.out.println();
		List<Hero> heroes = game.Field.GetHeroes();
		for (int i = 0; i < heroes.size(); i++) {
			Hero hero = heroes.get(i);
			System.out.println("Герой [" + hero.GetId() + "] набрал очков: " + hero.GetBonusesCount() + ". У него осталось единиц здоровья: " + hero.GetLivesCount() + ".");
		}
		
		for (int y = 0; y < game.Field.GetLength(); y++) {
			for (int x = 0; x < game.Field.GetWidth(); x++) {
				if (game.Field.Cells[x][y].getClass() == Trap.class) 
					System.out.print(((Trap)game.Field.Cells[x][y]).Cooldown > 0 ? "O" : "X");
				else
					System.out.print(CharMapper.get(game.Field.Cells[x][y].getClass()));
			}
			System.out.println();
		}
	}
}
