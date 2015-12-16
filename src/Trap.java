import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
	public ICell View(Hero hero) {
		return (MovablesId.contains(hero.Id) ? this : new EmptyCell());
	}

	@Override
	public Trap Tick() {
		int decreasedCooldown = GetDecreasedCooldown();
		return new Trap (GetDecreasedCooldown(), MaxCooldown, MovablesId);
	}
	
	public int GetDecreasedCooldown() {
		return Math.max(Cooldown - 1, 0);
	}

	public Trap Act(int triggerId) {
		ImmutableSet.Builder<Integer> builder = MovablesId.builder();
		return new Trap(MaxCooldown, MaxCooldown, builder.add(triggerId).build());
	}
	
	@Override
	public ICell Action(IMovable movable) {
		return Action.Act(movable, this).StayOn(Action.AfterAct(movable, this));
	}
}
