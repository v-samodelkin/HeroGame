
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



}
