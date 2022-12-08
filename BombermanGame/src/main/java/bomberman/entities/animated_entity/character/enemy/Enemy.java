package bomberman.entities.animated_entity.character.enemy;

import java.awt.Color;

import bomberman.Board;
import bomberman.Games;
import bomberman.Graphics.Screen;
import bomberman.Graphics.Sprite;
import bomberman.entities.Message;
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
	private int currentDirect = 0; // =0 di xuong
								  // =1 di sang phai
								  // =2 di sang trai
								  // =3 di len tren

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
		moving = true;
		if(steps != max_steps) {
			steps--;
			if(steps == 0) steps = max_steps;
			}else {
				steps--;
				////////////////////////////////
				
			}
			if(currentDirect == 0) move(x, y+speed);
			if(currentDirect == 1) move(x+speed, y);
			if(currentDirect == 2) move(x-speed ,y);
			if(currentDirect == 3) move(x, y-speed);
	}

	@Override
	public void move(double xc, double yc) {
		if(!alive) return;
		this.y += yc;
		this.x += xc;
	}

	@Override
	public void kill() {
		// kiem tra Enemy da bi giet chua neu bi giet thi cong diem cho nguoi choi
		if(! this.alive) return;
		this.alive = false;
		
		board.addPoints(points);
		
		Message msg = new Message("+ " + this.points, max_steps, max_steps, 2, Color.white,14);
		this.board.addMessage(msg);
	}

	@Override
	protected void afterKill() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean canMove(double x, double y) {
		double xr = this.x;
		double yr = this.y -16;
		return false;
	}

}