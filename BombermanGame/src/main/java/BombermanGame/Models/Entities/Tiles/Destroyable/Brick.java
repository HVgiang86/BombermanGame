package BombermanGame.Models.Entities.Tiles.Destroyable;

import BombermanGame.Utils.CoordinateConverter;
import BombermanGame.controller.GameRenderController;
import BombermanGame.Views.Sprites.Sprite;

public class Brick extends DestroyableTile {
    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(GameRenderController gameRenderController) {
        if (destroyed) {
            sprite = movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2);
            gameRenderController.renderEntityWithBelowSprite(CoordinateConverter.tileToPixel(x), CoordinateConverter.tileToPixel(y), this, belowSprite);
        } else gameRenderController.renderEntity(CoordinateConverter.tileToPixel(x), CoordinateConverter.tileToPixel(y), this);
    }
}