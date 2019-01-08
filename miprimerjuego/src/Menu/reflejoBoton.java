package Menu;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import base.Handler;
import base.ID;
import personajes.GameObject;

public class reflejoBoton extends GameObject{
	private float alfa = 1;
	private Handler handler;
	private Color color;
	private float ancho, alto;
	private float vidaEsperada;
	private int colorRGB;
	private int degrade;
	
	public reflejoBoton(float x, float y, ID id, Color color, int ancho, int alto, float vida, Handler handl, int degrado) {
		
		super(x, y, id);
		this.ancho = ancho;
		this.alto = alto;
		this.color = Color.white;
		this.vidaEsperada = vida;
		this.handler = handl;
		colorRGB = 255;
		degrade = degrado;
	}
	
	
	public void tick() {
		if(alfa > vidaEsperada) {
			alfa -= (vidaEsperada - 0.001f);
			
			//HACE MAS GRANDE EL TAMAÑO DE LA CAJA MIENTRAS SE DEGRADA SU COLOR
			x -= 1f;
			y -= 1f;
			ancho += 2f;
			alto += 2f;
			if(colorRGB >= degrade) {
				colorRGB -= degrade;
			}
		} else handler.removeObject(this);
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(hacerTranspartente(alfa));
		g.setColor(new Color(colorRGB, colorRGB, colorRGB)); 
		g2d.draw(perimetro());
		g2d.setComposite(hacerTranspartente(1));
	}
	
	@Override
	public Rectangle perimetro() {
		return new Rectangle((int)x, (int) y, (int)ancho, (int)alto);
	}
	
	private AlphaComposite hacerTranspartente(float alfa) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alfa));
	}


}
