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
	public ICell View(Hero hero) {
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
}
