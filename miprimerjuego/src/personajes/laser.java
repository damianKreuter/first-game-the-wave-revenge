package personajes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import Spawn.Spawn;
import base.Handler;
import base.HandlerEnemigo;
import base.ID;
import base.JuegoBase;

public class laser extends Enemigo {
	
	float altura = 5;
	float ancho = 5;
	float velX, velY;
	Random r = new Random();
	int tiempoDeVida;
	int tiempoDeMuerte;
	boolean horizontal;
	int adicion;
	Color color;
	float transparencia;
	public laser(int x,int y, HandlerEnemigo handE, Handler hands, char tipo)
	{
		super(x, y, ID.Laser, handE, 0, hands);
		//, hand, 2, hands);
		// TODO Auto-generated constructor stub
		tiempoDeVida = 50;
		tiempoDeMuerte = 100;
		if(tipo == 'H') {
			horizontal = true;
			ancho = JuegoBase.ANCHO;
		} else {
			horizontal = false;
			altura = JuegoBase.ALTO;
		}
		adicion = 0;
		color = new Color(1f, 1f, 0.6f, 1f);
		transparencia = 1f;
	}
	
	public Rectangle perimetro() {
		return new Rectangle((int)x, (int) y, (int) ancho, (int) altura);
	}
	
	@Override
	public void tick() {
		
		if(tiempoDeVida > 0) {
			tiempoDeVida--;
		} else {
			laserDanio();
		}
//		handlerEnemigo.addObject(new colaDeLaser(x, y, color, ancho, altura, handlerEnemigo, handlerNormal, horizontal));
	}
	
	public void laserDanio() {
		if(transparencia <= 0.03f) {
			finDeVidaRayo();
		} else {
			danio = 10;
			if(horizontal) {
				if(adicion < 10) {
					y -= 1;
					altura += 2;
					adicion++;
				} else {
					transparencia -= 0.03f;
				}
			} else {
				if(adicion < 10) {
					x -= 1;
					ancho += 2;
					adicion++;
				} else {
					transparencia -= 0.03f;
				}
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(new Color(1f, 1f, 0.6f, transparencia));
		g2d.draw(perimetro());
		g.setColor(new Color(1f, 1f, 0.6f, transparencia));
		g.fillRect((int)x, (int)y, (int)ancho, (int)altura);
	}
	
	public float haceDanio() {
		return danio;
	}
	
	public void finDeVidaRayo() {
			Spawn.hayRayoDisponible = true;
			handlerEnemigo.removeObject(this);
		}
	
	public void desaparece() {
	//	handlerEnemigo.removeObject(this);
	}
	
}
