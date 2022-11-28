package bomberman;

import java.util.ArrayList;
import java.util.List;

import bomberman.Graphics.IRenderable;
import bomberman.Graphics.Screen;
import bomberman.Input.KeyBoard;
import bomberman.Level.LevelLoader;
import bomberman.entities.Entity;
import bomberman.entities.Message;
import bomberman.entities.animated_entity.Bomb;

public class Board implements IRenderable {

	protected LevelLoader levelLoader;
	protected Games games;
	protected KeyBoard input;
	protected Screen screen;

	public Entity[] entities;
	public List<Character> characters = new ArrayList<>();
	protected List<Bomb> bombs = new ArrayList<>();
	private final List<Message> messages = new ArrayList<>();

	private int screenToShow = -1;// 1:endgame, 2: changelevel, 3:paused

	private int time = Games.time;
	private int points = Games.points;
	private int lives = Games.lives;

	private Board(Games games, KeyBoard keyBoard, Screen screen) {
		this.games = games;
		this.input = keyBoard;
		this.screen = screen;

	
	}
	
	public void loadLevel(int level) {
		time = Games.time;
		screenToShow = 2;
		games.resetScreenDelay();
		games.pause();
		characters.clear();
		bombs.clear();
		messages.clear();
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public Entity getEntity(double x, double y, Character e) {
		Entity res = null;
		
		
		
		return res;
		
	}

	public void update() {

		if (games.isPaused())
			return;
		
		

	}

	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
		
	}

}
