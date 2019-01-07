package personajes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import base.Handler;
import base.HandlerEnemigo;
import base.ID;
import base.JuegoBase;

public class cola extends GameObject{
	private float alfa = 1;
	private Handler handler;
	private Color color;
	private float ancho, alto;
	private float vidaEsperada;
	
	public cola(float x, float y, ID id, Color color, int ancho, int alto, float vida, Handler handl) {
		
		super(x, y, id);
		this.ancho = ancho;
		this.alto = alto;
		this.color = color;
		this.vidaEsperada = vida;
		this.handler = handl;
	}
	
	
	public void tick() {
		if(alfa > vidaEsperada) {
			alfa -= (vidaEsperada - 0.001f);
		} else handler.removeObject(this);
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(hacerTranspartente(alfa));
		g.setColor(color); 
		g.fillRect((int)x, (int)y, (int)ancho, (int)alto);
		
		g2d.setComposite(hacerTranspartente(1));
	}
	
	private AlphaComposite hacerTranspartente(float alfa) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alfa));
	}

	@Override
	public Rectangle perimetro() {
		// TODO Auto-generated method stub
		return null;
	}
}
