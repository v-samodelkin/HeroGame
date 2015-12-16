import java.util.ArrayList;

public interface IMovementAction {
	public void Act(ArrayList<Movement> movements, Movement movement, Field newField, Field oldField);
}
