package BombermanGame.Models.Entities.Characters.Enemies;

import BombermanGame.Models.Entities.Characters.AI.AIMedium;
import BombermanGame.Views.GUI.GameBoard;
import BombermanGame.Views.Sprites.Sprite;

public class Minvo extends Enemy {
    public Minvo(int x, int y, GameBoard gameBoard) {
        super(x, y, gameBoard, Sprite.minvo_dead, 1.0, 800);
        sprite = Sprite.minvo_right1;
        ai = new AIMedium(gameBoard, this, true);
        direct = ai.calculateDirection();
    }

    @Override
    protected void chooseSprite() {
        switch (direct) {
            case 0, 1:
                if (moving)
                    sprite = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate, 60);
                else sprite = Sprite.minvo_left1;
                break;
            case 2, 3:
                if (moving)
                    sprite = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate, 60);
                else sprite = Sprite.minvo_left1;
                break;
        }
    }
}