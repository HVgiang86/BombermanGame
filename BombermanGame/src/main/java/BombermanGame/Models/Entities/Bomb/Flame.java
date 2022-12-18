package BombermanGame.Models.Entities.Bomb;

import BombermanGame.Models.Entities.Characters.Character;
import BombermanGame.Models.Entities.Entity;
import BombermanGame.Models.Entities.LayeredEntity;
import BombermanGame.Models.Entities.Tiles.Destroyable.DestroyableTile;
import BombermanGame.Models.Entities.Tiles.Wall;
import BombermanGame.Views.GUI.GameBoard;
import BombermanGame.controller.GameRenderController;

public class Flame extends Entity {
    private final int radius;
    protected GameBoard gameBoard;
    protected int direction;
    protected int xOrigin, yOrigin;
    protected FlameSegment[] flameSegments = new FlameSegment[0];

    public Flame(int x, int y, int direction, int radius, GameBoard gameBoard) {
        xOrigin = x;
        yOrigin = y;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.radius = radius;
        this.gameBoard = gameBoard;
        createFlameSegments();
    }

    private void createFlameSegments() {
        boolean last = false;
        int length = calculatePermitedDistance();
        flameSegments = new FlameSegment[length];
        for (int i = 0; i < length; i++) {
            if (i == calculatePermitedDistance() - 1) last = true;
            if (direction == 0) {
                flameSegments[i] = new FlameSegment(xOrigin, yOrigin - i, direction, last, gameBoard);
                flameSegments[i].collide(gameBoard.getCharacterAtExcluding(xOrigin, yOrigin - i, null));
                flameSegments[i].collide(gameBoard.getBombAt(xOrigin, yOrigin - i));
            } else if (direction == 1) {
                flameSegments[i] = new FlameSegment(xOrigin + i, yOrigin, direction, last, gameBoard);
                flameSegments[i].collide(gameBoard.getCharacterAtExcluding(xOrigin + i, yOrigin, null));
                flameSegments[i].collide(gameBoard.getBombAt(xOrigin + i, yOrigin));
            } else if (direction == 2) {
                flameSegments[i] = new FlameSegment(xOrigin, yOrigin + i, direction, last, gameBoard);
                flameSegments[i].collide(gameBoard.getCharacterAtExcluding(xOrigin, yOrigin + i, null));
                flameSegments[i].collide(gameBoard.getBombAt(xOrigin, yOrigin + i));
            } else {
                flameSegments[i] = new FlameSegment(xOrigin - i, yOrigin, direction, last, gameBoard);
                flameSegments[i].collide(gameBoard.getCharacterAtExcluding(xOrigin - i, yOrigin, null));
                flameSegments[i].collide(gameBoard.getBombAt(xOrigin - i, yOrigin));
            }
        }
    }

    private int calculatePermitedDistance() {
        if (direction == 0) {
            for (int i = 0; i < radius; i++) {
                Entity barrier = gameBoard.getEntityAt(xOrigin, yOrigin - i);
                if (barrier instanceof Wall) return i;
                if (barrier instanceof LayeredEntity) {
                    Entity top = ((LayeredEntity) barrier).getTopEntity();
                    if (top instanceof DestroyableTile) {
                        if (!((DestroyableTile) top).isDestroyed()) {
                            ((DestroyableTile) top).destroy();
                            return i;
                        }
                    }
                }
            }
        }
        if (direction == 1) {
            for (int i = 0; i < radius; i++) {
                Entity barrier = gameBoard.getEntityAt(xOrigin + i, yOrigin);
                if (barrier instanceof Wall) return i;
                if (barrier instanceof LayeredEntity) {
                    Entity top = ((LayeredEntity) barrier).getTopEntity();
                    if (top instanceof DestroyableTile) {
                        if (!((DestroyableTile) top).isDestroyed()) {
                            ((DestroyableTile) top).destroy();
                            return i;
                        }
                    }
                }
            }
        }
        if (direction == 2) {
            for (int i = 0; i < radius; i++) {
                Entity barrier = gameBoard.getEntityAt(xOrigin, yOrigin + i);
                if (barrier instanceof Wall) return i;
                if (barrier instanceof LayeredEntity) {
                    Entity top = ((LayeredEntity) barrier).getTopEntity();
                    if (top instanceof DestroyableTile) {
                        if (!((DestroyableTile) top).isDestroyed()) {
                            ((DestroyableTile) top).destroy();
                            return i;
                        }
                    }
                }
            }
        }
        if (direction == 3) {
            for (int i = 0; i < radius; i++) {
                Entity barrier = gameBoard.getEntityAt(xOrigin - i, yOrigin);
                if (barrier instanceof Wall) return i;
                if (barrier instanceof LayeredEntity) {
                    Entity top = ((LayeredEntity) barrier).getTopEntity();
                    if (top instanceof DestroyableTile) {
                        if (!((DestroyableTile) top).isDestroyed()) {
                            ((DestroyableTile) top).destroy();
                            return i;
                        }
                    }
                }
            }
        }
        return radius;
    }

    public FlameSegment flameSegmentAt(int x, int y) {
        for (FlameSegment flameSegment : flameSegments)
            if (flameSegment.getX() == x && flameSegment.getY() == y) return flameSegment;
        return null;
    }

    @Override
    public void update() {
        for (FlameSegment flameSegment : flameSegments) flameSegment.update();
    }

    @Override
    public void render(GameRenderController gameRenderController) {
        for (FlameSegment flameSegment : flameSegments) flameSegment.render(gameRenderController);
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Character) ((Character) e).kill();
        return false;
    }
}