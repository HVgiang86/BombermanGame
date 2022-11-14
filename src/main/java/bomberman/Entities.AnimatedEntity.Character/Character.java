package bomberman.Entities.AnimatedEntity.Character;

import bomberman.Entities.AnimatedEntity;

public abstract class Character extends AnimatedEntity{
	
	/**
	 * param: 
	 * 		direct: character đi thẳng/trái/phải/dưới
	 * 		alive: lưu lại trạng thái: sống/đã bị tiêu diệt của character
	 * 		moving: lưu lại trạng thái có đang di chuyển hay không
	 */
    protected int direct = -1;
    protected boolean alive = true;
    protected boolean moving = false;
    
    public Character(int x,int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public abstract void render();

    @Override
    public abstract void update();
    
    // phương thức tính hoạt động di chuyern của entity
    public abstract void calculateMove();


    /**
     *
     */
    public abstract void move(double xc, double yc);
    
    public abstract void kill();
    
    protected abstract void afterKill();
    
    protected abstract boolean canMove(double x,double y);
}
