
public class TrapAtackAction extends TrapAction {

	@Override
	public IMovable Act(IMovable movable, Trap trap) {
		return movable.Trapped(1);
	}

	@Override
	public Trap AfterAct(IMovable movable, Trap trap) {
		return trap.Act(movable.GetId());
	}

}
