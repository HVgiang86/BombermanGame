package bomberman.entities.animated_entity.character.enemy;

import java.awt.Color;

import bomberman.Board;
import bomberman.Games;
import bomberman.Graphics.Screen;
import bomberman.Graphics.Sprite;
import bomberman.Level.Coordinates;
import bomberman.entities.Entity;
import bomberman.entities.LayeredEntity;
import bomberman.entities.Message;
import bomberman.entities.animated_entity.Characters;
import bomberman.entities.tiles.Wall;
import bomberman.entities.tiles.destroyable.Brick;

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
		if (alive)
			chooseSprite();
		else {
			if (timeAfter > 0) {
				sprite = deadSprite;
				animate = 0;
			} else {
				sprite = Sprite.movingSprite(Sprite.ghost_dead1, Sprite.ghost_dead2, Sprite.ghost_dead3, animate, 60);
			}
		}
		screen.renderEntity((int) this.x, (int) this.y - sprite.SIZE, this);
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
		if (steps != max_steps) {
			steps--;
			if (steps == 0)
				steps = max_steps;
		} else {
			steps--;
			////////////////////////////////

		}
		if (currentDirect == 0)
			move(x, y + speed);
		if (currentDirect == 1)
			move(x + speed, y);
		if (currentDirect == 2)
			move(x - speed, y);
		if (currentDirect == 3)
			move(x, y - speed);
	}

	@Override
	public void move(double xc, double yc) {
		if (!alive)
			return;
		if(!canMove(xc, yc)) {
			soften(xc, yc);
			moving = true;
			return;
		}	
		
		if(y>yc) direct = 0;
		if(y<yc) direct = 3;
		if(x<xc) direct = 2;
		if(x>xc) direct = 1;
		y = yc;
		x = xc;
	}
	
	private void soften(double xa, double ya) {
        if (xa != x && y == ya) {
            double near1 = (ya / Games.Tiles_size) * Games.Tiles_size;
            double near2 = (ya / Games.Tiles_size + 1) * Games.Tiles_size;
            if (ya - near1 <= 8) {
                if (canMove(xa, near1)) {
                    y--;
                    soften(xa, ya--);
                    if (xa > x) direct = 4;
                    else direct = 3;
                }
            }
            if (near2 - ya <= 8) {
                if (canMove(xa, near2)) {
                    y++;
                    move(xa, ya++);
                    if (xa > x) direct = 4;
                    else direct = 3;
                }
            }
        } else if (xa == x && y != ya) {
            double near1 = (xa /Games.Tiles_size) * Games.Tiles_size;
            double near2 = (xa / Games.Tiles_size + 1) *Games.Tiles_size;
            if (xa - near1 <= 8) {
                if (canMove(near1, ya)) {
                    x--;
                    soften(xa--, ya);
                }
            }
            if (near2 - xa <= 8) {
                if (canMove(near2, ya)) {
                    x++;
                    soften(xa++, ya);
                    direct = 1;
                }
            }
        }
    }

	public boolean canMove(double x, double y) {

		double top_y = y - 1;
		double screen_y = y - Games.Tiles_size;
		double screen_leftx = x - 1 + Games.Tiles_size;

		int tile_upLx = Coordinates.pixelToTile(x);
		int tile_upLy = Coordinates.pixelToTile(screen_y);

		int tile_upRx = Coordinates.pixelToTile(screen_leftx);
		int tile_upRy = Coordinates.pixelToTile(screen_y);

		int tile_topLx = Coordinates.pixelToTile(x);
		int tile_topLy = Coordinates.pixelToTile(top_y);

		int tile_topRx = Coordinates.pixelToTile(screen_leftx);
		int tile_topRy = Coordinates.pixelToTile(top_y);

		Entity entity_UpLeft = board.getEntity(tile_upLx, tile_upLy, this);
		Entity entity_UpRight = board.getEntity(tile_upLx, tile_upLy, this);
		Entity entity_LoLeft = board.getEntity(tile_topLx, tile_topLy, this);
		Entity entity_LoRight = board.getEntity(tile_topRx, tile_topRy, this);
		if (entity_LoLeft instanceof Wall || entity_LoRight instanceof Wall || entity_UpLeft instanceof Wall || entity_UpRight instanceof Wall) return false;
		 else if (entity_LoLeft instanceof LayeredEntity || entity_LoRight instanceof LayeredEntity || entity_UpLeft instanceof LayeredEntity || entity_UpRight instanceof LayeredEntity) {
	            if (entity_LoLeft instanceof LayeredEntity) {
	                Entity top = ((LayeredEntity) entity_LoLeft).getTopEntity();
	                if (top instanceof Brick) return false;
	            }
	            if (entity_LoRight instanceof LayeredEntity) {
	                Entity top = ((LayeredEntity) entity_LoRight).getTopEntity();
	                if (top instanceof Brick) return false;
	            }
	            if (entity_UpLeft instanceof LayeredEntity) {
	                Entity top = ((LayeredEntity) entity_UpLeft).getTopEntity();
	                if (top instanceof Brick) return false;
	            }
	            if (entity_UpRight instanceof LayeredEntity) {
	                Entity top = ((LayeredEntity) entity_UpRight).getTopEntity();
	                if (top instanceof Brick) return false;
	            }
	        }
	        return !collide(entity_LoLeft) && !collide(entity_LoRight) && !collide(entity_UpLeft) && !collide(entity_UpRight);

	}

	@Override
	public void kill() {
		// kiem tra Enemy da bi giet chua neu bi giet thi cong diem cho nguoi choi
		if (!this.alive)
			return;
		this.alive = false;

		board.addPoints(points);

		Message msg = new Message("+ " + this.points, max_steps, max_steps, 2, Color.white, 14);
		this.board.addMessage(msg);
	}

	@Override
	protected void afterKill() {
		if (timeAfter > 0)
			timeAfter--;
		else {
			if (finalAnimation > 0)
				finalAnimation--;
			else
				remove();
		}
	}

	protected abstract void chooseSprite();

}
