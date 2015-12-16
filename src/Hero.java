public class Hero implements IMovable {
	public final int Bonuses;
	public final int Lives;
	public final int Id;
	public final Game CurrentGame;
	public final ICell Before;
	public final IAi Ai;
	public int GetWeight() { return 70; };
	public int GetDamage() { return 1; };
	
	public Hero(int bonuses, int lives, int id, Game game, ICell before, IAi ai) {
		Bonuses = bonuses;
		Lives = lives;
		Id = id;
		CurrentGame = game;
		Before = before;
		Ai = ai;
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
	public ICell View(Hero hero) {
		return this;
	}
	
	@Override
	public Position GetPosition (Field field) {
		for (int x = 0; x < field.GetWidth(); x++)
			for (int y = 0 ; y < field.GetLength(); y++)
				if (field.Cells[x][y] == this)
					return new Position(x, y);
		return new Position();
	}

	@Override
	public ICell Action(IMovable movable) {
		if (GetWeight() > movable.GetWeight())
			return Attacked(movable.GetDamage());
		else
			return movable.Attacked(GetDamage());
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
		if (Lives > 0)
			return new Hero(Bonuses, Lives, Id, CurrentGame, cell, Ai);
		else
			return cell;
	}
	@Override
	public ICell GetStayOn() {
		return (Before == null ? new EmptyCell() : Before);
	}
	public int GetBonusesCount() {
		return Bonuses;
	}
	
	public int GetLivesCount() {
		return Lives;
	}

}
