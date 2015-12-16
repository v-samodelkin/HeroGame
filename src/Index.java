
public class Index {
	public static void main(String[] args) {
		Game game = new Game(20, 10);
		game.InsertHero(new DefaultAi());
		game.InsertHero(new LongRunnerAi());
		for (int i = 0; i < game.Field.GetWidth(); i++)
			game.Field.Cells[i][4] = new Trap();
		for (int i = 0; i < 1000; i++) {
			game.MakeTurn();
			GameVisualizer.Visualize(game);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
