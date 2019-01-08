package personajes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import base.Handler;
import base.HandlerEnemigo;
import base.ID;
import base.JuegoBase;

public class EnemigoMuyDificil extends Enemigo {
	
	int altura = 10;
	int ancho = 10;
	float velX, velY;
	Random r = new Random();
	Color color = new Color(246, 255, 104);
	public EnemigoMuyDificil(int x,int y, HandlerEnemigo handE, Handler hands)
	{
		super(x, y, ID.EnemigoMuyDificil, handE, 50, hands);
		//, hand, 2, hands);
		// TODO Auto-generated constructor stub
		
		velX = r.nextInt(21);
		velY = r.nextInt(21);
	}
	
	public Rectangle perimetro() {
		return new Rectangle((int)x, (int) y, ancho, altura);
	}
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= JuegoBase.BASEALTURAHUD || y >= JuegoBase.ALTO - altura - 21) 
			{
				//SI LA VELOCIDAD Y ES POSITIVA, LA CAMBIO A LA NEGATIVA 
				if(velY > 0) {
					velY = r.nextInt(21)  * -1;
				} else {
					velY = r.nextInt(21);
				}
			} 
		if(x <= 0 || x >= JuegoBase.ANCHO - ancho- 5) {
			//SI LA VELOCIDAD Y ES POSITIVA, LA CAMBIO A LA NEGATIVA
			if(velX > 0) {
				velX = r.nextInt(21)  * -1;
			} else {
				velX = r.nextInt(21);
			}
		}
	
		handlerNormal.addObject(new cola(x, y, ID.Cola, color, ancho, altura, 0.1f, handlerNormal)); 
	}
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(color);
		g2d.draw(perimetro());
		
	}
	
	public float haceDanio() {
		return danio;
	}
	
	public void desaparece() {
		handlerEnemigo.removeObject(this);
	}
	
}
