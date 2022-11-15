package bomberman.Graphics;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Objects;

import javax.imageio.ImageIO;

public class SpriteSheet {

	//co tap hop tat ca cac thuc the co trong game vao mot tam
	// nhiem vu: tach nhung tam anh do ra
	
	private String path; // luu lai dia chi dan den tat ca cac entity
	public final int sizeofimage; //do rong cua anh
	public int[] pixels; //mang tap hop tat ca cac phan cua mot hinh anh

	public static SpriteSheet tiles = new SpriteSheet("/Utils/Textures/classic.png", 256);
	
	
	//lay mot anh tu trong anh chung cac nhan vat
	public SpriteSheet(String path, int size) {
		this.path = path;
		sizeofimage = size;
		pixels = new int[sizeofimage * sizeofimage];
		load();
	}

	private void load() {
		try {
			URL url = SpriteSheet.class.getResource(path);
			BufferedImage image = ImageIO.read(Objects.requireNonNull(url));
			int width = image.getWidth();
			int height = image.getHeight();				
			image.getRGB(0, 0,width , height, pixels, 0, width);
			//chia tap hop cac anh ra thanh cac anh nho 
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}	
	}

}
