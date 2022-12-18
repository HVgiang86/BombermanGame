package BombermanGame.Utils.Level;

import BombermanGame.Utils.CoordinateConverter;
import BombermanGame.Views.Game;
import BombermanGame.Views.GUI.GameBoard;
import BombermanGame.Models.Entities.Characters.Bomber;
import BombermanGame.Models.Entities.Characters.Enemies.*;
import BombermanGame.Models.Entities.LayeredEntity;
import BombermanGame.Models.Entities.Tiles.Destroyable.Brick;
import BombermanGame.Models.Entities.Tiles.Grass;
import BombermanGame.Models.Entities.Tiles.Items.BombItem;
import BombermanGame.Models.Entities.Tiles.Items.FlameItem;
import BombermanGame.Models.Entities.Tiles.Items.SpeedItem;
import BombermanGame.Models.Entities.Tiles.Portal;
import BombermanGame.Models.Entities.Tiles.Wall;
import BombermanGame.Exceptions.LoadLevelException;
import BombermanGame.Views.Sprites.Sprite;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FileLevelLoader extends LevelLoader {
    private static char[][] map;

    public FileLevelLoader(GameBoard gameBoard, int level) throws LoadLevelException {
        super(gameBoard, level);
    }

    @Override
    public void loadLevel(int level) {
        try {
            Class<?> c = Class.forName("BombermanGame.Utils.Level.FileLevelLoader");
            InputStream stream = c.getResourceAsStream("/Utils/Levels/level" + level + ".txt");
            Reader r = new InputStreamReader(Objects.requireNonNull(stream), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(r);

            String line;
            line = br.readLine();
            String[] sizes = line.split("\\s");
            this.level = Integer.parseInt(sizes[0]);
            height = Integer.parseInt(sizes[1]);
            width = Integer.parseInt(sizes[2]);
            map = new char[width][height];

            int rowNum = 0;
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) map[i][rowNum] = line.charAt(i);
                rowNum++;
            }

            stream.close();
            br.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createEntities() {
        Game.levelHeight = height;
        Game.levelWidth = width;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pos = x + y * width;
                switch (map[x][y]) {
                    case 'p' -> {
                        gameBoard.addEntity(pos, new Grass(x, y, Sprite.grass));
                        gameBoard.addCharacter(new Bomber(CoordinateConverter.tileToPixel(x), CoordinateConverter.tileToPixel(y) + Game.TILE_SIZE, gameBoard));
                    }
                    case '#' -> gameBoard.addEntity(pos, new Wall(x, y, Sprite.wall));
                    case '*' -> gameBoard.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new Brick(x, y, Sprite.brick)));
                    case 'x' -> gameBoard.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new Portal(x, y, Sprite.portal), new Brick(x, y, Sprite.brick)));
                    case 'b' -> gameBoard.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new BombItem(x, y, Sprite.powerup_bombs), new Brick(x, y, Sprite.brick)));
                    case 'f' -> gameBoard.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new FlameItem(x, y, Sprite.powerup_flames), new Brick(x, y, Sprite.brick)));
                    case 's' -> gameBoard.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new SpeedItem(x, y, Sprite.powerup_speed), new Brick(x, y, Sprite.brick)));
                    case '1' -> {
                        gameBoard.addEntity(pos, new Grass(x, y, Sprite.grass));
                        gameBoard.addCharacter(new Balloon(CoordinateConverter.tileToPixel(x), CoordinateConverter.tileToPixel(y) + Game.TILE_SIZE, gameBoard));
                    }
                    case '2' -> {
                        gameBoard.addEntity(pos, new Grass(x, y, Sprite.grass));
                        gameBoard.addCharacter(new Oneal(CoordinateConverter.tileToPixel(x), CoordinateConverter.tileToPixel(y) + Game.TILE_SIZE, gameBoard));
                    }
                    case '3' -> {
                        gameBoard.addEntity(pos, new Grass(x, y, Sprite.grass));
                        gameBoard.addCharacter(new Doll(CoordinateConverter.tileToPixel(x), CoordinateConverter.tileToPixel(y) + Game.TILE_SIZE, gameBoard));
                    }
                    case '4' -> {
                        gameBoard.addEntity(pos, new Grass(x, y, Sprite.grass));
                        gameBoard.addCharacter(new Minvo(CoordinateConverter.tileToPixel(x), CoordinateConverter.tileToPixel(y) + Game.TILE_SIZE, gameBoard));
                    }
                    case '5' -> {
                        gameBoard.addEntity(pos, new Grass(x, y, Sprite.grass));
                        gameBoard.addCharacter(new Ghost(CoordinateConverter.tileToPixel(x), CoordinateConverter.tileToPixel(y) + Game.TILE_SIZE, gameBoard));
                    }
                    case '6' -> {
                        gameBoard.addEntity(pos, new Grass(x, y, Sprite.grass));
                        gameBoard.addCharacter(new Kondoria(CoordinateConverter.tileToPixel(x), CoordinateConverter.tileToPixel(y) + Game.TILE_SIZE, gameBoard));
                    }
                    default -> gameBoard.addEntity(pos, new Grass(x, y, Sprite.grass));
                }
            }
        }
    }
}