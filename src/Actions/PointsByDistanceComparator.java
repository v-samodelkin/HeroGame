package Actions;
// +
import java.util.Comparator;
import Helpers.Position;

public class PointsByDistanceComparator implements Comparator<Position> {

	public final Position Centre;
	
	public PointsByDistanceComparator (Position centre) {
		Centre = centre;
	}
	@Override
	public int compare(Position arg0, Position arg1) {
		return Math.round(Math.signum(Centre.Distantion(arg0) - Centre.Distantion(arg1)));
	}
}
