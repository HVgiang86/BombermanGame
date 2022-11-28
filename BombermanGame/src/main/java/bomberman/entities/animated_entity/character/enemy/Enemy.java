package bomberman.entities.animated_entity.character.enemy;

import bomberman.Board;
import bomberman.Games;
import bomberman.Graphics.Screen;
import bomberman.Graphics.Sprite;
import bomberman.entities.animated_entity.Characters;

public abstract class Enemy extends Characters {

	// thong so nguoi choi
	protected int points;// diem so
	protected double speed; // toc do

	protected final double max_steps; // do dai lon nhat 1 buoc
	protected final double rest; //
	protected double steps; // do dai 1 buoc

	protected int finalAnimation = 30;
	protected Sprite deadSprite;

	public Enemy(int x, int y, Board board, Sprite deadSprite, double speed, int point) {
		super(x, y, board);

		this.points = point;
		this.speed = speed;
		this.max_steps = Games.Tiles_size / this.speed;
		this.rest = (this.max_steps - (int) this.max_steps) / this.max_steps;
		this.steps = this.max_steps;
		this.timeAfter = 20;
		this.deadSprite = deadSprite;
	}

	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		animate();

		if (!this.alive) {
			afterKill();
			return;
		}
		if (this.alive)
			calculateMove();

	}

	@Override
	public void calculateMove() {
		

	}

	@Override
	public void move(double xc, double yc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void kill() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void afterKill() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean canMove(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

}
