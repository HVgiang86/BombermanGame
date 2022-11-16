package bomberman.entities;

import bomberman.Graphics.IRenderable;
import bomberman.Graphics.Sprite;

public abstract class Entity implements IRenderable {

	/**
	 * param: 
	 * x,y :  
	 * removed: tinh trang cua  entity : da bi tieu diet hay chua
	 * 			
	 */
	protected double x,y;
	protected boolean removed = false;
	protected Sprite sprite;
	
    public abstract void render() ;

    public abstract void update();
    
  
    public void remove() {
    	removed = true;
    }
    
      public boolean isRemoved() {
    	  return removed;
      }
  
      public double getX() {
    	  return x;
      }
      
      public double getY() {
    	  return y;
      }

	public Sprite getSprite() {
		return sprite;
	}

	// phuong thuc xu ly va cham giua cac thuc the
	public abstract boolean collide(Entity e);
      
}
