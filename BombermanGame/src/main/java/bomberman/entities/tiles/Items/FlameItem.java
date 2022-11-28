package bomberman.entities.tiles.Items;

import bomberman.Graphics.Sprite;
import bomberman.entities.Entity;

public class FlameItem extends Item{

	public FlameItem(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	public boolean collide(Entity e) {
		
		return false;
	}

}
