package hojaDeSprites;

import java.awt.image.BufferedImage;

public class Sprites {
	
	private BufferedImage sprite;
	
	public Sprites(BufferedImage ss) {
		this.sprite = ss;
	}
	
	public BufferedImage darImagen(int col, int row, int ancho, int alto) {
		BufferedImage img = sprite.getSubimage((row * 32)-32, (col*32)-32, ancho, alto);
		return img;
	}

}
