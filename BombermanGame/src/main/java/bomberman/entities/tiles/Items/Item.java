package bomberman.entities.tiles.Items;

import bomberman.Graphics.Sprite;
import bomberman.entities.tiles.Tile;

public abstract class Item extends Tile{
	
	protected int duration = -1;// thoi gian cua item
	protected boolean active = false;
	protected int level;

	public Item(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

}
