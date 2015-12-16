import java.util.List;
import java.util.Random;

public class LongRunnerAi implements IAi {
	
	private Position remember;
	
	@Override
	public Direction GetTurnDirection(Hero hero, Field viewField) {
		Position heroPosition = hero.GetPosition(viewField);
		List<Position> bonuses = viewField.GetBonusPositions();
		
		if (remember == null || heroPosition.Same(remember) || !(viewField.Cells[remember.X][remember.Y] instanceof Bonus))
			remember = null;
		
		
		
		if (bonuses.isEmpty())
			return Direction.None;
		else {
			Position mostClose = bonuses.get(0);
			for (int i = 1; i < bonuses.size(); i++) 
				if (heroPosition.Distantion(bonuses.get(i)) > heroPosition.Distantion(mostClose))
					mostClose = bonuses.get(i);
			if (remember != null)
				mostClose = remember;
			else 
				remember = mostClose;
			int dx = Math.round(Math.signum(mostClose.X - heroPosition.X));
			int dy = Math.round(Math.signum(mostClose.Y - heroPosition.Y));
			Random rand = new Random();
			if (rand.nextInt() % 2 == 0)
				dx = (dy == 0 ? dx : 0);
			else
				dy = (dx == 0 ? dy : 0);
			return new Direction(dx, dy);
		}
	}
}
