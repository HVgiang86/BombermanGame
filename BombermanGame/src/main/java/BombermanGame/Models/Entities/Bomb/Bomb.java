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

public class Bomb extends AnimatedEntity {
    public int timeAfter = 20;
    protected double timeToExplode = 120;
    protected GameBoard gameBoard;
    protected Flame[] flames;
    protected boolean exploded = false;

    public Bomb(int x, int y, GameBoard gameBoard) {
        this.x = x;
        this.y = y;
        this.gameBoard = gameBoard;
        sprite = Sprite.bomb;
    }

    @Override
    public void update() {
        if (timeToExplode > 0) timeToExplode--;
        else {
            if (!exploded) explode();
            else updateFlames();
            if (timeAfter > 0) timeAfter--;
            else remove();
        }
        animate();
    }

    @Override
    public void render(GameRenderController gameRenderController) {
        if (exploded) {
            sprite = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, animate, 20);
            renderFlames(gameRenderController);
        } else sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 40);
        gameRenderController.renderEntity((int) x << 4, (int) y << 4, this);
    }

    public void renderFlames(GameRenderController gameRenderController) {
        for (Flame flame : flames) flame.render(gameRenderController);
    }

    public void updateFlames() {
        for (Flame flame : flames) flame.update();
    }

    protected void explode() {
        Game.playSE(2);
        timeToExplode = 0;
        exploded = true;

        flames = new Flame[4];
        flames[0] = new Flame((int) x, (int) y - 1, 0, Game.getBombRadius(), gameBoard);
        flames[1] = new Flame((int) x + 1, (int) y, 1, Game.getBombRadius(), gameBoard);
        flames[2] = new Flame((int) x, (int) y + 1, 2, Game.getBombRadius(), gameBoard);
        flames[3] = new Flame((int) x - 1, (int) y, 3, Game.getBombRadius(), gameBoard);

        Character ch = gameBoard.getCharacterAtExcluding((int) x, (int) y, null);
        if (ch != null) ch.kill();
    }

    public FlameSegment flameAt(int x, int y) {
        if (!exploded) return null;
        for (Flame flame : flames) {
            if (flame == null) return null;
            FlameSegment e = flame.flameSegmentAt(x, y);
            if (e != null) return e;
        }
        return null;
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof FlameSegment && !exploded) explode();

        if (e instanceof Bomber) {
            double loLy = e.getY() - 1;
            double loRy = e.getY() - 1;
            double upLy = e.getY() + 1 - Game.TILE_SIZE;
            double upRy = e.getY() + 1 - Game.TILE_SIZE;
            double upLx = e.getX() + 1;
            double loLx = e.getX() + 1;
            double upRx = e.getX() - 1 + (double) Game.TILE_SIZE * 3 / 4;
            double loRx = e.getX() - 1 + (double) Game.TILE_SIZE * 3 / 4;
            int tile_UpLx = CoordinateConverter.pixelToTile(upLx);
            int tile_UpLy = CoordinateConverter.pixelToTile(upLy);
            int tile_UpRx = CoordinateConverter.pixelToTile(upRx);
            int tile_UpRy = CoordinateConverter.pixelToTile(upRy);
            int tile_LoLx = CoordinateConverter.pixelToTile(loLx);
            int tile_LoLy = CoordinateConverter.pixelToTile(loLy);
            int tile_LoRx = CoordinateConverter.pixelToTile(loRx);
            int tile_LoRy = CoordinateConverter.pixelToTile(loRy);
            return (tile_LoLx != x || tile_LoLy != y) && (tile_LoRx != x || tile_LoRy != y) && (tile_UpLx != x || tile_UpLy != y) && (tile_UpRx != x || tile_UpRy != y);
        }
        return true;
    }
}