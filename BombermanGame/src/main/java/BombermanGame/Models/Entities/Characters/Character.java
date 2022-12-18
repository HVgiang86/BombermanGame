package BombermanGame.Models.Entities.Characters;

import BombermanGame.Models.Entities.AnimatedEntity;
import BombermanGame.Views.GUI.GameBoard;
import BombermanGame.Views.Game;
import BombermanGame.controller.GameRenderController;

public abstract class Character extends AnimatedEntity {
    public int timeAfter = 70;
    protected GameBoard gameBoard;
    protected int direct = -1;
    protected boolean alive = true;
    protected boolean moving = false;

    public Character(int x, int y, GameBoard gameBoard) {
        this.x = x;
        this.y = y;
        this.gameBoard = gameBoard;
    }

    @Override
    public abstract void update();

    @Override
    public abstract void render(GameRenderController gameRenderController);

    protected abstract void calculateMove();

    protected abstract void move(double xa, double ya);

    public abstract void kill();

    protected abstract void afterKill();

    protected abstract boolean canMove(double x, double y);

    protected double getXMessage() {
        return (x * Game.SCALE_MULTIPLE) + ((double) sprite.SIZE / 2 * Game.SCALE_MULTIPLE);
    }

    protected double getYMessage() {
        return (y * Game.SCALE_MULTIPLE) - ((double) sprite.SIZE / 2 * Game.SCALE_MULTIPLE);
    }
}