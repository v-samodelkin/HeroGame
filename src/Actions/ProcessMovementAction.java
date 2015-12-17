package Actions;
import java.util.ArrayList;
import FieldObjects.*;
import Helpers.*;
import BindingTies.*;


public class ProcessMovementAction implements IMovementAction {

	@Override
	public void Act(ArrayList<Movement> movements, Movement movement, Field newField, Field oldField) {
			Position pos = movement.Waiting(5).DependOnPatience();
			newField.Cells[movement.X][movement.Y] = oldField.Cells[movement.X][movement.Y].GetStayOn();
			ICell previous = newField.Cells[pos.X][pos.Y];
			pos = movement.Mover.GetPositionAfterSurrenderCheck(pos, previous, new Position(movement.X, movement.Y));
			previous = TryToHide(newField.Cells[pos.X][pos.Y], oldField.Cells[pos.X][pos.Y]);
			pos = previous.TryToScare(movement.Mover, pos, new Position(movement.X, movement.Y));
			previous = TryToHide(newField.Cells[pos.X][pos.Y], oldField.Cells[pos.X][pos.Y]);
			newField.Cells[pos.X][pos.Y] = previous.Action(movement.Mover);

	}
	
	public ICell TryToHide(ICell verifiable, ICell replacement) {
		try {
			return verifiable.TryHideBehind(replacement);
		} catch (Exception e) {
			return replacement;
		}
	}
}
