package Actions;
import java.util.Comparator;

import FieldObjects.Hero;

public class HeroesByIdComparator implements Comparator<Hero> {
	@Override
	public int compare(Hero o1, Hero o2) {
		return Math.round(Math.signum(o2.GetId() - o1.GetId()));
	}
}
