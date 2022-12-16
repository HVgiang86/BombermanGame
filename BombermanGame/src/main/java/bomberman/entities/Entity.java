package bomberman.entities;

import bomberman.Graphics.IRenderable;
import bomberman.Graphics.Screen;
import bomberman.Graphics.Sprite;
import bomberman.Level.Coordinates;

public abstract class Entity implements IRenderable {

	/**
	 * param: x,y : toa do thuc the tren ma tran removed: tinh trang cua entity : da
	 * bi tieu diet hay chua
	 * 
	 */
	protected double x, y;
	protected boolean removed = false;
	protected Sprite sprite;

	public abstract void render(Screen screen);

	public abstract void update();

	public void remove() {
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	// phuong thuc xu ly va cham giua cac thuc the
	public abstract boolean collide(Entity e);

	// lay ra vi tri cua cac thuc the tren man hinh
	public int getXTile() {
		return Coordinates.pixelToTile(x + (double) sprite.SIZE / 2);
	}

	public int getYTile() {
		return Coordinates.pixelToTile(y - (double) sprite.SIZE / 2);
	}
}
