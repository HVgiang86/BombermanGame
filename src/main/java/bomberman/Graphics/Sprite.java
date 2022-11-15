package bomberman.Graphics;

import java.util.Arrays;

public class Sprite {

	/**
	 * DEFAULT_size: mac dinh do rong cua anh.
	 * SIZE: do rong cua anh .
	 * x,y : toa do hien tai cua character trong tam anh chung cua tat ca thuc the.
	 *  pixels: tap hop tat ca cac phan cua hinh anh thanh mot hinh anh hoan chinh .
	 *  currentWidth,currentHeight: chieu rong, cao cua anh sheet: tam anh hoan chinh.
	 */
	public static final int DEFAULT_SIZE = 16;
	public final int SIZE;
	private int x, y;
	public int[] pixels;
	protected int currentWidth, currentHeight;
	private SpriteSheet sheet;

	public Sprite(int size, int x, int y, int currentWidth, int currentHeight, SpriteSheet sheet) {
		SIZE = size;
		this.x = x;
		this.y = y;
		pixels = new int[SIZE * SIZE];
		this.currentWidth = currentWidth;
		this.currentHeight = currentHeight;
		this.sheet = sheet;
		load();
	}
	

	public int getSIZE() {
		return SIZE;
	}
	
	public int getPixel(int i) {
		return pixels[i];
	}

	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	private void setColor(int color) {
		Arrays.fill(pixels, color);
	}

	private void load() {
		for (int i = 0; i < SIZE; i++) {
			for (int y = 0; y < SIZE; y++) {
				pixels[y + x * SIZE] = sheet.pixels[(y + this.y) + (x + this.x) * sheet.sizeofimage];
			}
		}
	}
	
	

	// tach tung thuc the ra tu anh thuc the chung

	// Board sprites
	public static Sprite grass = new Sprite(DEFAULT_SIZE, 6, 0, 16, 16, SpriteSheet.tiles);
	public static Sprite brick = new Sprite(DEFAULT_SIZE, 7, 0, 16, 16, SpriteSheet.tiles);
	public static Sprite wall = new Sprite(DEFAULT_SIZE, 5, 0, 16, 16, SpriteSheet.tiles);
	public static Sprite portal = new Sprite(DEFAULT_SIZE, 4, 0, 16, 16, SpriteSheet.tiles);

	// Character: tach cac nhan vat ra khoi anh chung
	// Enemy:ke thu
	// tiger: ho
	public static Sprite tiger_left1 = new Sprite(DEFAULT_SIZE, 4, 5, 16, 16, SpriteSheet.tiles);
	public static Sprite tiger_left2 = new Sprite(DEFAULT_SIZE, 4, 6, 16, 16, SpriteSheet.tiles);
	public static Sprite tiger_left3 = new Sprite(DEFAULT_SIZE, 4, 7, 16, 16, SpriteSheet.tiles);

	public static Sprite tiger_right1 = new Sprite(DEFAULT_SIZE, 5, 5, 16, 16, SpriteSheet.tiles);
	public static Sprite tiger_right2 = new Sprite(DEFAULT_SIZE, 5, 6, 16, 16, SpriteSheet.tiles);
	public static Sprite tiger_right3 = new Sprite(DEFAULT_SIZE, 5, 7, 16, 16, SpriteSheet.tiles);

	public static Sprite tiger_dead = new Sprite(DEFAULT_SIZE, 4, 8, 16, 16, SpriteSheet.tiles);

	// ghost: ma
	public static Sprite ghost_left1 = new Sprite(DEFAULT_SIZE, 6, 5, 16, 16, SpriteSheet.tiles);
	public static Sprite ghost_left2 = new Sprite(DEFAULT_SIZE, 6, 6, 16, 16, SpriteSheet.tiles);
	public static Sprite ghost_left3 = new Sprite(DEFAULT_SIZE, 6, 7, 16, 16, SpriteSheet.tiles);

	public static Sprite ghost_right1 = new Sprite(DEFAULT_SIZE, 7, 5, 16, 16, SpriteSheet.tiles);
	public static Sprite ghost_right2 = new Sprite(DEFAULT_SIZE, 7, 6, 16, 16, SpriteSheet.tiles);
	public static Sprite ghost_right3 = new Sprite(DEFAULT_SIZE, 7, 7, 16, 16, SpriteSheet.tiles);

	public static Sprite ghost_dead = new Sprite(DEFAULT_SIZE, 6, 8, 16, 16, SpriteSheet.tiles);
	
	//Tat ca ke thu bi no
	public static Sprite ghost_dead1 = new Sprite(DEFAULT_SIZE, 15, 0, 16, 16, SpriteSheet.tiles);
	public static Sprite ghost_dead2 = new Sprite(DEFAULT_SIZE, 15, 1, 16, 16, SpriteSheet.tiles);
	public static Sprite ghost_dead3 = new Sprite(DEFAULT_SIZE, 15, 2, 16, 16, SpriteSheet.tiles);
	
	
	//Bomber: nhan vat choi
	public static Sprite bomber_up1 = new Sprite(DEFAULT_SIZE, 0, 0, 16, 16, SpriteSheet.tiles);
	public static Sprite bomber_up2 = new Sprite(DEFAULT_SIZE, 0, 1, 16, 16, SpriteSheet.tiles);
	public static Sprite bomber_up3 = new Sprite(DEFAULT_SIZE, 0, 2, 16, 16, SpriteSheet.tiles);
	
	public static Sprite bomber_down1 = new Sprite(DEFAULT_SIZE, 2, 0, 16, 16, SpriteSheet.tiles);
	public static Sprite bomber_down2 = new Sprite(DEFAULT_SIZE, 2, 1, 16, 16, SpriteSheet.tiles);
	public static Sprite bomber_down3 = new Sprite(DEFAULT_SIZE, 2, 2, 16, 16, SpriteSheet.tiles);
	
	public static Sprite bomber_left1 = new Sprite(DEFAULT_SIZE, 3, 0, 16, 16, SpriteSheet.tiles);
	public static Sprite bomber_left2 = new Sprite(DEFAULT_SIZE, 3, 1, 16, 16, SpriteSheet.tiles);
	public static Sprite bomber_left3 = new Sprite(DEFAULT_SIZE, 3, 2, 16, 16, SpriteSheet.tiles);

	public static Sprite bomber_right1 = new Sprite(DEFAULT_SIZE, 1, 0, 16, 16, SpriteSheet.tiles);
	public static Sprite bomber_right2 = new Sprite(DEFAULT_SIZE, 1, 1, 16, 16, SpriteSheet.tiles);
	public static Sprite bomber_right3 = new Sprite(DEFAULT_SIZE, 1, 2, 16, 16, SpriteSheet.tiles);
	
	//Nhan vat choi bi giet
	public static Sprite bomber_dead1 = new Sprite(DEFAULT_SIZE, 4, 2, 16, 16, SpriteSheet.tiles);
	public static Sprite bomber_dead2= new Sprite(DEFAULT_SIZE, 5, 2, 16, 16, SpriteSheet.tiles);
	public static Sprite bomber_dead3 = new Sprite(DEFAULT_SIZE, 6, 2, 16, 16, SpriteSheet.tiles);
	
	//Bomb  
	public static Sprite bomb_exploded1= new Sprite(DEFAULT_SIZE, 2, 3, 16, 16, SpriteSheet.tiles);
	public static Sprite bomb_exploded2= new Sprite(DEFAULT_SIZE, 1, 3, 16, 16, SpriteSheet.tiles);
	public static Sprite bomb_exploded3 = new Sprite(DEFAULT_SIZE, 0, 3, 16, 16, SpriteSheet.tiles);
	
	//FlameSegement
	public static Sprite bomb_exploded_vertical1= new Sprite(DEFAULT_SIZE, 1, 5, 16, 16, SpriteSheet.tiles);
	public static Sprite bomb_exploded_vertical2= new Sprite(DEFAULT_SIZE, 2, 5, 16, 16, SpriteSheet.tiles);
	public static Sprite bomb_exploded_vertical3= new Sprite(DEFAULT_SIZE, 3, 5, 16, 16, SpriteSheet.tiles);
	
	public static Sprite bomb_exploded_horizontal1= new Sprite(DEFAULT_SIZE, 1, 7, 16, 16, SpriteSheet.tiles);
	public static Sprite bomb_exploded_horizontal2= new Sprite(DEFAULT_SIZE, 1, 7, 16, 16, SpriteSheet.tiles);
	public static Sprite bomb_exploded_horizontal3= new Sprite(DEFAULT_SIZE, 1, 8, 16, 16, SpriteSheet.tiles);
	
	public static Sprite bomb_exploded_horizontal_left_last = new Sprite(DEFAULT_SIZE, 0, 7, 16, 16, SpriteSheet.tiles);
	public static Sprite bomb_exploded_horizontal_left_last1 = new Sprite(DEFAULT_SIZE, 0, 8, 16, 16, SpriteSheet.tiles);
	public static Sprite bomb_exploded_horizontal_left_last2 = new Sprite(DEFAULT_SIZE, 0, 9, 16, 16, SpriteSheet.tiles);
	
	public static Sprite ebomb_exploded_horizontal_right_last = new Sprite(DEFAULT_SIZE, 2, 7, 16, 16, SpriteSheet.tiles);
	public static Sprite bomb_exploded_horizontal_right_last1 = new Sprite(DEFAULT_SIZE, 2, 8, 16, 16, SpriteSheet.tiles);
	public static Sprite bomb_exploded_horizontal_right_last2 = new Sprite(DEFAULT_SIZE, 2, 9, 16, 16, SpriteSheet.tiles);
	
	public static Sprite bomb_exploded_horizontall_top_last = new Sprite(DEFAULT_SIZE, 1, 4, 16, 16, SpriteSheet.tiles);
	public static Sprite bomb_exploded_horizontal_top_last1 = new Sprite(DEFAULT_SIZE, 2, 4, 16, 16, SpriteSheet.tiles);
	public static Sprite ebomb_exploded_horizontal_top_last2 = new Sprite(DEFAULT_SIZE, 3, 4, 16, 16, SpriteSheet.tiles);
	
	public static Sprite bomb_exploded_horizontal_down_last = new Sprite(DEFAULT_SIZE, 1, 6, 16, 16, SpriteSheet.tiles);
	public static Sprite bomb_exploded_horizontal_down_last1 = new Sprite(DEFAULT_SIZE, 2, 6, 16, 16, SpriteSheet.tiles);
	public static Sprite bomb_exploded_horizontal_down_last2 = new Sprite(DEFAULT_SIZE, 3, 6, 16, 16, SpriteSheet.tiles);
	
	
	//Brick FlameSegment: gach bi no
	public static Sprite brick_exploded = new Sprite(DEFAULT_SIZE, 7, 1, 16, 16, SpriteSheet.tiles);
	public static Sprite brick_exploded1 = new Sprite(DEFAULT_SIZE, 7, 2, 16, 16, SpriteSheet.tiles);
	public static Sprite brick_exploded2 = new Sprite(DEFAULT_SIZE, 7, 3, 16, 16, SpriteSheet.tiles);
	
	//Power: suc manh
	public static Sprite powerup_bombs = new Sprite(DEFAULT_SIZE, 0, 10, 16, 16, SpriteSheet.tiles);
	public static Sprite powerup_flames = new Sprite(DEFAULT_SIZE, 1, 10, 16, 16, SpriteSheet.tiles);
	public static Sprite powerup_speed = new Sprite(DEFAULT_SIZE, 2, 10, 16, 16, SpriteSheet.tiles);
	public static Sprite powerup_wallpass = new Sprite(DEFAULT_SIZE, 3, 10, 16, 16, SpriteSheet.tiles);
	public static Sprite powerup_detonator = new Sprite(DEFAULT_SIZE, 4, 10, 16, 16, SpriteSheet.tiles);
	public static Sprite powerup_bombpass = new Sprite(DEFAULT_SIZE, 5, 10, 16, 16, SpriteSheet.tiles);
	public static Sprite powerup_flamepass = new Sprite(DEFAULT_SIZE, 6, 10, 16, 16, SpriteSheet.tiles);
	
	
	
}
