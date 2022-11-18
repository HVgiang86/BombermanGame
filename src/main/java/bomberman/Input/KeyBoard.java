package bomberman.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener{

	private final boolean[] keys = new boolean[1000];
	public boolean up,down,left,right,enter;
	
	//Thiet lap nhung phim dung de di chuyen bomber va dat bom
	public void update() {
		up = keys[KeyEvent.VK_UP]|| keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN]|| keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT]|| keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT]|| keys[KeyEvent.VK_D];
		enter = keys[KeyEvent.VK_ENTER]|| keys[KeyEvent.VK_X];
		
	}
	
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;	
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
		
	}

}