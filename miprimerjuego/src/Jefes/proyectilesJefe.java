package Jefes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import AyudaExtra.ayudaExtra;
import base.Handler;
import base.HandlerEnemigo;
import base.ID;
import base.JuegoBase;
import personajes.Enemigo;
import personajes.GameObject;
import personajes.cola;

public class proyectilesJefe extends Enemigo {
	
	int altura = 20;
	int ancho = 20, vecesARebotar;
	float velX, velY;
	Random r = new Random();
	Color color = new Color(246, 255, 104);
	private boolean estaEnfase2;
	public boolean deRetorno;
	private GameObject player;
	
	
	public proyectilesJefe(float x,float y, HandlerEnemigo handE, Handler hands, boolean enFase2, GameObject jugador, GameObject jefe)
	{
		super(x+40, y+40, ID.ProyectilesJefe, handE, 100, hands);
		//, hand, 2, hands);
		// TODO Auto-generated constructor stub
		estaEnfase2 = enFase2;
	//	velX = r.nextInt(21);
	//	velY = r.nextInt(21);
		vecesARebotar =  1;
		deRetorno = false;
		this.player = jugador;
		
		velX = ayudaExtra.vectorXHaciaPersonaje(player.getX(), player.getY(), x, y);
		velY = ayudaExtra.vectorYHaciaPersonaje(player.getX(), player.getY(), x, y);
		
		velX *=15;
		velY *=15;
		
		if(r.nextInt(2) == 0) {
			velX +=  r.nextInt(5);
		} else {
			velX -=  r.nextInt(5);
		}
		if(r.nextInt(2) == 0) {
			velY +=  r.nextInt(5);
		} else {
			velY -=  r.nextInt(5);
		}
		
	}
	
	
	public Rectangle perimetro() {
		return new Rectangle((int)x, (int) y, ancho, altura);
	}
	
	private void rebotar() {
		
		if(y <= JuegoBase.BASEALTURAHUD || y >= JuegoBase.ALTO - altura - 21) 
		{
			//SI LA VELOCIDAD Y ES POSITIVA, LA CAMBIO A LA NEGATIVA 
			if(velY > 0) {
		//		velY = ayudaExtra.vectorYHaciaPersonaje(player, x, y) + ayudaExtra.modificarValorVelocidad();
				velY = (10 +r.nextInt(11))  * -1;
				vecesARebotar--;
				deRetorno = true;
			} else {
				velY = (10+r.nextInt(21));
				vecesARebotar--;
				deRetorno = true;
	//			velY = ayudaExtra.vectorYHaciaPersonaje(player, x, y) + ayudaExtra.modificarValorVelocidad();
			}
		} 
		if(x <= 0 || x >= JuegoBase.ANCHO - ancho- 5) {
			//SI LA VELOCIDAD Y ES POSITIVA, LA CAMBIO A LA NEGATIVA
			if(velX > 0) {
	//		velX *= -1;
				velX = (10 +r.nextInt(11))  * -1;
				deRetorno = true;
				vecesARebotar--;
			} else {
		//		velX = ayudaExtra.vectorXHaciaPersonaje(player, x, y) + ayudaExtra.modificarValorVelocidad();
				velX = (10+r.nextInt(21));
				vecesARebotar--;
				deRetorno = true;
			}
		}
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		
		if(vecesARebotar > 0) {
			rebotar();
			
		}
		else 
		{
			if(x < -20 || x > JuegoBase.ANCHO +50) {
				desaparece();
			}
			if(y < JuegoBase.BASEALTURAHUD - 20 || y > JuegoBase.ALTO+50) {
				desaparece();
			}	
		}
		handlerNormal.addObject(new cola(x, y, ID.Cola, color, ancho, altura, 0.2f, handlerNormal)); 
	}
	
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(color);
		g2d.draw(perimetro());
	}
	
	public float haceDanioJefe() {
		return (danio/10)+50;
	}
	
	public float haceDanio() {
		return danio;
	}
	
	public void desaparece() {
		handlerEnemigo.removeObject(this);
	}
	
}