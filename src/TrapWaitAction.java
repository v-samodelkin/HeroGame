
public class TrapWaitAction extends TrapAction {

	@Override
	public IMovable Act(IMovable movable, Trap trap) {
		return movable;
	}

	@Override
	public Trap AfterAct(IMovable movable, Trap trap) {
		return trap;
	}

}
