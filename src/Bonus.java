// +
public class Bonus implements ICell {
	
	public final int BonusCount;
	
	public Bonus(int bonusCount) {
		BonusCount = bonusCount;
	}
	
	
	public Bonus() {
		this(1);
	}
	
	
	@Override
	public ICell ViewBy(Hero hero) {
		return this;
	}
	
	@Override
	public Bonus Tick() {
		return this;
	}


	@Override
	public ICell Action(IMovable mover) {
		return mover.AddBonus(BonusCount).StayOn(new EmptyCell());
	}


	@Override
	public ICell GetStayOn() {
		return this;
	}


	@Override
	public Position TryToScare(IMovable mover, Position from, Position to) {
		return from;
	}


	@Override
	public boolean IsEmpty() {
		return false;
	}


	@Override
	public int getViewId() {
		return GameVisualizer.V_BONUS;
	}


	@Override
	public ICell TryHideBehind(ICell replacement) {
		return this;
	}


	@Override
	public boolean IsPassable() {
		return true;
	}
}
