package BombermanGame.Models.Entities.Tiles.Items;

import BombermanGame.Models.Entities.Characters.Bomber;
import BombermanGame.Models.Entities.Entity;
import BombermanGame.Views.Game;
import BombermanGame.Views.Sprites.Sprite;

public class SpeedItem extends Item {
    public SpeedItem(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Bomber)
            if (getX() == e.getXTile() && getY() == e.getYTile()) {
                Game.playSE(5);
                remove();
                Game.addBomberSpeed(1);
            }
        return false;
    }
}