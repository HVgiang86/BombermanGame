package bomberman;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bomberman.Exceptions.LoadLevelException;
import bomberman.Graphics.IRenderable;
import bomberman.Graphics.Screen;
import bomberman.Input.KeyBoard;
import bomberman.Level.FileLevelLoader;
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

	public Board(Games games, KeyBoard keyBoard, Screen screen) throws LoadLevelException {
		this.games = games;
		this.input = keyBoard;
		this.screen = screen;
		loadLevel(1);
	}

	public void loadLevel(int level) throws LoadLevelException {
		time = Games.time;
		screenToShow = 2;
		games.resetScreenDelay();
		games.pause();
		characters.clear();
		bombs.clear();
		messages.clear();

		resetPopertiesButKeepScore();
		Screen.setOffset(0, 0);
		try {

			Games.playSE(7);
			levelLoader = new FileLevelLoader(this, level);
			entities = new Entity[this.levelLoader.getHeight() * this.levelLoader.getWidth()];
			levelLoader.createEntities();
		} catch (NullPointerException e) {
			finishGame();
		}

	}

	// reset lai dac tinh nhung giu nguyen diem
	public void resetPopertiesButKeepScore() {
		Games.Bomber_Speed = 1.0;
		Games.Bomb_Radius = 1;
		Games.Bomb_Rate = 1;
	}

	public void finishGame() {
		screenToShow = 4;
		games.resetScreenDelay();
		Games.playSE(8);
		games.pause();
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
		for (int i = 0; i < characters.size(); i++) {
			if (characters.get(i) instanceof Bomber == false) {
				total += 1;
			}
		}
		return total == 0;
	}

	public void nextLevel() {
		Games.setBombRadius(1);
		Games.setBombRate(1);
		Games.setBomberSpeed(1.0);
		try {
			loadLevel(this.levelLoader.getLevel() + 1);
		} catch (LoadLevelException e) {
			e.printStackTrace();
		}
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

	public void newGame() throws LoadLevelException {
		loadLevel(1);
		resetProperties();
		lives = Games.lives;
	}

	public void restart() throws LoadLevelException {
		if (lives > 0) {
			loadLevel(levelLoader.getLevel());
			resetProperties();
		}
	}

	public void resetProperties() {
		// dat lai thuoc tinh cho game
		points = Games.points;
		Games.Bomber_Speed = 1.0;
		Games.Bomb_Radius = 1;
		Games.Bomb_Rate = 1;
	}

	public void endGame() {
		screenToShow = 1;
		games.resetScreenDelay();
		Games.playSE(9);
		games.pause();
	}

	@Override
	public void render(Screen screen) {
		if (this.games.isPaused())
			return;

		// chi hien thi phan man hinh

		// bên trái x
		int x0 = Screen.xOffset >> 4;
		// bên phải của x
		int x1 = (Screen.xOffset + screen.getWidth() + Games.Tiles_size) / Games.Tiles_size;
		int y0 = screen.yOffset >> 4;
		int y1 = (Screen.yOffset + screen.getHeight()) / Games.Tiles_size;

		for (int y = 0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				this.entities[x + y * this.levelLoader.getWidth()].render(screen);
			}
		}
		renderBombs(screen);
		renderCharacter(screen);
	}
	
	protected void renderCharacter(Screen screen) {
		Iterator<Characters> c= this.characters.iterator();
		
		while(c.hasNext())
			c.next().render(screen);
				
	}

	protected void renderBombs(Screen screen) {
		Iterator<Bomb> b = this.bombs.iterator();

		while (b.hasNext())
			b.next().render(screen);
	}

	protected void renderMessages(Graphics g) {
		Message message;
		for (int i = 0; i < this.messages.size(); i++) {
			message = this.messages.get(i);

			g.setFont(new Font("Arial", Font.PLAIN, message.getSize()));
			g.setColor(message.getColor());
			g.drawString(message.getMessage(), (int) message.getX() - Screen.xOffset * Games.scale,
					(int) message.getY());
		}
	}

	public void update() {

		if (games.isPaused())
			return;

		updateEntities();
		updateCharacter();
		updateBombs();
		updateMessages();
		detectEndGame();

		for (int i = 0; i < this.characters.size(); i++) {
			Characters a = this.characters.get(i);
			if (a.isRemoved())
				this.characters.remove(i);
		}
	}

	public void updateEntities() {
		if (games.isPaused())
			return;
		for (int i = 0; i < entities.length; i++) {
			entities[i].update();
		}
	}

	public void updateCharacter() {
		if (games.isPaused())
			return;
		Iterator<Characters> c = characters.iterator();

		while (c.hasNext() && !games.isPaused())
			c.next().update();
	}

	public void updateBombs() {
		if (games.isPaused())
			return;
		for (Bomb bomb : bombs)
			bomb.update();

	}

	public void updateMessages() {
		if (games.isPaused())
			return;
		Message message;
		int left;
		for (int i = 0; i < messages.size(); i++) {
			message = messages.get(i);
			left = message.getDuration();
			if (left > 0)
				message.setDuration(--left);
			else {
				this.messages.remove(i);
			}
		}

	}

	public void addMessage(Message m) {
		messages.add(m);
	}

	protected void detectEndGame() {
		if (this.time <= 0)
			endGame();
	}
}