package bomberman.entities.tiles;

import bomberman.Graphics.Sprite;
import bomberman.entities.Entity;

public class Grass extends Tile{

	public Grass(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	public boolean collide(Entity e) {
		return true;
	}

}
