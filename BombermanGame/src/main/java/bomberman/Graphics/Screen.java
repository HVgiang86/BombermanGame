package bomberman.Graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Arrays;

import bomberman.Board;
import bomberman.Games;
import bomberman.entities.Entity;
import bomberman.entities.animated_entity.Bomber;

public class Screen {

	/**
	 * transparent_color duoc coi la mau trong suot, doc lap tren moi thiet bi
	 * width,height : chieu rong,cao cua anh
	 */
	private final int transparent_color = 0xffff00ff;
	protected int width, height;
	public int[] pixels;

	public static int xOffset = 0, yOffset = 0;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	
	
	public int getWidth() {
		return width;
	}



	public void setWidth(int width) {
		this.width = width;
	}



	public int getHeight() {
		return height;
	}



	public void setHeight(int height) {
		this.height = height;
	}



	public static void setOffset(int x1, int y1 ) {
		xOffset = x1;
		yOffset = y1;;
	}

	// xoa anh
	public void clear() {
		Arrays.fill(pixels, 0);// cac phan tu trong mang pixels deu bang 0
	}

	public void renderEntity(int xp, int yp, Entity entity) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < entity.getSprite().getSIZE(); y++) {
			int ya = y + yp;
			for (int x = 0; y < entity.getSprite().getSIZE(); x++) {
				int xa = x + xp;
				if (xa < -entity.getSprite().getSIZE() || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSIZE());
				if (color != transparent_color)
					pixels[xa + ya * width] = color;
			}
		}
	}
	
	public static int calculateXOffset(Board board,Bomber bomber) {
		  if (bomber == null) return 0;
	        int temp = xOffset;
	        double bomberX = bomber.getX() / 16;
	        double complement = 0.5;
	        int firstBreakpoint = board.getWidth() / 4;
	        int lastBreakpoint = board.getWidth() - firstBreakpoint;
	        if (bomberX > firstBreakpoint + complement && bomberX < lastBreakpoint - complement) temp = (int) bomber.getX()  - (Games.width / 2);
	        return temp;
	}

	public void renderEntityWithBelowSprite(int xp, int yp, Entity entity, Sprite bellow) {

		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < entity.getSprite().getSIZE(); y++) {
			int ya = y + yp;
			for (int x = 0; x < entity.getSprite().getSIZE(); x++) {
				int xa = x + xp;
				if (xa < -entity.getSprite().getSIZE() || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSIZE());
				if(color != transparent_color)
					this.pixels[xa+ya*width] = color;
				else {
					this.pixels[xa+ya*width] = bellow.getPixel(x+y*bellow.getSIZE());
				};
			}

		}
	}
	
	 /* Vẽ các màn hình game */
    public void drawEndGame(Graphics g, int points) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        Font font = new Font("Arial", Font.PLAIN, 20 * Games.scale);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("GAME OVER", getWidth(), getHeight(), g);

        font = new Font("Arial", Font.PLAIN, 10 * Games.scale);
        g.setFont(font);
        g.setColor(Color.yellow);
        drawCenteredString("SCORE: " + points, getWidth(), getHeight() + (Games.Tiles_size * 2) * Games.scale, g);
    }

    public void drawChangeLevel(Graphics g, int level) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        Font font = new Font("Arial", Font.PLAIN, 20 * Games.scale);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("LEVEL " + level, getWidth(), getHeight(), g);
    }

    public void drawPaused(Graphics g) {
        Font font = new Font("Arial", Font.PLAIN, 20 * Games.scale);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("PAUSED", getWidth(), getHeight(), g);
    }

    public void drawFinishGame(Graphics g, int points) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        Font font = new Font("Arial", Font.PLAIN, 20 * Games.scale);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("VICTORY", getWidth(), getHeight(), g);

        font = new Font("Arial", Font.PLAIN, 10 * Games.scale);
        g.setFont(font);
        g.setColor(Color.yellow);
        drawCenteredString("SCORE: " + points, getWidth(), getHeight() + (Games.Tiles_size * 2) * Games.scale, g);
    }
    
    public void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        g.drawString(s, (w - fm.stringWidth(s)) / 2, (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2));
    }


}
