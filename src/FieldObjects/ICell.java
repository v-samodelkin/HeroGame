package FieldObjects;
import Helpers.*;
public interface ICell {
	public ICell ViewBy(Hero hero);
	public ICell Tick();
	public ICell Action(IMovable mover);
	public ICell GetStayOn();
	public Position TryToScare(IMovable hero, Position from, Position to);
	public boolean IsEmpty();
	public int getViewId();
	public boolean IsPassable();
	public ICell TryHideBehind(ICell replacement);
}
