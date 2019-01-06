package hojaDeSprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class cargarImagen {
	
	BufferedImage imagen;
	
	public BufferedImage cargarImagen(String path) {
		try {
			imagen = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imagen;
	}
	
}
