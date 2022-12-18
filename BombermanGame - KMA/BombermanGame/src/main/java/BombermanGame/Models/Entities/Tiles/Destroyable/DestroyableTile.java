package BombermanGame.Models.Entities.Tiles.Destroyable;

import BombermanGame.Models.Entities.Bomb.Flame;
import BombermanGame.Models.Entities.Entity;
import BombermanGame.Models.Entities.Tiles.Tile;
import BombermanGame.Views.Game;
import BombermanGame.Views.Sprites.Sprite;

public class DestroyableTile extends Tile {
    private final int MAX_ANIMATE = 7500;
    protected boolean destroyed = false;
    protected int timeToDisappear = 20;
    protected Sprite belowSprite = Sprite.grass;
    private int animate = 0;

    public DestroyableTile(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {
        if (destroyed) {
            if (animate < MAX_ANIMATE) animate++;
            else animate = 0;
            if (timeToDisappear > 0) timeToDisappear--;
            else remove();
        }
    }

    public void destroy() {
        destroyed = true;
        Game.playSE(3);
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Flame) destroy();
        return false;
    }

    public void addBelowSprite(Sprite sprite) {
        belowSprite = sprite;
    }

    protected Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2) {
        if (animate % 30 < 10) return normal;
        if (animate % 30 < 20) return x1;
        return x2;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}