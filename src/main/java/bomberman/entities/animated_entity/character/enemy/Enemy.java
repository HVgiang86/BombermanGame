package bomberman.entities.animated_entity.character.enemy;

import bomberman.Graphics.Screen;
import bomberman.entities.animated_entity.Characters;

public abstract class Enemy extends Characters {

	
	// thong so nguoi choi
	protected int points;//diem so
	protected double speed; // toc do
	
	
	
	
	public Enemy() {
	
		}
	
	@Override
	public void render(Screen screen) {
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
