// +
public class Wall implements ICell {

	@Override
	public ICell View(Hero hero) {
		return this;
	}

	@Override
	public Wall Tick() {
		return this;
	}

	@Override
	public ICell Action(IMovable movable) {
		return movable.Die();
	}

	@Override
	public ICell GetStayOn() {
		return this;
	}

	@Override
	public Position TryToScare(Hero hero, Position from, Position to) {
		return to;
	}

	@Override
	public int IsEmpty() {
		return 0;
	}
	
	public int getViewId() {
		return GameVisualizer.V_WALL;
	}

}
