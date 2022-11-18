package bomberman;

import java.util.ArrayList;
import java.util.List;

import bomberman.Graphics.IRenderable;
import bomberman.Graphics.Screen;
import bomberman.Input.KeyBoard;
import bomberman.Level.LevelLoader;
import bomberman.entities.Entity;
import bomberman.entities.Message;
import bomberman.entities.animated_entity.character.Bomb;

public class Board implements IRenderable{

	
	protected LevelLoader levelLoader;
	protected Games games;
	protected KeyBoard input;
	protected Screen screen;
	
	public Entity[] entities;
	public List<Character> characters = new ArrayList<>();
	protected List<Bomb> bombs = new ArrayList<>();
	private final List<Message> messages = new ArrayList<>();
	
	private int screenToShow = -1;
	
	private int time = Games.time;
	private int points = Games.points;
	
	
	
	public void update() {	
	
		if(games.isPaused()) return;
		
		
	}

	public void render() {
	}

	
}
