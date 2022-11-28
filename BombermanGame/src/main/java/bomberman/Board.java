package bomberman;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bomberman.Graphics.IRenderable;
import bomberman.Graphics.Screen;
import bomberman.Input.KeyBoard;
import bomberman.Level.LevelLoader;
import bomberman.entities.Entity;
import bomberman.entities.Message;
import bomberman.entities.animated_entity.Bomber;
import bomberman.entities.animated_entity.Characters;
import bomberman.entities.animated_entity.bomb.Bomb;
import bomberman.entities.animated_entity.bomb.FlameSegement;

public class Board implements IRenderable {

	protected LevelLoader levelLoader;
	protected Games games;
	protected KeyBoard input;
	protected Screen screen;

	public Entity[] entities;
	public List<Characters> characters = new ArrayList<>();
	protected List<Bomb> bombs = new ArrayList<>();
	private final List<Message> messages = new ArrayList<>();

	private int screenToShow = -1;// 1:endgame, 2: changelevel, 3:paused

	private int time = Games.time;
	private int points = Games.points;
	private int lives = Games.lives;

	public Board(Games games, KeyBoard keyBoard, Screen screen) {
		this.games = games;
		this.input = keyBoard;
		this.screen = screen;
		loadLevel(1);
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

	public Entity getEntity(double x, double y, Characters e) {
		Entity res = null;

		res = getFlameSegementAt((int) x, (int) y);
		if (res != null)
			return res;

		res = getBombAt(x, y);
		if (res != null)
			return res;

		res = getChararcterAtExcluding((int) x, (int) y, e);
		if (res != null)
			return res;

		res = getEntityAt(x, y);
		return res;

	}

	// tra ve thuc the tai vi ti x, y
	public Characters getChararcterAtExcluding(int x, int y, Characters a) {
		Iterator<Characters> itr = this.characters.iterator();

		Characters character;
		while (itr.hasNext()) {
			character = itr.next();
			if (character == a) {
				continue;
			}
			if (character.getXTile() == x && character.getYTile() == y) {
				return character;
			}

		}
		return null;
	}

	public FlameSegement getFlameSegementAt(int x, int y) {
		Iterator<Bomb> bom = this.bombs.iterator();

		Bomb b;
		while (bom.hasNext()) {
			b = bom.next();

			FlameSegement flameSegement = b.flameAt(x, y);
			if (flameSegement != null)
				return flameSegement;
		}
		return null;

	}

	public Entity getEntityAt(double x, double y) {
		return this.entities[(int) x + (int) y * levelLoader.getWidth()];
	}

	public Bomb getBombAt(double x, double y) {
		Iterator<Bomb> bs = this.bombs.iterator();

		Bomb bomb;
		while (bs.hasNext()) {
			bomb = bs.next();
			if (bomb.getX() == (int) x && bomb.getY() == (int) y) {
				return bomb;
			}
		}
		return null;
	}
	
	public boolean detectEnemies() {// phat hien ra enemy
		int total = 0;
		for(int i = 0; i<characters.size();i++) {
			if(characters.get(i) instanceof Bomber == false) {
				total += 1;
			}
		}
		return total == 0;
	}
	
	public void nextLevel() {
		Games.setBombRadius(1);
		Games.setBombRate(1);
		Games.setBomberSpeed(1.0);
		loadLevel(this.levelLoader.getLevel()+1);
	}

	public List<Bomb> getBomb() {
		return bombs;
	}

	public KeyBoard getInput() {
		return input;
	}

	public LevelLoader getLevel() {
		return levelLoader;
	}

	public Games getGame() {
		return games;
	}

	public int getShow() {
		return screenToShow;
	}

	public void setShow(int i) {
		screenToShow = i;
	}

	public int getTime() {
		return time;
	}

	public int getPoints() {
		return points;
	}

	public int getLives() {
		return lives;
	}

	public void addPoints(int points) {
		this.points += points;
	}

	public void addLives(int lives) {
		this.lives += lives;
	}

	public int getWidth() {
		return levelLoader.getWidth();
	}

	public void update() {

		if (games.isPaused())
			return;

	}

	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub

	}

	public void updateEntities() {

	}

}
