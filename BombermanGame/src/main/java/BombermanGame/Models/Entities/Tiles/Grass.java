package BombermanGame.Models.Entities.Tiles;

import BombermanGame.Models.Entities.Entity;
import BombermanGame.Views.Sprites.Sprite;

public class Grass extends Tile {
    public Grass(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public boolean collide(Entity e) {
        return true;
    }
}