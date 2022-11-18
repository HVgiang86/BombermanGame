package bomberman.Level;

import bomberman.Board;

/**
 * Load va  luu tru thong tin ban do cac man choi
 *
 */

public abstract class LevelLoader {

	
	protected int width, height, level;
	protected Board board;
	public LevelLoader(int level, Board board) {
		this.level = level;
		this.board = board;
	}
	
	
	public abstract void loadLevel(int level);
	
	
	public abstract void createEntities();
	
	public int getWidth() {
		return width;
		}
	
	public int getHeight() {
		return height;
	}
	
	public int getLevel() {
		return level;
	}
}
