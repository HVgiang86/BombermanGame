package BombermanGame.Models;

import BombermanGame.controller.GameRenderController;

public interface Renderable {
    void update();
    void render(GameRenderController gameRenderController);
}
