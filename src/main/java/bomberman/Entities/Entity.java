package bomberman.Entities;

import bomberman.Graphics.IRenderable;

public abstract class Entity implements IRenderable {

	/**
	 * param: 
	 * x,y : kich thuoc ban dau cua entity 
	 * removed: lưu lại tình trạng của entity 
	 * 			(false: chưa bị tiêu diệt, true : đã bị tiêu diệt)
	 */
	protected double x,y;
	protected boolean removed = false;
	
    @Override
    public abstract void render() ;

    @Override
    public abstract void update();
    
  
   //Khi gọi đến phương thức remove để xác định tình trạng của entity
    public void remove() {
    	removed = true;
    }
    
    //Trả về tình trạng của entity 
      public boolean isRemoved() {
    	  return removed;
      }
  
      public double getX() {
    	  return x;
      }
      
      public double getY() {
    	  return y;
      }
      
}
