package BombermanGame.Views;

import BombermanGame.Utils.Sound.Sound;
import BombermanGame.Views.GUI.GameBoard;
import BombermanGame.Views.GUI.GameWindowsFrame;
import BombermanGame.controller.GameRenderController;
import BombermanGame.controller.Input.KeyboardHandler;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas {
    //Thông số game
    public static final String TITLE = "Bomberman";
    public static final int TILE_SIZE = 16;
    public static final int WIDTH = TILE_SIZE * (31 / 2), HEIGHT = 13 * TILE_SIZE;
    //Thông số mặc định của game
    public static final int TIME = 200;
    public static final int POINTS = 0;
    public static final int LIVES = 3;
    //Thông số mặc định của người chơi
    private static final int BOMB_RATE = 1;
    private static final int BOMB_RADIUS = 1;
    private static final double BOMBER_SPEED = 1.0;
    public static int SCALE_MULTIPLE = 3;
    //Thông số riêng của người chơi, có thể thay đổi khi ăn được các item
    public static int bombRate = BOMB_RATE;
    public static int bombRadius = BOMB_RADIUS;
    public static double bomberSpeed = BOMBER_SPEED;
    public static int levelWidth;
    public static int levelHeight;
    protected static int SCREEN_DELAY = 3;
    static Sound sound = new Sound();
    private final KeyboardHandler input;
    //Sử dụng để render game
    private final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private final int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private final GameBoard gameBoard;
    private final GameRenderController gameRenderController;
    private final GameWindowsFrame gameWindowsFrame;
    //Thời gian trong một level
    protected int screenDelay = SCREEN_DELAY;
    //Trạng thái game
    private boolean running = false;
    private boolean paused = true;

    public Game(GameWindowsFrame gameWindowsFrame) {
        this.gameWindowsFrame = gameWindowsFrame;
        this.gameWindowsFrame.setTitle(TITLE);

        input = new KeyboardHandler();
        gameRenderController = new GameRenderController(WIDTH, HEIGHT);

        gameBoard = new GameBoard(this, input, gameRenderController);
        addKeyListener(input);
    }

    public static int getBombRate() {
        return bombRate;
    }

    public static int getBombRadius() {
        return bombRadius;
    }

    public static double getBomberSpeed() {
        return bomberSpeed;
    }

    public static void addBomberSpeed(double i) {
        bomberSpeed += i;
    }

    public static void addBombRadius(int i) {
        bombRadius += i;
    }

    public static void addBombRate(int i) {
        bombRate += i;
    }

    public static void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }

    //Render game, cài đặt để tạo ra FPS lớn nhất có thể tương thích với máy
    private void renderGame() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        gameRenderController.clear();
        gameBoard.render(gameRenderController);

        System.arraycopy(gameRenderController.pixels, 0, pixels, 0, pixels.length);

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        gameBoard.renderMessages(g);

        g.dispose();
        bs.show();
    }

    private void renderScreen() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        gameRenderController.clear();

        Graphics g = bs.getDrawGraphics();
        gameBoard.drawScreen(g);

        g.dispose();
        bs.show();
    }

    //Update các thông số game
    private void update() {
        input.update();
        gameBoard.update();
    }

    //Start game
    public void start() {
        playMusic(0);

        running = true;

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double nps = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0, updates = 0;

        requestFocus();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nps;
            lastTime = now;
            while (delta > 1) {
                update();
                updates++;
                delta--;
            }

            if (paused) {
                if (screenDelay <= 0) {
                    gameBoard.setShow(-1);
                    paused = false;
                }
                renderScreen();
            } else renderGame();

            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                gameWindowsFrame.setTime(gameBoard.subtractTime());
                gameWindowsFrame.setPoints(gameBoard.getPoints());
                gameWindowsFrame.setLives(gameBoard.getLives());

                timer += 1000;

                this.gameWindowsFrame.setTitle("Bomberman" + " | " + updates + " rate, " + frames + " fps");
                updates = 0;
                frames = 0;

                if (this.gameBoard.getShow() == 2) screenDelay--;
            }
        }
    }

    public void resetScreenDelay() {
        screenDelay = SCREEN_DELAY;
    }

    public GameBoard getBoard() {
        return gameBoard;
    }

    public void resume() {
        this.paused = false;
        screenDelay = -1;
    }

    public boolean isPaused() {
        return paused;
    }

    public void pause() {
        paused = true;
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
}