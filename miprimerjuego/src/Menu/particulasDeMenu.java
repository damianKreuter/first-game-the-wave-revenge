package Menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import base.Handler;
import base.HandlerEnemigo;
import base.ID;
import base.JuegoBase;
import personajes.Enemigo;
import personajes.GameObject;
import personajes.cola;

public class particulasDeMenu extends Enemigo
{

	int altura = 20;
	int ancho = 20;
	int velX, velY;
	
	private Random r = new Random();
	private int rojo = r.nextInt(255);
	private int verde = r.nextInt(255);
	private int azul = r.nextInt(255);
	
	private Color color;
	
	private int dir = r.nextInt(2);
	private int velocidadX = r.nextInt(15)+1;
	private int velocidadY = r.nextInt(15)+1;
	
	public particulasDeMenu(int x, int y, HandlerEnemigo handEnemigo, Handler hands) {
		super(x, y, ID.EnemigoRapido, handEnemigo, 2, hands);
		// TODO Auto-generated constructor stub
		if(dir == 0) {
			velX = velocidadX;
			velY = velocidadY;
		} else {
			velX = velocidadX*-1;
			velY = velocidadY*-1;
		}
		color = new Color(rojo, verde, azul);
	}
	
	public Rectangle perimetro() {
		return new Rectangle((int)x, (int)y, ancho, altura);
	}
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= JuegoBase.BASEALTURAHUD  || y >= JuegoBase.ALTO - altura - 21) velY *= -1; 
		if(x <= 0 || x >= JuegoBase.ANCHO - ancho- 5) velX *= -1; 
		
		handlerNormal.addObject(new cola(x, y, ID.Cola, color, ancho, altura, 0.1f, handlerNormal)); 
//		hands.addObject(new cola(x, y, ID.Cola, new Color(27, 27, 255), ancho, altura, 0.05f, hands)); 
	}
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(color);
		g2d.draw(perimetro());
		
	}
	
	public float haceDanio() {
		return 50;
	}
	
	public void desaparece() {
		handlerEnemigo.removeObject(this);
	}
	

}
