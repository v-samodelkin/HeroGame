import java.util.List;

import AIs.*;
import Actions.HeroesByIdComparator;
import BindingTies.*;
import FieldObjects.Hero;
// +
public class Index {
	public static void main(String[] args) {
		String mapsDirectory = System.getProperty("user.dir") + "/maps/";
		FieldParser fieldParser = new FieldParser(mapsDirectory + "map3.txt");
		
		
		Game game = new Game(fieldParser.Parse());
		
		game.Field.InsertHero(new PriorityBfsAi(), game);
		game.Field.InsertHero(new PriorityBfsAi(), game);
		game.Field.InsertHero(new PriorityBfsAi(), game);
		game.Field.InsertHero(new PriorityBfsAi(), game);
		game.Field.InsertHero(new PriorityBfsAi(), game);
		game.Field.InsertHero(new PriorityBfsAi(), game);
		game.Field.InsertHero(new PriorityBfsAi(), game);
		game.Field.InsertHero(new PriorityBfsAi(), game);
		game.Field.InsertHero(new PriorityBfsAi(), game);
		
		for (int i = 0; i < 1000; i++) {
			game.MakeTurn();
			List<Hero> heroes = game.Field.GetHeroes();
			heroes.sort(new HeroesByIdComparator());
			GameVisualizer.Visualize(game.GetViewField(heroes.get(0)));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
