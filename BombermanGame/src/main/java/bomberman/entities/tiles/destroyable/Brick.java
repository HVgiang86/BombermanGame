package bomberman.entities.tiles.destroyable;

import bomberman.Graphics.Screen;
import bomberman.Graphics.Sprite;
import bomberman.Level.Coordinates;

public class Brick extends Destroyable {

	public Brick(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	public void update() {
		super.update();
	}

	public void render(Screen screen) {
		int x = Coordinates.tileToPixel(this.x);
		int y = Coordinates.tileToPixel(this.y);

		if (destroyed) {
			sprite = movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2);
			screen.renderEntityWithBelowSprite(x, y, this,belowSprite);
		}
		else {
			screen.renderEntity(x, y, this);
		}
	}

}