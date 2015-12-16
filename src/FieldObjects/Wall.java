package FieldObjects;
import BindingTies.*;
import Helpers.*;
public class Wall implements ICell {

	@Override
	public ICell ViewBy(Hero hero) {
		return this;
	}

	@Override
	public Wall Tick() {
		return this;
	}

	@Override
	public ICell Action(IMovable movable) {
		System.out.println("Head-On");
		return movable.Die();
	}

	@Override
	public ICell GetStayOn() {
		return this;
	}

	@Override
	public Position TryToScare(IMovable mover, Position from, Position to) {
		return to;
	}

	@Override
	public boolean IsEmpty() {
		return false;
	}
	
	public int getViewId() {
		return GameVisualizer.V_WALL;
	}

	@Override
	public ICell TryHideBehind(ICell replacement) {
		return this;
	}

	@Override
	public boolean IsPassable() {
		return false;
	}

}
