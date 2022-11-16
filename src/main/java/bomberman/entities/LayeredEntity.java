package bomberman.entities;

import java.util.LinkedList;

/**
 * chứa và quản lý Entity tại cùng một vị trí
 */

public class LayeredEntity extends Entity {

	protected LinkedList<Entity> entities = new LinkedList<Entity>();

	public LayeredEntity(int x,int y, Entity ...entities) {
		
		
	}
	
	@Override
	public void render() {

	}

	@Override
	public void update() {

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
}
