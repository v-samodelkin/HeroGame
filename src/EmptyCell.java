// +
public class EmptyCell implements ICell, IEmpty {

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
}
