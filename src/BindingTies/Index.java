package BindingTies;
import FieldObjects.*;
import AIs.*;
// +
public class Index {
	public static void main(String[] args) {
		String mapsDirectory = System.getProperty("user.dir") + "/maps/";
		FieldParser fieldParser = new FieldParser(mapsDirectory + "map2.txt");
		
		
		Game game = new Game(fieldParser.Parse());
		for (int i = 0; i < game.Field.GetWidth(); i+=2)
			game.Field.Cells[i][4] = new Trap();
		
		game.Field.InsertHero(new BfsAi(), game);



		
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
