package bomberman.entities.tiles.destroyable;

import bomberman.Graphics.Sprite;
import bomberman.entities.tiles.Tile;

public class Destroyable extends Tile {

	private final int Max_animate = 7500; // mac dinh chuyen dong la 7500
	private int animate = 0;// luu so lan chuyen dong
	protected boolean destroyed = false; // da pha huy hay chua
	protected int timeToDisappear = 20; // thoi gian de bien mat
	protected Sprite belowSprite = Sprite.grass;
	

	public Destroyable(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	public void update() {

		if (destroyed) {
			if (animate < Max_animate)
				animate++;
			else
				animate = 0;
			if (timeToDisappear > 0)
				timeToDisappear--;
			else
				remove();
		}
	}

	public void destroy() {
		destroyed = true;

	}

	protected Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2) {
		int calc = animate % 30;

		if (calc < 10) {
			return normal;
		}
		if (calc < 10) {
			return x1;
		}
		return x2;
	}

	public void addBelowSprite(Sprite sprite) {
		belowSprite = sprite;
		
	}

}
