package BombermanGame.Models.Entities.Tiles.Items;

import BombermanGame.Models.Entities.Tiles.Tile;
import BombermanGame.Views.Sprites.Sprite;

public abstract class Item extends Tile {
    public Item(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }
}