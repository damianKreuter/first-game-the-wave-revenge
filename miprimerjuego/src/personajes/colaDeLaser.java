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

public class colaDeLaser extends Enemigo{

	private float alfa = 1;
	private Handler handler;
	private Color color;
	private float ancho, alto;
	private float vidaEsperada;
	private boolean horizontal;
	private int maximo = 5;
	
	public colaDeLaser(float x, float y, Color color, float ancho, float alto, HandlerEnemigo handl, Handler handler, boolean tipo) {
		
		super(x, y, ID.Laser, handl, 10, handler);
		vidaEsperada = 0.1f;
		this.ancho = ancho;
		this.alto = alto;
		this.color = color;
		horizontal = tipo;
		this.handler = handler;
		tipo = horizontal;
	}
	
	
	public void tick() {
		if(alfa > vidaEsperada) {
			alfa -= (vidaEsperada - 0.01f);
		} else handler.removeObject(this);
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(hacerTranspartente(alfa));
		g.setColor(color); 
		if(horizontal) {
			y--;
			alto += 2;
			g.fillRect((int)x, (int)y, (int)ancho, (int)alto);
		} else {
			x--;
			ancho += 2;
			g.fillRect((int)x, (int)y, (int)ancho, (int)alto);
		}
		
		
	//	g.fillRect((int)x, (int)y, (int)ancho, (int)alto);
		
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


	@Override
	public float haceDanio() {
		// TODO Auto-generated method stub
		return danio;
	}


	@Override
	public void desaparece() {
		// TODO Auto-generated method stub
	//	handlerEnemigo.removeObject(this);
	}
}
