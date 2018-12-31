package personajes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import AyudaExtra.ayudaExtra;
import HUD.HUDBARRA;
import HUD.HUDPrincipal;
import base.ID;
import base.JuegoBase;
import base.Handler;
import base.HandlerEnemigo;
import base.ID;

import java.awt.Color;

public class Player extends GameObject {

	Random r = new Random();
	Handler handler;
	HandlerEnemigo handlerEnemigo;
	
	Color color = Color.green;
	private int altura = 32, ancho = 32;
	
	public Player(float x, float y, ID id, Handler hand, HandlerEnemigo handEnemigo) {
		super(x, y, id);
		this.handler = hand;
		handlerEnemigo = handEnemigo;
		// TODO Auto-generated constructor stub
	//	velX = r.nextInt(5) +1;
	//	velY = r.nextInt(5) +1;
	}
	public Rectangle perimetro() {
		return new Rectangle((int)x, (int)y, ancho, altura);
	}
	
	private void coalision() {
		Enemigo enemigo = tocoUnEnemigo();
		if(enemigo != null) {
			HUDPrincipal.VIDA -= enemigo.haceDanio();
			enemigo.desaparece();
		}
	}
	
	private Enemigo tocoUnEnemigo() {
		for(int i = 0; i < handlerEnemigo.object.size(); i++) {
			Enemigo tempObject = handlerEnemigo.object.get(i);
			if(ayudaExtra.esUnEnemigo(tempObject)) {
				if(perimetro().intersects(tempObject.perimetro())) {
					//HACE COALISION
					return tempObject;
				}
			}
		}
		return null;
	}
	
	public void tick() {
		// TODO Auto-generated method stub
		y += velY;
		x += velX;
		
		x = JuegoBase.clamp((int)x, 0, JuegoBase.ANCHO -37);
		y = JuegoBase.clamp((int)y, JuegoBase.BASEALTURAHUD, JuegoBase.ALTO -62);
		

	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(color);
		g2d.draw(perimetro());
		coalision();
		// TODO Auto-generated method stub
	//	if(id == ID.Player) g.setColor(Color.red);
	//	g.fillRect(x, y, ancho, altura);
	}
}
