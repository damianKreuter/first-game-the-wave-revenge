package base;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Jugador.Player;
import Paquete.PaqueteMovimientoJugador;
import personajes.GameObject;

public class keyinput extends KeyAdapter {
	private Handler handler;
	
	private boolean aIzquierda = false;
	private boolean aDerecha = false;
	private boolean aArriba = false;
	private boolean aAbajo = false;
	private JuegoBase juego;
	private PaqueteMovimientoJugador packetmov;
	
	public keyinput(Handler newhandler, JuegoBase juego) {
		this.handler = newhandler;
		this.juego = juego;
		packetmov = new PaqueteMovimientoJugador(juego.nombreSistema);
	}
	
	private boolean esJugadorDeEstaApp(Player tempObject) {
		return tempObject.nombreJug == juego.nombreSistema;
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//This gets the ASCII's code from every key pressed
//		System.out.println(key);
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//KEY EVENTS FOR PLAYER 1VK
				int velocidadExtra = 10;
				int velocidadInversa = velocidadExtra *-1;
				
				if(key == KeyEvent.VK_UP) {
					if(aAbajo) {
						aAbajo = false;
					}
					juego.jugador.setVelY(velocidadInversa);
					if(juego.nombreUserMP != null) {
						packetmov.mandarDatos(juego.socketClie, "arriba");
					}
			//		tempObject.setVelY(velocidadInversa);
					aArriba = true;
					
				}
				if(key == KeyEvent.VK_DOWN)
				{
					if(aArriba) {
						aArriba = false;
					}
					juego.jugador.setVelY(velocidadExtra);
					if(juego.nombreUserMP != null) {
						packetmov.mandarDatos(juego.socketClie, "abajo");
					}
			//		tempObject.setVelY(velocidadExtra);
					aAbajo = true;
				}
				if(key == KeyEvent.VK_LEFT) {
					if(aDerecha) {
						aDerecha = false;
					}
					juego.jugador.setVelX(velocidadInversa);
					if(juego.nombreUserMP != null) {
						packetmov.mandarDatos(juego.socketClie, "izquierda");
					}
		//			tempObject.setVelX(velocidadInversa);
					aIzquierda = true;
				}
				if(key == KeyEvent.VK_RIGHT) {
					if(aIzquierda) {
						aIzquierda = false;
					}
					juego.jugador.setVelX(velocidadExtra);
					if(juego.nombreUserMP != null) {
						packetmov.mandarDatos(juego.socketClie, "derecha");
					}
					
			//		tempObject.setVelX(velocidadExtra);
					aDerecha = true;
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//KEY EVENTS FOR PLAYER 1VK
				if(key == KeyEvent.VK_UP && aArriba == true) {
					juego.jugador.setVelY(0);
					if(juego.nombreUserMP != null) {
					packetmov.mandarDatos(juego.socketClie, "vertical0");
					}
				}
				if(key == KeyEvent.VK_DOWN && aAbajo == true) {
					juego.jugador.setVelY(0);
					if(juego.nombreUserMP != null) {
					packetmov.mandarDatos(juego.socketClie, "vertical0");
					}
				}
				if(key == KeyEvent.VK_LEFT && aIzquierda == true) {
					juego.jugador.setVelX(0);
					if(juego.nombreUserMP != null) {
					packetmov.mandarDatos(juego.socketClie, "horizontal0");
					}
				}
				if(key == KeyEvent.VK_RIGHT && aDerecha == true) {
					juego.jugador.setVelX(0);
					if(juego.nombreUserMP != null) {
					packetmov.mandarDatos(juego.socketClie, "horizontal0");
					}
				}
				
				/*
				if(key == KeyEvent.VK_W) tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) tempObject.setVelY(0);
				if(key == KeyEvent.VK_A) tempObject.setVelX(0);
				if(key == KeyEvent.VK_D) tempObject.setVelX(0);
			*/
			}
		}
		
		
	}
	
	
}
