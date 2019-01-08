package Spawn;

import java.awt.Color;
import java.util.Random;

import AyudaExtra.ayudaExtra;
import HUD.HUDPrincipal;
import Jefes.JefeComun;
import Jefes.proyectilesJefe;
import Jugador.Player;
import Menu.Menu2;
import base.Handler;
import base.HandlerEnemigo;
import base.ID;
import base.JuegoBase;
import musicayefectosdesonido.audioplayer;
import personajes.Enemigo;
import personajes.EnemigoMuyDificil;
import personajes.EnemigoQuePersigue;
import personajes.EnemigoQueVuelve;
import personajes.GameObject;
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
	private boolean hayJugador, hayEnemigoBorde;
	
	private int enemigosComunes, enemigoRapido, enemigoInteligente, enemigosQueVuelven, cantidadRayos;
	
	private audioplayer audio;
	private JuegoBase juego;
	
	public Spawn(HandlerEnemigo handler, HUDPrincipal h, Handler hh, Menu2 menu, audioplayer audio, JuegoBase juegoBase) {
		this.handlerEnemigo = handler;
		this.handler = hh;
		HUD = h;
		
		juego = juegoBase;
		this.audio = audio;
		hayJugador = false;
		salioJefe = false;
		hayRayoDisponible = true;
		this.menu = menu;
		hayEnemigoBorde = false;
		for(int i = 0; i < hh.object.size(); i++) {
			if(hh.object.get(i).getId() == ID.Player) {
				player = hh.object.get(i);
			}
		}
		
		enemigoInteligente = 0;
		cantidadRayos = 0;
		enemigosQueVuelven = 0;
		enemigosComunes = 0;
		enemigoRapido = 0;
		enemigoInteligente = 0;
		cantidadRayos = 0;
		
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
	
	private void cambiarCantidadDeEnemigos() {
		int nivel = HUD.getNivel();
		if(HUD.getNivel() == 1) {
			enemigosComunes = nivel;
			enemigosQueVuelven = nivel;
			
			cantidadRayos = nivel;
			hayRayoDisponible = true;
			
		}
		if(HUD.getNivel() == 2) {
			enemigosComunes = nivel;
			enemigosQueVuelven = nivel;
			cantidadRayos = 1;
		}
		if(HUD.getNivel() == 3) {
			enemigosComunes = nivel;
			cantidadRayos = 2;
		}
		if(HUD.getNivel() == 4) {
			enemigosComunes = 5;
			enemigoRapido = 2;
			enemigosQueVuelven = 4;
			
		}
		if(HUD.getNivel() == 5) {
			enemigosComunes = 6;
			enemigoRapido = 4;
			enemigosQueVuelven = 5;
			cantidadRayos = 4;
		}
		if(HUD.getNivel() == 6) {
			enemigosComunes = 7;
			enemigoRapido = 5;
			enemigosQueVuelven = 6;
			enemigoInteligente = 2;
		}
		if(HUD.getNivel() == 7) {
			enemigosComunes = 8;
			enemigoRapido = 5;
			enemigosQueVuelven = 8;
			enemigoInteligente = 3;
			
		}
		if(HUD.getNivel() == 8) {
			enemigosComunes = 10;
			enemigoRapido = 6;
			enemigosQueVuelven = 10;
			enemigoInteligente = 4;
			cantidadRayos = 5;
		}
		if(HUD.getNivel() == 9) {
			enemigosComunes = 10;
			enemigoRapido = 6;
			enemigosQueVuelven = 10;
			enemigoInteligente = 6;
			cantidadRayos = 6;
		}
	}
	
	private void cargarEnemigosBasico() {
		if(enemigosComunes != 0) {
			int cantidad = ayudaExtra.enemigoEsDeID(handlerEnemigo, ID.BasicEnemy);
			while(cantidad < enemigosComunes) {
				handlerEnemigo.addObject(new basicEnemy(r.nextInt(JuegoBase.ANCHO), noEnHUB(), handlerEnemigo, handler));
				cantidad++;
			}
		}
	}
	
	private void cargarEnemigosRapidos() {
		if(enemigoRapido != 0) {
			int cantidad = ayudaExtra.enemigoEsDeID(handlerEnemigo, ID.EnemigoRapido);
			while(cantidad < enemigoRapido) {
				crearEnemigoRapido();
				cantidad++;
			}
		}
	}
	
	private void cargarEnemigosInteligentes() {
		if(enemigoInteligente != 0) {
			int cantidad = ayudaExtra.enemigoEsDeID(handlerEnemigo, ID.EnemigoMuyDificil);
			while(cantidad < enemigoInteligente) {
				handlerEnemigo.addObject(new EnemigoMuyDificil(r.nextInt(JuegoBase.ANCHO), noEnHUB(), handlerEnemigo, handler));
				cantidad++;
			}
		}
	}
	
	private void cargarEnemigosRayo() {
		if(cantidadRayos != 0) {
			int cantidad = ayudaExtra.enemigoEsDeID(handlerEnemigo, ID.Laser);
			while(cantidad < cantidadRayos) {
				crearLaserInteligente();
			}
		}
	}
	
	
	public void tick() {
		buscarJugador();
		cambiarCantidadDeEnemigos();
		cargarEnemigos();

		//NIVEL FINAL
		if(HUD.getNivel() == 10 && salioJefe == false) {
			enemigoInteligente = 0;
			cantidadRayos = 0;
			enemigosQueVuelven = 0;
			enemigosComunes = 0;
			enemigoRapido = 0;
			enemigoInteligente = 0;
			cantidadRayos = 0;
			eliminarEnemigosQueNoPersiguen();
			handlerEnemigo.addObject(new JefeComun(-80, JuegoBase.BASEALTURAHUD +20, handlerEnemigo, handler, menu.hudPrincipalMenu(), this, audio));
			salioJefe = true;
			
	//		handlerEnemigo.addObject(new EnemigoQuePersigue(r.nextInt(JuegoBase.ANCHO), r.nextInt(JuegoBase.ALTO), handlerEnemigo, handler));
		}
		if(hayRayoDisponible && salioJefe == true) {
			crearLaserInteligente();
		}
		HUD.setPuntaje(HUD.getPuntaje()+1);
	}

	private void cargarEnemigos() {
		cargarEnemigosBasico();
		cargarEnemigosRapidos();
		cargarEnemigosQueVuelve();
		cargarEnemigosInteligentes();
//		cargarEnemigosRayo();
		
	}
	
	private void cargarEnemigosQueVuelve() {
		if(enemigosComunes != 0) {
			int cantidad = ayudaExtra.enemigoEsDeID(handlerEnemigo, ID.EnemigoQueVuelve);
			while(cantidad < enemigosComunes) {
				crearEnemigoQueVuelve();
				cantidad++;
			}
		}
	}
	
	private void crearEnemigoRapido() {
		
		//falta el tema la cantidad
		handlerEnemigo.addObject(new fastenemy(r.nextInt(JuegoBase.ANCHO), noEnHUB(), handlerEnemigo, handler));
	}
	
	private void crearEnemigoBorde() {
		if(!hayEnemigoBorde) {
			//HORIZONTAL ABAJO
			handlerEnemigo.addObject(new enemigoBorde(JuegoBase.ANCHO-30, JuegoBase.ALTO-35, 'H', 'B', handlerEnemigo, handler));	
					
			//HORIZONTAL ARRIBA
			handlerEnemigo.addObject(new enemigoBorde(0, JuegoBase.BASEALTURAHUD, 'H',  'A',handlerEnemigo, handler));	
							
			//LATERAL IZQUIERDA
			handlerEnemigo.addObject(new enemigoBorde(0, JuegoBase.BASEALTURAHUD, 'L', 'A', handlerEnemigo, handler));	
					
			//LATERAL DERECHA
			handlerEnemigo.addObject(new enemigoBorde(JuegoBase.ANCHO-15, JuegoBase.ALTURAJUEGO-50, 'L', 'B', handlerEnemigo, handler));
			
			hayEnemigoBorde = true;
		}
	}
	
	private void eliminarEnemigosQueNoPersiguen() {
		for(int i = 0; i < handlerEnemigo.object.size(); i++) {
			GameObject tempObject = handlerEnemigo.object.get(i);
			if(ayudaExtra.esUnEnemigoQuePersigue(tempObject)) {
				handlerEnemigo.removeObject(tempObject);
			}
		}
	}
	
	
	public void crearEnemigoQueVuelve() {
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
		handlerEnemigo.addObject(new EnemigoQueVuelve(xSeleted, ySelected, handlerEnemigo, handler, maximo, tipo));
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
		
		

		float xJugador = juego.jugador.getX();
		float yJugador = juego.jugador.getY();
		
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
	
	public void vaciarHandlerEnemigo() {
		while(handlerEnemigo.object.size() > 0) {
			GameObject tempObject = handlerEnemigo.object.get(0);
			handlerEnemigo.removeObject(tempObject);
		}
		enemigoInteligente = 0;
		cantidadRayos = 0;
		enemigosQueVuelven = 0;
		enemigosComunes = 0;
		enemigoRapido = 0;
		enemigoInteligente = 0;
		cantidadRayos = 0;
		salioJefe = false;
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
