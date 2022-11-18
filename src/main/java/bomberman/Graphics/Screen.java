package bomberman.Graphics;

import java.util.Arrays;

import bomberman.entities.Entity;

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

	// xoa anh
	public void clear() {
		Arrays.fill(pixels, 0);// cac phan tu trong mang pixels deu bang 0
	}
	
	public void renderEntity(int xp,int yp,Entity entity) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y= 0; y< entity.getSprite().getSIZE();y++) {
			int ya = y + yp;
			for(int x = 0; y<entity.getSprite().getSIZE();x++) {
				int xa = x + xp;
				if(xa < -entity.getSprite().getSIZE() || xa >= width || ya < 0 || ya >= height)
					break;
				if(xa < 0) xa = 0;
				int color = entity.getSprite().getPixel(x + y*entity.getSprite().getSIZE());
				if(color != transparent_color) pixels[xa + ya*width] = color;
			}
		}
	}
	

}
