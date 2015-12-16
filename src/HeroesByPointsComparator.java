import java.util.Comparator;

public class HeroesByPointsComparator implements Comparator<Hero> {
	@Override
	public int compare(Hero o1, Hero o2) {
		return Math.round(Math.signum(o2.Bonuses - o1.Bonuses));
	}

}
