package BombermanGame.Models.Entities.Bomb;

import BombermanGame.Models.Entities.AnimatedEntity;
import BombermanGame.Models.Entities.Characters.Bomber;
import BombermanGame.Models.Entities.Characters.Character;
import BombermanGame.Models.Entities.Entity;
import BombermanGame.Utils.CoordinateConverter;
import BombermanGame.Views.GUI.GameBoard;
import BombermanGame.Views.Game;
import BombermanGame.controller.GameRenderController;
import BombermanGame.Views.Sprites.Sprite;

public class FlameSegment extends AnimatedEntity {
    protected boolean last;
    protected int direction;
    protected GameBoard gameBoard;

    public FlameSegment(int x, int y, int direction, boolean last, GameBoard gameBoard) {
        this.x = x;
        this.y = y;
        this.last = last;
        this.direction = direction;
        this.gameBoard = gameBoard;
        switch (direction) {
            case 0:
                if (!this.last)
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, animate, 60);
                else
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, animate, 60);
                break;
            case 1:
                if (!this.last)
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, animate, 60);
                else
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, animate, 60);
                break;
            case 2:
                if (!this.last)
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, animate, 60);
                else
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, animate, 60);
                break;
            case 3:
                if (!this.last)
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, animate, 60);
                else
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, animate, 60);
                break;
        }
    }

    @Override
    public void render(GameRenderController gameRenderController) {
        switch (direction) {
            case 0:
                if (!last)
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, animate, 20);
                else
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, animate, 20);
                break;
            case 1:
                if (!last)
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, animate, 20);
                else
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, animate, 20);
                break;
            case 2:
                if (!last)
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, animate, 20);
                else
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, animate, 20);
                break;
            case 3:
                if (!last)
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, animate, 20);
                else
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, animate, 20);
                break;

        }
        gameRenderController.renderEntity((int) x << 4, (int) y << 4, this);
    }

    @Override
    public void update() {
        animate();
    }

    @Override
    public boolean collide(Entity e) {
        Bomber b = gameBoard.getBomber();

        int leftX = CoordinateConverter.pixelToTile(b.getX() + (double) Game.TILE_SIZE / 6);
        int rightX = CoordinateConverter.pixelToTile(b.getX() + (double) Game.TILE_SIZE * 4 / 6);
        int bottomY = CoordinateConverter.pixelToTile(b.getY() - (double) Game.TILE_SIZE / 6);
        int topY = CoordinateConverter.pixelToTile(b.getY() - (double) Game.TILE_SIZE * 4 / 6);

        if ((leftX == x && b.getYTile() == y) || (rightX == x && b.getYTile() == y)) b.kill();
        if ((b.getXTile() == x && bottomY == y) || (b.getXTile() == x && topY == y)) b.kill();

        if (e instanceof Character) ((Character) e).kill();
        if (e instanceof Bomb) e.collide(this);

        return false;
    }
}