// +
import java.util.HashMap;
import java.util.Map;

public class Hero implements IMovable {
	public final int Bonuses;
	public final int Lives;
	public final int Id;
	public final Game CurrentGame;
	public final ICell Before;
	public final IAi Ai;
	public final Map<Boolean, ICell>  DieChecker;
	public int GetWeight() { return 70; };
	public int GetDamage() { return 1; };
	
	public Hero(int bonuses, int lives, int id, Game game, ICell before, IAi ai) {
		Bonuses = bonuses;
		Lives = lives;
		Id = id;
		CurrentGame = game;
		Before = before;
		Ai = ai;
		DieChecker = new HashMap<Boolean, ICell>();
		DieChecker.put(true, before);
		DieChecker.put(false, this);
	}
	
	public Hero(Game game, IAi ai) {
		this(0, 5, game.GetNewHeroId(), game, new EmptyCell(), ai);
	}
	@Override
	public Hero AddBonus(int bonusCount) {
		return new Hero(Bonuses + bonusCount, Lives, Id, CurrentGame, Before, Ai);
	}
	@Override
	public Hero Trapped(int damage) {
		return new Hero(Bonuses, Lives - damage, Id, CurrentGame, Before, Ai);
	}
	@Override
	public Hero Die() {
		return new Hero(Bonuses, 0, Id, CurrentGame, Before, Ai);
	}
	@Override
	public Hero Tick() {
		return this;
	}
	@Override
	public Direction GetTurnDirection() {
		return Ai.GetTurnDirection(this, CurrentGame.GetViewField(this));
	}

	@Override
	public ICell ViewBy(Hero hero) {
		return this;
	}
	
	@Override
	public Position GetPosition (Field field) {
		return field.GetPosition(this);
	}

	@Override
	public ICell Action(IMovable movable) {
		try {
			Hero h = (Hero)movable;
			System.out.println("!!!!!!!!!!!!! " + GetId() + " " + movable.GetId());
		} catch (Exception e) {}
		return Attacked(movable.GetDamage());
	}

	@Override
	public int GetId() {
		return Id;
	}
	@Override
	public ICell Attacked (int damage) {
		return Trapped(damage);
	}
	@Override
	public ICell StayOn(ICell cell) {
		return new Hero(Bonuses, Lives, Id, CurrentGame, cell, Ai).CheckDie();
	}
	@Override
	public ICell GetStayOn() {
		return Before;
	}
	public int GetBonusesCount() {
		return Bonuses;
	}
	
	public int GetLivesCount() {
		return Lives;
	}
	
	public ICell CheckDie() {
		return DieChecker.get(Lives == 0);
	}
	@Override
	public Position IsSurrender(Position from, ICell what, Position to) {
		try {
			return what.TryToScare(this, from, to);
		} catch (Exception e) {
			return from;
		}
	}
	@Override
	public Position TryToScare(Hero hero, Position from, Position to) {
		return to;
	}
	@Override
	public boolean IsEmpty() {
		return false;
	}
	
	public int getViewId() {
		return GameVisualizer.V_HERO;
	}
	@Override
	public ICell TryHideBehind(ICell replacement) {
		return this;
	}

}
