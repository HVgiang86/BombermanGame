package BombermanGame.Models.Entities.Tiles;

import BombermanGame.Models.Entities.Characters.Bomber;
import BombermanGame.Models.Entities.Entity;
import BombermanGame.Views.Game;
import BombermanGame.Views.Sprites.Sprite;

public class Portal extends Tile {
    public Portal(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Bomber)
            if (getX() == e.getXTile() && getY() == e.getYTile()) {
                Game.playSE(7);
                return true;
            }
        return false;
    }
}
