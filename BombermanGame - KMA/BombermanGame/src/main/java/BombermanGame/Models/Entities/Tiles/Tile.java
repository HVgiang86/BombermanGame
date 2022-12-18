package BombermanGame.Models.Entities.Tiles;

import BombermanGame.Models.Entities.Entity;
import BombermanGame.controller.GameRenderController;
import BombermanGame.Views.Sprites.Sprite;
import BombermanGame.Utils.CoordinateConverter;

public abstract class Tile extends Entity {
    public Tile(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    @Override
    public void render(GameRenderController gameRenderController) {
        gameRenderController.renderEntity(CoordinateConverter.tileToPixel(x), CoordinateConverter.tileToPixel(y), this);
    }

    @Override
    public void update() {}
}