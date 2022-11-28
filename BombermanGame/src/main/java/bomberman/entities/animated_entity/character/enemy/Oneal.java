package bomberman.entities.animated_entity.character.enemy;

import bomberman.Board;
import bomberman.Graphics.Screen;
import bomberman.Graphics.Sprite;
import bomberman.entities.Entity;

public class Oneal extends Enemy {

	public Oneal(int x, int y, Board board, Sprite deadSprite, double speed, int point) {
		super(x, y, board, deadSprite, speed, point);
		
	}

	@Override
	public boolean collide(Entity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
		
	}

}
