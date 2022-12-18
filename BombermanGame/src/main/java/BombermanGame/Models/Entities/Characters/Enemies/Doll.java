package BombermanGame.Models.Entities.Characters.Enemies;

import BombermanGame.Models.Entities.Characters.AI.AILow;
import BombermanGame.Views.GUI.GameBoard;
import BombermanGame.Views.Sprites.Sprite;

public class Doll extends Enemy {
    public Doll(int x, int y, GameBoard gameBoard) {
        super(x, y, gameBoard, Sprite.doll_dead, 1.0, 200);
        sprite = Sprite.doll_right1;
        ai = new AILow();
        direct = ai.calculateDirection();
    }

    @Override
    protected void chooseSprite() {
        switch (direct) {
            case 0, 1:
                if (moving)
                    sprite = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animate, 60);
                else sprite = Sprite.doll_left1;
                break;
            case 2, 3:
                if (moving)
                    sprite = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animate, 60);
                else sprite = Sprite.doll_left1;
                break;
        }
    }
}