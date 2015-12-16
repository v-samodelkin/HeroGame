import java.util.ArrayList;
import java.util.List;
import java.util.Random;
// 1 if
public class DefaultAi implements IAi {

	@Override
	public Direction GetTurnDirection(Hero hero, Field viewField) {
		Position heroPosition = hero.GetPosition(viewField);
		List<Position> bonuses = viewField.GetBonusPositions();
		
		Position mostClose = heroPosition;
		int dist = 10000;
		for (int i = 0; i < bonuses.size(); i++) 
			if (heroPosition.Distantion(bonuses.get(i)) < dist) {
				mostClose = bonuses.get(i);
				dist = heroPosition.Distantion(bonuses.get(i));
			}
		
		int dx = Math.round(Math.signum(mostClose.X - heroPosition.X));
		int dy = Math.round(Math.signum(mostClose.Y - heroPosition.Y));
		Random rand = new Random();
		dx = dx * (int)(Math.signum(rand.nextInt(2) + (1 - Math.abs(dy))));
		dy = dy * (1 - Math.abs(dx));
		return new Direction(dx, dy);
	}

}
