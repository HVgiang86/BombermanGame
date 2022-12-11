package bomberman.entities.animated_entity;

import java.util.Iterator;
import java.util.List;

import bomberman.Board;
import bomberman.Games;
import bomberman.Exceptions.LoadLevelException;
import bomberman.Graphics.Screen;
import bomberman.Graphics.Sprite;
import bomberman.Input.KeyBoard;
import bomberman.Level.Coordinates;
import bomberman.entities.Entity;
import bomberman.entities.LayeredEntity;
import bomberman.entities.animated_entity.bomb.Bomb;
import bomberman.entities.animated_entity.bomb.FlameSegement;
import bomberman.entities.animated_entity.character.enemy.Enemy;
import bomberman.entities.tiles.Portal;
import bomberman.entities.tiles.Wall;
import bomberman.entities.tiles.Items.Item;
import bomberman.entities.tiles.destroyable.Brick;

public class Bomber extends Characters {

	private final int time = 15;
	protected KeyBoard keyBoard;
	protected int finalAnimation = 30;

	protected int timeBetweenPutBomb = 0;
	private final List<Bomb> bombs;
	private boolean auto = false;
	private boolean isPlaceBomb = false;
	private int countTime = 0;
	private boolean render = true;

	private final int maxStep;
	private int steps;

	public Bomber(int x, int y, Board b) {
		super(x, y, b);
		timeAfter = 250;
		bombs = this.board.getBomb();
		keyBoard = this.board.getInput();
		sprite = Sprite.bomber_right1;
		maxStep = (int) Math.round(Games.Tiles_size / Games.getBomberSpeed());
		steps = maxStep + 1;

	}

	@Override
	public void update() {
		animate();
		clearBombs();
		if (!alive) {
			afterKill();
			return;
		}
		if (timeBetweenPutBomb < 7500)
			timeBetweenPutBomb = 0;
		else
			timeBetweenPutBomb--;
		calculateMove();

	}

	public void clearBombs() {
		Iterator<Bomb> bom = bombs.iterator();
		Bomb b;
		while (bom.hasNext()) {
			b = bom.next();
			if (b.isRemoved()) {
				bom.remove();
				Games.addBombRate(1);
			}
		}
	}

	@Override
	public void calculateMove() {
		if (auto)
			processAuto();
		else
			processManual();

	}

	// di chuyen bomber tu dong
	private void processAuto() {
		if (steps == maxStep + 1) {

		}
		steps--;
		if (steps == 0) {
			steps = maxStep;
			////////////////////////////
			
			
			
			
			
			
		}
	}

	// di chuyen bomber bang cac pim
	public void processManual() {
		// di chuyen xuong
		if (keyBoard.down) {
			moving = true;
			move(x, y + Games.getBomberSpeed());
			countTime--;
			if (countTime < 0)
				countTime = time;
			;
		}
		// di chuyen len
		else if (keyBoard.up) {
			moving = true;
			move(x, y - Games.getBomberSpeed());
			countTime--;
			if (countTime < 0)
				countTime = time;
		}
		// di chuyen sang phai
		else if (keyBoard.right) {
			moving = true;
			move(x + Games.getBomberSpeed(), y);
			countTime--;
			if (countTime < 0)
				countTime = time;
		}
		// di chuyen sang trai
		else if (keyBoard.left) {
			moving = true;
			move(x - Games.getBomberSpeed(), y);
			countTime--;
			if (countTime < 0)
				countTime = time;
		}
		// khong di chuyen
		else {
			countTime = 0;
			moving = false;
		}
	}

	@Override
	public void render(Screen screen) {
		//kiem tra bomber da chet hay chua render ra man hinh, hinh tuong ung
		calculateXOffset();
		if (alive)
			chooseSprite();
		else
			sprite = Sprite.movingSprite(Sprite.bomber_dead1, Sprite.bomber_dead2, animate, 60);
		if (render)
			screen.renderEntity((int) x, (int) y - sprite.SIZE, this);
	}

	public void calculateXOffset() {
		int x = Screen.calculateXOffset(board, this);
		Screen.setOffset(x, 0);
	}

	private void chooseSprite() {
		switch (direct) {
		case 0:
			sprite = Sprite.bomber_up2;
			if (moving)
				sprite = Sprite.movingSprite(Sprite.bomber_up1, Sprite.bomber_up2, animate, 20);

		case 1:
			sprite = Sprite.bomber_down2;
			if (moving)
				sprite = Sprite.movingSprite(Sprite.bomber_down1, Sprite.bomber_down2, animate, 20);

		case 2:
			sprite = Sprite.bomber_left2;
			if (moving)
				sprite = Sprite.movingSprite(Sprite.bomber_left1, Sprite.bomber_left2, animate, 20);
		default:
			sprite = Sprite.bomber_right2;
			if (moving)
				sprite = Sprite.movingSprite(Sprite.bomber_right1, Sprite.bomber_right2, animate, 20);
		}

	}

	@Override
	public void move(double xc, double yc) {
		// su dung canMove() de kiem tra xem co di chuyen ti diem da tinh toan hay khong
		// cap nhat vi tri moi cho bomber
		// luu lai gia tri direct sau khi di chuyen

		if (yc > 0)
			this.direct = 1;// di xuong
		if (yc < 0)
			this.direct = 0;// di len
		if (xc < 0)
			this.direct = 3;// sang trai
		if (xc > 0)
			this.direct = 4;// sang phai
		if (canMove(0, yc)) {
			this.y += yc;
		}
		if (canMove(xc, 0)) {
			this.x += xc;
		}
	}

	@Override
	public void kill() {
		if (!this.alive)
			return;
		Games.playSE(4);
		alive = false;
		board.addLives(-1);
	}

	@Override
	protected void afterKill() {
		if (this.timeAfter > 0) {
			--timeAfter;
			if (finalAnimation > 0)
				finalAnimation--;
			else
				render = false;
		} else {

			try {
				if (board.getLives() > 0)
					board.restart();
				else
					board.endGame();
			} catch (LoadLevelException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	protected boolean canMove(double x, double y) {
		// kiem tra vi tri tiep theo bomber co the di co an toan khong
		// *         entity_BackLeft     *
		// entity_UpRight   bomber     entity_UpLeft
		// *      entity_BackRight    *
		double back_Ly = y - 1 + (double) Games.Tiles_size;
		double up_Lx = x + 1;
		double back_Rx = x - 1 + (double) Games.Tiles_size * 3 / 4;
		double back_Ry = y - 1 + (double) Games.Tiles_size;
		double up_Rx = x - 1 + (double) Games.Tiles_size * 3 / 4;
		double up_Ry = y + 1 - Games.Tiles_size;
		double back_Lx = x;
		double up_Ly = y + 1 - Games.Tiles_size;

		int tile_UpLx = Coordinates.pixelToTile(up_Lx);
		int tile_UpLy = Coordinates.pixelToTile(up_Ly);//

		int tile_UpRx = Coordinates.pixelToTile(up_Rx);
		int tile_UpRy = Coordinates.pixelToTile(up_Ry);

		int tile_BackLx = Coordinates.pixelToTile(back_Lx);
		int tile_BackLy = Coordinates.pixelToTile(back_Ly);

		int tile_BackRx = Coordinates.pixelToTile(back_Rx);
		int tile_BackRy = Coordinates.pixelToTile(back_Ry);

		Entity entity_UpLeft = board.getEntity(tile_UpLx, tile_UpLy, this);
		Entity entity_UpRight = board.getEntity(tile_UpRx, tile_UpRy, this);
		Entity entity_BackLeft = board.getEntity(tile_BackLx, tile_BackLy, this);
		Entity entity_BackRight = board.getEntity(tile_BackRx, tile_BackRy, this);
		if (entity_BackLeft instanceof Wall || entity_BackRight instanceof Wall || entity_UpLeft instanceof Wall
				|| entity_UpRight instanceof Wall)
			return false;
		if (entity_BackLeft instanceof LayeredEntity || entity_BackRight instanceof LayeredEntity
				|| entity_UpLeft instanceof LayeredEntity || entity_UpRight instanceof LayeredEntity) {
			if (entity_BackLeft instanceof LayeredEntity) {
				Entity top = ((LayeredEntity) entity_BackLeft).getTopEntity();
				if (top instanceof Brick)
					return false;
				if (top instanceof Item) {
					top.collide(this);
					return true;
				}
				if (top instanceof Portal && board.detectEnemies()) {
					if (top.collide(this))
						board.nextLevel();
					return true;
				}
			}
			if (entity_BackRight instanceof LayeredEntity) {
				Entity top = ((LayeredEntity) entity_BackRight).getTopEntity();
				if (top instanceof Brick)
					return false;
				if (top instanceof Item) {
					top.collide(this);
					return true;
				}
				if (top instanceof Portal && board.detectEnemies()) {
					if (top.collide(this))
						board.nextLevel();
					return true;
				}
			}
			if (entity_UpLeft instanceof LayeredEntity) {
				Entity top = ((LayeredEntity) entity_UpLeft).getTopEntity();
				if (top instanceof Brick)
					return false;
				if (top instanceof Item) {
					top.collide(this);
					return true;
				}
				if (top instanceof Portal && board.detectEnemies()) {
					if (top.collide(this))
						board.nextLevel();
					return true;
				}
			}
			if (entity_UpRight instanceof LayeredEntity) {
				Entity top = ((LayeredEntity) entity_UpRight).getTopEntity();
				if (top instanceof Brick)
					return false;
				if (top instanceof Item) {
					top.collide(this);
					return true;
				}
				if (top instanceof Portal && board.detectEnemies()) {
					if (top.collide(this))
						board.nextLevel();
					return true;
				}
			}
		}
		return !collide(entity_BackLeft) && !collide(entity_BackRight) && !collide(entity_UpLeft)
				&& !collide(entity_UpRight);
	}

	@Override
	public boolean collide(Entity e) {
		// kiem tra neu bomber va cham voi enemy thi bomber se chet
		if (e instanceof FlameSegement)
			e.collide(this);
		if (e instanceof Enemy)
			if (getXTile() == e.getXTile() && getYTile() == e.getYTile())
				kill();
		if (e instanceof Bomb)
			return e.collide(this);
		;
		return false;
	}
	
	 public void setAuto(boolean q) {
	        auto = q;
	    }

}
