package bomberman.entities.tiles;

import bomberman.Graphics.Sprite;
import bomberman.entities.Entity;

public class Portal extends Tile{

	public Portal(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	public boolean collide(Entity e) {
		
		
		return false;
	}

}
