package bomberman.entities;

import java.util.LinkedList;

import bomberman.Graphics.Screen;
import bomberman.entities.tiles.destroyable.Destroyable;

/**
 * chứa và quản lý Entity tại cùng một vị trí
 */

public class LayeredEntity extends Entity {

	protected LinkedList<Entity> entities = new LinkedList<>();

	
	public LayeredEntity(int x,int y, Entity ...entities) {
		 this.x = x;
	        this.y = y;

	        for (int i = 0; i < entities.length; i++) {
	            this.entities.add(entities[i]);
	            if (i > 1)
	                if (entities[i] instanceof Destroyable) ((Destroyable) entities[i]).addBelowSprite(entities[i - 1].getSprite());
	        }
		
	}

	@Override
	public void update() {
		clearRemoved();
		getTopEntity().update();
	}

	@Override
	public boolean collide(Entity e) {
		return false;
	}

	
	// lấy ra entity trong cuối danh sách
	public Entity getTopEntity() {
		return entities.getLast();
	}

	// kiểm tra tình trạng của entity nếu đã bị tiêu diệt thì xóa
	public void clearRemoved() {
		if (getTopEntity().isRemoved())
			entities.removeLast();
	}

	@Override
	public void render(Screen screen) {
		getTopEntity().render(screen);
		
	}
}
