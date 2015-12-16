import java.util.HashMap;
import java.util.Map;

public abstract class TrapAction {

	public static Map<Boolean, TrapAction> ActionMapper;
	
	static {
		ActionMapper = new HashMap<Boolean, TrapAction>();
		ActionMapper.put(true, new TrapAtackAction());
		ActionMapper.put(false, new TrapWaitAction());
	}
	
	public abstract IMovable Act(IMovable movable, Trap trap);

	public abstract Trap AfterAct(IMovable movable, Trap trap);

	public static TrapAction GetAction(int currentCooldown) {
		return ActionMapper.get(currentCooldown == 0);
	}
}
