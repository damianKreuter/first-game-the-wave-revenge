package HUD;

import java.awt.Color;
import java.awt.Graphics;

public class HUDBARRA {
	int ALTURA, ANCHO; 
	public HUDBARRA(int alto, int anchura) {
		 ALTURA = alto;
		 ANCHO = anchura;
	 }
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, ANCHO, ALTURA);
	}
}
