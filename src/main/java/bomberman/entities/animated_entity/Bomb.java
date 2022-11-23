package bomberman.entities.animated_entity;

import bomberman.Board;
import bomberman.Games;
import bomberman.Graphics.Screen;
import bomberman.Graphics.Sprite;
import bomberman.entities.AnimatedEntity;
import bomberman.entities.Entity;
import bomberman.entities.animated_entity.bomb.Flame;
import bomberman.entities.animated_entity.bomb.FlameSegement;

public class Bomb extends AnimatedEntity {

	protected double timeToExplode = 120; // thoi gian phat no
	protected int timeAfter = 20; // thoi gian de no
	protected Board board;
	protected Flame[] flames;
	protected boolean exploded = false;
	protected boolean allowedToPassThru = true;

	public Bomb(int x, int y, Board board) {
		this.x = x;
		this.y = y;
		this.board = board;
		this.sprite = Sprite.bomb_exploded1;

	}

	@Override
	public void update() {
		if (this.timeToExplode > 0)
			timeToExplode--;
		else {
			if (!exploded)
				explode();
			else {
				updateFlames();
			}

			if (this.timeAfter > 0)
				timeAfter--;
			else {
				remove();
			}
		}
		animate();

	}

	// xu ly bomb no
	protected void explode() {
		exploded = true;
		allowedToPassThru = true;

		// xu ly cac thuc the dung tai vi tri bomb no
		Characters character = this.board.getChararcterAtExcluding((int) this.x, (int) this.y, null);
		if (character != null) {
			character.kill();
		}
		
		//tao cac ngon lua khi bomb no
		this.flames = new Flame[4];
		for (int i = 0; i < this.flames.length; i++) {
			flames[i] = new Flame((int) x, (int) y, i, Games.getBomberRadius(), board);
		}
	}

	@Override
	public boolean collide(Entity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void render(Screen screen) {
		if(this.exploded) {
			this.sprite = Sprite.bomb_exploded2;
			renderFlames(screen);
		}
		else {
			Sprite  x1 = Sprite.bomb_exploded1;
			Sprite  x2 = Sprite.bomb_exploded2;
			Sprite  x3 = Sprite.bomb_exploded3;
			this.sprite = Sprite.movingSprite(x1, x2, x3, this.animate, 60);
		}
		
		int xt = (int)this.x << 4;
		int yt = (int)this.y << 4;
		
		screen.renderEntity(xt, yt, this);
	}

	public FlameSegement flameAt(int x, int y) {
		if (this.exploded)
			return null;

		for (int i = 0; i < this.flames.length; i++) {
			if (this.flames[i] == null)
				return null;
			FlameSegement flameSegement = this.flames[i].flameSegementAt(x, y);
			if (flameSegement != null)
				return flameSegement;
		}
		return null;
	}

	public void updateFlames() {
		for (int i = 0; i < this.flames.length; i++) {
			this.flames[i].update();
		}
	}
	
	public void renderFlames(Screen screen) {
		for(int i = 0; i< this.flames.length; i++) {
			this.flames[i].render(screen);
		}
	}

}
