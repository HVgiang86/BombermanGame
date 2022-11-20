package bomberman.Level;

import bomberman.Games;

public class Coordinates {

	public static int pixelToTile(double i) {
		return (int)(i/Games.Tiles_size);
	}
	
	public static int tileToPixel(double i) {
		return (int)(i*Games.Tiles_size);
	}
	
	public static int tileToPixel(int i) {
		return i*Games.Tiles_size;
	}
}
