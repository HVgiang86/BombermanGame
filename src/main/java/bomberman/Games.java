package bomberman;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import bomberman.Graphics.Screen;
import bomberman.Input.KeyBoard;

public class Games extends Canvas {
	
	//Thong so game
	public static final String title = "BombermanGame";
	public static final int Tiles_size = 16, width = Tiles_size * (31 / 2), height = Tiles_size * 13;
	public static int scale = 3; // ti le 
	
	
	//Thong so cua nguoi choi va bom
	public static final int Bombrate = 1;//trang thai bom no hay khong
	public static final int Bombradius = 1;// ban kinh anh huong khi bom no
	public static final double Bomberspeed = 1; //toc do bom no
	
	//Thong so game bat dau choi
	public static final int time = 200;
	public static final int points = 0;
	
	// Thoi gian tre man hinh
	public static int Screen_Delay = 3;
	
	//Thong so mac dinh cua nguoi choi va bom
	protected static int Bomb_Rate = Bombrate;
	protected static int Bomb_Radius = Bombradius;
	protected static double Bomber_Speed = Bomberspeed;
	
	protected int ScreenDelayLevel = Screen_Delay;
	
	// chieu dai, rong trong tung level
	public static int levelWidth;
	public static int levelHeight;
	
	
	private final KeyBoard input;
	
	// Trang thai cua game
	private boolean running = false;
	private boolean paused = true;
	
	// sử dụng để load ảnh trong game
	private final BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB );
	private final int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	private final Board board;
	private final Screen screen;
	private final Frame frame;
	
	
	public Games(Frame frame) {
		this.frame = frame;
		this.frame.setTitle(title);
		input = new KeyBoard();
		screen = new Screen(width, height);
		board = new Board();
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
	
}
