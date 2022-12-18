package BombermanGame.Models.Entities.Characters.Enemies;

import BombermanGame.Models.Entities.Characters.AI.AILow;
import BombermanGame.Views.GUI.GameBoard;
import BombermanGame.Views.Sprites.Sprite;

public class Balloon extends Enemy {
    public Balloon(int x, int y, GameBoard gameBoard) {
        super(x, y, gameBoard, Sprite.balloom_dead, 0.5, 100);
        sprite = Sprite.balloom_left1;
        ai = new AILow();
        direct = ai.calculateDirection();
    }

    @Override
    protected void chooseSprite() {
        switch (direct) {
            case 0, 1 ->
                    sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 60);
            case 2, 3 ->
                    sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 60);
        }
    }
}