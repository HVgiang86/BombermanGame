package BombermanGame.Models.Entities.Characters.Enemies;

import BombermanGame.Models.Entities.Characters.AI.AIMedium;
import BombermanGame.Views.GUI.GameBoard;
import BombermanGame.Views.Sprites.Sprite;

public class Oneal extends Enemy {
    public Oneal(int x, int y, GameBoard gameBoard) {
        super(x, y, gameBoard, Sprite.oneal_dead, 0.5, 400);
        sprite = Sprite.oneal_left1;
        ai = new AIMedium(gameBoard, this, false);
        direct = ai.calculateDirection();
    }

    @Override
    protected void chooseSprite() {
        switch (direct) {
            case 0, 1:
                if (moving)
                    sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 60);
                else sprite = Sprite.oneal_left1;
                break;
            case 2, 3:
                if (moving)
                    sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 60);
                else sprite = Sprite.oneal_left1;
                break;
        }
    }
}