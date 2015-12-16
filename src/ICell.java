
public interface ICell {
	public ICell View(Hero hero);
	public ICell Tick();
	public ICell Action(IMovable mover);
	public ICell GetStayOn();
	public Position TryToScare(Hero hero, Position from, Position to);
	public int IsEmpty();
	public int getViewId();
}
