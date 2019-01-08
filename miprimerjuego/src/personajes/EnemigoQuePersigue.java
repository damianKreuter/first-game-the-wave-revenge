package personajes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import AyudaExtra.ayudaExtra;
import base.Handler;
import base.HandlerEnemigo;
import base.ID;
import base.JuegoBase;

public class EnemigoQuePersigue extends Enemigo {
	
	int altura = 15;
	int ancho = 15;
	float velX, velY;
	
	private Color colorLuminico = new Color(255, 255, 135);
	private Color colorBase = new Color(231, 231, 42);
	private int vida; // MEDIDAD EN SEGUNDOS
	private GameObject player;
	
	public EnemigoQuePersigue(int x, int y, HandlerEnemigo handEnemigo, Handler hands) {
		super(x, y, ID.EnemigoQuePersigue, handEnemigo, 500, hands);
		
		for(int i = 0; i < handlerNormal.object.size(); i++) {
			if(handlerNormal.object.get(i).getId() == ID.Player) {
				player = handlerNormal.object.get(i);
			}
		}
		vida = 7;
	}

	
	public Rectangle perimetro() {
		return new Rectangle((int)x, (int)y, ancho, altura);
	}

	public void tick() {
		
		velX = ayudaExtra.vectorXHaciaPersonaje(player.getX(), player.getY(), x, y);
		velY = ayudaExtra.vectorYHaciaPersonaje(player.getX(), player.getY(), x, y);
		
		x += velX;
		y += velY;
		
		
		if(y <= JuegoBase.BASEALTURAHUD  || y >= JuegoBase.ALTO - altura - 21) velY *= -1; 
		if(x <= 0 || x >= JuegoBase.ANCHO - ancho- 5) velX *= -1; 
		
		handlerNormal.addObject(new cola(x, y, ID.Cola, new Color(191, 191, 23), ancho, altura, 0.03f, handlerNormal)); 
//		hands.addObject(new cola(x, y, ID.Cola, new Color(27, 27, 255), ancho, altura, 0.05f, hands)); 
	}
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(colorBase);
		g2d.draw(perimetro());
		
	}
	
	public float haceDanio() {
		return danio;
	}
	
	public void desaparece() {
//		handlerEnemigo.removeObject(this);
	}
	
}
