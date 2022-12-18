package BombermanGame.Utils.Level;

import BombermanGame.Views.GUI.GameBoard;
import BombermanGame.Exceptions.LoadLevelException;

public abstract class LevelLoader {
    protected int width, height, level;
    protected GameBoard gameBoard;

    public LevelLoader(GameBoard gameBoard, int level) throws LoadLevelException {
        loadLevel(level);
        this.gameBoard = gameBoard;
    }

    public abstract void loadLevel(int level) throws LoadLevelException;

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
