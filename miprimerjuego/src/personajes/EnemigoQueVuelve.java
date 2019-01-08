package personajes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import AyudaExtra.ayudaExtra;
import base.Handler;
import base.HandlerEnemigo;
import base.ID;
import base.JuegoBase;

public class EnemigoQueVuelve extends Enemigo {
	
	int altura = 20, ancho = 20;
	boolean entra;
	int maximoAAlcazar;
	Color color = new Color(136, 18, 203);
	private boolean[] direccion = new boolean[4];
	private Random r;
	
	public EnemigoQueVuelve(int x, int y, HandlerEnemigo handE, Handler hand, int maximo, String tipo) {
		super(x, y, ID.EnemigoQueVuelve, handE, 300, hand);
		// TODO Auto-generated constructor stub
		maximoAAlcazar = maximo;
		for(int i = 0; i < 4; i++) {
			direccion[i] = false;
		}
		if(tipo == "arriba") {
			direccion[0]  = true;
			velY = 7;
			velX = 0;
		} else if(tipo == "abajo"){
			direccion[1] = true;
			velY = -7;
			velX = 0;
		} else if(tipo == "izquierda"){
			velX = 7;
			velY = 0;
			direccion[2] = true;
		} else {
			velX = -7;
			velY = 0;
			direccion[3] = true;
		}
		entra = true;
	}

	
	
	@Override
	public float haceDanio() {
		// TODO Auto-generated method stub
		return danio;
	}

	@Override
	public void desaparece() {
		// TODO Auto-generated method stub
		handlerEnemigo.removeObject(this);
	}
	
	private void llegoAlMaximo() {
		if(direccion[0] == true) {
			if( y >= maximoAAlcazar) {
				velY *= -1;
				entra = false;
			}	
		}
		if(direccion[1] == true) {
			if(y <= maximoAAlcazar) {
				velY *= -1;
				entra = false;}
		}
		if(direccion[2] == true) {
			if(x >= maximoAAlcazar) {
				velX *= -1;
				entra = false;
			}
			
		}
		if(direccion[3] == true) {
			if(x <= maximoAAlcazar) {
				velX *= -1;
				entra = false;}
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x += velX;
		y += velY;
		
		if(entra) {
			llegoAlMaximo();
		} else {
			if(x <= 0 || x >= JuegoBase.ANCHO - ancho- 5) {
				desaparece();
			}
			if(y <= JuegoBase.BASEALTURAHUD  || y >= JuegoBase.ALTO - altura - 21) {
				desaparece();
			}
		}
		
		handlerNormal.addObject(new cola(x, y, ID.Cola, color, ancho, altura, 0.05f, handlerNormal)); 
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(color);
	}

	@Override
	public Rectangle perimetro() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int) y, ancho, altura);
	}

}
