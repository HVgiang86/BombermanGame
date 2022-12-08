package bomberman.entities.animated_entity;

import bomberman.Board;
import bomberman.Graphics.Screen;
import bomberman.entities.AnimatedEntity;

public abstract class Characters extends AnimatedEntity{
	
	/**
	 * param: 
	 * 		direct: character đi thẳng/trái/phải/dưới
	 * 		alive: lưu lại trạng thái: sống/đã bị tiêu diệt của character
	 * 		moving: lưu lại trạng thái có đang di chuyển hay không
	 */
    protected int direct = -1;
    protected Board board;
    protected boolean alive = true;
    protected boolean moving = false;
    public int timeAfter = 40;
    
    public Characters() {
		
	}
    
    public Characters(int x,int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

   
    @Override
    public abstract void update();
    
    
    public abstract void calculateMove();

    public abstract void render(Screen screen);

    /**
     *
     */
    public abstract void move(double xc, double yc);
    
    public abstract void kill();
    
    protected abstract void afterKill();
    
    protected abstract boolean canMove(double x,double y);
}