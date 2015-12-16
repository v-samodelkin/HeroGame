package Actions;
import java.util.ArrayList;
import BindingTies.Field;
import Helpers.Movement;

public interface IMovementAction {
	public void Act(ArrayList<Movement> movements, Movement movement, Field newField, Field oldField);
}
