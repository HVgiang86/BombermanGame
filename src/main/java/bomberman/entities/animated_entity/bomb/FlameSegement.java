package bomberman.entities.animated_entity.bomb;

import bomberman.Graphics.Screen;
import bomberman.Graphics.Sprite;
import bomberman.entities.Entity;

public class FlameSegement extends Entity {

	protected boolean last;

	/**
	 * 
	 * @param x:         do dai ngon lua theo truc x
	 * @param y:         do dai ngon lua the truc y
	 * @param direction: danh dau huong cua ngon lua 
	 * 					0 : theo chieu duong cua truc x
	 *                  1 : theo chieu duong cua truc y, 
	 *                  2 : theo chieu am cua truc x,
	 *                  3: theo chieu am cua truc y
	 * @param last       danh dau phan cuoi cung cua ngon lua
	 */
	public FlameSegement(int x, int y, int direction, boolean last) {
		this.x = x;
		this.y = y;
		this.last = last;

		switch (direction) {
		case 0: // theo chieu duong cua truc x
			if (!last) {
				sprite = Sprite.bomb_exploded_horizontal2;
			} else {
				sprite = Sprite.bomb_exploded_horizontal_right_last1;
			}
			break;
		case 1: // theo chieu duong cua truc y
			if (!last) {
				sprite = Sprite.bomb_exploded_vertical2;
			} else {
				sprite = Sprite.bomb_exploded_vertical_top_last1;
			}
			break;
		case 2: // theo chieu am cua truc x
			if (!last) {
				sprite = Sprite.bomb_exploded_horizontal2;
			} else {
				sprite = Sprite.bomb_exploded_horizontal_left_last1;
			}
			break;
		case 3: // theo chieu am cua truc y
			if (!last) {
				sprite = Sprite.bomb_exploded_horizontal2;
			} else {
				sprite = Sprite.bomb_exploded_vertical_down_last1;
			}
			break;
		}
	}
	
	
	@Override
	public void render(Screen screen) {
		int xt = (int) x << 4;
		int yt = (int) y << 4;
		
		screen.renderEntity(xt, yt, this);

	}

	@Override
	public void update() {
		

	}

	@Override
	public boolean collide(Entity e) {
		
		return false;
	}

}
