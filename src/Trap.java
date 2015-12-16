// +
import com.google.common.collect.ImmutableSet;

public class Trap implements ICell {
	

	public final TrapAction Action;
	public final ImmutableSet<Integer> MovablesId;
	public final int Cooldown;
	public final int MaxCooldown;

	public Trap(int currentCooldown, int maxCooldown, ImmutableSet<Integer> movablesId) {
		Cooldown = currentCooldown;
		MaxCooldown = maxCooldown;
		MovablesId = movablesId;
		Action = TrapAction.GetAction(currentCooldown);
	}
	
	public Trap() {
		this(0, 10, ImmutableSet.of());
	}
	@Override
	public ICell ViewBy(Hero hero) {
		return (MovablesId.contains(hero.Id) ? this : new EmptyCell());
	}

	@Override
	public Trap Tick() {
		return new Trap (GetDecreasedCooldown(), MaxCooldown, MovablesId);
	}
	
	public int GetDecreasedCooldown() {
		return Math.max(Cooldown - 1, 0);
	}

	public Trap Act(int triggerId) {
		ImmutableSet.Builder<Integer> builder = new ImmutableSet.Builder<Integer>();
		builder.addAll(MovablesId);
		return new Trap(MaxCooldown, MaxCooldown, builder.add(triggerId).build());
	}
	
	@Override
	public ICell Action(IMovable movable) {
		return Action.Act(movable, this).StayOn(Action.AfterAct(movable, this));
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
	
	public int getViewId() {
		return Action.GetViewId();
	}

	@Override
	public ICell TryHideBehind(ICell replacement) {
		return this;
	}
}
