package BombermanGame.Models.Entities;

import BombermanGame.Models.Renderable;
import BombermanGame.controller.GameRenderController;
import BombermanGame.Views.Sprites.Sprite;
import BombermanGame.Utils.CoordinateConverter;

public abstract class Entity implements Renderable {
    protected double x, y;
    protected boolean removed = false;
    protected Sprite sprite;

    @Override
    public abstract void update();

    @Override
    public abstract void render(GameRenderController gameRenderController);

    public void remove() {
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public abstract boolean collide(Entity e);

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getXTile() {
        return CoordinateConverter.pixelToTile(x + (double) sprite.SIZE / 2);
    }

    public int getYTile() {
        return CoordinateConverter.pixelToTile(y - (double) sprite.SIZE / 2);
    }
}