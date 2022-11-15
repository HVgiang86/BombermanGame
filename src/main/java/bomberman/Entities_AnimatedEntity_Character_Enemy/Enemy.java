package bomberman.Entities_AnimatedEntity_Character_Enemy;

import bomberman.Entities_AnimatedEntity_Character.Character;

public abstract class Enemy extends Character{

	
	// thong so nguoi choi
	protected int points;//diem so
	protected double speed; // toc do
	
	
	public Enemy() {
	
		}
	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calculateMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(double xc, double yc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kill() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void afterKill() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean canMove(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

}
