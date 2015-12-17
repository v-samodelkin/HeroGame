package AIs;

import java.util.List;
import FieldObjects.*;
import Helpers.*;
import Actions.*;
import BindingTies.*;
public class DefaultAi implements IAi {

	@Override
	public Direction GetTurnDirection(Hero hero, Field viewField) {
		Position heroPosition = hero.GetPosition(viewField);
		List<Position> bonuses = viewField.GetBonusPositions();
		bonuses.sort(new PointsByDistanceComparator(heroPosition));
		Position mostClose;
		try {
			mostClose = bonuses.get(0);
		} catch (Exception e) {
			mostClose = heroPosition;
		}
		int dx = Math.round(Math.signum(mostClose.X - heroPosition.X));
		int dy = Math.round(Math.signum(mostClose.Y - heroPosition.Y));
		return new Direction(dx, dy).RandomNormalize();
	}

	public String GetAiName() {
		return "DE";
	}
}
