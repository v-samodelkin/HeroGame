import java.util.ArrayList;

public class AddToMovementsAction implements IMovementAction {

	@Override
	public void Act(ArrayList<Movement> movements, Movement movement, Field newField, Field oldField) {
		movements.add(movement);
	}

}
