package BombermanGame.Utils;

import BombermanGame.Views.Game;

public class CoordinateConverter {
    public static int pixelToTile(double i) {
        return (int) (i / Game.TILE_SIZE);
    }

    public static int tileToPixel(int i) {
        return i * Game.TILE_SIZE;
    }

    public static int tileToPixel(double i) {
        return (int) (i * Game.TILE_SIZE);
    }
}