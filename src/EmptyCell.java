// +
public class EmptyCell implements ICell {

	@Override
	public ICell View(Hero hero) {
		return this;
	}
	@Override
	public EmptyCell Tick() {
		return this;
	}
	@Override
	public ICell Action(IMovable movable) {
		return movable.StayOn(this);
	}
	@Override
	public ICell GetStayOn() {
		return this;
	}
	@Override
	public Position TryToScare(Hero hero, Position from, Position to) {
		return from;
	}
	@Override
	public int IsEmpty() {
		return 1;
	}
	@Override
	public int getViewId() {
		return GameVisualizer.V_EMPTY;
	}
}
