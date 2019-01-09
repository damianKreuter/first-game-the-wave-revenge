package Jugador;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import AyudaExtra.ayudaExtra;
import HUD.HUDBARRA;
import HUD.HUDPrincipal;
import base.ID;
import base.JuegoBase;
import hojaDeSprites.Sprites;
import musicayefectosdesonido.audioplayer;
import personajes.Enemigo;
import personajes.GameObject;
import base.Handler;
import base.HandlerEnemigo;
import base.ID;

import java.awt.Color;

public class Player extends GameObject {

	Random r = new Random();
	Handler handler;
	HandlerEnemigo handlerEnemigo;
	
	private BufferedImage imagen_jugador;
	
	public boolean estaVivo;
	public String nombreJug;
	public boolean terminoScripMuerte;
	int tempMuerte = 50;
	
	Color color = Color.green;
	private int altura = 32, ancho = 32;
	Sprites spritehoja;
	
	public Player(float x, float y, ID id, Handler hand, HandlerEnemigo handEnemigo) {
		super(x, y, id);
		this.handler = hand;
		handlerEnemigo = handEnemigo;
		estaVivo = true;
		terminoScripMuerte = false;
		Sprites ss = new Sprites(JuegoBase.sprite_hoja);
		
		imagen_jugador = ss.darImagen(1, 3, ancho, altura);
	}
	
	public Rectangle perimetro() {
		return new Rectangle((int)x, (int)y, ancho, altura);
	}
	
	private void coalision() {
		Enemigo enemigo = tocoUnEnemigo();
		if(enemigo != null) {
			HUDPrincipal.VIDA -= enemigo.haceDanio();
	//		if(enemigo.haceDanio() > 0) {
				audioplayer.getSound("danioPersonaje").play();
	//		}
			
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
	
	public boolean haMuerto() {
		return terminoScripMuerte;
	}
	
	public void tick() {
		// TODO Auto-generated method stub
		if(estaVivo) {
			y += velY;
			x += velX;
			
			x = JuegoBase.clamp((int)x, 0, JuegoBase.ANCHO -37);
			y = JuegoBase.clamp((int)y, JuegoBase.BASEALTURAHUD, JuegoBase.ALTO -62);
			
			if(HUDPrincipal.VIDA <=0) {
				estaVivo = false;
			}
		} 
	}
	
	public void morir() {
//		handler.addObject(new ParticulasMuerte(x, y, 1, 1, handler));
//		handler.addObject(new ParticulasMuerte(x, y, 1, -1, handler));
//		handler.addObject(new ParticulasMuerte(x, y, -1, 1, handler));
//		handler.addObject(new ParticulasMuerte(x, y, -1, -1, handler));
//		while(!exiteExplosivosPosMorten()) {
			terminoScripMuerte = true;
//		}
		//handler.removeObject(this);
	}
	
	private boolean exiteExplosivosPosMorten() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.ParticulasMuerte) {
				return true;
			}
		}
		return false;
	}

	public void render(Graphics g) {
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(color);
//		g2d.draw(perimetro());
		
		
		
		g.drawImage(imagen_jugador, (int)x, (int)y, null);
		coalision();
		// TODO Auto-generated method stub
	//	if(id == ID.Player) g.setColor(Color.red);
	//	g.fillRect(x, y, ancho, altura);
	}
}
