package BombermanGame.Models.Entities.Characters.Enemies;

import BombermanGame.Models.Entities.Bomb.Bomb;
import BombermanGame.Models.Entities.Bomb.FlameSegment;
import BombermanGame.Models.Entities.Characters.AI.AI;
import BombermanGame.Models.Entities.Characters.Bomber;
import BombermanGame.Models.Entities.Characters.Character;
import BombermanGame.Models.Entities.Entity;
import BombermanGame.Models.Entities.LayeredEntity;
import BombermanGame.Models.Entities.Message;
import BombermanGame.Models.Entities.Tiles.Destroyable.Brick;
import BombermanGame.Models.Entities.Tiles.Wall;
import BombermanGame.Utils.CoordinateConverter;
import BombermanGame.Views.GUI.GameBoard;
import BombermanGame.Views.Game;
import BombermanGame.controller.GameRenderController;
import BombermanGame.Views.Sprites.Sprite;

import java.awt.*;

public abstract class Enemy extends Character {
    protected final double MAX_STEPS;
    protected final double rest;
    protected double steps;

    protected int points;
    protected double speed;
    protected AI ai;

    protected int finalAnimation = 30;
    protected Sprite deadSprite;
    private int currentDirect = 0;

    public Enemy(int x, int y, GameBoard gameBoard, Sprite dead, double speed, int points) {
        super(x, y, gameBoard);

        this.points = points;
        this.speed = speed;

        MAX_STEPS = Game.TILE_SIZE / this.speed;
        rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
        steps = MAX_STEPS;

        timeAfter = 20;
        deadSprite = dead;
    }

    @Override
    public void update() {
        animate();
        if (!alive) {
            afterKill();
            return;
        }
        calculateMove();
    }

    @Override
    public void render(GameRenderController gameRenderController) {
        if (alive) chooseSprite();
        else {
            if (timeAfter > 0) {
                sprite = deadSprite;
                animate = 0;
            } else sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate, 60);
        }
        gameRenderController.renderEntity((int) this.x, (int) this.y - sprite.SIZE, this);
    }

    @Override
    public void calculateMove() {
        moving = true;
        if (steps != MAX_STEPS) {
            steps--;
            if (steps == 0) steps = MAX_STEPS;
        } else {
            steps--;
            currentDirect = ai.calculateDirection();
        }
        if (currentDirect == 0) move(x, y + speed);
        if (currentDirect == 1) move(x + speed, y);
        if (currentDirect == 2) move(x - speed, y);
        if (currentDirect == 3) move(x, y - speed);
    }

    @Override
    public void move(double xa, double ya) {
        if (!alive) return;
        if (!canMove(xa, ya)) {
            soften(xa, ya);
            moving = true;
            return;
        }
        if (y < ya) direct = 2;
        if (y > ya) direct = 0;
        if (x > xa) direct = 3;
        if (x < xa) direct = 1;
        y = ya;
        x = xa;
    }

    private void soften(double xa, double ya) {
        if (xa != x && y == ya) {
            double near1 = (ya / Game.TILE_SIZE) * Game.TILE_SIZE;
            double near2 = (ya / Game.TILE_SIZE + 1) * Game.TILE_SIZE;
            if (ya - near1 <= 8) {
                if (canMove(xa, near1)) {
                    y--;
                    soften(xa, ya--);
                    if (xa > x) direct = 4;
                    else direct = 3;
                }
            }
            if (near2 - ya <= 8) {
                if (canMove(xa, near2)) {
                    y++;
                    move(xa, ya++);
                    if (xa > x) direct = 4;
                    else direct = 3;
                }
            }
        } else if (xa == x && y != ya) {
            double near1 = (xa / Game.TILE_SIZE) * Game.TILE_SIZE;
            double near2 = (xa / Game.TILE_SIZE + 1) * Game.TILE_SIZE;
            if (xa - near1 <= 8) {
                if (canMove(near1, ya)) {
                    x--;
                    soften(xa--, ya);
                }
            }
            if (near2 - xa <= 8) {
                if (canMove(near2, ya)) {
                    x++;
                    soften(xa++, ya);
                    direct = 1;
                }
            }
        }
    }

    @Override
    public boolean canMove(double x, double y) {
        double loLy = y - 1;
        double loRy = y - 1;
        double upLy = y - Game.TILE_SIZE;
        double upRy = y - Game.TILE_SIZE;
        double upRx = x - 1 + Game.TILE_SIZE;
        double loRx = x - 1 + Game.TILE_SIZE;
        int tile_UpLx = CoordinateConverter.pixelToTile(x);
        int tile_UpLy = CoordinateConverter.pixelToTile(upLy);
        int tile_UpRx = CoordinateConverter.pixelToTile(upRx);
        int tile_UpRy = CoordinateConverter.pixelToTile(upRy);
        int tile_LoLx = CoordinateConverter.pixelToTile(x);
        int tile_LoLy = CoordinateConverter.pixelToTile(loLy);
        int tile_LoRx = CoordinateConverter.pixelToTile(loRx);
        int tile_LoRy = CoordinateConverter.pixelToTile(loRy);
        Entity entity_UpLeft = gameBoard.getEntity(tile_UpLx, tile_UpLy, this);
        Entity entity_UpRight = gameBoard.getEntity(tile_UpRx, tile_UpRy, this);
        Entity entity_LoLeft = gameBoard.getEntity(tile_LoLx, tile_LoLy, this);
        Entity entity_LoRight = gameBoard.getEntity(tile_LoRx, tile_LoRy, this);
        if (entity_LoLeft instanceof Wall || entity_LoRight instanceof Wall || entity_UpLeft instanceof Wall || entity_UpRight instanceof Wall)
            return false;
        else if (entity_LoLeft instanceof LayeredEntity || entity_LoRight instanceof LayeredEntity || entity_UpLeft instanceof LayeredEntity || entity_UpRight instanceof LayeredEntity) {
            if (entity_LoLeft instanceof LayeredEntity) {
                Entity top = ((LayeredEntity) entity_LoLeft).getTopEntity();
                if (top instanceof Brick) return false;
            }
            if (entity_LoRight instanceof LayeredEntity) {
                Entity top = ((LayeredEntity) entity_LoRight).getTopEntity();
                if (top instanceof Brick) return false;
            }
            if (entity_UpLeft instanceof LayeredEntity) {
                Entity top = ((LayeredEntity) entity_UpLeft).getTopEntity();
                if (top instanceof Brick) return false;
            }
            if (entity_UpRight instanceof LayeredEntity) {
                Entity top = ((LayeredEntity) entity_UpRight).getTopEntity();
                if (top instanceof Brick) return false;
            }
        }
        return !collide(entity_LoLeft) && !collide(entity_LoRight) && !collide(entity_UpLeft) && !collide(entity_UpRight);
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Bomb) return true;
        if (e instanceof FlameSegment) e.collide(this);
        if (e instanceof Bomber) {
            int leftX = CoordinateConverter.pixelToTile(e.getX() + (double) Game.TILE_SIZE / 6);
            int rightX = CoordinateConverter.pixelToTile(e.getX() + (double) Game.TILE_SIZE * 4 / 6);
            int bottomY = CoordinateConverter.pixelToTile(e.getY() - (double) Game.TILE_SIZE / 6);
            int topY = CoordinateConverter.pixelToTile(e.getY() - (double) Game.TILE_SIZE * 4 / 6);
            if ((leftX == getXTile() && e.getYTile() == getYTile()) || (rightX == getXTile() && e.getYTile() == getYTile()))
                ((Bomber) e).kill();
            if ((e.getXTile() == getXTile() && bottomY == getYTile()) || (e.getXTile() == getXTile() && topY == getYTile()))
                ((Bomber) e).kill();
            if (e.getXTile() == getXTile() && e.getYTile() == getYTile()) ((Bomber) e).kill();
        }
        return false;
    }

    @Override
    public void kill() {
        if (!alive) return;

        alive = false;
        Game.playSE(6);
        gameBoard.addPoints(points);

        Message msg = new Message("+" + points, getXMessage(), getYMessage(), 2, Color.white, 14);
        gameBoard.addMessage(msg);
    }


    @Override
    protected void afterKill() {
        if (timeAfter > 0) timeAfter--;
        else {
            if (finalAnimation > 0) finalAnimation--;
            else remove();
        }
    }

    protected abstract void chooseSprite();

    public void setSpeed(double s) {
        speed = s;
    }
}