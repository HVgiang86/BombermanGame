package bomberman.entities.tiles;

import bomberman.Graphics.Screen;
import bomberman.Graphics.Sprite;
import bomberman.Level.Coordinates;
import bomberman.entities.Entity;

public abstract class Tile extends Entity{

	public Tile(int x, int y,Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public boolean collide(Entity e) {
		return false;
	}
	
	public void render(Screen screen) {
		screen.renderEntity(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y),this);
	}
	
	public void update() {
		
	}
}
