package Spawn;

import java.awt.Color;
import java.util.Random;

import AyudaExtra.ayudaExtra;
import HUD.HUDPrincipal;
import Jefes.JefeComun;
import Jefes.proyectilesJefe;
import Menu.Menu2;
import base.Handler;
import base.HandlerEnemigo;
import base.ID;
import base.JuegoBase;
import personajes.EnemigoQuePersigue;
import personajes.EnemigoQueVuelve;
import personajes.GameObject;
import personajes.Player;
import personajes.basicEnemy;
import personajes.enemigoBorde;
import personajes.fastenemy;
import personajes.laser;

public class Spawn {
	
	private HandlerEnemigo handlerEnemigo;
	private HUDPrincipal HUD;
	private Handler handler;
	
	private Random r = new Random();
	private boolean salioJefe;
	public static boolean hayRayoDisponible;
	
	private GameObject player;
	private Menu2 menu;
	private boolean hayJugador;
	
	public Spawn(HandlerEnemigo handler, HUDPrincipal h, Handler hh, Menu2 menu) {
		this.handlerEnemigo = handler;
		this.handler = hh;
		HUD = h;
		
		hayJugador = false;
		salioJefe = false;
		hayRayoDisponible = true;
		this.menu = menu;
		
		for(int i = 0; i < hh.object.size(); i++) {
			if(hh.object.get(i).getId() == ID.Player) {
				player = hh.object.get(i);
			}
		}
		
	}
	
	public int noEnHUB() {
		int xselecionado = r.nextInt(JuegoBase.ALTO);
		while(xselecionado <= JuegoBase.BASEALTURAHUD) {
			xselecionado++;
		}
		return xselecionado;
	}
	
	private void buscarJugador() {
		if(!hayJugador) {
			for(int i = 0; i < menu.darHandler().object.size(); i++) {
				if(menu.darHandler().object.get(i).getId() == ID.Player) {
					player = menu.darHandler().object.get(i);
					hayJugador = true;
				}
			}
		}
	}
	
	public void noHayJugador() {
		hayJugador = false;
	}
	
	public void tick() {
		buscarJugador();
		/*
		if(HUD.getPuntaje() >= 100*HUD.getNivel() && HUD.getNivel() < 4) {
			HUD.setNivel(HUD.getNivel()+1);
			crearEnemigoQueVuelve(handlerEnemigo, handler);
			
			handlerEnemigo.addObject(new EnemigoQuePersigue(r.nextInt(JuegoBase.ANCHO), r.nextInt(JuegoBase.ALTO), handlerEnemigo, handler));
	//		handlerEnemigo.addObject(new basicEnemy(r.nextInt(JuegoBase.ANCHO), r.nextInt(JuegoBase.ALTO), ID.BasicEnemy, handlerEnemigo, handler));
	//		handlerEnemigo.addObject(new basicEnemy(r.nextInt(JuegoBase.ANCHO), noEnHUB(), handlerEnemigo, handler));
	//		handlerEnemigo.addObject(new fastenemy(r.nextInt(JuegoBase.ANCHO), noEnHUB(), handlerEnemigo, handler));
		}
		if(HUD.getNivel() == 4 && HUD.getPuntaje() >= 100*HUD.getNivel()) {
			HUD.setNivel(HUD.getNivel()+1);
			crearLaser();
	//		crearEnemigoQueVuelve(handlerEnemigo, handler);
			
			handlerEnemigo.addObject(new fastenemy(r.nextInt(JuegoBase.ANCHO), noEnHUB(), handlerEnemigo, handler));
			
			//HORIZONTAL ABAJO
			handlerEnemigo.addObject(new enemigoBorde(JuegoBase.ANCHO-30, JuegoBase.ALTO-35, 'H', 'B', handlerEnemigo, handler));	
					
			//HORIZONTAL ARRIBA
			handlerEnemigo.addObject(new enemigoBorde(0, JuegoBase.BASEALTURAHUD, 'H',  'A',handlerEnemigo, handler));	
							
			//LATERAL IZQUIERDA
			handlerEnemigo.addObject(new enemigoBorde(0, JuegoBase.BASEALTURAHUD, 'L', 'A', handlerEnemigo, handler));	
					
			//LATERAL DERECHA
			handlerEnemigo.addObject(new enemigoBorde(JuegoBase.ANCHO-15, JuegoBase.ALTURAJUEGO-50, 'L', 'B', handlerEnemigo, handler));
			
		}
		*/
		
		
		if(HUD.getNivel() == 1 && salioJefe == false) {
			
			handlerEnemigo.addObject(new JefeComun(-80, JuegoBase.BASEALTURAHUD +20, handlerEnemigo, handler, menu.hudPrincipalMenu(), this));
			salioJefe = true;
			handlerEnemigo.addObject(new EnemigoQuePersigue(r.nextInt(JuegoBase.ANCHO), r.nextInt(JuegoBase.ALTO), handlerEnemigo, handler));
		}
		if(hayRayoDisponible && salioJefe == true) {
			crearLaserInteligente();
		}
		HUD.setPuntaje(HUD.getPuntaje()+1);
	}
	
	
	
	
	
	public void crearEnemigoQueVuelve(HandlerEnemigo handE, Handler hand) {
		int xSeleted, ySelected;
		int result = ayudaExtra.numeroRandomInt(0, 4);
		String tipo;
		//ESTO ES PARA OBTENER POR DONDE SE ORIGINARÁ
		if(result == 0) {
			tipo = "arriba";
		} else if(result ==1) {
			tipo = "abajo";
		} else if(result == 2) {
			tipo = "izquierda";
		} else {tipo = "derecha";}
	
		//AHORA GENERO LA UBICACION A PARTIR DEL TIPO D ELUGAR QUE SALIERON
		int maximo;
		//EMPIEZO CON LA HORIZONTAL QUE ES MAS FÁCIL QUE LA VERTICAL
		if(result <= 1) {
			//ESTE SALTA DE FORMA VERTICAL
			xSeleted = ayudaExtra.numeroRandomInt(20, JuegoBase.ANCHO - 35);
			maximo = JuegoBase.ALTURAJUEGO/2 + JuegoBase.BASEALTURAHUD;
			if(result == 0) {
				ySelected = JuegoBase.BASEALTURAHUD;
			} else {
				ySelected = JuegoBase.ALTO - 53;
			}
			
		} else {
			//ESTE SALTA DE FORMA HORIZONTAL
			ySelected = ayudaExtra.numeroRandomInt(JuegoBase.BASEALTURAHUD+20, JuegoBase.ALTO - 45);
			maximo = JuegoBase.ANCHO/2;
			if(result == 2) {
				xSeleted = 0;
			} else {
				xSeleted = JuegoBase.ANCHO-30;
				
			}
		}
		/*
		tipo = "arriba";
		xSeleted = ayudaExtra.numeroRandomInt(20, JuegoBase.ANCHO - 35);
		maximo = JuegoBase.ALTURAJUEGO/2 + JuegoBase.BASEALTURAHUD;
		ySelected = JuegoBase.BASEALTURAHUD;
		*/
		handlerEnemigo.addObject(new EnemigoQueVuelve(xSeleted, ySelected, handE, hand, maximo, tipo));
	}
	
	
	public void crearLaser() {
		hayRayoDisponible = false;
		Random r = new Random();
		int numeroRandom = r.nextInt(2);
		if(numeroRandom == 0 ) {
			//ES RAYO HORIZONTAL, O SEA, MANTIENE LA MISMA Y
			numeroRandom = ayudaExtra.numeroRandomInt(JuegoBase.BASEALTURAHUD, JuegoBase.ALTO);
			handlerEnemigo.addObject(new laser(0, numeroRandom, handlerEnemigo, handler, 'H'));
		} else {
			//ES RAYO VERTICAL, O SEA, MANTIENE LA MISMA X
			numeroRandom = ayudaExtra.numeroRandomInt(0, JuegoBase.ANCHO);
			handlerEnemigo.addObject(new laser(numeroRandom, 0, handlerEnemigo, handler, 'V'));
		}
	}
	
	public void crearLaserInteligente() {
		//LA DIFERENCIA ENTRE ESTE RAYO Y EL ANTERIOR ES QUE ESTE APARECERÁ CERCA DE LAS COORDENADAS
		//DEL JUGADOR
		hayRayoDisponible = false;

		float xJugador = player.getX();
		float yJugador = player.getY();
		
		Random r = new Random();
		//0 HORIZONTAL--- 1 VERTICAL
		int numeroRandom = r.nextInt(2);
		int posicionAleatoriaCercana = r.nextInt(11);
		if(numeroRandom == 0 ) {
			//ES RAYO HORIZONTAL, O SEA, MANTIENE LA MISMA X
			numeroRandom = r.nextInt(2);
			//SI ES 0 ENTONCE SE RESTA, SI ES 1 SE SUMA
			if(numeroRandom == 0) {
				yJugador -= (float)posicionAleatoriaCercana;
			} else {
				yJugador += (float)posicionAleatoriaCercana;
			}
			handlerEnemigo.addObject(new laser(0, (int)yJugador, handlerEnemigo, handler, 'H'));
		} else {
			//ES RAYO VERTICAL, O SEA, MANTIENE LA MISMA Y
			numeroRandom = r.nextInt(2);
			//SI ES 0 ENTONCE SE RESTA, SI ES 1 SE SUMA
			if(numeroRandom == 0) {
				xJugador -= (float)posicionAleatoriaCercana;
			} else {
				xJugador += (float)posicionAleatoriaCercana;
			}
			handlerEnemigo.addObject(new laser((int)xJugador, 0, handlerEnemigo, handler, 'V'));
		}
	}
	
	public void crearProyectilesDeJefeComun(boolean tieneVidaALaMitad) {
		GameObject jefe = obtenerJefe();
		float xJugador = player.getX();
		float yJugador = player.getY();
		handlerEnemigo.addObject(new proyectilesJefe(jefe.getX(), jefe.getY(), handlerEnemigo, handler, tieneVidaALaMitad, player, jefe));
	}
	
	public HandlerEnemigo obtenerHandlerEnemigo() {
		return handlerEnemigo;
	}
	
	private GameObject obtenerJefe() {
		GameObject pp = null;
		for(int i = 0; i < handlerEnemigo.object.size(); i++) {
			if(handlerEnemigo.object.get(i).getId() == ID.JefeComun) {
				pp = handlerEnemigo.object.get(i);
			}
		}
		return pp;
	}
}
