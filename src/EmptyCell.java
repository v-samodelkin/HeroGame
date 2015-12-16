// +
public class EmptyCell implements ICell {

	@Override
	public ICell ViewBy(Hero hero) {
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
	public boolean IsEmpty() {
		return true;
	}
	@Override
	public int getViewId() {
		return GameVisualizer.V_EMPTY;
	}
	@Override
	public ICell TryHideBehind(ICell replacement) {
		return this;
	}
}
