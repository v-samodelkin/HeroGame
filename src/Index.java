// +
public class Index {
	public static void main(String[] args) {
		Game game = new Game(20, 10);
//		for (int i = 0; i < game.Field.GetWidth(); i++)
//			game.Field.Cells[i][4] = new Trap();
		
		game.Field.InsertHero(new DefaultAi(), game);
		game.Field.InsertHero(new DefaultAi(), game);
		game.Field.InsertHero(new DefaultAi(), game);
		game.Field.InsertHero(new DefaultAi(), game);
		game.Field.InsertHero(new DefaultAi(), game);
		game.Field.InsertHero(new DefaultAi(), game);
		game.Field.InsertHero(new DefaultAi(), game);
		game.Field.InsertHero(new LongRunnerAi(), game);
		
		for (int i = 0; i < 1000; i++) {
			game.MakeTurn();
			GameVisualizer.Visualize(game);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
