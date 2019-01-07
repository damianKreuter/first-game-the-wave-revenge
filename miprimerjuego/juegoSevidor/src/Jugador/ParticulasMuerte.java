package Jugador;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import base.Handler;
import base.ID;
import personajes.GameObject;

public class ParticulasMuerte extends GameObject {

	Random r = new Random();
	Handler handler;
	int tempMuerte = 100;
	
	Color color = Color.green;
	private int altura = 32, ancho = 32;
	private int radio;
	float transparencia;
	
	public ParticulasMuerte(float x, float y, float velX, float velY, Handler hand) {
		super(x, y, ID.ParticulasMuerte);
		this.handler = hand;
		radio = 5;
		// TODO Auto-generated constructor stub
		this.velX = 5*velX;
		this.velY = 5*velY;
		transparencia = 1.0f;
		color = new Color(55, 250, 55);
	}
	public Rectangle perimetro() {
		return new Rectangle((int)x, (int)y, ancho, altura);
	}

	public void tick() {
		// TODO Auto-generated method stub
		/*
		if(tempMuerte >= 0) {
			y += velY;
			x += velX;
			tempMuerte--;
			transparencia -= 0.02f;
		} else {
			morir();
		}
		*/
	}
	
	private void morir() {
		handler.removeObject(this);
	}

	public void render(Graphics g) {
		/*
		g.setColor(color);
		int xx, yy;
		xx = (int) (x-(radio/2));
		yy = (int) (y-(radio/2));
		g.fillOval(xx,yy,radio,radio);
		*/
		// TODO Auto-generated method stub
	//	if(id == ID.Player) g.setColor(Color.red);
	//	g.fillRect(x, y, ancho, altura);
	}
}