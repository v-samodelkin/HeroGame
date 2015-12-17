package FieldObjects;
import BindingTies.*;
import Helpers.*;
public interface IMovable extends ICell {
	

	public ICell Die();
	public IMovable Trapped(int damage);
	public IMovable AddBonus(int bonusCount);
	
	public int GetId();
	public Direction GetTurnDirection();
	public int GetWeight();
	public int GetDamage();
	public ICell Attacked(int damage);
	public ICell StayOn(ICell cell);
	public ICell GetStayOn();
	public Position GetPosition(Field field);
	public Position GetPositionAfterSurrenderCheck(Position from, ICell what, Position to);
}
