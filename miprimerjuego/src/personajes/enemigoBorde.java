package personajes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import base.Handler;
import base.HandlerEnemigo;
import base.ID;
import base.JuegoBase;

public class enemigoBorde extends Enemigo {

	char tipoEnemigo; 			//H -> HORIZONTAL       	L -> LATERAL
	char ubicacionEnemigo; 		//A -> ARRIBA O IZQUIERDA 	B -> ABAJO O DERECHA
	int ancho, altura;
	
	public enemigoBorde(float x, float y, char tipo, char ubicacion, HandlerEnemigo handler, Handler hand) {
		super(x, y, ID.EnemigoBorde, handler, 5, hand);
		tipoEnemigo = tipo;
		ubicacionEnemigo = ubicacion;
		if(tipoEnemigo == 'H') {
			ancho = 25;
			altura =10;
			velX =25;
		} else {
			velY =25;
			altura = 25;
			ancho =10;
		}
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
	//	x += velX;
	//	y += velY;
		
		if(tipoEnemigo == 'H' && ubicacionEnemigo == 'A') {
			//BARA DSOLO HORIZONTAL ARRIBA
			
			x += velX;
			if(x <= 0 || x >= JuegoBase.ANCHO - ancho- 5) velX *= -1; 
		}
		if(tipoEnemigo == 'L' && ubicacionEnemigo == 'A') {
			//BARRA SOLO LATERAL IZQUIERDA
			
			y += velY;
			if(y <= JuegoBase.BASEALTURAHUD  || y >= JuegoBase.ALTO - altura - 21) velY *= -1; 
		}
		if(tipoEnemigo == 'H' && ubicacionEnemigo == 'B') {
			//BARA DSOLO HORIZONTAL ARRIBA
			
			x -= velX;
			if(x <= 0 || x >= JuegoBase.ANCHO - ancho- 5) velX *= -1; 
		}
		if(tipoEnemigo == 'L' && ubicacionEnemigo == 'B') {
			//BARA DSOLO HORIZONTAL ARRIBA
			
			y -= velY;
			if(y <= JuegoBase.BASEALTURAHUD  || y >= JuegoBase.ALTO - altura - 21) velY *= -1; 
		}
		
		

		handlerNormal.addObject(new cola(x, y, ID.Cola, new Color(110, 151, 255), ancho, altura, 0.1f, handlerNormal)); 
	}
	@Override
	public void render(Graphics g1) {
		// TODO Auto-generated method stub
		g1.setColor(new Color(110, 151, 255));
		/*
		//BORDE
		if(tipoEnemigo == 'H') {
			if(ubicacionEnemigo == 'A') {
		//		g.fillRect(0,  0,  ancho, altura);
			} else {
		//		g.fillRect(JuegoBase.ANCHO-30,  JuegoBase.ALTO-35,  ancho, altura);
			}
		} else {
			if(ubicacionEnemigo == 'A') {
		//		g.fillRect(0,  0,  ancho, altura);
			} else {
		//		g.fillRect(JuegoBase.ANCHO-15,  JuegoBase.ALTO-50,  ancho, altura);
			}
		}*/
	}
	
	@Override
	public Rectangle perimetro() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, ancho, altura);
	}
	@Override
	public void desaparece() {
		return;
	}

	@Override
	public float haceDanio() {
		// TODO Auto-generated method stub
		return danio;
	}

}
