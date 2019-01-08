package personajes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import HUD.HUDPrincipal;
import base.*;

public class basicEnemy extends Enemigo {
	
	int altura = 10;
	int ancho = 10;
	float velX, velY;
	public basicEnemy(int x,int y, HandlerEnemigo handE, Handler hands)
	{
		super(x, y, ID.BasicEnemy, handE, 5, hands);
		//, hand, 2, hands);
		// TODO Auto-generated constructor stub
		velX = 10;
		velY = 10;
	}
	
	public Rectangle perimetro() {
		return new Rectangle((int)x, (int) y, ancho, altura);
	}
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= JuegoBase.BASEALTURAHUD || y >= JuegoBase.ALTO - altura - 21) velY *= -1; 
		if(x <= 0 || x >= JuegoBase.ANCHO - ancho- 5) velX *= -1; 
	
		handlerNormal.addObject(new cola(x, y, ID.Cola, new Color(255, 27, 27), ancho, altura, 0.1f, handlerNormal)); 
	}
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(new Color(255, 27, 27));
		g2d.draw(perimetro());
		
	}
	
	public float haceDanio() {
		return danio;
	}
	
	public void desaparece() {
		handlerEnemigo.removeObject(this);
	}
	
}
