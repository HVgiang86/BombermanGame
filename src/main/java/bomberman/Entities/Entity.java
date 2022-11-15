package bomberman.Entities;

import bomberman.Graphics.IRenderable;

public abstract class Entity implements IRenderable {

	/**
	 * param: 
	 * x,y : kich thuoc ban dau cua entity 
	 * removed: tinh trang cua  entity : da bi tieu diet hay chua
	 * 			
	 */
	protected double x,y;
	protected boolean removed = false;
	
	
    public abstract void render() ;

    public abstract void update();
    
  
   //entity hoat dong
    public void remove() {
    	removed = true;
    }
    
    //Tra ve trang thai cua entity
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
