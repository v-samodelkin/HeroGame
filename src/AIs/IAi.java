package AIs;
import FieldObjects.*;
import Helpers.*;
import BindingTies.*;
public interface IAi {
	Direction GetTurnDirection(Hero hero, Field viewField);
	String GetAiName();
}
