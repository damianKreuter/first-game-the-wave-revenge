package Jefes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import AyudaExtra.ayudaExtra;
import HUD.HUDPrincipal;
import Menu.Menu2;
import Spawn.Spawn;
import base.Handler;
import base.HandlerEnemigo;
import base.ID;
import base.JuegoBase;
import musicayefectosdesonido.audioplayer;
import personajes.Enemigo;
import personajes.GameObject;

public class JefeComun extends Enemigo {
	int altura = 80;
	int ancho = 80;
	long vidaTotal;
	float velX, velY;
	boolean enCombate, vidaALaMitad;
	int velocidad;
	private Spawn spawn;
	private int tiempoAEsperar;
	private audioplayer audio;
	
	public JefeComun(int x,int y, HandlerEnemigo handE, Handler hands, HUDPrincipal hud, Spawn ss, audioplayer audio)
	{
		super(x, y, ID.JefeComun, handE, 30000, hands);
		//, hand, 2, hands);
		// TODO Auto-generated constructor stub
		velocidad = 5;
		velX = velocidad;
		velY = 0;
		enCombate= false;
		vidaTotal = 5000;
		spawn = ss;
		hud.ponerVidaJefe(vidaTotal);
		tiempoAEsperar = 0;
		this.audio = audio;
	}
	
	public Rectangle perimetro() {
		return new Rectangle((int)x, (int) y, ancho, altura);
	}
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(x > 40) {
			enCombate = true;
		}
		
		if(enCombate) {
			if(!tieneVidaALaMitad()) {
				if(x <= 20 || x >= JuegoBase.ANCHO - ancho- 25) {
					velX *=-1;
				}
				atacar();
				
		//		velY = 0;
			}  else {
				velocidad = 10;
				atacar();
				//SI LLEGA A UNA ESQUINA, SE FIJA SI SE MOVIA HORIZONTAL O VERTICAL PRIMERO
				//LUEGO ANALIZA SI ESTA EN LA PARTE SUPERIOR, INFERIOR, DERECHA O IZQUIERDA
				//Y REALIZA EL CAMBIO DE DIRECCIÓN
				if(velY == 0) {
					if(x <= 20 || x >= JuegoBase.ANCHO - ancho- 45) {
						if(y > 100) {
							velY = velocidad*(-1);
							velX = 0;
						}
						if(y < 100) {
							velY = velocidad;
							velX = 0;
						}
					}
				} else if(velX == 0) {
					if(y <= JuegoBase.BASEALTURAHUD + 20 || y >= JuegoBase.ALTO - altura- 80) {
						if(x > 100) {
							velX = velocidad*(-1);
							velY = 0;
						}
						if(x < 100) {
							velX = velocidad;
							velY = 0;
						}
					}
				}
			}
		}
		tocoLaser();
	}
	
	private void atacar() {
		if(disparoProyectiles() == false) {
			for(int i = 0; i < 10; i++) {
				spawn.crearProyectilesDeJefeComun(tieneVidaALaMitad());
			}
		}
		
	}
	
	private boolean disparoProyectiles() {
		if(tiempoAEsperar > 0) {
			tiempoAEsperar--;
			return true;
		}
		tiempoAEsperar = 60;
		if(tieneVidaALaMitad()) {
			tiempoAEsperar = 30;
		}
		return false;
		/*
		for(int i = 0; i < spawn.obtenerHandlerEnemigo().object.size(); i++) {
			if(spawn.obtenerHandlerEnemigo().object.get(i).getId() == ID.ProyectilesJefe) {
				return true;
			}
		}
		return false;
		*/
	}
	
	private boolean tieneVidaALaMitad() {
		return HUDPrincipal.vidaJefeHUD < mediaVida();
	}
	
	private int mediaVida() {
		return (int) (vidaTotal/2);
	}
	
	private void tocoLaser() {
		for(int i = 0; i < handlerEnemigo.object.size(); i++) {
			Enemigo tempObject = handlerEnemigo.object.get(i);
			if(tempObject.getId() == ID.Laser) {
				if(perimetro().intersects(tempObject.perimetro())) {
					//HACE COALISION POR LO TANTO DAÑO
					HUDPrincipal.vidaJefeHUD -= tempObject.haceDanio();
					audioplayer.getSound("danioJefe").play();
				}
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(new Color(255, 27, 27));
		g2d.draw(perimetro());
		g.setColor(new Color(255, 27, 27));
		g.fillRect((int)x, (int)y, (int)ancho, (int)altura);
		
	}
	
	public float haceDanio() {
		return danio;
	}
	
	public void desaparece() {
//		handlerEnemigo.removeObject(this);
	}
}
