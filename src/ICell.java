
public interface ICell {
	public ICell View(Hero hero);
	public ICell Tick();
	public ICell Action(IMovable mover);
}
