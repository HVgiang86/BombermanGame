package bomberman;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import bomberman.Exceptions.LoadLevelException;
import bomberman.GUI.Frame;
import bomberman.Graphics.Screen;
import bomberman.Input.KeyBoard;
import bomberman.Sound.Sound;

public class Games extends Canvas {

	// Thong so game
	public static final String title = "BombermanGame";
	public static final int Tiles_size = 16, width = Tiles_size * (31 / 2), height = Tiles_size * 13;
	public static int scale = 3; // ti le

	// Thong so cua nguoi choi va bom
	public static final int Bombrate = 1;// trang thai bom no hay khong
	public static final int Bombradius = 1;// ban kinh anh huong khi bom no
	public static final double Bomberspeed = 1; // toc do bom no

	// Thong so game bat dau choi
	public static final int time = 200;
	public static final int points = 0;
	public static final int lives = 3;

	// Thoi gian tre man hinh
	public static int Screen_Delay = 3;

	// Thong so mac dinh cua nguoi choi va bom
	protected static int Bomb_Rate = Bombrate;
	protected static int Bomb_Radius = Bombradius;
	protected static double Bomber_Speed = Bomberspeed;

	protected int ScreenDelayLevel = Screen_Delay;

	// chieu dai, rong trong tung level
	public static int levelWidth;
	public static int levelHeight;

	private final KeyBoard input;
	static Sound sound = new Sound();

	// Trang thai cua game
	private boolean running = false;
	private boolean paused = true;

	// sử dụng để load ảnh trong game
	private final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private final int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private final Board board;
	private final Screen screen;
	private final Frame frames_game;

	protected int screen_Delay = Screen_Delay;

	public Games(Frame frame) throws LoadLevelException {

		this.frames_game = frame;
		this.frames_game.setTitle(title);
		input = new KeyBoard();
		screen = new Screen(width*scale, height*scale);

		this.board = new Board(this, input, screen);
		addKeyListener(input);
	}

	private void renderScreen() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		
		Graphics g = bs.getDrawGraphics();
		board.drawScreen(g);

		g.dispose();
		bs.show();
	}

	// Render game, cài đặt để tạo ra FPS lớn nhất có thể tương thích với
	// máy
	private void renderGame() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		board.render(screen);

		System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		board.renderMessages(g);

		g.dispose();
		bs.show();
	}

	// Update các thông số game
	private void update() {
		input.update();
		board.update();
	}

	// Start game
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
                if ( screen_Delay <= 0) {
                    board.setShow(-1);
                    paused = false;
                }
                renderScreen();
            } else renderGame();

            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
            	this.frames_game.setTime(board.substractTime());
            	this.frames_game.setPoints(board.getPoints());
                 this.frames_game.setLives(board.getLives());
                timer += 1000;

                this.frames_game.setTitle("Bomberman" + " | " + updates + " rate, " + frames + " fps");
                updates = 0;
                frames = 0;

                if (this.board.getShow() == 2) screen_Delay--;
            }
        }
    }

	public static int getBombRate() {
		return Bomb_Rate;
	}

	public static int getBomberRadius() {
		return Bomb_Radius;
	}

	public static double getBomberSpeed() {
		return Bomber_Speed;
	}

	public static void addBomberSpeed(double i) {
		Bomber_Speed += i;
	}

	public static void addBombRadius(int i) {
		Bomb_Radius += i;
	}

	public static void addBombRate(int i) {
		Bomb_Rate += i;
	}

	public void resetScreenDelay() {
		ScreenDelayLevel = Screen_Delay;
	}

	public Board getBoard() {
		return board;
	}

	public void resume() {
		this.paused = false;
		ScreenDelayLevel = -1;
	}

	public boolean isPaused() {
		return paused;
	}

	public void pause() {
		paused = true;
	}

	public static void setBombRate(int bombRate) {
		Games.Bomb_Rate = bombRate;
	}

	public static void setBombRadius(int bombRadius) {
		Games.Bomb_Radius = bombRadius;
	}

	public static void setBomberSpeed(double bomberSpeed) {
		Games.Bomber_Speed = bomberSpeed;
	}

	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}

	public static void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}

}
