package bomberman.entities.animated_entity.bomb;

import bomberman.Board;
import bomberman.Graphics.Screen;
import bomberman.entities.Entity;
import bomberman.entities.animated_entity.Bomb;

public class Flame extends Entity{
	
	protected Board board;
	protected int direction;
	private int max_length;// do dai lon nhat cua ngon lua
	protected  int xOrigin, yOrigin; 
	protected FlameSegement [] flameSegement = new FlameSegement[0];
	
	/**
	 * 
	 * @param x: hoanh do bat dau cua ngon lua
	 * @param y: tung do bat dau cua ngon lua
	 * @param direction: luu lai huong cua ngon lua
	 * @param maxlength do dai lon nhat cua ngon lua
	 * @param board
	 */
	public Flame(int x,int y,int direction,int maxlength,Board board) {
		this.xOrigin = x;
		this.yOrigin = y;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.max_length = maxlength;
		this.board = board;
		
	}
	
	public void createFlameSegement() {
		// 1 do dai tuong ung vi 1 segement
		this.flameSegement = new FlameSegement[caculateDistance()];
		
		boolean last = false; // danh dau cho segement cuoi cung moi nhanh ngon lua
		
		int x1 = (int) x;
		int y1 = (int) y;
		
		for(int a = 0; a< flameSegement.length; a++) {
			last = (a==flameSegement.length -1)? true : false;
			
			switch (direction) {
			case 0: x++;
			case 1: y++;
			case 2: x--;
			case 3: y--;
			}
			
			flameSegement[a] = new FlameSegement(x1, y1, direction, last);
		}
		
	}
	
	
	//tinh do dai cua ngon lua
	public int caculateDistance() {
		
		int max = 0;
		int x = (int) this.x;
		int y = (int) this.y;
		
		while(max < max_length) {
			if(direction == 0 ) x++;
			if(direction == 1 ) y++;
			if(direction == 2 ) x--;
			if(direction == 3 ) y--;
			
		Entity a = this.board.getEntity(x, y, null);
		
		if(a instanceof Bomb) ++ max;
		
		
		
		}
		return max;
	}
	
	//tra ve ngon lua do bomb tao ra tai  vi tri x,y
	public FlameSegement flameSegementAt(int x, int y) {
		for(int i = 0; i<this.flameSegement.length;i++) {
			if(this.flameSegement[i].getX() == x && this.flameSegement[i].getY()== y)
				return flameSegement[i];
		}
		return null;
	}


	@Override
	public void render(Screen screen) {
	for(int i =0; i< this.flameSegement.length; i++) {
		this.flameSegement[i].render(screen);
	}
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public boolean collide(Entity e) {
		
		
		return false;
	}

}
