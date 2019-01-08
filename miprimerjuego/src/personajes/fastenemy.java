package personajes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import HUD.HUDPrincipal;
import base.*;

public class fastenemy extends Enemigo {
	
	int altura = 10;
	int ancho = 10;
	int velX, velY;
	
	public fastenemy(int x, int y, HandlerEnemigo handEnemigo, Handler hands) {
		super(x, y, ID.EnemigoRapido, handEnemigo, 2, hands);
		// TODO Auto-generated constructor stub
		velX = 3;
		velY = 20;
	}
	
	public Rectangle perimetro() {
		return new Rectangle((int)x,(int) y, ancho, altura);
	}
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= JuegoBase.BASEALTURAHUD  || y >= JuegoBase.ALTO - altura - 21) velY *= -1; 
		if(x <= 0 || x >= JuegoBase.ANCHO - ancho- 5) velX *= -1; 
		
		handlerNormal.addObject(new cola(x, y, ID.Cola, new Color(27, 27, 255), ancho, altura, 0.03f, handlerNormal)); 
//		hands.addObject(new cola(x, y, ID.Cola, new Color(27, 27, 255), ancho, altura, 0.05f, hands)); 
	}
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(new Color(27, 27, 255));
		g2d.draw(perimetro());
		
	}
	
	public float haceDanio() {
		return 50;
	}
	
	public void desaparece() {
		handlerEnemigo.removeObject(this);
	}
	
}

