package bomberman.entities.animated_entity.bomb;

import bomberman.Board;
import bomberman.Games;
import bomberman.Graphics.Screen;
import bomberman.Graphics.Sprite;
import bomberman.Level.Coordinates;
import bomberman.entities.AnimatedEntity;
import bomberman.entities.Entity;
import bomberman.entities.animated_entity.Bomber;
import bomberman.entities.animated_entity.Characters;

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
		// xu ly bomber di ra sau khi vua dat bom
		if(e instanceof Bomber) {
			 double diffX = e.getX() - Coordinates.tileToPixel(getX());
			 double diffY = e.getY() - Coordinates.tileToPixel(getY());
		// kiem tra xem bomber di chuyen ra ngoai vi tri dat bom hay chua
		if(!(diffX >= -10 && diffX < 16 && diffY >= 1 && diffY <= 28))
			{this.allowedToPassThru = false;}
		
		return this.allowedToPassThru;
		}
		
		// xu ly va cham voi Flame cua bomb khac
		if( e instanceof Flame) {
			time_explode();
			return true;
		}
		return false;
	}
	
	public void time_explode() {
		this.timeToExplode = 0;
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
