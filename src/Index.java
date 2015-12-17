import AIs.*;
import BindingTies.*;
// +
public class Index {
	public static final String filename = "map3.txt";
	
	public static void main(String[] args) {
		String mapsDirectory = System.getProperty("user.dir") + "/maps/";
		FieldParser fieldParser = new FieldParser(mapsDirectory + filename);
		
		
		Game game = new Game(fieldParser.Parse());
		
		for (int i = 0; i < 3; i++) {
			game.Field.InsertHero(new PriorityBfsAi(), game);
			game.Field.InsertHero(new BfsAi(), game);
		}
		
		
		for (int i = 0; i < 1000; i++) {
			game.MakeTurn();
			GameVisualizer.Visualize(game.Field);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
