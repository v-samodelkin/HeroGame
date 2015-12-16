// +
import java.util.List;
import java.util.Random;

public class DefaultAi implements IAi {

	@Override
	public Direction GetTurnDirection(Hero hero, Field viewField) {
		Position heroPosition = hero.GetPosition(viewField);
		List<Position> bonuses = viewField.GetBonusPositions();
		bonuses.sort(new BonusComparator(heroPosition));
		Position mostClose;
		try {
			mostClose = bonuses.get(0);
		} catch (Exception e) {
			mostClose = heroPosition;
		}
		int dx = Math.round(Math.signum(mostClose.X - heroPosition.X));
		int dy = Math.round(Math.signum(mostClose.Y - heroPosition.Y));
		Random rand = new Random();
		dx = dx * (int)(Math.signum(rand.nextInt(2) + (1 - Math.abs(dy))));
		dy = dy * (1 - Math.abs(dx));
		return new Direction(dx, dy);
	}

	public String GetAiName() {
		return "DE";
	}
}
